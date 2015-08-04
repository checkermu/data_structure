package com.gt.list;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月3日下午5:17:16
 */
public class CircleList {
	private Node head;//还是标识头结点
	private Node rear;//标识尾指阵，指向尾结点
	
	
	public CircleList(){
		head = new Node(null, null);
		rear = head;
	}
	
	/**
	 * 初始化一个循环链表
	 * @param n
	 */
	public Node init(int b, int e){
		for(int i=b; i<e; i++){
			Node p = new Node(i);
			rear.setNext(p);
			rear=p;
		}
		rear.setNext(head);
		return rear;
	}
	
	/**
	 * 合并两个循环链表
	 * @param rA
	 * @param rB
	 * @return
	 */
	public Node merge(Node rA, Node rB){
		if(rA==null)
			return rB;
		if(rB==null)
			return rA;
		
		Node p = rA.getNext(); // 循环链表rA的头结点
		rA.setNext(rB.getNext().getNext());
		
//		Node q = rB.getNext();
		rB.setNext(p);
		
		return rB;
	}
	
}
