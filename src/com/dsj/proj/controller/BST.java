package com.dsj.proj.controller;

public class BST {
	private BSTNode root;
	public BST(){
		root = null;
	}
    public boolean isEmpty()
    {
        return root == null;
    }
    public void insert(int data,String h)

    {

        root = insert(root, data,h);

    }

    /* Function to insert data recursively */
    private BSTNode insert(BSTNode node, int data,String hu)
    {
        if (node == null)
            node = new BSTNode(data,hu);
        else
        {
            if (data <= node.getData())
                node.left = insert(node.left, data,hu);
            else
                node.right = insert(node.right, data,hu);
        }
        return node;
    }
    public String search(int val)
    {
        return search(root, val);
    }
    /* Function to search for an element recursively */
    private String search(BSTNode r, int val)
    {
        String huff = null;
        boolean found = false;
        while ((r != null) && !found)
        {
            int rval = r.getData();
            if (val < rval)
                r = r.getLeft();
            else if (val > rval)
                r = r.getRight();
            else
            {
                found = true;
                huff = r.huf;
                break;
            }
            huff = search(r, val);
        }
        return huff;

    }
}
