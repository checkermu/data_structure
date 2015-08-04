package com.gt.list;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月31日下午9:49:19
 * 
 * 构建一个单链表类，节点，长度，初始化，添加，查找，删除，输出
 */
public class ListNodeOne {

	private Node head;
	private int length;
	
	/**
	 * 构造函数
	 */
	public ListNodeOne(){
		head = new Node(null, null);
		length = 0;
	}
	
	public Node getHead(){
		return head;
	}
	
	/**
	 * 添加到链表头部，感觉这个是不带头节点
	 * @param item
	 */
	public void addHead1(Object item){
		Node p = head;
		Node iNew = new Node(item);
		if(length==0){
			head = iNew;
			length++;
			return;
		}
		//下面这两行是不带头节点
		iNew.setNext(p);
		head=iNew;
	}
	
	/**
	 * 添加到链表头部，带头节点
	 * @param item
	 */
	public void addHead2(Object item){
		Node iNew = new Node(item);
		iNew.setNext(head.getNext());//回忆C语言，p->next = (*L)->next;
		head.setNext(iNew);				//C语言中,(*L)->next = p;
		length++;
	}
	
	/**
	 * 不带头节点
	 * @param item
	 */
	public void addTail1(Object item){
		Node p = head;
		Node iNew = new Node(item);
		if(length==0){
			head = iNew;
			length++;
			return;
		}
		while(p.getNext()!=null){
			p=p.getNext();
		}
		p.setNext(iNew);
		length++;
	}
	
	/**
	 * 带头节点
	 * @param item
	 */
	public void addTail2(Object item){
		Node iNew = new Node(item);
		//Node p = head.getNext();//这里不能用这一行；那下面换成while(p!=null)
		Node p = head;//为啥？如果是只有一个头结点，p.getNext()就是null，
		while(p.getNext()!=null){
			p=p.getNext();
		}
		p.setNext(iNew);   
		length++;
	}
	
	/**
	 * 模仿C语言尾插法新建链表
	 */
	public Node createListTail(int n){
		Node r = head; //尾插法需要一个尾部别名，一开始链表空的时候是和头结点一起的
		for(int i=0; i<n; i++){
			Node p = new Node(i);
			r.setNext(p);
			r=p;
		}
		Node q = head;
		while(q.getNext()!=null){
			System.out.print(q.getNext().getData()+"  ");
			q=q.getNext();
		}
		return head;
	}
	
	/**
	 * 递归逆序输出链表
	 * @param p
	 */
	public void funRev(Node p){
		if(null==p)
			return;
		if(p.getNext()!=null)
			funRev(p.getNext());
		System.out.print(p.getData()+" ");
	}
	
	
	/**
	 * 将一个链表反转
	 * @param head
	 */
	public void reverseList(Node head){
		if(head.getNext()==null)
			return;
		Node prv = null, next = null;
		Node p = head.getNext();
		while(p!=null){
			next = p.getNext();
			p.setNext(prv);
			prv = p;
			p=next;
		}
		printNode(prv);
	}
	
	
	
	
	/**
	 * 指定位置插入元素
	 * @param index
	 * @param item
	 */
	public void setIndex(int index, Object item){
		if(index<0||index>length){
			System.out.println("插入位置: "+index+"不合理");
			return;
		}
		
		int i=0;
		Node p = head.getNext();
		while(i<index){
			p = p.getNext();
			i++;
		}
		Node iNew = new Node(item);
		iNew.setNext(p.getNext());
		p.setNext(iNew);
		length++;
	}
	
	/**
	 * 删除指定位置元素
	 * @param index
	 */
	public void deleteIndex(int index){
		if(index<0||index>length-1){
			System.out.println("删除位置不合理");
			return;
		}
		Node p = head.getNext();
		for(int i=0; i<index; i++)
			p=p.getNext();
		p.setNext(p.getNext().getNext());
		length--;
	}
	
	public void print(){
		Node p = head;
		while(p.getNext()!=null){
			System.out.print(p.getNext().getData()+"  ");
			p=p.getNext();
		}
	}
	
	public void printNode(Node p){
		while(p!=null){
			System.out.print(p.getData()+"  ");
			p=p.getNext();
		}
	}
	
	public int getLength(){
		return length;
	}
	
	/**
	 * 在O(1)的时间内删除指定的链表节点
	 * @param p
	 * @return
	 */
	public void deleO1(Node list, Node p){
		Node tmp = null;
		if(list==null||p==null)
			return;
		if(p.getNext()!=null){//要删除的不是尾部节点
			tmp = p.getNext();
			p.setData(tmp.getData());//设置要删除的数据为后面的
			p.setNext(tmp.getNext());
//			return list;
		}else if(p==list){//要删除的链表就一个节点
			p=null;
			list=null;
//			return list;
		}else{//是链表尾部节点，且链表不止一个节点的时候
			tmp = list;
			while(tmp.getNext()!=p){
				tmp = tmp.getNext();
			}
			tmp.setNext(null);
		}
		printNode(list);//输出
	}
	
	
}
