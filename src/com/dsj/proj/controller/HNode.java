package com.dsj.proj.controller;

public class HNode implements Comparable<HNode>,java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HNode left;
	public HNode right;
	public int frequency;
	public char value;
	public int id;
	public String hCode;
	public HNode(int freq,char val){
		this.frequency = freq;
		this.value = val;
		this.left = null;
		this.right =null;
	}
	public HNode(HNode a,HNode b){
		this.left  = a;
		this.right = b;
		this.frequency = a.frequency+b.frequency;		
	}
	public int compareTo(HNode node){
		return this.frequency - node.frequency;		
		
	}
	public void setHCode(StringBuffer pre) {
		// TODO Auto-generated method stub
		hCode = pre.toString();
	}
	
		

}
