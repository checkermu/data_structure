package com.uestc.sort;

import com.uestc.util.Util;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月25日下午9:19:45
 */
public class SelectionSort {

	private Util util = new Util();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SelectionSort ss = new SelectionSort();
		int[] arr = {9, 1, 5, 8, 3, 7, 6, 4, 2};
//		int[] arr = {2, 1, 3, 4, 5, 6, 7, 8, 9};
		
		ss.simpleSelectSort(arr);

	}
	
	/**
	 * 简单选择排序
	 * @param arr
	 */
	public void simpleSelectSort(int[] arr){
		System.out.println("排序前：");
		util.print(arr);
		int n=arr.length;
		int min=0;
		for(int i=0; i<n; i++){
			min=i;
			for(int j=i+1; j<n; j++){
				if(arr[j]<arr[min]){
					min=j;
				}
			}
			if(i!=min){
				util.swap(arr, i, min);
			}
		}
		System.out.println("排序后：");
		util.print(arr);
	}

}
