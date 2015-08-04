package com.uestc.stackm;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月2日上午11:00:41
 * 栈接口, 想想那些基本的操作，入，弹出，清空栈，长度，	判断是否为空
 */
public interface MyStack<T> {

	public boolean isEmpty();
	
	public boolean push(T data);
	
	public void clear();
	
	public T pop();
	
	//获得栈顶，但不弹出
	public T getTop();
	
	public int length();
}
