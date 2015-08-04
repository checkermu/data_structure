package com.uestc.tree;

import java.util.ArrayList;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月23日下午7:49:35
 */
public class HeapArrayList<T extends Comparable<T>> {
	
	private ArrayList<T> heap;
	public HeapArrayList() {
		// TODO Auto-generated constructor stub
		heap = new ArrayList<T>();
	}
	
	public static void main(String[] args) {
		HeapArrayList<Integer> heapA = new HeapArrayList<Integer>();
		/*int[] arr = {6, 9, 3, 5, 11, 18, 4};
		for(int i=0; i<arr.length; i++){
			heapA.addItem(arr[i]);
		}
		heapA.print();
		heapA.addItem(30);
		heapA.print();
		heapA.deleteTop();
		heapA.print();*/
		int[] aa = {6, 9, 3, 5, 11, 18, 4};
		heapA.bottomToTop(aa);
		
		for(int tmp:aa){
			System.out.print(tmp+" ");
		}
		
	}
	/*
	18 9 11 5 6 3 4 

	30 18 11 9 6 3 4 5 

	18 9 11 5 6 3 4 
	*/
	public void print(){
		for(T tmp:heap){
			System.out.print(tmp+" ");
		}
		System.out.println("\n");
	}
	
	public void addItem(T el){
		heap.add(el);
		shiftUp2(heap.size()-1);
	}
	
	public T deleteTop(){
		if(heap.isEmpty()){
			System.out.println("the heap is empty");
		}
		T maxItem = heap.get(0);
		T lastItem = heap.remove(heap.size()-1);
		if(heap.isEmpty()){
			return lastItem;
		}
		//将尾部的节点放置顶端,下移，重构
		heap.set(0, lastItem);
		shiftDown2(0);
		
		return maxItem;
	}
	
	/**
	 * 堆上移动
	 * 父节点是 (i-1)/2
	 * @param index 上移操作的起始位置（一般是尾端）
	 */
	public void shiftUp(int index){
		T item = heap.get(index);
		while(index>0){
			int pindex = (index-1)/2;
			T parent = heap.get(pindex);
			if(item.compareTo(parent)>0){
				heap.set(index, parent);//把父节点的值设置到插入的位置。
				index = pindex;
			}else
				break;
		}
		heap.set(index, item);//
	}
	
	/**
	 * 下移操作，
	 * 左子节点i*2+1;
	 * @param index
	 */
	public void shiftDown(int index){
		T item = heap.get(index);
		int lindex = index*2+1;
		while(lindex<heap.size()){
			T maxChild = heap.get(lindex);
			int max_index = lindex;
			
			int rindex = lindex+1;
			if(rindex<heap.size()){
				T rightChild = heap.get(rindex);
				if(maxChild.compareTo(rightChild)<0){
					maxChild = rightChild;
					max_index = rindex;
				}
			}
			if(item.compareTo(maxChild)<0){ //item和左右子节点中最大的比要小
				heap.set(index, maxChild);
				index = max_index;
				lindex=2*index+1;
			}else
				break;
		}
		heap.set(index, item);
	}
	
	public void shiftUp2(int index){
		T item = heap.get(index);
		
		int pIndex = (index-1)/2;
		while(index>0){
			T pItem = heap.get(pIndex);
			if(item.compareTo(pItem)>0){
				heap.set(index, pItem);
				index = pIndex;
				pIndex = (index-1)/2;
			}else break;
		}
		heap.set(index, item);
	}
	
	/**
	 * 目的，向上移动，跟父节点比较，如果大了，那么把父节点的值设置到index中，小了，就把插入的值设置的index
	 * @param index
	 */
	public void shiftUp3(int index){
		T value = heap.get(index);
		while(index>0){
			int pI = (index-1)/2;
			T pV = heap.get(pI);
			if(value.compareTo(pV)>0){
				heap.set(index, pV);
				index = pI;
			}else
				break;
		}
		heap.set(index, value);
	}
	
	/*
	 * 下移，当前节点，和子节点中最大的比较，小了就换，不小就结束
	 */
	public void shiftDown2(int index){
		T value = heap.get(index); //当前节点
		int maxI = index*2+1;
		while(maxI<heap.size()){
				T vL = heap.get(maxI);
				T maxV = vL;
				
				int iR = maxI+1;
				if(iR<heap.size()){
					T vR = heap.get(iR);
					if(vR.compareTo(vL)>0){
						maxV = vR;
						maxI = iR;
					}
				}
				if(value.compareTo(maxV)<0){
					heap.set(index, maxV);
					index = maxI;
					maxI = index*2+1;
				}else
					break;
		}
		
		heap.set(index, value);
	}
	
	/**
	 * 向下移动，index和子节点最大的比较，小了，互换，在比较，不小，结束
	 * @param index
	 */
	public void shiftDown3(int index){
		T value = heap.get(index);
		int maxI = index*2+1;
		while(maxI<heap.size()){
			T maxValue = heap.get(maxI);
			int rightI = maxI+1;
			if(rightI<heap.size()){
				T rightValue = heap.get(rightI);
				if(maxValue.compareTo(rightValue)<0){
					maxValue=rightValue;
					maxI = rightI;
				}
			}
			if(value.compareTo(maxValue)<0){
				heap.set(index, maxValue);
				index = maxI;
				maxI = index*2+1;
			}else break;
		}
		heap.set(index, value);
	}
	
	/**
	 * 自底向上建堆
	 * @param arr
	 */
	public void bottomToTop(int[] arr){
		for(int i=arr.length/2-1; i>=0; i--){
			moveDown(arr, i, arr.length);
		}
	}
	
	/**
	 * 下移函数，
	 * @param arr
	 * @param location
	 * @param arrSize
	 */
	public void moveDown(int[] arr, int location, int arrSize){
		int temp = 0 ,leftchild =0 ;
		for (temp = arr[location];(2*location +1) < arrSize ;location = leftchild) {
			leftchild = 2*location +1;
			if (leftchild != arrSize -1 && arr[leftchild] < arr[leftchild+1]) {
				leftchild++; 
			}
			if (temp < arr[leftchild]) {
				arr[location] =arr[leftchild];
			}
			else
				break;
		}
		arr[location] = temp;
	}
	
	//往下移动就是，单纯的互换两个元素位置
	public void shiftDown(int[] arr, int location, int size){
		int tmp=0, leftchild=0;
		for(tmp=arr[location]; (location*2+1)<size; location=leftchild){
			leftchild = 2*location+1;
			if(leftchild != size-1 && arr[leftchild]<arr[leftchild+1]){
				leftchild++;	//the max chilad
			}
			if(tmp<arr[leftchild]){
				arr[location]=arr[leftchild];
			}else break;
		}
		arr[location]=tmp;
	}
}
