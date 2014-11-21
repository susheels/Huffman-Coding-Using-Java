package com.dsj.proj.controller;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class PriorityQ<T extends Comparable<T>> {// Using min Heap
	private ArrayList<T> items;
	int size = 0;
	
	public PriorityQ(){
		items = new ArrayList<T>();
				
	}
	
	public void shiftUp(){ 
		
		
			int k = items.size()-1;
			
			while(k > 0){
				T item = items.get(k);
				int p = (k-1)/2;
				T parent  = items.get(p);
				if(item.compareTo(parent) < 0){
					//swap 
					items.set(p, item);
					items.set(k, parent);
					// up 
					k = p;
				}else{
					break;
				}			
				
			
			
		}
		
		
	}
	public void shiftDown(){
		int k = 0;
        int l = 2*k+1;
        while (l < items.size()) {
            int min=l, r=l+1;
            if (r < items.size()) { // there is a right child
                if (items.get(l).compareTo(items.get(r)) <= 0) {
                    min = l;
                }else{
                	min = r;
                }
            }
            if (items.get(k).compareTo(items.get(min)) >  0) {
                    // switch
                    T temp = items.get(k);
                    items.set(k, items.get(min));
                    items.set(min, temp);
                    k = min;
                    l = 2*k+1;
            } else {
                break;
            }
        }
		
		
		
	}
	public T de() throws NoSuchElementException {
	        if (items.size() == 0) {
	            throw new NoSuchElementException();
	        }
	        if (items.size() == 1) {
	        	size--;
	            return items.remove(0);
	            
	        }
	        T hold = items.get(0);
	        items.set(0, items.remove(items.size()-1));
	        size--;
	        shiftDown();
	        return hold;
		}
		
	
	
	
	
	public void en(T item){
		items.add(item);
		
		//shiftUp
		size++;
		//System.out.println(size);

		shiftUp();
		
	}

	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

}
