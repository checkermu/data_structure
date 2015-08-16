package com.gt.question;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月13日下午5:27:03
 * 
 * 最长回文子串
 */
public class LongestPalindromicNum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestPalindromicNum lp = new LongestPalindromicNum();
		String s = "abac";
		String s1 = "dabcba"; // "caa" "abaf"  "dabcba"
		lp.longestPalindromic(s);
		lp.longestDP(s);
		lp.longestP(s);
	}
	
	/**
	 * Manacher Algorithm，求解最长公共子序列
	 */
	
	/**
	 * 处理字符串，加上#和 边界符号
	 * @param s
	 * @return ^#s#$
	 */
	public String process(String s){
		if(s==null)
			return null;
		char[] cs = s.toCharArray();
		int n = cs.length;
		if(n==0)
			return "^$";
		StringBuilder sb = new StringBuilder("^");
		for(int i=0; i<n; i++)
			sb.append("#"+cs[i]);
		sb.append("#$");
		
		System.out.println("Process String ："+sb.toString());
		return sb.toString();
	}
	
	/**
	 * Manacher 算法求解最长回文子串，O(n)时间复杂度
	 * 根据中心Tc回文串所能到达的最左L和最右R
	 * 一个 i和他的镜像i'， 如果镜像i'的回文值大于 R-i，说明超出了回文中心Tc回文串所能掌控的了，
	 * 因此先临时设置为p[i]=R-i，并接着根据回文属性扩充p[i]，还要更新Tc和R。
	 * p[]数组记录了以每个字符为中心的最长回文半径（针对的是预处理后的字符）
	 * @param s
	 * @return
	 */
	public String longestP(String s){
		String T = process(s);
		System.out.println("处理过的字符串为:"+T);
		char[] ct = T.toCharArray();
		int n=ct.length;
		int[] p = new int[n];
		
		int C=0, R=0;
		for(int i=1; i<n-1; i++){ // 加了两个边界字符
			int i_mirror = 2*C - i; // 等于 i' = C - (i-C)
//			p[i] = (R>i)?( (R-i)<p[i_mirror]?(R-i):p[i_mirror]):0;
			//添加左右边界不仅仅下面while扩展的时候避免了边界烦恼，这里R>i的时候，也不会有边界烦恼，因为此时i_mirror = -1，而R=0是小于i的不会去判断p[-1]；
			//这个三目运算符写简单点：R-i > p[i_mirror] 则p[i]=p[i_mirror]否则 p[i]=R-i;前提是R>i；
			p[i] = (R>i)?Math.min(p[i_mirror], R-i):0; //R<i的时候为何p[i]=0呢，
			
			//这个算是补救措施 ！尝试扩展回文串，中心在i
			while(ct[i+1+p[i]] == ct[i-1-p[i]])
				p[i]++;
			
			//中心i扩展右边界越过了R，更新
			if(i+p[i]>R){
				C=i;
				R=i+p[i];
			}
		}
		
		//find the max
		int max = 0;
		int midIndex =0;
		for(int i=1; i<n-1; i++){
			if(p[i]>max){
				max = p[i];
				midIndex = i;
			}
		}
		
		System.out.println("最长回文串长度："+max+"中间位置"+midIndex+"回文串为:"+T.substring(midIndex-max, midIndex+max).replace("#", ""));
		
		return T.substring(midIndex-max, midIndex+max).replace("#", "");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	/**
	 * 动态规划求解最长模式串,i==j 则dp[i][j]看dp[i+1][j-1];否则直接false;
	 * @param s
	 */
	public String longestDP(String s){
		if(s==null||s.length()<=1)
			return s;
		char[] cs = s.toCharArray();
		int n = cs.length;
		boolean[][] dp = new boolean[n][n];
		int i, j;
		for(i=0; i<n; i++){
			for(j=0; j<n; j++){
				if(i>=j)
					dp[i][j]=true; //==明显是true，大于是空串
				else
					dp[i][j]=false;
			}
		}
		int max=0;
		int sb = 0, se = 0;
		for(int k=1; k<n; k++){
			for(i=0; i+k <n; i++){
				j=i+k;
				if(cs[i]!=cs[j])
					dp[i][j]=false;
				else{//如果相等查看之前的
					dp[i][j]=dp[i+1][j-1];
					if(dp[i][j]){//如果是回文串，看看是否超越
						if(k+1>max){//为何是k+1呢，因为这个下标是从i到j中间差了k个
							max = k+1;
							sb = i;
							se = j;
						}
					}
					
				}
			}
		}
		System.out.println("最大长度："+max+" 回文串:"+s.substring(sb, se+1));
		return s.substring(sb, se+1);
		
	}
	
	
	/**
	 * 求最长回文子串
	 * 思路是把每个i作为中心，看看最大的相同的前缀和后缀
	 * 不过要分奇数和偶数
	 * @param s
	 * @return
	 */
	public String longestPalindromic(String s){
		if(s==null||s.equals(""))
			return "";
		
		char[] cs = s.toCharArray();
		int n =cs.length;
		int max=0, c=0;
		int index = 0;
		for(int i=0; i<n; i++){
			//这个是针对回文子串长度是奇数时候设定的,首先自己和自己比，然后左右比
			for(int j=0; (i-j)>=0&&(i+j)<n; j++){
				if(cs[i-j]!=cs[i+j])
					break;
				c = 2*j+1;//此时回文串的长度
				if(c>max){
					index = i;
					max =c;
				}
			}
			
			//下面是针对回文子串长度是偶数的时候设定的，首先自己和后面的比，然后
			for(int j=0; (i-j)>=0 && (i+j+1)<n; j++){
				if(cs[i-j]!=cs[i+j+1])
					break;
				c = 2*j +2;//此时回文串的长度
				if(c>max){
					index = i;
					max =c;
				}
			}
		}
		int ie = index+(max>>1);
		int ib = ie-max+1;
		System.out.println("字符串："+s+" 的最长回文子串长度是:"
				+max+"index:"+index+"回文子串是："+s.substring(ib, ie+1));
		
		return s.substring(ib, ie+1);
	}
	
	
	
}
