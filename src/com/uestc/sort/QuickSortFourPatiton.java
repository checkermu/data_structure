package com.gt.datastructure;

import java.util.Arrays;
import java.util.Random;

/**
 * ��������ļ��ַָ����ʽ
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
	 * �ָ��1���õ�һ����Ϊ�ָb, 
	 * ��ʼ�� ��ָ�� i=j=b+1��
	 * iָ����ǵ�һ������[b]���±�;
	 * ������󻥻���ʱ��  i-1
	 * @param arr
	 * @param b
	 * @param e
	 * @return
	 */
	public static int patition1(int[] arr, int b, int e){
		int tmp = arr[b]; //ѡ���һ��Ϊ��־λ
		
		int i=b+1; int j=b+1;
		for(; j<=e; j++){
			if(arr[j]<=tmp){
				swap(arr, i, j);
				i++;
			}//i��Զ��ʾ��һ������tmp���±�,�������Ҫ����
		}
		swap(arr, b, i-1);
		return i-1;
	}
	
	/**
	 * �ָ��2�������õ�һ��������˼���Ϊ���˼��
	 * �ѵ�һ��copy����tmp����Ϊ��һ���ӣ��Ӻ���ǰ���ҵ�һ��С��tmp�ľ�����ӣ������Ϊ�¿�
	 * �ٴ�ǰ����ҵ�����tmp�ģ������¿ӣ����������ʣ��b����tmpҪ�����λ��
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
	 * �����һ����Ϊ��־λ�� 
	 * ����ָ�룬��ʼ��ʱ�� i=begin-1; j=begin
	 * iά���������һ��С��tmp�������±ꡣ
	 * @param arr
	 * @param b
	 * @param e
	 * @return
	 */
	public static int patition3(int[] arr, int b, int e){
		int tmp = arr[e];
		int i=b-1; int j=b;
		//�����һ����Ϊ��־λ��iά���������һ��С��tmp���±�
		for(; j<e; j++){//����Ϊ�� ��e-1�أ����һ����Ҫ��������Ȼ�ظ������ˡ�
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
		//������������ô���أ��� b~e��Щ�±��������һ��
		Random rand = new Random();
//		int t = b+ rand.nextInt(e)%(e-b+1);
		int t = b+ rand.nextInt(e-b+1); //����Ч��һ����
		//�����ѡ��һ���±꣬���Խ�����ͷ��Ҳ���Խ�����β����ʣ�µľ͸�ǰ��������һ�����ˣ�
		//�����������ǽ�����β����
		swap(arr, t, e);
		
		int i=b-1, j=b;//����ָ�뷽����ʼ��
		int tmp = arr[e];
		for(; j<e; j++){
			if(arr[j]<=tmp){
				i++;//iָ��������һ��������tmp���±�Ҫ�����϶�����i++���ҵ���һ�����ڵĽ���
				if(i!=j){
					swap(arr, i, j);
				}
			}
		}
		//���������Ҫ��tmp�ŵ����ʵ�λ��
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
