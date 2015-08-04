package com.gt.list;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月31日下午9:46:44
 * 自己实现链表节点类
 */
public class Node {
	private Object data;
	private Node next;
	
	public Node(){}
	public Node(Object data){
		this.data = data;
	}
	public Node(Object data, Node next){
		this.data = data;
		this.next = next;
	}
	
	
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public Node getNext() {
		return next;
	}
	public void setNext(Node next) {
		this.next = next;
	}
	
}
