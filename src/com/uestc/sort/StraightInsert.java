package com.uestc.sort;

import com.uestc.util.Util;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月25日下午10:09:49
 */
public class StraightInsert {
	public Util util = new Util();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StraightInsert si = new StraightInsert();
		int[] arr={2,3,4,5,6};
		int[] arr1 = {9, 1, 5, 8, 3, 7, 6, 4, 2};
		si.insert(arr);

	}

	/**
	 * 直接插入排序,由名字发现:要插入的已经排好序
	 * @param arr
	 */
	public void insert(int[] arr){
		int n=arr.length;
		for(int i=1; i<n; i++){
			if(arr[i]<arr[i-1]){
				int tmp=arr[i];
				int j=i-1;
				for(; (j>=0)&&arr[j]>tmp; j--){
					arr[j+1]=arr[j];
				}
				arr[j+1] = tmp;
			}
		}
	}
	
}
