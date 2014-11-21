package com.dsj.proj.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;


public class HuffmanCode {
	public HuffmanCode(){
		
	}
	ArrayList<HNode> by = new ArrayList<HNode>();
	
	
    // input is an array of frequencies, indexed by character code
    public  HNode buildTree(int[] charFreqs) {
        PriorityQ<HNode> trees = new PriorityQ<HNode>();
        // initially, we have leaves
        // one for each non-empty character
        for (int i = 0; i < charFreqs.length; i++)
            if (charFreqs[i] > 0)
                trees.en(new HNode(charFreqs[i], (char)i));// storing frequency and value ie char.// priority based on freq..
 
        
        // loop until there is only one tree left
        while (trees.size() > 1) {
            // two trees with least frequency
            HNode a = trees.de();
            HNode b = trees.de();
            
            // put into new node and re-insert into queue
            trees.en(new HNode(a, b));
                      
        }
        HNode n = trees.de();
        
        return n;
    }
    //
    public void printHuffmanCode(HNode node, StringBuffer pre){    	
    	if(node.left != null){    		
    		pre.append('0');
    		printHuffmanCode(node.left, pre);
    	}else{
    		node.setHCode(pre);    	
    		by.add(node);
    		
    	}
    	if(node.right != null){
    		pre.append('1');
    		printHuffmanCode(node.right, pre);
    	}
    	if(pre.length() == 0){
    		
    	}else{
    	pre.deleteCharAt(pre.length()-1);
    	}    	
    	//System.out.println(pre);
    }
    
    public ArrayList<HNode> getHuffmanCode(String input,String file)  {
    	
        String test = input;
        
 
        // we will assume that all our characters will have
        // code less than 256, for simplicity
        int[] charFreqs = new int[255];
        
        // read each character and record the frequencies
        for (char c : test.toCharArray()){
            charFreqs[c]++;// each array index ie 0-255 represents the char itself. 
        }
        
        // build tree
        HNode tree = buildTree(charFreqs);// built final tree...
        
        printHuffmanCode(tree, new StringBuffer());
        
        try
        {
           FileOutputStream fileOut =
           new FileOutputStream(file+".ser");
           ObjectOutputStream out = new ObjectOutputStream(fileOut);
           out.writeObject(tree);
           out.close();
           
           fileOut.close();
           System.out.println("Serialized data is saved!!!");
        }catch(IOException i)
        {
            i.printStackTrace();
        }
     
        
        
        
        
        return by;
        // print out results
        }
}