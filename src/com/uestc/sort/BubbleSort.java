package com.uestc.sort;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月24日下午8:17:10
 */
public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BubbleSort bs = new BubbleSort();
		int[] arr = {9, 1, 5, 8, 3, 7, 6, 4, 2};
//		int[] arr = {2, 1, 3, 4, 5, 6, 7, 8, 9};
		
//		bs.bubbleSort0(arr);
//		bs.bubbleSortNormal(arr);
		bs.bubbleSortImporve(arr);
		
	}
	
	/**
	 * 冒泡排序改进，避免排好序之后剩余的比较
	 * @param arr
	 */
	public void bubbleSortImporve(int[] arr){
		System.out.println("排序前数组为：");
		print(arr);
		int n=arr.length;
		boolean flag=true;
		for(int i=0; (i<n-1)&&flag; i++){
			flag=false;
			for(int j=n-2; j>=i; j--){
				if(arr[j]>arr[j+1]){
					swap(arr, j, j+1);
					flag=true;
				}
			}
		}
		System.out.println("排序后数组为：");
		print(arr);
		
	}
	
	/**
	 * 正宗的冒泡，两两相邻的进行比较
	 * @param arr
	 */
	public void bubbleSortNormal(int[] arr){
		System.out.println("排序前数组为：");
		print(arr);
		int n=arr.length;
		for(int i=0; i<n-1; i++){
			for(int j=n-2; j>=i; j--){
				if(arr[j]>arr[j+1]){
					swap(arr, j, j+1);
				}
			}
		}
		System.out.println("排序后数组为：");
		print(arr);
	}
	
	
	/**
	 * 最简单的冒泡排序，第一轮最小的放到第一个位置，第二轮第二小的放到第二位置
	 * 每一个都和后面的所有进行比较
	 * @param arr
	 */
	public void bubbleSort0(int[] arr){
		System.out.println("排序前数组为：");
		print(arr);
		int n = arr.length;
		for(int i=0; i<n-1; i++){
			for(int j=i+1; j<n; j++){
				if(arr[i]>arr[j]){
					swap(arr, i, j);//交换i和j
				}
			}
		}
		
		System.out.println("排序后数组为：");
		print(arr);
	}

	/**
	 * 交换数组的两个元素
	 * @param arr
	 * @param i
	 * @param j
	 */
	public void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	/**
	 * 输出数组arr的元素
	 * @param arr
	 */
	public void print(int[] arr){
		int n=arr.length;
		for(int i=0; i<n; i++){
			System.out.print(arr[i]+ " ");
		}
		System.out.println();
	}
	
}
