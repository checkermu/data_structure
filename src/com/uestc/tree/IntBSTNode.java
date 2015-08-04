package com.uestc.tree;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月18日上午11:18:08
 * 二叉查找树的节点类实现 针对整数树。
 */
public class IntBSTNode {
	protected int key;
	protected IntBSTNode left, right;
	public IntBSTNode(){
		left = right = null;
	}
	public IntBSTNode(int el){
		this(el, null, null);
	}
	public IntBSTNode(int el, IntBSTNode lt, IntBSTNode rt){
		this.key = el; this.left = lt; this.right = rt;
	}
}
