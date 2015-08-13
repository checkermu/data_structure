package com.uestc.tree;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月13日上午10:26:25
 * 
 * 归并排序：二路归并可以不需要递归，
 * 在最重要的merge函数的时候，可以传递两个数组参数，来回互相传值，但是这样的缺点是arr与arrNew，奇数个数排序arr是排好序的，偶数个的话arrNew是排好序的
 * 
 * 可以不传递新数组参数，而是在函数内部新建个数组，不过最后还要进行copy，
 * 同样在传递步长的时候这样也可以再改进
 * 看看如下，可以AboutMergeSort中的归并排序mergePass方法和这里的对比
 * 
 */
public class MergeSortTwo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeSortTwo ms = new MergeSortTwo();
		int[] arr = {50, 10, 90, 30, 70, 40, 80, 60, 20};
		
		ms.mergeSort(arr);
		
	}
	/**
	 * 归并排序：
	 * 
	 * @param arr
	 */
	public void mergeSort(int[] arr){
		if(arr==null||arr.length<=1)
			return;
		int n = arr.length;
		int len =1;
		//边界处理，步长与个数的对比
		while(len<=n){
			System.out.println("步长为:"+len);
			//将数组一开始就是认为1个个的有序，然后两两归并，一开始步长为1
			for(int i=0; i+len<=n-1; i += 2*len){
				//三个位置，起始位置，中间位置，尾部位置
				int b=i; int m=i+len-1; int e=i+len*2-1;
//				e = e>(n-1)?(n-1):e;
				if(e>n-1)	e=n-1;
				merge(arr, b, m, e);
			}
			len *= 2;
			
			System.out.println("数组："+Arrays.toString(arr));
		}
		
	}
	
	
	
	/**
	 * 归并排序最终的合并函数
	 * @param arr	传入要归并数组
	 * @param b	左边子数组起始位置下标
	 * @param m	左边子数组终止下标	
	 * @param e	右边子数组终止下标
	 */
	public void merge(int[] arr, int b, int m, int e){
		/*if(arr==null||arr.length<=0||b<0||b>e)
			return;*/
		int[] tmp = new int[arr.length];
		int i=m+1, k=b, j=b;
		while(j<=m&&i<=e){
			if(arr[j]<arr[i])
				tmp[k++]=arr[j++];
			else
				tmp[k++]=arr[i++];
		}
		while(j<=m){
			tmp[k++]=arr[j++];
		}
		while(i<=e){
			tmp[k++]=arr[i++];
		}
		//copy数组
		while(b<=e){
			arr[b]=tmp[b];
			b++;
		}
	}
	
	
}
