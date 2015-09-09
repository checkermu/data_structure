package com.gt.bishi;

import java.util.Arrays;

/**
 * �ҳ�ǰn������
 * @author checkermu
 *
 */
public class PrimNumberOne {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		prime(20);
	}
	/**
	 * ����������1�ͱ����ܱ������κ���������
	 * ������ж�һ�����Ƿ�����������2������-1�����������ǣ��˷�̫��
	 * 2����2�������ƽ�������ܱ�����
	 * 3���������˷ѣ�����2��3�Ĺ�ͬ�������ּ����˺ܶ࣬����m����ټ�1
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
		int gap=2;		//�������� 2����4
		//����֮������5��ʼ��2��4ѭ�������Ӳ�������
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
