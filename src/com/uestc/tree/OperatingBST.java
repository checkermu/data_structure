package com.uestc.tree;
/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年5月21日下午9:21:30
 */
public class OperatingBST {
	private IntBSTNode root;
	
	public OperatingBST(IntBSTNode root){
		this.root = root;
	}
	private static final int arr[] ={10, 5, 8, 2, 15, 18};
	public static IntBST intBstree = new IntBST();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int len = arr.length;
		for(int i=0; i<len; i++){
			intBstree.insert(arr[i]);
		}
		intBstree.insert(7);
		System.out.println("前序遍历：");
		intBstree.preorder(intBstree.root);
		
		 System.out.println("\n中序遍历: ");
		 intBstree.inorder(intBstree.root);
		 
		 System.out.println("\n后序遍历: ");
		 intBstree.postorder(intBstree.root);
		 
		 OperatingBST opt = new OperatingBST(intBstree.root);
		 opt.deleteByMerging(5);
		 
		 System.out.println("\n删除5后前序遍历: ");
		 intBstree.preorder(opt.root);
		 
		 System.out.println("\n删除5后中序遍历: ");
		 intBstree.inorder(opt.root);
		 
		 
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
	
	/**
	 * 归并删除法
	 * @param el
	 */
	@SuppressWarnings("unused")
	public void deleteByMerging(int el){
		IntBSTNode p = root, tmp, node, prev=null;
		while(p!=null){
			prev = p;
			if(p.key<el)
				p=p.right;
			else
				p=p.left;
		}
		node=p;	//找到了这个要删除的节点
		if(p!=null && p.key==el){
			if(node.right==null)//没有右孩子
				node=node.left;
			else if(node.left==null)//没有左孩子
				node=node.right;
			else{
				tmp = node.left;	//move left
				while(tmp.right!=null)	// 最右子节点
					tmp = tmp.right;
				tmp.right = node.right;//和要删除节点的右子树建立连接
				
				node = node.left;	//左边节点替代原先要删除节点
			}
			if(p==root)
				root = node;
			else if(prev.left==p)
				prev.left=node;
			else prev.right = node;
		}
		else if(root !=null){
			System.out.println("key "+el+" is not in the tree!");
		}else System.out.println("the tree is empty");
		
	}
	
	/**
	 * 复制删除法
	 * @param el
	 */
	public void deleteByCoping(int el){
		IntBSTNode p = root, node, prev=null;
		while(p!=null && p.key!=el){
			prev=p;
			if(p.key<el)
				p=p.right;
			else p = p.left;
		}
		node=p;
		if(p!=null && p.key==el){
			if(node.right==null)
				node=node.left;
			else if(node.left==null)
				node=node.right;
			else{
				IntBSTNode tmp = node.left;	//左右两个子树都存在
				IntBSTNode previous = node;	//目的：记住左子树最右结点的父节点，因为把最右结点值赋给了要删除的那个节点
				while(tmp.right!=null){		//，这个最右结点也就是要不存在了，那如果有左孩子，就需要指向他的previous了
					previous = tmp;
					tmp=tmp.right;
				}
				node.key = tmp.key;		//3. overwrite the 
										//reference of the key being deleted
				if(previous==node)		//4.要删除的这个节点左子树就一个节点。
					previous.left = tmp.left;	
				else previous.right = tmp.left;
			}
			if(p==root)
				root = node;
			else if(prev.left==p)
				prev.left=node;
			else prev.right=node;
		}
		else if(root !=null){
			System.out.println("key "+el+" is not in the tree!");
		}else System.out.println("the tree is empty");
	}

}
