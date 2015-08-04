package com.uestc.sort;

import java.util.Stack;

import com.uestc.util.Util;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月27日上午9:08:24
 */
public class QuickSort {
	public Util util = new Util();
	public static Util u = new Util();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] arr={2,3,6,4};
		int[] arr1 = {9, 1, 5, 8, 3, 7, 6, 4, 2};
		QuickSort qs = new QuickSort();
//		qs.quickSort(arr1, 0, arr1.length-1);
		qs.quicksortS(arr1);
		u.print(arr1);
		System.out.println("OK");
	}
	
	/**************快速排序非递归，使用栈--begin************************/
	public void quicksortS(int[] array) {
        if (array == null || array.length == 1) return;
        //存放开始与结束索引
        Stack<Integer> s = new Stack<Integer>(); 
        //压栈       
        s.push(0); 
        s.push(array.length - 1); 
        //利用循环里实现
        while (!s.empty()) { 
            int right = s.pop(); 
            int left = s.pop(); 
            //如果最大索引小于等于左边索引，说明结束了
            if (right <= left) continue; 
                     
            int i = partitionS(array, left, right); 
            if (left < i - 1) {
                s.push(left);
                s.push(i - 1);
            } 
            if (i + 1 < right) {
                s.push(i+1);
                s.push(right);
            }
        } 
    }
    //找到轴心，进行交换
    public int partitionS (int[] data, int first, int end)
    {
        int temp;
        int i=first,j=end;
        if(first<end)
        {
            temp=data[i];
            //当i=j的时候，则说明扫描完成了
            while(i<j)
            {
                //从右边向左边扫描找到一个小于temp的元素
                while(j>i&&data[j]>temp)j--;
                if(i<j)
                {
                    //将该元素赋值给temp
                    data[i]=data[j];
                    //赋值后就应该将i+1指向下一个序号
                    i++;
                }
                       
                //然后从左边向右边开始扫描，找到一个大于temp的元素
                while(i<j&&temp>data[i])i++;
                if(i<j)
                {
                    //将该元素赋值给temp
                    data[j]=data[i];
                    //赋值后就应该将j-1指向前一个序号
                    j--;
                }
            }
            //将轴数据放在i位置中
            data[i]=temp;
        }
        return i;
    }
	
    /************快速排序非递归，使用栈--end**************************/
	
	
	
	//版本三
	private  void qsort(int[] arr, int low, int high){
	    if (low < high){
	        int pivot=partition(arr, low, high);        //将数组分为两部分
	        qsort(arr, low, pivot-1);                   //递归排序左子数组
	        qsort(arr, pivot+1, high);                  //递归排序右子数组
	    }
	    util.print(arr);
	}
	//版本三
	private  int partition(int[] arr, int low, int high){
	    int pivot = arr[low];     //枢轴记录
	    while (low<high){
	        while (low<high && arr[high]>=pivot) --high;
	        arr[low]=arr[high];             //交换比枢轴小的记录到左端
	        while (low<high && arr[low]<=pivot) ++low;
	        arr[high] = arr[low];           //交换比枢轴小的记录到右端
	    }
	    //扫描完成，枢轴到位
	    arr[low] = pivot;
	    //返回的是枢轴的位置
	    return low;
	}
	
	
	//版本二
	public void quickSort1(int s[], int l, int r){
		if(l<r){
			int i=adjustArray(s, l, r);//先用挖坑填数法调整s[]
			quickSort1(s, l, i);
			quickSort1(s, i+1, r);
		}
		util.print(s);
	}
	//版本二
	public int adjustArray(int[] s, int l, int r){//返回调整后基准数的位置
		int i=l, j=r;
		int x = s[l]; //s[l]就是s[i]为第一个坑
		while(i<j){
			//从右向左找小于x的数来填坑s[i]
			while(i<j && s[j]>=x)
				j--;
			if(i<j){
				s[i]=s[j];//将s[j]填充到s[i]中，s[j]为新的坑
				i++;
			}
			while(i<j && s[i]<x)
				i++;
			if(i<j){
				s[j]=s[i];//将s[i]填充到s[j]中，s[i]就是新坑
				j--;
			}
		}
		s[i]=x;//退出时，i等于j，将x填入到这个坑
		return i;
	}

	
	
	
	/**
	 * 快速排序my, wrong
	 * @param arr
	 * @param l
	 * @param r
	 */
	public void quickSort(int[] arr, int l, int r){
		
		if(l<r){
			int sepa = separteArr(arr, l, r);
			quickSort(arr, l, sepa-1);
			quickSort(arr, sepa+1, r);
		}
		util.print(arr);
	}
	/**
	 * 分隔数组，左边比arr[low]小，右边比arr[low]大
	 * @param arr
	 * @param low
	 * @param high
	 */
	public int separteArr(int[] arr, int low, int high){
		int tmp = arr[low];
		System.out.println("low:"+low+"high"+high+"arr:");
		while(low<high){
			while(low<high&&tmp<=arr[high])
				high--;
			arr[low]=arr[high];
			while(low<high&&tmp>arr[low])
				low++;
			arr[high]=arr[low];
		}
		arr[low]=tmp;
		return low;
	}
}
