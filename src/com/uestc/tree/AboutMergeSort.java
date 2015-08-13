package com.uestc.tree;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月12日下午10:22:16
 * 关于归并排序，注意有两个
 * 一个是自底向上和一个是自顶向下
 * 自底向上是常常说的二路归并；二路归并是不需要递归的
 * 自顶向下是大部分人常常采用的归并排序。	自定向下是要递归的
 */
public class AboutMergeSort {

	public static void main(String[] args) {
		AboutMergeSort ms = new AboutMergeSort();
		int[] arr = {50, 10, 90, 30, 70, 40, 80, 60};
		
		ms.mergeSort2(arr);
		
		System.out.println(Arrays.toString(arr));
	}
	
	/**
	 * 先看看自底向上，也就是二路归并排序算法，此方法是非递归的
	 * 借用一个全局等长的数组arrNew
	 */
	
	public void mergeSort2(int[] arr){
		if(arr==null||arr.length<=0)
			return;
		int length = arr.length;
		int[] arrNew = new int[length];
		
		int k=1;
		while(k<length){
			mergePass(arr, arrNew, k, length-1);
			k=2*k;
			mergePass(arrNew, arr, k, length-1);
			k=2*k;
		}
		
		/*int k=1, i=0; //步长
		while(i+k<=length-1){
			mergePass(arr, arrNew, i, length-1);
			i = i+k*2;
			mergePass(arrNew, arr, i, length-1);
			i = i+k*2;
		}*/
		System.out.println("复制的新数组");
		System.out.println(Arrays.toString(arrNew));
		
	}
	
	
	/**
	 * 二路归并实现,将原数组arr中相邻长度为s的子序列两两归并到arrNew中
	 * 
	 * 
	 * 
	 * @param arr
	 * @param arrNew
	 * @param s	步长
	 * @param n	数组最大的下标
	 */
	public void mergePass(int[] arr, int[] arrNew, int s, int n){
		if(arr==null||arr.length==0||s>n)
			return;
		int i=0, j; //在大话数据结构中，这里i=1，函数参数中n是数组长度，用的是指针，
		//但是在这里数组下标从0开始，所以n也要变为最大的数组下标
		while(i<= n-2*s+1){
			merge(arr, arrNew, i, i+s-1, i+2*s-1); //两两归并
			i=i+2*s;
		}
		
		if(i<n-s+1) //归并最后两个序列
			merge(arr, arrNew, i, i+s-1, n);
		else{
			for(j=i; j<=n; j++)
				arrNew[j]=arr[j];
		}
	}
	
	/**
	 * 归并排序最终的合并函数，借用了一个外部的数组
	 * @param arr 原数组
	 * @param arrNew	将原数组数据由小到大归并到arrNew中
	 * @param b		原数组归并的起始位置
	 * @param m		前半部分终止位置
	 * @param e		后半部分终止位置
	 */
	public void merge(int[] arr, int[] arrNew, int b, int m, int e){
		//异常判断
		if(arr==null||arr.length==0||b<0||b>m||m>e)
			return;
		int i, j, k=b;
		for(i=b, j=m+1; i<=m&&j<=e; k++){
			if(arr[i]<arr[j])
				arrNew[k]=arr[i++];
			else
				arrNew[k]=arr[j++];
		}
		//这个地方的判断需要再验证一下………………………………………………………………………………………………………………………………………………………………………………
		while(i<=m){
			arrNew[k++]=arr[i++];
		}
		while(j<=e){
			arrNew[k++]=arr[j++];
		}
	}
}
