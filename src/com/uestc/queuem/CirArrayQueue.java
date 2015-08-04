package com.uestc.queuem;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月2日下午2:35:55
 * 
 * 循环队列的数组实现：
 * 队列只允许一端插入，一端删除，是受限制的线性表： 允许插入的一段是队尾 Rear，允许删除的一端是队头Front
 * 先进先出的线性表  FIFO
 * 
 * 顺序队列：rear和front是变化的，因此设置两个指针；初始化时候均是0；
 * 
 * 入队：新元素插入rear所指的位置的后一位
 * 出队：删除front所指的元素，front+1 并返回删除元素
 * 
 * “顺序表溢出现象”
 * 1、“下溢出”
 * 		队列为空，出队允许产生溢出现象，下溢出是正常，常用作程序控制转移条件
 * 2、“真上溢出”
 * 		队列满，入队则产生恐惧溢出，真上溢出是出错现象
 * 3、“假上溢出”
 * 		由于出队和入队，rear和front都只增加不减小，导致被删除元素空间无法重新利用。
 * 
 * 循环队列：头尾相接的顺序存储结构叫做循环队列（circular queue）
 * 	注意：队空的判断条件是：front==rear
 * 		队满的判断条件是：（rear+1）%QueueSize==front。
 * 
 * http://blog.csdn.net/wuwenxiang91322/article/details/12259099
 */
								/////注意队列满的情况下存储 size个元素，而不是size-1个元素
public class CirArrayQueue<T> {
	//对象数组，最多存储a.length-1个对象
	T[] a;
	
	private static final int DEFAULT_SIZE = 16;
	int front;
	int rear;
	
	public CirArrayQueue(){
		this(DEFAULT_SIZE);
	}
	
	/**
	 * 初始化指定长度的队列，自己指定
	 * @param size
	 */
	public CirArrayQueue(int size){
		a = (T[])new Object[size];
		front =0;
		rear = 0;
	}
	
	/**
	 * 入队操作
	 * @param data
	 * @return
	 */
	public boolean enqueue(T data){
		//是否满队列
		if((rear+1)%a.length == front){
			return false;
		}else{
			a[rear]=data;
			//没满，那么插入一个元素，rear的变化
			rear = (rear+1)%a.length;
			return true;
		}
	}
	
	/**
	 * 出队操作
	 * @return
	 */
	public T dequeue(){
		if(rear==front){
			return null;
		}
		else{
			T data = a[front];
			a[front]=null;
			//队不为空，出队操作后队头的变化
			front = (front+1)%a.length;
			
			return data;
		}
	}
	
	public int size(){
		return (rear-front+a.length)%a.length;
	}
	
	public boolean isEmpty(){
		return rear==front;
	}
	
	public static void main(String[] args) {
		CirArrayQueue<String> que = new CirArrayQueue<String>(4);
		
		que.enqueue("1");
		que.enqueue("2");
		que.enqueue("3");
		que.enqueue("4");
		System.out.println(que.size());
		
		System.out.println("de:"+que.dequeue());
		System.out.println("de:"+que.dequeue());
		System.out.println(que.size());
		
		que.enqueue("5");
		System.out.println(que.size());
		
	}
	
}
