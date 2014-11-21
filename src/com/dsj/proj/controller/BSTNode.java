package com.dsj.proj.controller;

public class BSTNode {
	public BSTNode left;
	public BSTNode right;
	public int data;
	public String huf;
	
    public BSTNode()
    {
        left = null;
        right = null;
        data = 0;
        huf = null;
    }
    /* Constructor */
    public BSTNode(int n,String h)
    {
        left = null;
        right = null;
        data = n;
        huf = h;
        
    }
    /* Function to set left node */
    public void setLeft(BSTNode n)
    {
        left = n;
    }
    /* Function to set right node */
    public void setRight(BSTNode n)
    {
        right = n;
    }
    /* Function to get left node */
    public BSTNode getLeft()
    {
        return left;
    }
    /* Function to get right node */
    public BSTNode getRight()
    {
        return right;
    }
    /* Function to set data to node */
    public void setData(int d)
    {
        data = d;
    }
    /* Function to get data from node */
    public int getData()
    {
        return data;
    }     
}
