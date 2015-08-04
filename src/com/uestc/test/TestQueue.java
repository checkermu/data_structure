package com.uestc.test;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月18日下午2:15:01
 * 对Java queue的一些学习测试
 */
public class TestQueue {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestQueue tq = new TestQueue();
		
//		tq.linkListQueue();
		tq.priortyQueue2();
	}

	/**
	 * linkedList 实现的Queue
	 */
	public void linkListQueue(){
		Queue  test = new LinkedList();
		for(int i=10; i>=0; i--){
			test.add(i);
		}
		System.out.println(test);
		while(!test.isEmpty()){
			System.out.println(test.remove());
		}
	}
	
	/**
	 * PriortyQueue
	 * 优先队列-会对入队的元素进行排序，队列顶端总是最小的元素。
	 */
	public void priortyQueue(){
		Queue que = new PriorityQueue<>();
		for(int i=10; i>=0; i--){
			que.add(i);
		}
		System.out.println(que);
		while(!que.isEmpty()){
			System.out.println(que.remove());
		}
	}
	
	/**
	 * 查看优先队列随着输出对元素排序的变换情况,保证队首元素永远是最小的元素.--但是内部元素却随着在变换，单纯看不出变化规律
	 * apple boy fox easy dog 
		pq.poll(): apple
		boy dog fox easy 
		pq.poll(): boy
		dog easy fox 
		pq.poll(): dog
		easy fox 
		pq.poll(): easy
		fox 
		pq.poll(): fox
	 */
	public void priortyQueue2(){
		Queue<String> pq = new PriorityQueue<String>();
		pq.add("dog");
        pq.add("apple");
        pq.add("fox");
        pq.add("easy");
        pq.add("boy");
        
        while (!pq.isEmpty()) {
            for (String s : pq) {
                System.out.print(s + " ");
            }
            System.out.println();
            System.out.println("pq.poll(): " + pq.poll());
        }
	}
	

}
