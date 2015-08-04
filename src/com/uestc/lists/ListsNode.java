package com.uestc.lists;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年6月23日下午10:15:40
 */
public class ListsNode {
	public Nodes L;
	
	public static void main(String[] args) {
		ListsNode ln = new ListsNode();
		ln.addHead(10);
		ln.addHead(20);
		ln.addHead(80);
		ln.addHead(90);
		
		System.out.println(ln.getLastK(ln.L, 4));
	}
	
	public void init(){
		
		
	}
	
	public int getLastK(Nodes L, int k){
		Nodes ahead = L;
		Nodes behand = L;
		for(int i=0; i<k-1; i++)
			ahead = ahead.next;
		
		while(ahead.next!=null){
			ahead = ahead.next;
			behand = behand.next;
		}
		
		
		return behand.el;
	}
	
	public void addHead(int el){
		Nodes newNode = new Nodes(el, L);
		L = newNode;
	}

}
