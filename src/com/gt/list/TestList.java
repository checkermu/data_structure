package com.gt.list;

import java.util.Comparator;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月31日下午10:11:39
 * 反转链表，带头节点，不带头节点
 * 合并两个排序的链表
 */
public class TestList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//测试发现有头节点与无头节点的区别
		/*ListNodeOne list = new ListNodeOne();
		for(int i=9; i>0;i--)
			list.addHead2(i);
		
		list.print();
		TestList tl = new TestList();
		tl.reverseList(list);*/
		
		Node head = new Node(null, null);
		Node h1 = new Node(1);
		Node h2 = new Node(4);
		Node h3 = new Node(5);
		Node h4 = new Node(6);
		Node h5 = new Node(8);
		head.setNext(h1);h1.setNext(h2); h2.setNext(h3); h3.setNext(h4); h4.setNext(h5);
		
		
		Node k1 = new Node(2);
		Node k2 = new Node(3);
		Node k3 = new Node(7);
		Node k4 = new Node(9);
		Node k5 = new Node(11);
		k1.setNext(k2); k2.setNext(k3); k3.setNext(k4); k4.setNext(k5);
		
		
//		reverseList(head);
//		reverseNoHead(h1);
//		mergeList(h1, k1);
		
//		System.out.println(funSum(10));
		
		/*ListNodeOne list = new ListNodeOne();
		list.createListTail(10);
		
		CircleList cl = new CircleList();
		Node rA = cl.init(1,5);
		CircleList cl2 = new CircleList();
		Node rB = cl2.init(5, 10);
		
		cl.merge(rA, rB);*/

		ListNodeOne list = new ListNodeOne();
//		Node p = list.createListTail(10);
//		System.out.println("nx");
//		list.funRev(p); //递归输出
//		list.reverseList(p);
		
		list.deleO1(h1, h5);
	}
	
	/**
	 * 合并两个不带头结点的排序链表
	 * @param h1
	 * @param k1
	 */
	public static void mergeList(Node h1, Node k1){
		Node p = h1; Node q = k1;
		Node me = new Node(null, null);//现在看来这个很像一个头指针，指向着最开始的地方，下面的tmp一直在变
		Node tmp = me;
//		p=p.getNext(); q = q.getNext();
		
		while(p!=null && q!=null){
			if(comp(p, q)){
				tmp.setNext(p);
				p=p.getNext();
				tmp=tmp.getNext();
			}else{
				tmp.setNext(q);
				q=q.getNext();
				tmp = tmp.getNext();
			}
		}
		if(p!=null){
			tmp.setNext(p);
		}else if(q!=null)
			tmp.setNext(q);
		
		System.out.println(me.getNext().getData());
	}
	
	/**
	 * 前一个比后一个小返回true
	 * @param p
	 * @param q
	 * @return
	 */
	public static boolean comp(Node p, Node q){
		if((int)p.getData()<(int)q.getData())
			return true;
		else
			return false;
	}
	
	
	/**
	 * 反转一个链表，不带头节点
	 * @param h1
	 */
	public static void reverseNoHead(Node h1){
		if(h1==null)
			return;
		Node p=h1;
//		Node reH = null;
		Node prev = null;
		Node q = null;
		while(p!=null){
			q = p.getNext();
			p.setNext(prev);
			prev = p;
			p = q;
		}
		
		System.out.println(prev.getData());
	}
	
	
	
	/**
	 * 假设是个带头节点的链表，使用Node类实现，反转它
	 * @param head
	 */
	public static void reverseList(Node head){
		Node p = head.getNext();
		Node prev = null;
		Node reHead = head;//这样把原先的链表都覆盖了。没有使用新的空间
		Node q = null;
		if(p.getNext()==null)
			return;
		/**
		 * 当前P,前一个prev,后一个q;设置p.next = prev; 然后，p变为prev，q变为当前的p
		 */
		while(p!=null){
//			p=p.getNext();//如果while(p.getNext()!=null)，那么这里需要p=p.getNext()，且下一个是q还要p.getNext();这样导致的问题是脱节了，跟联系不上。
			q = p.getNext();
			p.setNext(prev);
			prev = p;
			p=q;
		}
		reHead.setNext(prev);
		System.out.println(reHead.getNext().getData());
	}
	
	/**
	 * 实现逆置一个链表,借用了链表类，操作显得比较简单，如果不借用，单纯使用Node类
	 * @param list
	 */
	public void reverseList(ListNodeOne list){
		if(list.getLength()<1)
			return;
		ListNodeOne reList = new ListNodeOne(); //反转链表头
		Node p = list.getHead();
		Node prev=null;
		while(p.getNext()!=null){
			p=p.getNext();
			reList.addHead2(p.getData());
		}
		reList.print();
	}
	
	public static int funSum(int n){
		if(n==1)
			return 1;
		return n+funSum(n-1);
	}
	
	
}
