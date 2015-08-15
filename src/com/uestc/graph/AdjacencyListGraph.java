package com.uestc.graph;

import java.util.LinkedList;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月15日下午3:08:17
 * 
 * 邻接表表示图
 * 邻接表思路：同时利用数组和矩阵的优点保存图
 * 数组存取方便，用于保存顶点(是个结构)，链表链接方便不会浪费空间用于链接这个顶点的邻接点。
 * 
 */
public class AdjacencyListGraph {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[]labels = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};//9个顶点,15条边
		String[][] edges = {
				{"A", "B"},{"A", "F"},{"B", "C"},{"B", "I"},{"B", "G"},
				{"C", "I"},{"C", "D"},{"D", "I"},{"D", "G"},{"D", "H"},
				{"D", "E"},{"E", "H"},{"E", "F"},{"F", "G"},{"G", "H"},
		};
		
		/**
		 * 这样构造出来的邻接表是：
		 * 0, A->5->1
		 * 1, B->6->8->2->0
		 * 2, C->3->8->1
		 * 3, D->4->7->6->8->2
		 * 4, E->5->7->3
		 * 5, F->6->4->0
		 * 6, G->7->5->3->1
		 * 7, H->6->4->3
		 * 8, I->3->2->1
		 * 
		 */
		AdjacencyListGraph alGraph = new AdjacencyListGraph(labels, edges);
		System.out.println("深度优先："); //根据上面的邻接表，这个是正确的深度优先:A F G H E D I C B
		alGraph.DFS();
		
		System.out.println("广度优先");	//根据上面的邻接表,这个是正确的广度优先：A F B G E I C H D,
		//上面广度优先的也没问题，因为是入栈的顺序问题。
		alGraph.BFS();
		
	}

	
	/**
	 * 图的邻接表展示，邻接表：数组和链表同时表示图，一个表示顶点信息，一个表示边或弧信息
	 */
	int numVertex, numEdge; //顶点数目, 边数目
	VertexNode[]  vertex; //图的顶点数组
	boolean[] visited; 	//记录顶点是否被遍历过
	
	public AdjacencyListGraph(){}
	
	public AdjacencyListGraph(String[] v, String[][] edges){
		if(v==null)
			return;
		//初始化顶点数和边数
		int vlen = v.length;
		int vedge = edges.length;
		
		//下面初始化顶点
		vertex = new VertexNode[vlen];//
		for(int i=0; i<vlen; i++){
			vertex[i] = new VertexNode();
			vertex[i].data = v[i];
			vertex[i].firstEdge = null;
		}
		
		//初始化边
		for(int j=0; j<vedge; j++){
			//读取边的起始顶点和结束顶点
			String c1 = edges[j][0];
			String c2 = edges[j][1];
			//得到对应的顶点在数组中从存储下标
			int p1 = getPosition(c1);
			int p2 = getPosition(c2);
			
			//初始化一个边——对象
			EdgeNode node1= new EdgeNode();
			node1.adjvexIndex = p2;//这个边的链接下标到了p2
			
			//将这个边链接到 p1所在链表的末尾
			if(vertex[p1].firstEdge==null)
				vertex[p1].firstEdge=node1;
			else{//还是用头插法吧
				node1.next = vertex[p1].firstEdge;
				vertex[p1].firstEdge = node1;
			}
				
			//因为是无向图, 要在p2为数组顶点的哪里也要插入
			EdgeNode node2 = new EdgeNode();
			node2.adjvexIndex = p1; //这个边链接到了下标p1
			
			if(vertex[p2].firstEdge==null)
				vertex[p2].firstEdge = node2;
			else{//还是头插法
				node2.next = vertex[p2].firstEdge; 
				vertex[p2].firstEdge = node2;
			}
		}
	}
	
	
	/**
	 * 下面是邻接表的深度优先和广度优先
	 * 思路是一样：深度优先，首先访问当前节点的第一个邻接点，每次都递归访问第一个，因此有回溯，查看当时当前节点的其他邻接点没有访问的话继续访问
	 */
	
	private void dfsInside(boolean[] visited, int i){
		visited[i]=true;
		System.out.print(vertex[i].data +" ");
		//访问了当前节点，就去访问当前节点的第一个邻接点
		//首先要得到第一个邻接点是哪个
		EdgeNode p = vertex[i].firstEdge;// 		这个像是邻接矩阵中for循环的int j=0后，第一个edge[i][j]==1的
		while(p!=null){
			//如果这个p的下标所代表的   邻接点没有被访问
			if(!visited[p.adjvexIndex]){  //		这个像是判断没有被访问过
				dfsInside(visited, p.adjvexIndex);	//一样的递归方法
			}
			p = p.next;		//一样的后移
		}
	}
	
	public void DFS(){
		boolean[] visited = new boolean[vertex.length];
		for(int i=0; i<vertex.length; i++){
			visited[i]=false;
		}
		
		for(int j=0; j<vertex.length; j++){
			if(!visited[j])//对没有访问过的调用，若是连通图，只会调用一次
				dfsInside(visited, j);
		}
	}
	
	/**
	 * 广度优先搜索，还是使用队列
	 * 思路一样：当前节点访问了，出队，把当前节点的所有邻接点访问并入队
	 */
	
	private void bfsInside(boolean[] visited, int i){
		LinkedList<Integer> queue = new LinkedList<Integer>();
		visited[i]=true;
		System.out.print(vertex[i].data +" ");
		queue.addLast(i);
		
		while(!queue.isEmpty()){
			int c = queue.removeFirst();//当前的第一个出队
			EdgeNode p = vertex[c].firstEdge;
			while(p!=null){
				if(!visited[p.adjvexIndex]){
					visited[p.adjvexIndex]=true;
					System.out.print(vertex[p.adjvexIndex].data +" ");
					queue.addLast(p.adjvexIndex);
				}
				p=p.next;
			}
		}
	}
	
	public void BFS(){
		boolean[] visited = new boolean[vertex.length];
		for(int i=0; i<vertex.length; i++){
			visited[i]=false;
		}
		
		for(int j=0; j<vertex.length; j++){
			if(!visited[j])
				bfsInside(visited, j);
		}
		
	}
	
	
	
	/**
	 * 返回顶点字符串s在顶点数组的下标
	 * @param s
	 * @return index
	 */
	private int getPosition(String s){
		for(int i=0; i<vertex.length; i++){
			if(vertex[i].data.equals(s))
				return i;
		}
		return -1;
	}
	
	
}

/**
 * 邻接表的边节点， 作为链表元素
 * @author checkermu
 *
 */
class EdgeNode{//边节点，作为链表元素
	int adjvexIndex; //邻接点域，存储该顶点对应的数组元素下标
	EdgeNode next;   //链域，指向下一个邻接点
}

/**
 * 邻接表的顶点节点，作为顶点数组的 元素
 * @author checkermu
 *
 */
class VertexNode{//顶点节点，作为数组元素
	String data;
	EdgeNode firstEdge;// 顶点的第一个邻接表指向的链表元素
}