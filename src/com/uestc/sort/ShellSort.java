package com.uestc.sort;

import com.uestc.util.Util;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月26日上午9:22:01
 */
public class ShellSort {

	private Util util = new Util();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ShellSort ss = new ShellSort();
		int[] arr={2,3,6,4};
		int[] arr1 = {9, 1, 5, 8, 3, 7, 6, 4, 2};
		ss.shellSort0(arr);
	}
	
	public void shellSort0(int[] arr){
		util.print(arr);
		int length=arr.length;
		int space =length;
		do {//如果 space=space/3+1；用for循环判断条件space>0会一直循环不停止，所以改用do while
			space=space/3+1;	//元素个数为100的时候，会减少最外面的循环次数，也就是间隔数
			for(int i=space; i<length; i++){//从间隔的后一个开始比较，比较对象是i-space；
				if(arr[i]<arr[i-space]){
					int tmp = arr[i];
					int j=i-space;
					for(; (j>=0)&&arr[j]>tmp; j-=space){
						arr[j+space]=arr[j];//前面比后面大，把前面移到后面
					}
					arr[j+space]=tmp;	//插入到要排序位置，因为for循环最后又-space
				}
			}
		} while (space>1);
		
		util.print(arr);
	}
	
	public void shellSort1(int[] arr){
		//希尔排序，分隔后直接插入，基本有序再最后一个整体直接插入
		int n=arr.length;
		for(int space=n/2; space>0; space/=2){
			for(int i=space; i<n; i++){
				//间隔进行判断，还是直接插入的判断，只不过现在的直接插入是有间隔的比较，不是相邻比较
				if(arr[i]<arr[i-space]){//总是认为第一个自己有序，后面的和前面的比较，当前的i和前面的比较
					int tmp=arr[i];		//当前的元素比前面的小，那么就改插入，（插入也就是前面的元素后移动）
					int j=i-space;//代表前面的
					for(;(j>=0)&&arr[j]>tmp; j-=space){//前面那个大于当前的元素，那就把前面的元素移到当前的位置。
						arr[j+space]=arr[j];			//arr[j]是前面的元素，赋值给后面的也就是后移
					}//j-=space;如果j>=0，当前的tmp继续和前面的比较，直到找到合适的插入位置，比如1…5…3；
					//当前i位置元素为3，i-space位置的元素为5，大了则移动，5移动到3的位置，j-=space;j变为1的位置，比较不符合，退出for循环
					//所以下面一行是arr[j+space]=tmp,无论什么时候for截止，j都是变小了space;因此要加回来。
					arr[j+space]=tmp;
				}
			}
		}
	}

}
