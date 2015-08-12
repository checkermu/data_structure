package com.uestc.sort;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月12日下午8:05:12
 * 快速排序第三个版本，以第一个为标志位，但是不是采用两个指针分别从后向前法，而是两个指针从头走向尾部。
 */
public class QuickSortThree {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSortThree qs = new QuickSortThree();
		int[] arr={3, 7, 2, 1, 8, 6, 5};
		qs.quickSort(arr, 0, arr.length-1);
		System.out.println(Arrays.toString(arr));
	}
	
	public void quickSort(int[] arr, int s, int e){
		if(s<e){
			int index = partition(arr, s, e);
			quickSort(arr, s, index-1);
			quickSort(arr, index+1, e);
		}
	}
	
	/**
	 * 分隔原始问题为两个子问题，
	 * 以s为标志位，采用双指针一起往后移动,注意和另一种方法的区别,两种方法时间都一样都是 O(n)
	 * @param arr
	 * @param s
	 * @param e
	 * @return
	 */
	public int partition(int[] arr, int s, int e){
		if(arr==null||s<0||s>e)
			return -1;
		int x = arr[s]; //标志位变为起始
		int i=s+1, j=s+1; //两个指针分别从头部开始
		
		for(; j<=e; j++){
			if(arr[j]<x){
				swap(arr, i, j);
				i++;
			}
		}//不过这样到尾部，i指向第一个大于x的元素下标
		swap(arr, s, i-1);
		return i-1;
	}
	
	/**
	 * 快速排序，填坑思想。
	 * @param arr
	 * @param s
	 * @param e
	 * @return
	 */
	public int partition2(int[] arr, int s, int e){
		if(arr==null||s<0||s>e)
			return -1;
		int x = arr[s];
		while(s<e){
			while(s<e&&arr[e]>=x)
				e--;
			swap(arr, s, e);
			while(s<e&&arr[s]<x)
				s++;
			swap(arr, s, e);
		}
		arr[s]=x;
		return s;
	}
	
	
	
	public void swap(int[] arr, int i, int j){
		int tmp =arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
