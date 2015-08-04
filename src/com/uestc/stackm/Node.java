package com.uestc.stackm;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月2日上午11:26:55
 */
public class Node<T> {
	
	public T value;
	public Node<T> pre;
	
	public Node(T value, Node<T> next){
		this.value = value;
		this.pre = next;
	}
	
	public Node(){}

}
