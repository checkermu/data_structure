package com.uestc.test;

import com.uestc.util.Util;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月26日下午7:17:14
 */
public class TestSort {

	public Util util = new Util();
	public static Util u=new Util();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TestSort ts = new TestSort();
		int[] arr={2,3,6,4};
		int[] arr1 = {9, 1, 5, 8, 3, 7, 6, 4, 2};
//		ts.shellSort1(arr1);
//		ts.heapSort(arr1);
		u.print(arr1);
		ts.quickSort(arr1, 0, arr1.length-1);
		u.print(arr1);
	}
	
	/**
	 * 快速排序
	 * @param arr
	 * @param low
	 * @param high
	 */
	public void quickSort(int[] arr, int low, int high){
		if(low<high){
			int line = separateArr(arr, low, high);
			quickSort(arr, low, line-1);
			quickSort(arr, line+1, high);
		}
	}
	/**
	 *快速排序的辅助方法,分隔数组
	 * @param arr
	 * @param low
	 * @param high
	 * @return
	 */
	public int separateArr(int[] arr, int low, int high){
		int tmp=arr[low];	//对枢轴进行备份
		while(low<high){
			while(low<high&&arr[high]>tmp)
				high--;
			arr[low]=arr[high];//把high赋值到了low,此时这个high就是新的坑了
				//接着把low++,是因为不然的话下面的while会执行一遍无用功
			while(low<high&&arr[low]<tmp)
				low++;
			arr[high]=arr[low];
		}
		arr[low]=tmp;
		return low;
	}
	
	
	
	/**
	 * 有间距的直接插入排序 space=n/3 +1;
	 * @param arr
	 */
	public void shellSort(int[] arr){
		util.print(arr);
		int n=arr.length;
		int space=n;
		
		do {
			space = space/3 +1;
			for(int i=space; i<n; i++){
				if(arr[i]<arr[i-space]){	//先比较而不是先循环
					int tmp = arr[i];
					int j=i-space;
					for(; (j>=0)&&arr[j]>tmp; j-=space){
						arr[j+space] = arr[j];
					}
					arr[j+space]=tmp;
				}
			}
		} while (space>1);
		util.print(arr);
	}
	
	public void shellSort1(int[] arr){
		util.print(arr);
		int n = arr.length;
		for(int space=n/2; space>0; space/=2){
			for(int i=space; i<n; i++){
				if(arr[i]<arr[i-space]){
					int tmp = arr[i];
					int j=i-space;
					for(; (j>=0)&&tmp<arr[j]; j-=space){
						arr[j+space]=arr[j];
					}
					arr[j+space]=tmp;
				}
			}
		}
		util.print(arr);
	}
	
	/**
	 * 堆排序中的下降移动函数，要下降的根i,数组的长度n
	 * @param arr
	 * @param i
	 * @param n
	 */
	public void moveDown(int[] arr, int i, int n){
		int tmp=arr[i];//根
		int j=2*i+1;	//左孩子下标
		for(;j<n; j=2*i+1){//左孩子小于n
			if(j<(n-1)&&arr[j]<arr[j+1]) //左右两个调最大的
				j=j+1;
			if(tmp>arr[j])
				break;	//因为是从最底层的根节点开始，一步步满足最大堆，如果某个高层根节点>左右最大值，那如果左右孩子是子树那肯定已经是满足最大堆了
			arr[i]=arr[j];
			i=j;
		}
		arr[i]=tmp;
	}
	
	public void heapSort(int[] arr){
		util.print(arr);
		int n=arr.length;
		for(int r=n/2-1; r>=0; r--){
			moveDown(arr, r, n);
		}
		util.print(arr);
		for(int j=n-1; j>=0; j--){
			swap(arr, 0, j);
			moveDown(arr, 0, j);
		}
		util.print(arr);
	}
	
	public void swap(int[] arr, int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}

}
