package com.dsj.proj.controller;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

import javafx.stage.FileChooser;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class HuffmanController {
	
	@FXML
	private TextArea input;
	
	@FXML
	public TextArea output = null;
	@FXML
	private Button encode;
	@FXML
	private Button chooseFile;
	@FXML
	private Label labelFile;
	@FXML
	private Button decode;
	@FXML
	private Button dec;
	StringBuffer get = new StringBuffer();
	
	ArrayList<HNode> out = new ArrayList<HNode>();
	
	File file;
	File fileg;
	File fileser;
	
	public HuffmanController(){
		
	}
	@FXML
    private void initialize() {
		output.setText("1.) Select a file using file explorer.\n"
					 + "2.) To Compress select Encode button.\n"
					 + "3.) To Decompress select the .dat and .ser file of the same file.\n"
					 + "4.) Press Decode button to decompress the files");
    
        
    }
	@FXML
	public void handleEncode(){		
		try {
			output.clear();
			long a = System.nanoTime();
			HuffmanCode hCode= new HuffmanCode();
			String s = get.toString();
			
			String f = file.getParent();
			String g = file.getName();
			String e = null;
			int u =0;
	        u =g.indexOf(".");
	        e = g.substring(0,u);
	        StringBuffer h =  null;
			
			
			if(s != null){			
				out = hCode.getHuffmanCode(s,f+"/"+e);
				BST bst = new BST();
				System.out.println("ok1");

				System.out.println(out.size());
				System.out.println(s.length());

				for(int i = 0;i<out.size();i++){
					bst.insert((int)out.get(i).value, out.get(i).hCode);
				}
				 h = new StringBuffer();
				
				for(int k = 0;k<s.length();k++){
					String h1 = bst.search(s.charAt(k));
					if(h1 != null){// it is present
						//output.appendText(h);
						h.append(h1);
					}
				}
				
			}
			long q = System.nanoTime();
			System.out.println(q-a);
			output.setText("Successfully compressed the file to .dat and .ser file!\n");
			
			 //System.out.println(h.length());
			int cal = 8-(h.length() % 8);
			
	        //System.out.println(cal);
	        String make = null;
	        make = findmake(cal);
	        
	        String fin = make+h;
	        //System.out.println("ok");
	        //System.out.println(fin);
						
			
			FileOutputStream out = new FileOutputStream(f+"/"+e+".dat");
			
			for (int i = 0; i < fin.length(); i += 8) {
		        int b = 0;
		        for (int j = Math.min(i + 7, fin.length()-1); j >= i; j--) {
		            b = (b << 1) | ((fin.charAt(j)=='1') ? 1 : 0);
		        }
		      
		        out.write(b);
		        
		        
		        
			
			}
			
			
			output.appendText("The original file size was "+file.length()+" bytes\n");
			output.appendText("The compressed file size is "+new File(f+"/"+e+".dat").length()+" bytes.");
			System.out.println("The file size was "+new File(f+"/"+e+".dat").length()+" bytes.");
			
			
			out.close();

			
			
		} catch (Exception d) {
			// TODO Auto-generated catch block
			output.setText("Error... Please open the file to be compressed!");
			d.printStackTrace();
			
		}
	}
	public  String findmake(int c) {
		String m = null;
		switch(c){
		case 1:
			m =  "10000000";
			break;
		case 2:
			m =  "01000000";
			break;
		case 3:
			m =  "11000000";
			break;
		case 4:
			m =  "00100000";
			break;
		case 5:
			m =  "10100000";
			break;
		case 6:
			m =  "01100000";
			break;
		case 7:
			m =  "11100000";
			break;
		}
		
		return m;
		// TODO Auto-generated method stub
		
		
	}
	@FXML
	public void handleFile(){
		output.clear();
		input.clear();
		FileChooser fileChooser = new FileChooser();		 
        //Set extension filter 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("All Files (*.*)", "*.txt","*.*");
        fileChooser.getExtensionFilters().add(extFilter);        
        //Show open file dialog
        file = fileChooser.showOpenDialog(null);        
        
        labelFile.setText(file.getPath());
        
        
        
        try {
			BufferedReader in = new BufferedReader(new FileReader(file.getPath()));
			
			while(in.ready())
			{
				get.append(in.readLine()+"\n");
			    //input.appendText(in.readLine()+"\n");
			}
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        output.setText("File at "+file.getPath()+" \nSuccesfully selected for compression");
        
        
	
	}
	@FXML
	public void handleDecode(){
		FileChooser fileChooser = new FileChooser();
		 
        //Set extension filter 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("DAT Files (*.dat*)", "*.dat");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show open file dialog
        fileg = fileChooser.showOpenDialog(null);
        
        System.out.println(fileg.getPath());
        
        handleSer();
        
        
        
        
	}
	public void handleSer(){
		FileChooser fileChooser = new FileChooser();
		 
        //Set extension filter 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("SER Files (*.ser*)", "*.ser");
        fileChooser.getExtensionFilters().add(extFilter);
        
        //Show open file dialog
        fileser = fileChooser.showOpenDialog(null);
        
        System.out.println(fileser.getPath());
        System.out.println(fileser.getParent());

        
	}
	@FXML
	public void Decode(){
		HuffmanDecode hd = null;
		HNode n = null;
	      try
	      {
	         FileInputStream fileIn = new FileInputStream(fileser);
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         n = (HNode) in.readObject();
	         in.close();
	         fileIn.close();
	         hd = new HuffmanDecode(n);
	 		
	      }catch(IOException i)
	      {
	         i.printStackTrace();
	         return;
	      }catch(ClassNotFoundException c)
	      {
	        
	         c.printStackTrace();
	         return;
	      }
	      try{
	      @SuppressWarnings("resource")
		FileInputStream in = new FileInputStream(fileg);
			System.out.println(in.available());
			System.out.println("ok");
			int b = in.read();
			
			System.out.println(b);
			
			int arr = (in.available()*8-b) ;
			System.out.println(arr);
			boolean[] ar = new boolean[arr];
		    for (int i = 0; i < arr; i += 8) {
		    	
		        int c = in.read();
		        
		        if (c < 0) throw new EOFException();
		        for (int j = i; j < i + 8 && j < arr; j++) {
		        	 ar[j] = (c & 1) != 0;
		            
		            c >>>= 1;
		        }
		    }
		    
		    StringBuffer gen = new StringBuffer(ar.length);
		    for(int i=0;i<ar.length;i++){
		    	  if(ar[i]){
		    		  gen.append("1");
		    	  }else{
		    		  gen.append('0');
		    	  }
		      }
		   // System.out.println(gen.toString());
		    int u =0;
	        u =fileg.getName().indexOf(".");
	       String e = fileg.getName().substring(0,u);
			hd.generateString(gen.toString(),fileg.getParent()+"/"+e+".txt");
			in.close();
			output.clear();
			output.setText("File decompresed succesfully!");
		    
	      }catch(IOException i){
	    	  
	      }
	      
		
		
	}
	
	
	public void setMainApp(MainApp mainApp) {

        
    }

}
