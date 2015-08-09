package com.uestc.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月9日下午7:21:30
 */
public class TraversingBinaryTree {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TraversingBinaryTree to = new TraversingBinaryTree();
		Node root = to.createTree();
//		to.inorderIter2(root);
//		to.preorderIter(root);
//		to.postorderIter4(root);
//		to.sequenceIter(root);
		to.postRecursion(root);

	}
	/**
	 * 层序遍历二叉树，用队列实现
	 * @param root
	 */
	public void sequenceIter(Node root){
		if (root==null)
			return ;
		Node p=root;
		Queue<Node> queue = new LinkedList<Node>();
		queue.offer(p);
		while(queue.size()>0){
			p=queue.poll();
			visit(p);
			if(p.left!=null)
				queue.offer(p.left);
			if(p.right!=null)
				queue.offer(p.right);
		}
	}
	/**
	 * 递归前序
	 * @param root
	 */
	public void preRecursion(Node root){
		if(root!=null){
			visit(root);
			preRecursion(root.left);
			preRecursion(root.right);
		}
	}
	
	/**
	 * 递归中序
	 * @param root
	 */
	public void inRecursion(Node root){
		if(root!=null){
			inRecursion(root.left);
			visit(root);
			inRecursion(root.right);
		}
	}
	
	/**
	 * 递归后序
	 * @param root
	 */
	public void postRecursion(Node root){
		if(root!=null){
			postRecursion(root.left);
			postRecursion(root.right);
			visit(root);
		}
	}
	
	/**根左右
	 * 非递归前序遍历二叉树
	 * 访问根，右，左，入栈即可
	 * @param root
	 */
	public void preorderIter(Node root){
		if(root ==null)
			return;
		Node p=root;
		Stack<Node> stack = new Stack<Node>();
		stack.push(p);
		while(stack.size()>0){
			p=stack.pop();
			visit(p);
			if(p.right!=null)
				stack.push(p.right);
			if(p.left!=null)
				stack.push(p.left);
		}
	}
	
	/**左根右
	 * 非递归中序遍历二叉树
	 * 方式一：一直根，左入栈，把右节点当做一个tree来处理
	 * @param root
	 */
	public void inorderIter1(Node root){
		if(root==null)
			return;
		Node p=root;
		Stack<Node> stack = new Stack<Node>();
		while(p!=null||stack.size()>0){
			while(p!=null){//一直根,左入栈
				stack.push(p);
				p=p.left;
			}
			
			if(stack.size()>0){
				p=stack.pop();
				visit(p); //上面根左到头后，访问栈最顶端节点
				p=p.right;//把右孩子当做一个新tree来处理，外面还需要一层循环
			}
		}
	}
	
	/**左根右
	 * 非递归中序访问二叉树方法2
	 * 思想：既然要去左根右的访问，那么入栈顺序就是右,根,左;
	 * 当然有一些右子树没有入栈。需要进一步处理
	 * 出栈访问的时候，当前p右右子节点那就再来一遍 右,根,左;
	 * 同样也是把右节点当做一个tree处理
	 * @param root
	 */
	public void inorderIter2(Node root){
		if(root == null)
			return;
		Node p=root;
		Stack<Node> stack = new Stack<Node>();
		while(p!=null){
			while(p!=null){//右根左的入栈
				if(p.right!=null)
					stack.push(p.right);
				stack.push(p);
				p=p.left;
			}
			p=stack.pop();
			//只有当出栈的p右子节点为null才访问他
			while(stack.size()>0 && p.right==null){
				visit(p);
				p=stack.pop();
			}
			//如果右子节点不为null
			visit(p);//因为肯定是个根,还是要访问
			//要更新p作为新tree的根重新，右根左入栈
			if(stack.size()>0)
				p=stack.pop();
			else
				p=null;
		}
		
	}
	
	/**左右根：方法一
	 * 后序遍历二叉树
	 * 思路：还是根，左的入栈，到头后不是跟上面中序一样直接访问，而是要进行判断
	 * 有一个prev节点，还有用tmp表示当前栈顶(peek)的right节点，
	 * (1)如果是空或者tmp==prev，则进行弹出pop()且访问，表明是个根且根的右子节点已经访问了(或null)，后prev = p; p=null;
	 * 为何把当前p设置为null呢，因为当前根的左右以及根都访问了，需要进行访问下一个了。设置为null，则直接到了stack.peek那里。
	 * (2)如果不是空且没有被prev(访问)过，那么说明右右节点，那么把这个右节点当做一个新的子树处理即可。p=tmp;
	 * 
	 * @param root
	 */
	public void postorderIter1(Node root){
		Node p=root, prv = root;
		Stack<Node> stack = new Stack<Node>();
		
		while(p!=null || stack.size()>0){
			while(p!=null){
				stack.push(p);
				p=p.left;
			}
			
			if(stack.size()>0){
				Node tmp = stack.peek().right;
				if(tmp==null || tmp==prv){
					p=stack.pop();
					visit(p);
					prv = p;
					p=null;//设置为null方便访问栈下个元素
				}else{//否则把存在且没有访问过的右子节点作为一棵树来处理。
					p=tmp;
				}
			}
		}
		
	}
	
	/**
	 * 左右根：方法三
	 * 非递归后序访问二叉树
	 * 思想：先左到头(最后一个左不放入栈)入栈;查看是否该访问这个节点
	 * 重点是如果要访问这个节点，(p!=null && (p.right==null || p.right==q);
	 * 不为空是必须的，并且要么这个节点的右节点为null要么这个节点的右节点已经访问过
	 * 右节点还是当做一个tree的root处理
	 * @param root
	 */
	public void postorderIter2(Node root){
		Node p=root, q=root;
		Stack<Node> stack = new Stack<Node>();
		while(p!=null){//这里就不需要或者stack不为空了，原因自己看
			for(;p.left!=null; p=p.left){
				stack.push(p);//注意这样入栈的区别，最后一个左并没有入栈。
			}
			while(p!=null &&(p.right==null || p.right==q)){
				//要进来访问的关键：不为null是必须的，并且 要么右节点为null要么有节点已经访问过
				visit(p);
				q=p;
				if(stack.isEmpty())
					return;
				p=stack.pop();
			}//这里的while跳出的原因就是在于p右未访问的右孩子
			stack.push(p);//其实这里造成了出栈后再入栈的重复
			p=p.right;
		}
	}
	
	/**后序遍历方法三，双栈法
	 * 左右根
	 * 用两个栈，左栈，右栈，左栈压入根，右栈压入右节点(null也压入),一一对应压入后；
	 * 出栈；如果右栈出来的是null 则 访问左栈弹出的节点，否则，重新把左栈出来的压入，右栈压入null；
	 * 把right赋值给p，进行当做一个右子树继续循环。
	 * 
	 * @param root
	 */
	public void postorderIter3(Node root){
		Node p=root, right;
		Stack<Node> ls = new Stack<Node>();
		Stack<Node> rs = new Stack<Node>();
		do{
			while(p!=null){//根与右节点分别压栈
				right = p.right;
				ls.push(p);
				rs.push(right);
				p=p.left;
			}
			//下面弹出开始访问；注意压入的时候两个栈是一一对应的
			p=ls.pop();
			right=rs.pop();
			if(right==null){
				visit(p);
			}else{
				ls.push(p);
				rs.push(null);
			}
			p=right;//把右节点当做右子树的根返回继续压栈
		}while(ls.size()>0 || rs.size()>0);
	}
	
	/**
	 * 后序双栈法
	 * 左右根，后序访问二叉树
	 * 思路：借助一个栈，把另一个栈按照根右左的思路入栈，那么最后迭代另一个栈即可。
	 * A,B(最终),同时根，右入栈；
	 * A不为空的话，弹出让A的左节点当做子树的根，重复上面入栈行为
	 * ；
	 * ；最后一次弹出B栈就可以
	 * @param root
	 */
	public void postorderIter4(Node root){
		Node p=root;
		Stack<Node> stack = new Stack<Node>();
		Stack<Node> tmp = new Stack<Node>();
		
		while(p!=null || stack.size()>0){
			while(p!=null){//两个栈分别根,右的入栈
				stack.push(p);
				tmp.push(p);
				p=p.right; //右节点
			}
			if(stack.size()>0){
				p=stack.pop();
				p=p.left;//把左节点当做tree的root重新压栈
			}
		}//结束那么tmp栈就OK了
		while(tmp.size()>0){
			p=tmp.pop();
			visit(p);
		}
	}
	
	/**
	 * 访问二叉树的一个节点
	 * @param p
	 */
	public void visit(Node p){
		System.out.print(" "+p.data);
	}
	
	public Node createTree(){
		Node d = new Node(4, null, null);
		Node e = new Node(5, null, null);
		Node f = new Node(6, null, null);
		Node g = new Node(7, null, null);
		Node b = new Node(2, d, e);
		Node c = new Node(3, f, g);
		Node a = new Node(1, b, c);
		return a;
	}

}

/**
 * 方便起见都是可以直接访问
 */
class Node{
	int data;
	Node left;
	Node right;
	public Node(){};
	public Node(int d){
		this.data=d;
	}
	public Node(int d, Node l, Node r){
		this.data=d;
		this.left=l;
		this.right=r;
	}
}