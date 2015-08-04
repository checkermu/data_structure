package com.uestc.sort;

import java.util.Arrays;

import com.uestc.util.Util;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月27日下午9:41:40
 */
public class MergeSort {
	public Util util = new Util();
	public static Util u = new Util();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MergeSort ms = new MergeSort();
		int[] arr={2,3,6,4};
		int[] arr1 = {9, 1, 5, 8, 3, 7, 6, 4, 2, 11, 10};
		int[] arr2={9,4,2,7,3};
		u.print(arr1);
//		ms.mergeSort(arr1, 0, arr1.length-1);
//		ms.sort(arr1, 0, arr1.length-1);
		
		//2015-07-13
		ms.mergeSortCo(arr2, 0, arr2.length-1);
		
		u.print(arr1);
		
		/*int[] arr={1,4,15,38,49,2,9,18,20,30};
		ms.mergeArr(arr, 0, 4, arr.length-1);
		u.print(arr);*/
	}
	
	/**
	 * 归并排序也是要用递归的，思想：有序的序列合并；
	 * 将待排序数组中间分开，合并有序的序列
	 * 
	 */
	
	public void mergeSort(int[] arr, int low, int high){
		if(low<high){
			int p = (low+high)/2;
			mergeSort(arr,low, p);
			mergeSort(arr,p+1, high);
			
			mergeArr(arr,low, p, high);
		}
	}
	//low-p为有序，p+1到high为有序，现在要合并到一起并有序
	public void mergeArr(int[] arr, int low, int p, int high){
		int[] b=new int[high-low+1];
		int k=0, t=p+1, l=low, h=high;
//		while(l<h){//多了这个while 调用会一直不停，是因为while存在的时候low=0, high=4的时候，循环结束了但是l<h,一直在死循环。
			while(l<=p&&t<=h){
				if(arr[l]<arr[t]) //左边的小
					b[k++]=arr[l++];
				else //右边的小
					b[k++]=arr[t++];
			}
			while(l<=p) b[k++]=arr[l++];//避免出现某个元素都是大的情况
			while(t<=h) b[k++]=arr[t++];//
//		}
		
		for(int i=0; i<b.length; i++){
			arr[low+i]=b[i];
		}
	}
	
	
	public void sort(int[] data, int left, int right) {  
	    if(left<right){  
	        int center=(left+right)/2; 	//找出中间索引    
	        sort(data,left,center); 	//对左边数组进行递归   
	        sort(data,center+1,right);	//对右边数组进行递归    
	        //合并  
	        merge(data,left,center,right);  
	          
	    }  
	}  
	public void merge(int[] data, int left, int center, int right) {  
	    int [] tmpArr=new int[data.length];  
	    int mid=center+1;  
	    //third记录中间数组的索引  
	    int third=left;  
	    int tmp=left;  
	    while(left<=center&&mid<=right){  
	        //从两个数组中取出最小的放入中间数组  
	        if(data[left]<=data[mid]){  
	            tmpArr[third++]=data[left++];  
	        }else{  
	            tmpArr[third++]=data[mid++];  
	        }  
	    }  
	    //剩余部分依次放入中间数组  
	    while(mid<=right){  
	        tmpArr[third++]=data[mid++];  
	    }  
	    while(left<=center){  
	        tmpArr[third++]=data[left++];  
	    }  
	    //将中间数组中的内容复制回原数组  
	    while(tmp<=right){  
	        data[tmp]=tmpArr[tmp++];  
	    }  
	    System.out.println(Arrays.toString(data));  
	}  
	
	
	
	/**
	 * 2015-07-13复习归并sort
	 */
	
	

	public void mergeSortCo(int[] arr, int be, int end){
		if(be<end){
			int mid = (be+end)/2;
			mergeSortCo(arr, be, mid);
			mergeSortCo(arr, mid+1, end);
			
			mergeCo(arr, be, mid, end);
		}
	}
	
	public void mergeCo(int[] arr, int be, int center, int end){
		int i=be, j=center+1,k=0;					//由最底层的0,1两个进行合并的时候，0-0, 1-1两个有序子数组合并，//再进一步：不平衡的两个子数组，0-1, 1+1 -2；前面这个子数组有两个数，而后面这个子数组只有一个数
		int[] tmpArr = new int[arr.length];
		while(i<=center&&j<=end){
			if(arr[i]<arr[j])
				tmpArr[k++]=arr[i++];
			else
				tmpArr[k++]=arr[j++];
		}
		//是否还有剩余部分
		while(i<=center)//之前一直疑惑为何是 <= 因为还等于的时候表明这个是没有进行比较也没有进行copy的，在上面中tmpArr[k++]=arr[j++]只要比较赋值过那就会变化
			tmpArr[k++]=arr[i++];
		while(j<=end)
			tmpArr[k++]=arr[j++];
		//copy回去
		for(int m=0; m<k; m++)
			arr[be+m]=tmpArr[m];
	}
}
