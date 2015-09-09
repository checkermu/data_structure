package com.gt.bishi;

import java.util.Arrays;

/**
 * 找出前n个素数
 * @author checkermu
 *
 */
public class PrimNumberOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		prime(20);
	}
	/**
	 * 素数：除了1和本身不能被其他任何数整除，
	 * 最暴力，判断一个数是否是素数，从2到本身-1不能整除则是，浪费太多
	 * 2、从2到本身的平方根不能被整除
	 * 3、还是又浪费，跳过2与3的共同倍数，又减少了很多，根号m最好再加1
	 */

	public static int[] prime(int n){
		if(n<=0)
			return new int[1];
		int[] prim = new int[1000];
		prim[0]=2; prim[1]=3; prim[2]=5;
		if(n<=3){
			return Arrays.copyOf(prim, n);
		}
		
		int count = 3;
		int gap=2;		//增长步长 2或者4
		//巧妙之处：从5开始，2和4循环的增加步长！！
		int mabe = 5;
		int i=0;
		boolean is = true;
		while(count<n){
			mabe+=gap;
			gap = 6-gap;
			is = true;
			for(i=2; prim[i]*prim[i]<=mabe && is; i++){
				if(mabe%prim[i]==0)
					is = false;
			}
			
			if(is)
				prim[count++]=mabe;
			
		}
		
		for(i=0; i<count; i++){
			if(i%10==0)
				System.out.println();
			System.out.printf("%5d", prim[i]);
		}
		return prim;
	}
	
	
}
