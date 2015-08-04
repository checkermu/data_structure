package com.uestc.test;

import com.uestc.util.Util;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月27日下午10:55:34
 */
public class TTTTT {
	public Util util = new Util();
	public static Util u = new Util();
	public static void main(String[] args) {
		TTTTT t = new TTTTT();
		int[] arr={2,3,6,4};
		int[] arr1 = {9, 1, 5, 8, 3, 7, 6, 4, 2};
		u.print(arr1);
//		t.bubble(arr1);
		t.mergeSort(arr1, 0, arr1.length-1);
		u.print(arr1);
	}
	
	public void bubble(int[] arr){
		int n=arr.length;
		for(int i=0; i<n-1; i++){
			int j=n-2;
			for(;j>=i; j--){
				if(arr[j]>arr[j+1]){
					util.swap(arr, j, j+1);
				}
			}
		}
	}
	
	public void mergeSort(int[] arr, int low, int high){
		if(low<high){
			int p=(low+high)/2;
			mergeSort(arr, low, p);
			mergeSort(arr, p+1, high);
			mergeArr(arr, low, p, high);
		}
	}
	
	public void mergeArr(int[] arr, int low, int p, int high){
		int i=low, s=p+1, k=0;
		int[] b = new int[high-low+1];
		while(i<=p&&s<=high){
			if(arr[i]<arr[s])
				b[k++]=arr[i++];
			else
				b[k++]=arr[s++];
		}
		
		while(i<=p)
			b[k++]=arr[i++];
		while(s<=high)
			b[k++]=arr[s++];
		
		for(int j=0; j<b.length; j++)
			arr[low+j]=b[j];
	}
}
