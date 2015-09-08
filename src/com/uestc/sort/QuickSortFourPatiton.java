package com.gt.datastructure;

import java.util.Arrays;
import java.util.Random;

/**
 * 快速排序的几种分割函数方式
 * @author checkermu
 *
 */
public class QuickSortFourPatiton {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr = {2,7,2,8,9,4,1,6,5,7, 100, 100, 78, 67, 98, 13,101, 103,156};
		quickSort(arr, 0, arr.length-1);
		
		System.out.println(Arrays.toString(arr));
	}

	public static void quickSort(int[] arr, int b, int e){
		if(arr==null||arr.length<=0)
			return;
		if(b<e){
			int m = patition4(arr, b, e);
			quickSort(arr, b, m-1);
			quickSort(arr, m+1, e);
		}
	}
	
	
	
	/**
	 * 分割函数1，用第一个作为分割，b, 
	 * 初始化 两指针 i=j=b+1；
	 * i指向的是第一个大于[b]的下标;
	 * 所以最后互换的时候  i-1
	 * @param arr
	 * @param b
	 * @param e
	 * @return
	 */
	public static int patition1(int[] arr, int b, int e){
		int tmp = arr[b]; //选择第一个为标志位
		
		int i=b+1; int j=b+1;
		for(; j<=e; j++){
			if(arr[j]<=tmp){
				swap(arr, i, j);
				i++;
			}//i永远表示第一个大于tmp的下标,所以最后要处理
		}
		swap(arr, b, i-1);
		return i-1;
	}
	
	/**
	 * 分割函数2，还是用第一个，不过思想变为填坑思想
	 * 把第一个copy放在tmp，作为第一个坑，从后向前，找到一个小于tmp的就填入坑，后面变为新坑
	 * 再从前向后找到大于tmp的，填入新坑，互换，最后剩下b就是tmp要填入的位置
	 * @param arr
	 * @param b
	 * @param e
	 * @return
	 */
	public static int patition2(int[] arr, int b, int e){
		int tmp = arr[b];
		while(b<e){
			while(b<e && (arr[e]>=tmp))
				e--;
			swap(arr, b, e);
			while(b<e && (arr[b])<=tmp)
				b++;
			swap(arr, b, e);
		}
		
		arr[b]=tmp;
		return b;
	}
	
	/**
	 * 以最后一个作为标志位， 
	 * 两个指针，初始化时候 i=begin-1; j=begin
	 * i维护的是最后一个小于tmp的区域下标。
	 * @param arr
	 * @param b
	 * @param e
	 * @return
	 */
	public static int patition3(int[] arr, int b, int e){
		int tmp = arr[e];
		int i=b-1; int j=b;
		//以最后一个作为标志位，i维护的是最后一个小于tmp的下标
		for(; j<e; j++){//这里为何 到e-1呢，最后一个还要互换，不然重复操作了。
			if(arr[j]<=tmp){
				++i;
				if(i!=j){
					swap(arr, i, j);
				}
			}
		}
		swap(arr, i+1, e);
		
		return i+1;
	}
	
	public static int patition4(int[] arr, int b, int e){
		//如果采用随机怎么做呢？从 b~e这些下标中随机挑一个
		Random rand = new Random();
//		int t = b+ rand.nextInt(e)%(e-b+1);
		int t = b+ rand.nextInt(e-b+1); //两个效果一样！
		//随机挑选了一个下标，可以交换到头，也可以交换到尾部，剩下的就跟前面三种是一样的了！
		//比如在这里是交换到尾部吧
		swap(arr, t, e);
		
		int i=b-1, j=b;//两个指针方法初始化
		int tmp = arr[e];
		for(; j<e; j++){
			if(arr[j]<=tmp){
				i++;//i指向的是最后一个不大于tmp的下标要交换肯定是先i++；找到第一个大于的交换
				if(i!=j){
					swap(arr, i, j);
				}
			}
		}
		//别忘了最后要把tmp放到合适的位置
		swap(arr, i+1, e);
		
		return i+1;
	}
	
	
	public static void swap(int[] arr, int i, int j){
		if(i==j)
			return;
		arr[i]=arr[i]^arr[j];
		arr[j]=arr[i]^arr[j];
		arr[i]=arr[i]^arr[j];
	}
	
}
