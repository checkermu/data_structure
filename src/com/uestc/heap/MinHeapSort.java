package com.uestc.heap;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月9日下午8:32:27
 * 创建最小堆，进行排序
 */
public class MinHeapSort {
	
	/**
	 * 堆调整，最小堆，堆顶为最小
	 * @param array
	 * @param i
	 * @param size
	 */
	public void adjustHeap(int[] array, int i, int size){
		int tmp=array[i];
		int j = 2*i +1;//左孩子
		for(; j<size; j=2*i+1){
			if((j<size-1)&&array[j]>=array[j+1]){
				j=j+1;//找到左右孩子最小的
			}
			if(array[j]>=tmp){
				break;
			}
			array[i]=array[j];//小于则覆盖
			i=j;
		}
		array[i]=tmp;
	}
	
	/**
	 * 递归实现,想想终止条件？ iMin == i?
	 * @param array
	 * @param i
	 * @param size
	 */
	public void adjustHeapRecursion(int[] array, int i, int size){
		int iMin = i;
		int iLeft = 2*i +1;
		int iRight = 2*(i+1);
		
		if(iLeft<size&& array[iMin]>array[iLeft]){
			iMin = iLeft;
		}
		if(iRight<size && array[iMin]>array[iRight]){
			iMin = iRight;
		}
		
		if(iMin != i){//后面的都比他大，或者到底了。
			swap(array, iMin, i);
			adjustHeapRecursion(array, iMin, size);
		}
		
	}
	
	/**
	 * 构建最小堆，将传入的无序数组构建为最小堆
	 * @param array
	 * @param size
	 */
	public void buildMinHeap(int[] array, int size){
		//size长度的数组，先得到最后一个飞叶子节点的下标
		int i=size/2 -1;
		for(; i>=0; i--){
			adjustHeapRecursion(array, i, size);
		}
	}
	
	/**
	 * 利用最小堆进行排序,最大堆是每次把堆顶元素放到堆顶，现在最小堆，第一个元素就是最小的
	 * 最小堆排序，从大到小序列
	 * @param array
	 */
	public void sortMinHeap(int[] array, int size){
		buildMinHeap(array, size);
		//0元素本身就是最小的,不用
		for(int i=size-1; i>=0; i--){
			swap(array, i, 0);
			adjustHeap(array, 0, i);
		}
		
	}
	
	
	public void swap(int[] array, int i, int j){
		int tmp=array[i];
		array[i]=array[j];
		array[j]=tmp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MinHeapSort mhs = new MinHeapSort();
		int[] array = {2,8,1,9,4,7,5,6};
		mhs.sortMinHeap(array, array.length);
		
		System.out.println(Arrays.toString(array));
	}

}
