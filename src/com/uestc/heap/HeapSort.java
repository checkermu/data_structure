package com.uestc.heap;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月9日上午10:26:42
 * 堆排序的核心在于最大堆的调整：
 */
public class HeapSort {

	/**
	 * 递归调整最大堆
	 * @param array 待调整的最大堆表示数组
	 * @param i		检查的起始下标
	 * @param size	堆数组的大小
	 */
	public void adjustHeap(int[] array, int i, int size){
		int iMax = i;
		int iLeft = 2*i +1;
		int iRight = 2*(i+1);
		
		if(iLeft<size && array[i]<array[iLeft]){
			iMax = iLeft;
		}
		if(iRight<size && array[iMax]<array[iRight]){
			iMax = iRight;
		}
		
		if(iMax != i){
			swap(array, iMax, i);
			adjustHeap(array, iMax, size);//递归调整
		}
	}
	/**
	 * 辅助函数，交换数组的两个元素
	 * @param array
	 * @param i
	 * @param j
	 */
	public void swap(int[] array, int i, int j){
		int tmp = array[i];
		array[i]=array[j];
		array[j]=tmp;
	}
	
	/**
	 * 迭代调整最大堆，这里用的是while
	 * @param array
	 * @param i
	 * @param size
	 */
	public void adjustHeapIter(int[] array, int i, int size){
		int iMax, iLeft, iRight;
		while(true){
			iMax = i;
			iLeft=2*i+1;
			iRight = 2*(i+1);
			if(iLeft<size && array[i]<array[iLeft]){
				iMax = iLeft;
			}
			if(iRight<size && array[iMax]<array[iRight]){
				iMax = iRight;
			}
			
			if(iMax != i){
				swap(array, iMax, i);
				i = iMax;
			}else{
				break;
			}
		}
	}
	
	/**
	 * 调整最大堆，这里用for循环进行迭代, 直接数组元素赋值替换，不用交换
	 * @param array
	 * @param i
	 * @param size
	 */
	public void adjustHeapIterFor(int[] array, int i, int size){
		int tmp = array[i];
		int left = 2*i +1; //左孩子
		for(; left<size; left = 2*i +1){
			if(left<size-1 && array[left]<array[left+1]){
				left = left+1;
			}
			if(array[left]>tmp){
				array[i]=array[left];
				i=left;
			}else{
				break;
			}
		}
		array[i]=tmp;
	}
	
	/**
	 * 创建最大堆
	 * @param array
	 * @param size
	 */
	public void buildHeap(int[] array, int size){
		int i1 = (size-1)/2;//下标既然是从0开始，用size计算最底层的一个非叶子结点，这样会出错
		int i = size/2 -1;//这样才是最后一个非叶子结点
		for (; i>=0; i--){
			adjustHeapIterFor(array, i, size);//从最后一个飞叶子节点，自底向上开始调整最大堆，
		}
	}
	
	/**
	 * 堆排序
	 * @param array
	 * @param size
	 */
	public void heapSort(int[] array, int size){
		//先把最大堆创建出来
		buildHeap(array, size);
		
		//每次把堆顶元素取出来
		for(int j=size-1; j>=0; j--){
			swap(array, j, 0);
			adjustHeapIterFor(array, 0, j);//把堆顶取走，从堆顶开始调整，现在整个要调整的堆大小为j即 size-1；
		}
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HeapSort hs = new HeapSort();
		int[] array = {3,5,2,8,6,1,9};
		
		hs.heapSort(array, array.length);
		
		System.out.println(Arrays.toString(array));

	}

}
