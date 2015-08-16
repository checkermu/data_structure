package com.gt.question;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月13日下午8:45:42
 * 最长回文子串回顾
 */
public class LongestPalindromicNumBack {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LongestPalindromicNumBack lpm = new LongestPalindromicNumBack();
		String s = "acbcaacbcd";
		String s1 = "dabcba"; // "caa" "abaf"  "dabcba"
		
		lpm.dpLongestP(s);
		lpm.manLongest(s);
		
	}

	/**
	 * 一个是基于动态规划的思想：
	 * dp[i][j]表示i到j这个子串是否是回文串：初始化 i==j肯定是true；i>j是空串也可以是true;
	 * k从1开始到n；内循环 i从0开始；i+k<n； j=i+k，因为后面 dp[i][j]需要dp[i+1][j-1]的判断，这样k从1开始就不用担心j的边界问题了。
	 * 对一个abac字符串来说，动态规划循环内部 k=1时候 (i,j)={(0,1),(1,2),(2,3)} k=2时(i,j)={(0,2),(1,3)} k=3时(i,j)={(0,3)}
	 * 只会前半截。而子串的长度是 j-i+1也就是 k+1
	 */
	
	public String dpLongestP(String s){
		if(s==null||s.length()<=1)
			return s;
		char[] cs = s.toCharArray();
		int n =cs.length;
		boolean[][] dp = new boolean[n][n];
		for(int i=0; i<n; i++){
			for(int j=0; j<n; j++){
				if(i>=j)
					dp[i][j]=true;
				else
					dp[i][j]=false;
			}
		}
		int max=0; //最大长度
		int sb =0, se =0, j; //开始和结束下标
		for(int k=1; k<n; k++){
			for(int i=0; i+k<n; i++){//为何i+k <n 呢，需要的是一个[i……j]的子串，保证j<n
				j=i+k;
				if(cs[i]!=cs[j])
					dp[i][j]=false;
				else{
					dp[i][j]=dp[i+1][j-1];
					if(dp[i][j]){
						if(k+1 > max){
							max = k+1; //最大长度
							sb = i;		//起始
							se = j;
						}
					}
				}
			}
		}//两个循环尽管是 n*n/2，但也是n2的复杂度
		System.out.println("最大长度"+max +"回文子串"+s.substring(sb, se+1));
		
		return s.substring(sb, se+1);
	}
	
	/**
	 * 前提是要插入进去特殊字符： 最好起点和终点来个左右边界
	 * 关于O(n)的时间复杂度
	 * 考虑到回文子串的性质，以C为中心的最右边界是R，那么 R-C这段距离中的i总有一个镜像i'在R的左侧，
	 * 而且是对称的，如果i'的回文大小p[i']<R-i，那么p[i]=p[i']，如果大于那么先赋值为 R-i，然后再扩展，同时注意扩展C和R
	 * 对于一个i,他的镜像如何计算呢，  i-C是距离C的大小，那么 C-(i-C)就是镜像 =  2*C -i;
	 * 
	 * 关于p[i]
	 * 如果右边界R > 当前的i，表明当前i的镜像值可以得到，那么就看看与R-i的大小，小的话直接赋值，大的话先把R-i赋给，然后再补救扩展
	 * 如果右边界R < 当前的i，表明直接是0， 看看有没有办法补救
	 * 
	 */
	
	public String manLongest(String s){
		if(s==null||s.length()<=1)
			return s;
		String T = precess(s); //预处理的字符串
		char[] ct = T.toCharArray();//字符数组
		int n=ct.length;
		int[] p = new int[n];
		//需要一个C，回文中心，和一个R右边界
		int C=0, R=0;
		for(int i=1; i<n-1; i++){//因为最开始和最结尾两个是特殊标记
			//找镜像
			int i_mirror = C-(i-C);//正儿八经镜像求法
			//写规范繁琐好理解一点
			if(R>i){//首先要把镜像点包括在内，把i包括在内，那么对应的i'也在内
				if(p[i_mirror]>(R-i))//镜像的回文串比R-i还要大，那么
					p[i]=R-i;	//先临时赋值为R-i,后面还要补救扩展
				else
					p[i]=p[i_mirror];
			}else{
				p[i]=0;
			}
			
			//补救,已知ct[i+p[i]] == ct[i-p[i]]所以在此基础上 +1， -1
			while(ct[i+1+p[i]] == ct[i-1-p[i]])//不用管边界问题
				p[i]++;
			
			//更新R和C
			if(i+p[i] > R){
				C=i;
				R=i+p[i]; //最右的边界更新
			}
		}
		int maxI=0;
		for(int i=0; i<n; i++){
			if(p[i]>p[maxI])
				maxI = i;
		}
		//最大的下标，对应的最大的回文串 p[maxI], 预处理的字符串中回文串是：maxI-p[maxI]~~ maxI+p[maxI]//+1;为何不在后面+1呢，因为无所谓啦的
		
		String res = T.substring(maxI-p[maxI], maxI+p[maxI]).replace("#", "");
		System.out.println("回文串："+res);
		return res;
		
	}
	
	
	/**
	 * 预处理方法，不仅插入#还在左右边界插入特殊字符
	 * @param s
	 * @return
	 */
	public String precess(String s){
		if(s==null)
			return null;
		int n = s.length();
		if(n==0)
			return "^$";
		char[] cs = s.toCharArray();
		StringBuilder sb = new StringBuilder("^");//起始标志位
		for(int i=0; i<n; i++){
			sb.append("#"+cs[i]);
		}
		sb.append("#$");
		return sb.toString();
	}
	
	
	
	
}
