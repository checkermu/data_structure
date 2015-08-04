package com.uestc.sort;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月23日上午10:41:54
 * 基数排序的一个实现，fromline：
 * http://www.cnblogs.com/developerY/p/3172379.html
 */
public class RadixSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int[] A=new int[]{73,22, 93, 43, 55, 14, 28, 65, 39, 81};
		 radixSort1(A, 100);
		 System.out.println(Arrays.toString(A));
	}

	public static void radixSort1(int[] arr, int d){
		int n=1; //代表位数对应的数，1,10,100
		int k=0; //保存每一位排序后的结果用于下一位的排序输入
		int length=arr.length;
		//排序桶用于保存每次排序后的结果，这一位上排序结果相同的数字放在同一个桶里
		int[][] bucket = new int[10][length];
		int[] order = new int[length];//用于保存每个桶里有多少个数字
		while(n<d){
			for(int num:arr){
				int digit = (num/n)%10;
				bucket[digit][order[digit]]=num;
				order[digit]++;
			}
			//将前一个循环生成的桶里的数据覆盖到原数组中用于保存这一位的排序结果
			for(int i=0; i<length; i++){
				if(order[i]!=0){
					for(int j=0; j<order[i]; j++){
						arr[k]=bucket[i][j];
						k++;
					}
				}
				order[i]=0;//桶计数器置为0，用于下一次位排序。
			}
			n *= 10;
			k=0; //k置为0，用于下一轮
		}
		
		
		
	}
	
}
