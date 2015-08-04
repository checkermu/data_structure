package com.uestc.queuem;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月2日下午3:33:28
 */
public class CirLinkedQueue<T> {

	//链的数据结构
	private class Node{
		public T data;
		public Node next;
		
		public Node(){}
		
		public Node(T data, Node next){
			this.data = data;
			this.next = next;
		}
	}
	
	private Node front;
	private Node rear;
	private int size=0;
	
	public CirLinkedQueue(){
		Node node = new Node(null, null);
		front=rear=node;
	}
	
	public void enqueue(T data){
		//创建一个节点
		Node s = new Node(data, null);
		//队尾指针指向新加入的节点，
		rear.next=s;
		rear = s;
		size++;
	}
	
	public T dequeue(){
		if(rear==front){
			try {
				throw new Exception("队列为空");
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			return null;
		}else{
			Node p = front.next;
			T x = p.data;
			//队头移除链
			front.next = p.next;
			//判断队列长度是否为1
			if(p.next==null){
				rear=front;
			}
			//删除节点
			p=null;
			size--;
			
			return x;
		}
	}
	
	public int size(){
		return size;
	}
	public boolean isEmpty(){
		return size==0;	
	}
	
	public String toString(){
		if(isEmpty()){
			return "[]";
		}else{
			StringBuilder sb = new StringBuilder("[");
			for(Node current=front.next; current!=null; current=current.next){
				sb.append(current.data.toString()).append(", ");
			}
			int len = sb.length();
			return sb.delete(len-1, len).append(" ]").toString();
		}
	}
	
	public static void main(String[] args){
		CirLinkedQueue<Integer> que = new CirLinkedQueue<Integer>();
		que.enqueue(1);
		que.enqueue(2);que.enqueue(3);
		que.enqueue(4);que.enqueue(5);
		System.out.println(que.toString());
		
		System.out.println("出队："+que.dequeue());
		System.out.println("长度："+que.size());
		
		System.out.println("出队："+que.dequeue());
		System.out.println("长度："+que.size());
		
		System.out.println("出队："+que.dequeue());
		System.out.println("长度："+que.size());
		
	}
	
}
