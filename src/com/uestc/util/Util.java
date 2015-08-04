package com.uestc.util;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月25日下午9:20:25
 */
public class Util {
	
	/**
	 * 输出数组的每个元素
	 * @param arr
	 */
	public void print(int[] arr){
		int n=arr.length;
		for(int i=0; i<n; i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println();
	}
	
	/**
	 * 交换数组arr的第i和第j个元素
	 * @param arr
	 * @param i
	 * @param j
	 */
	public void swap(int[] arr, int i, int j){
		int tmp=arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;
	}

}
