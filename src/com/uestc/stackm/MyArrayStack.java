package com.uestc.stackm;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月2日上午11:00:32
 * 用数组实现栈
 */
public class MyArrayStack<T> implements MyStack<T> {
	
	private Object[] obj = new Object[16];
	private int size=0;

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size==0;
	}


	@Override
	public void clear() {
		// 将数组中的数据置为null方便 GC回收
		for(int i=0; i<size; i++){
			obj[size]=null;
		}
		size=0;
		
	}

	@Override
	public int length() {
		// TODO Auto-generated method stub
		return size;
	}


	@Override
	public boolean push(T data) {
		// TODO Auto-generated method stub
		if(size>=obj.length){
			resize();
		}
		obj[size++]=data;
		return true;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T pop() {
		// TODO Auto-generated method stub
		if(size==0){
			return null;
		}
		T t = (T)obj[--size];
		obj[size]=null;
		return t;
	}


	@SuppressWarnings("unchecked")
	@Override
	public T getTop() {
		// TODO Auto-generated method stub
		if(size==0){
			return null;
		}
		return (T)obj[size-1];
	}

	/**
	 * 对数组进行扩容
	 */
	public void resize(){
		Object[] tmp = new Object[obj.length*2];
		for(int i=0; i<size; i++){
			tmp[i]=obj[i];
			obj[i]=null;
		}
		obj=tmp;
	}


}
