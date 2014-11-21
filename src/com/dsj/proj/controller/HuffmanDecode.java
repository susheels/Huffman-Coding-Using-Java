package com.dsj.proj.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class HuffmanDecode {
	private HNode root;
	BufferedWriter bw = null;
	FileWriter fw = null;
	StringBuffer create = new StringBuffer();
	int i;
	String hc;
	HuffmanController hc1 = new HuffmanController();
	public HuffmanDecode(HNode root_tree){
		this.root = root_tree;
	}
	public void generateString(String HCode,String file){
		hc = HCode;
		
		try {
			fw = new FileWriter(file);
			bw = new BufferedWriter(fw);
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long u = System.nanoTime();
		i = 0;
		while(i<hc.length()){
			genS(root,new StringBuffer());
		}
		long w = System.nanoTime();
		try {
			bw.write(create.toString());
			bw.close();
			System.out.println(w-u);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void genS(HNode node, StringBuffer buf){
		

		buf.append(hc.charAt(i));
		i++;
		
		if(buf.charAt(buf.length()-1) == '0'){
			if(node.left.left == null){
				//System.out.print(node.left.value);
				create.append(node.left.value);
				
			}else{
				genS(node.left,buf);
			}
			
		}
		else{
			if(node.right.left== null && node.right.right ==null){
				//System.out.print(node.right.value);
				create.append(node.right.value);
				
			}else{
				genS(node.right,buf);
			}
			
		}
		
	}

}
