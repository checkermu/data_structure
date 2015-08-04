package com.uestc.str;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年7月21日下午4:16:57
 * 
 * 串匹配算法, KMP
 */
public class KMPMatch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String ori = "BBC ABCDAB ABCDABCDABDE";
		String pattern = "ABCDABE";
		KMPMatch kmp = new KMPMatch();
//		kmp.volienceMatch(ori, pattern);
		kmp.kmp(ori, pattern);
	}
	
	public void kmp(String ori, String pattern){
		int[] next = getNextArray(pattern);
		char[] oA=ori.toCharArray();
		char[] pA = pattern.toCharArray();
		int lenO = oA.length;
		int lenP = pA.length;
		int i=0; 
		int j=0;
		while(i<lenO && j<lenP){
			if(j==-1 || oA[i]==pA[j]){
				i++;
				j++;
			}else if(j!= -1 || oA[i]!=pA[j]){
				j = next[j];
			}
		}
		
		if(j==lenP)
			System.out.println("匹配");
		else
			System.out.println("不匹配");
		
	}
	
	
	/**
	 * 获得一个字符串的next数组
	 * next数组含义：当前字符之前的子串中，最大长度的相同前缀和后缀
	 * 有点类似DP，知道了next[0…j],如何求的next[j+1]
	 * 不相等的时候，k=next[k]；next[j]=k, 开始k个和倒数k个对应相等， 
	 * h=next[k]表明在前面k个（索引是k-1）从头的h个和倒数的h个相同，
	 * 如果p[h] == next[j] ； 那么在后面的另一托和前面这k个一一对应的地方，也是一样相等
	 * 
	 * @param ori
	 * @return
	 */
	public int[] getNextArray(String ori){
		char[] oriArr = ori.toCharArray();
		int len = oriArr.length;
		int[] next = new int[len];
		next[0]=-1;
		int k=-1;
		int j=0;
		while(j<len-1){
			//p[k]表示前缀， p[j]表示后缀
			if(k==-1 ||oriArr[j]==oriArr[k]){
				++k;
				++j;
				next[j]=k;
			}else{	//k用next数组进行递归
				k=next[k];
			}
		}
		
		return next;
	}
	
	public int[] getNextArrayTwo(String ori){
		char[] oriArr = ori.toCharArray();
		int len = oriArr.length;
		int[] next = new int[len];
		next[0]=-1;
		int k=-1;
		int j=0;
		while(j<len-1){
			if(k==-1 || oriArr[j]==oriArr[k]){
				++k;
				++j;
				//第一个赋初值-1，第二个是0，
				//优化
				if(oriArr[j] != oriArr[k]){
					next[j]=k;
				}else{
					//因为不能出现p[j] == p[next[j]]
					next[j]=next[k];
				}
			}else{
				k=next[k];
			}
		}
		return next;
	}
	
	
	
	/**
	 * 暴力匹配
	 * 当失败的时候j变为0； i变为下一个，
	 * i的下一个就是，当前的i减去已经匹配的j再加1；
	 * @param ori
	 * @param pattern
	 */
	public void volienceMatch(String ori, String pattern){
		char[] oriArr = ori.toCharArray();
		char[] patternArr = ori.toCharArray();
		
		int lenO = oriArr.length;
		int lenP = patternArr.length;
		int i=0, j=0;
		while(i<lenO && j<lenP){
			if(oriArr[i]==patternArr[j]){
				i++; j++;
			}else{//匹配失败
				i=i-j+1;
				j=0;
			}
		}
		
		if(j==lenP)
			System.out.println("匹配成功");
		else
			System.out.println("匹配失败");
		
	}

}
