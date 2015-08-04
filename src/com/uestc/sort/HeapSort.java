package com.uestc.sort;

import com.uestc.util.Util;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月26日下午2:54:44
 * 堆排序
 */
public class HeapSort {
	public Util util = new Util();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeapSort hs = new HeapSort();
		int[] arr={0,2,3,4,6,5};
		int[] arr1 = {0,9, 1, 5, 8, 3, 7, 6, 4, 2};
		
//		hs.heapSort(arr);
		hs.heapSort12(arr);
		System.out.println(1024*1024);
		
	}
	
	//数组下标从0开始,
	public void heapSort(int[] arr){
		int n=arr.length;
		//数组下标从0开始,最后一个非叶节点下标(n/2 -1)
		//这个for循环构建好了最大堆
		for(int i=n/2-1; i>=0; i--){
			//当前i为根的树进行最大堆调整
			heapAdjust(arr, i, n);
		}
		for(int j=n-1; j>=0; j--){
			swap(arr,0,j);
			heapAdjust(arr, 0, j);//调整剩下最大堆,
		}
	}
	
	/**
	 * 最大堆调整,i是要调整的堆的root,n是这个数组的长度
	 * @param arr
	 * @param i
	 * @param n
	 */
	public void heapAdjust(int[] arr, int i, int n){
		int tmp=arr[i];//当前根的值
		int j=2*i+1;	//左孩子
		for(;j<n;j=2*i+1){
			if(j<(n-1)&&arr[j]<arr[j+1])
				j=j+1;//有右孩子，且右孩子大于左孩子，把j更新为右孩子
			if(tmp>arr[j])
				break;//根节点比孩子节点中最大值还要大，退出循环
					//因为建堆过程是从最底层的根开始，在上层的时候底层已经是最大堆,
			arr[i]=arr[j];//孩子节点最大值比根大，则交换(实际先把叶子赋值给根)
			i=j;//根节点下移，把这个最大的孩子节点作为根，若不为空，则一直往下比较;
		}
		arr[i]=tmp;	//把根节点放置到属于的位置。
	}
	
	public void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j]=tmp;
	}
	
	/**
	 * 考虑数组下标从1开始,0不存东西
	 * @param arr
	 * @param i
	 * @param n
	 */
	public void heapAdjust1(int[] arr, int i, int n){
		//调整以i为节点的根
		int tmp = arr[i];
		int j=2*i;//左孩子
		for(;j<=n; j=2*i){
			if(j<n&&arr[j]<arr[j+1])
				j=j+1;
			if(tmp>arr[j])
				break;
			arr[i]=arr[j];//根变为j
			i=j;//根节点下移
		}
		arr[i]=tmp;
	}
	
	/**
	 * 数组下标从1开始--有效位置从1开始
	 * @param arr
	 */
	public void heapSort12(int[] arr){
		util.print(arr);
		int n=arr.length-1;
		for(int i=n/2; i>0; i--){
			heapAdjust1(arr, i, n);
		}
		util.print(arr);
		for(int j=n; j>0; j--){//原先数组就是n个有效位置
			swap(arr,1,j);//交换0和j,j是当前所关注数组最后位置.
			heapAdjust1(arr, 1, j-1);
		}
		util.print(arr);
	}
	
}
