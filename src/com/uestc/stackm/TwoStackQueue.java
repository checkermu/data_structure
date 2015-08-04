package com.uestc.stackm;

import java.util.ArrayList;
import java.util.List;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月2日上午11:39:39
 */
public class TwoStackQueue {
	private MyLinkedStack<Integer> stack1;
	private MyLinkedStack<Integer> stack2;
	
	public TwoStackQueue(){
		stack1 = new MyLinkedStack<Integer>();
		stack2 = new MyLinkedStack<Integer>();
	}
	
	public boolean appendTail(int data){
		stack1.push(data);
		return true;
	}
	
	/**
	 * 两个栈实现队列，删除队头的时候，如果stack2不为空直接在里面删除就OK
	 * @return
	 */
	public int deleteHead(){
		
		if(stack2.length()<=0){
			while(!stack1.isEmpty()){
				int tmp = stack1.pop();
				stack2.push(tmp);
			}
		}
		if(stack2.isEmpty())
			return Integer.MIN_VALUE;
		int head = stack2.pop();
		return head;
	}
	
	
	public static void main(String[] args) {  
        TwoStackQueue queue=new TwoStackQueue();  
        List<Integer> re=new ArrayList<Integer>();  
        queue.appendTail(1);  
        queue.appendTail(2);  
        queue.appendTail(3);  
        re.add(queue.deleteHead());  
        queue.appendTail(4);  
        re.add(queue.deleteHead());  
        queue.appendTail(5);  
        re.add(queue.deleteHead());  
        re.add(queue.deleteHead());  
        re.add(queue.deleteHead()); 
        System.out.println(re.toString());  
    }  

}
