package com.uestc.tree;

import com.uestc.jk.Node;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月31日上午9:43:41
 * 二叉树节点类
 */
public class BinTreeNode implements Node{
	
	private Object data;	//数据域
	private BinTreeNode parent;	
	private BinTreeNode lChild;
	private BinTreeNode rChild;
	
	private int height;	//以该节点为根的子树的高度
	private int size;	//该节点的子孙数(包括自身)
	
	public BinTreeNode(){this(null);}
	
	public BinTreeNode(Object obj){
		data=obj; height=0; size=1;
		parent = lChild =rChild =null;
	}
	
	/**Node 接口方法*****/
	@Override
	public Object getData() {
		// TODO Auto-generated method stub
		return data;
	}

	@Override
	public void setData(Object obj) {
		// TODO Auto-generated method stub
		data=obj;
	}

	/****辅助方法,判断当前节点的位置情况********************/
	//是否有父亲
	public boolean hasParent(){return parent!=null;}
	//是否有左孩子
	public boolean hasLChild(){return lChild!=null;}
	//是否有右孩子
	public boolean hasRChild(){return rChild!=null;}
	//判断是否为叶子节点
	public boolean isLeaf(){return !hasLChild()&&!hasRChild();}
	//是否某节点的左孩子
	public boolean isLChild(){return (hasParent()&&this == parent.lChild);}
	//是否某节点的右孩子
	public boolean isRChild(){return (hasParent()&&this == parent.rChild);}
	
	/***与height相关操作**********/
	//取节点的高度，即以该节点为根的树的高度
	public int getHeight(){return height;}
	//更新当前节点及其祖先的高度
	public void updataHeight(){
		int newH=0;//新高度初始化为0，高度等于左右子树高度加1中的大者
		if(hasLChild())newH = Math.max(newH, 1+getLChild().height);
		if(hasRChild())newH = Math.max(newH, 1+getRChild().height);
		if(newH == height) return;	//高度没有变化直接返回
		height = newH;
		if(hasParent()) getParent().updataHeight();//递归更新祖先的高度
	}
	
	/**与size相关的操作**********/
	//取以该节点为根的树的节点数
	public int getSize(){return size;}
	//更新当前节点及其祖先的子孙数
	public void updateSize(){
		size =1; 	//初始化size为1，节点本身
		if(hasLChild()) size+=getLChild().getSize();	//加上左子树的数目
		if(hasRChild()) size+=getRChild().getSize();	//加上右子树的数目
		
		if(hasParent()) getParent().updateSize();
	}
	
	
	/**与父节点相关的操作**********/
	public BinTreeNode getParent(){return parent;}
	
	//断开与父节点的关系
	public void server(){
		if(!hasParent()) return ;
		if(isLChild())
			parent.lChild=null;
		else 
			parent.rChild=null;
		parent.updataHeight();	//更新父结点及其祖先高度
		parent.updateSize();	//更新父结点及其祖先规模
		parent = null;	//?
		
	}
	
	
	/**与左孩子相关的操作**********/
	//取左孩子
	public BinTreeNode getLChild(){return lChild;}
	
	//设置当前节点的左孩子，返回原先左孩子
	public BinTreeNode setLChild(BinTreeNode lc){
		BinTreeNode oldLC = this.lChild;
		if(hasLChild()){lChild.server();}//断开当前左孩子与结点的关系
		if(lc!=null){
			lc.server();//断开lc与其父节点关系
			this.lChild = lc;
			lc.parent = this;
			this.updataHeight();
			this.updateSize();
		}
		return oldLC;
	}
	
	/**与右孩子相关的操作**********/
	//取右孩子
	public BinTreeNode getRChild(){return rChild;}
	//设置当前节点的右孩子，返回原先右孩子
	public BinTreeNode setRChild(BinTreeNode rc){
		BinTreeNode oldRC = this.rChild;
		if(hasRChild()){rChild.server();}//断开当前右孩子与结点的关系
		if(rc!=null){
			rc.server();//断开rc与其父节点关系
			this.rChild = rc;
			rc.parent = this;
			this.updataHeight();
			this.updateSize();
		}
		return oldRC;
	}

	
	
}
