package com.uestc.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月18日上午11:22:40
 * 二叉搜索树实现
 */
public class IntBST {
	protected IntBSTNode root;
	
	public IntBST(){
		root = null;
	}
	
	public void visit(IntBSTNode p){
		System.out.print(p.key+"  ");
	}
	
	/**
	 * 在二叉树搜索（查找树）中插入元素el
	 * @param el
	 */
	public void insert(int el){
		IntBSTNode p = root, prev = null;
		while(p!=null){
			prev = p;
			if(p.key < el)
				p = p.right;
			else
				p=p.left;
		}
		if(root==null){
			root = new IntBSTNode(el);
		}else if(prev.key > el){
			prev.left = new IntBSTNode(el);
		}else
			prev.right = new IntBSTNode(el);
	}
	
	
	
	
	//树的遍历方式
	/**
	 * 广度优先遍历--使用队列实现：如果有子女，就将子节点放入队列尾，然后访问队列头部的节点。
	 * 考虑自顶向下，自左向右的广度优先遍历
	 * @param p
	 */
	public void breadFirst(IntBSTNode p){
		Queue queue = new LinkedList();
		if(p != null){
			queue.add(p);
			while(!queue.isEmpty()){
				p = (IntBSTNode)queue.poll();
				visit(p);
				if(p.left != null)
					queue.add(p.left);
				if(p.right != null)
					queue.add(p.right);
			}
		}
	}
	
	//深度优先遍历：是尽可能的沿着左边（或者右边）前进，然后返回到第一个岔路口，转而访问其右边（或左边）的一个节点，
	//然后再次尽可能的沿着左边（或右边）前进。重复这个过程，直到访问完所有的节点
	//分为先序、中序、后序
	/**
	 * 递归实现前序遍历, 根-左-右
	 * @param p
	 */
	public void preorder(IntBSTNode p){
		if(p != null){
			visit(p);
			preorder(p.left);
			preorder(p.right);
		}
	}
	
	/**
	 * 递归实现中序遍历 左-根-右
	 * @param p
	 */
	public void inorder(IntBSTNode p){
		if(p!=null){
			inorder(p.left);
			visit(p);
			inorder(p.right);
		}
	}
	
	/**
	 * 递归实现后序遍历  左-右-根
	 * @param p
	 */
	public void postorder(IntBSTNode p){
		if(p!=null){
			postorder(p.left);
			postorder(p.right);
			visit(p);
		}
	}
	
	/**
	 * 非递归实现二叉树中序遍历
	 * @param root
	 */
	public void iterativeInorder(IntBSTNode root){
		//栈，用于存放二叉树的节点
		Stack<IntBSTNode> stack = new Stack<IntBSTNode>();
		IntBSTNode p = root; //当前节点
		
		while(p != null || !stack.isEmpty()){
			//一直深入的寻找左子节点，并将路上的节点都进栈
			while(p !=null){
				stack.push(p);
				p = p.left;
			}
			//左孩子已经为空，取栈顶元素出栈(操作)，并继续遍历右子树(到上面while遍历)。
			if(!stack.isEmpty()){
				p = stack.pop();
				visit(p);	//对于节点的操作，可以直接输出该节点元素.
				p = p.right;
			}
		}
	}
	
	/**
	 * 非递归实现后序遍历
	 * @param root
	 */
	public void iterativePostorder(IntBSTNode root){
		IntBSTNode p, q = null;
		Stack<IntBSTNode> stack = new Stack<IntBSTNode>();
		stack.push(root);
		while(!stack.isEmpty()){
			p = stack.pop();
			if((p.left==null&&p.right==null)||(q!=null&&(q==p.left||q==p.right))){
				visit(p);
				q=p;
			}else{
				if((p.right!=null)){
					stack.push(p.right);
				}
				if(p.left!=null){
					stack.push(p.left);
				}
			}
					
		}
	}
	
}
