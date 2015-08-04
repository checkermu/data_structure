package com.uestc.stackm;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月2日上午11:22:21
 * 栈 用链表实现
 */
public class MyLinkedStack<T> implements MyStack<T> {
	
	private Node top; //栈顶指针
	private int size;	//栈的长度
	
	public MyLinkedStack(){
		top=null;
		size=0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}

	@Override
	public boolean push(T data) {
		// TODO Auto-generated method stub
		Node node = new Node();
		node.value = data;
		node.pre = top;
		//改变栈顶指针
		top = node;
		size++;
		return true;
	}

	@Override
	public void clear() {
		// TODO Auto-generated method stub
		top=null;
		size=0;
	}

	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(top!=null){
			Node node = top;
			//改变栈顶指针
			top = top.pre;
			size--;
			return (T) node.value;
		}
		return null;
	}

	@Override
	public T getTop() {
		// TODO Auto-generated method stub
		if(top!=null){
			return (T) top.value;
		}
		return null;
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return size;
	}
	/** 
     * 将数据封装成结点 
     */  
   /* private final class Node {  
        private Node pre;  
        private T data;  
    }  */
}
