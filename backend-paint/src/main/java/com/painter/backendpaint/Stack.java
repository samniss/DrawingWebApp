package com.painter.backendpaint;

import java.util.EmptyStackException;


/***
 * The class is an implementation of the stack datastructure
 * 
 * 
 *
 */
@SuppressWarnings({"unused"})
public class Stack implements IStack {
	/**
	 * 
	 * The node class of the stack data structure
	 *
	 */
	class Node{    
        Object data;    
        Node next;    
            
        public Node(Object data , Node next) {    
            this.data = data;    
            this.next = next; 
        }
        public Object getElement() {
        	return this.data;
        }
        public Node getNext() {
        	return this.next;
        }    
       
    }  
	
	private boolean empty = false;
	/**
	 * Top is the top element of the stack
	 */
	
	private Node top = null;
	/**
	 * Size is the size of the stack
	 */
	private int size=0;
	/***
 	*  Get the number of elements in the stack and has no parameters
 	*
 	* @return The number of elements in the stack
 	*/
	public int size(){
		return size;
	}

/**
 * Check whether the stack is empty 
 * 
 * @return boolean to determine whether the stack is empty
 */
public boolean isEmpty() {
	return (size==0);
}


/**
 * Examine the top element of the stack or throw EmptyStackException if the stack is empty
 *
 * @return Top element of the stack
 */
public Object peek() {
	if(isEmpty()) {
		throw new EmptyStackException();
	}
	return top.getElement();
}


/**
 * Get the top element and remove it from the stack or throw EmptyStackException if the stack is empty 
 * 
 * @return Top element of the stack
 */
public Object pop() {
	if(isEmpty()) {
		throw new EmptyStackException();
	}
	Object temp = top.getElement();
	top = top.getNext();
	size--;//reduce the size of the stack;
	return temp;
}



/**
* Pushes an item onto the top of this stack.
*
* @param element
* to insert
*/

public void push(Object element) {
	if(element == null) {
		throw new NullPointerException("Null Object is passed ");
	}
	Node newNode = new Node(element,top);
	top=newNode;
	size++;
}

public void setSize(int size) {
	this.size = size;
}
public void setTop(Node top) {
	this.top = top;
}
public Node getTop() {
	return top;
}
public int getSize() {
	return size;
}
public void setEmpty(boolean empty) {
	this.empty = empty;
}
public void clear(){
 while(!this.isEmpty()){
	 this.pop();
 }
}
}
