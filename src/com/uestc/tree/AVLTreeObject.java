package com.uestc.tree;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月31日上午10:34:32
 */
public class AVLTreeObject {
	private BinTreeNode root;
	private BinTreeNode startBN;
	public void insert(Object el){
		BinTreeNode p=root, prev =null;
		/*while(p!=null){
			prev=p;
			if(p.getData()<el)
		}*/
	}
	
	//从v开始重新平衡AVL树
	private BinTreeNode reBalance(BinTreeNode v){
		if(v==null) return root;
		BinTreeNode c=v;
		while(v!=null){//从v开始，向上逐一检查z的祖先
			if(!isBalance(v))	//若v失衡，则选择使之重新平衡
				v=rotate(v);
			c=v;
			v=v.getParent();
		}
		return c;
	}
	
	
	
	/**
	 * 判断一个节点是否失衡
	 * @param v
	 * @return
	 */
	public boolean isBalance(BinTreeNode v){
		if(v==null) return true;
		int lH =(v.hasLChild())?v.getLChild().getHeight():-1;
		int rH =(v.hasRChild())?v.getRChild().getHeight():-1;
		return(Math.abs(lH-rH)<=1);
	}
	
	/**
	 * 旋转,
	 * @param z 失衡的节点z
	 * @return 平衡后子树的根节点
	 */
	private BinTreeNode rotate(BinTreeNode z){
		BinTreeNode y = higherSubT(z);	//取y为z较高的孩子,就是案例中的p
		BinTreeNode x = higherSubT(y);	//取x为y较高的孩子	案例中的v
		boolean isLeft = z.isLChild();  //记录z是否为左孩子
		BinTreeNode p = z.getParent();	//p为z的父亲
		BinTreeNode a, b = null, c;	//自左向右，三个节点
		BinTreeNode t0, t1, t2 = null , t3;	//自左向右，四棵子树
		
		//以下分四种情况重命名
		if(y.isLChild()){//y是左孩子，y定了，那么x要么是左，要么是右，分两种情况，但是这时候，c和t3是确定的
			c=z; t3 = z.getRChild();
			if(x.isLChild()){ //如果x是左，那就出现zuozuo情况，都在左边
				b=y; t2 = y.getRChild();
				a=x; t1 = x.getRChild(); t0=x.getLChild();
			}else{	//如果x是在右边，那么就是出现左右情况，需要先左旋后右旋，不过在这里都是统一处理了
				a=y; t0=y.getLChild();
				b=x; t1=x.getLChild(); t2=x.getRChild();
			}
		}else{//y是右孩子，也是定y，那x只有两种情况
			a=z; t0 =z.getLChild();
			if(x.isRChild()){//x也在右边
				b=y; t1=y.getLChild();
				c=x; t2=x.getLChild(); t3=x.getRChild();
			}else{	//y在右边，x在左边，理论是先右旋，后左旋
				c=y; t3=y.getRChild();
				a=x; t0=x.getLChild(); t1=x.getRChild();
			}
		}//重命名工作结束
		
		//摘下三个节点
		z.server();
		y.server();
		x.server();
		//摘下四棵子树
		if(t0!=null) 
			t0.server();
		if(t1!=null) 
			t0.server();
		if(t2!=null) //为啥就t2需要初始化
			t2.server();
		if(t3!=null) 
			t3.server();
		//重新连接
		a.setLChild(t0);	a.setRChild(t1);
		c.setLChild(t2);	c.setRChild(t3);
		b.setLChild(a);		b.setRChild(c);
		
		return b;	//返回新的子树根
	}
	/**
	 * 返回节点v较高的子树
	 * @param v
	 * @return
	 */
	private BinTreeNode higherSubT(BinTreeNode v){
		if(v==null) return null;
		int lH=(v.hasLChild())?v.getLChild().getHeight():-1;
		int rH=(v.hasRChild())?v.getRChild().getHeight():-1;
		
		if(lH>rH)
			return v.getLChild();
		else if(lH<rH)
			return v.getRChild();
		else 
			return v.getRChild();
	}
}
