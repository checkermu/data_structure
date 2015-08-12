package com.uestc.sort;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月12日下午7:47:35
 * 快速排序另一个版本，以终点end为标志位，这个是算法导论的。
 */
public class QuickSortTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		QuickSortTwo qs = new QuickSortTwo();
		int[] arr={3, 7, 2, 1, 8, 6, 5};
		qs.quickSort(arr, 0, arr.length-1);
		
		System.out.println(Arrays.toString(arr));
		
	}

	/**
	 * 快速排序主函数
	 * 递归求解子问题，子问题仍然是原始问题
	 * 靠使用主函数进行求解
	 * @param arr
	 * @param s
	 * @param e
	 */
	public void quickSort(int[] arr, int s, int e){
		if(s<e){
			int index = partition(arr, s, e);
			quickSort(arr, s, index-1);
			quickSort(arr, index+1, e);
		}
	}
	
	/**
	 * 快速排序分隔函数
	 * 将原问题分解为规模小的，本质相同的子问题。
	 * 此版本以终点为标志位进行分隔
	 * @param arr
	 * @param s
	 * @param e
	 */
	public int partition(int[] arr, int s, int e){
		if(arr==null||s<0||s>e)
			return -1;
		int x = arr[e];//标志位
		int i=s-1; //下标i维护的是小于x的区块
		for(int j=s; j<e; j++){
			if(arr[j]<x){
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i+1, e);
		return i+1;
	}
	
	
	public void swap(int[] arr, int i, int j){
		int tmp =arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
}
