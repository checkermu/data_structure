package com.gt.datastructure;

import java.util.Arrays;

/**
 * ��С������
 * @author checkermu
 *
 */
public class HeapSortMin {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {2,7,2,8,9,4,1,6,5,7};
		heapSort1(arr);
		System.out.println(Arrays.toString(arr));
	}

	/**
	 * �����Ƕѵ�������
	 */
	public static void heapAdjust(int[] arr, int i, int n){
		int tmp = arr[i];
		int j = 2*i+1;
		for(;j<n; j=2*i+1){
			if((j<n-1)&&(arr[j]>arr[j+1]))
				j=j+1;
			if(arr[j]>tmp)//��С�ѣ�����������ֱ��break
				break;
			arr[i]=arr[j];
			i=j;
		}
		arr[i]=tmp;
	}
	
	/**
	 * �Ե����Ϻ���
	 */
	public static void heapSort1(int[] arr){
		if(arr==null||arr.length<=1)
			return;
		int n=arr.length;
		for(int i=n/2 -1; i>=0; i--){
			heapAdjust(arr, i, n);
		}
		
		for(int j=n-1; j>=0; j--){
			swap(arr, 0, j);
			heapAdjust(arr, 0, j);
		}
	}
	
	public static void swap(int[] arr, int i, int j){
		if(i==j)
			return;
		arr[i]=arr[i]^arr[j];
		arr[j]=arr[i]^arr[j];
		arr[i]=arr[i]^arr[j];
		/*int tmp =arr[i];
		arr[i]=arr[j];
		arr[j]=tmp;*/
	}
	
}
