package com.uestc.graph;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月15日上午11:57:04
 * 邻接矩阵存储图
 * 
 */
public class AdjacentMatrixGraph {
	public AdjacentMatrixGraph(){}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};//9个顶点,15条边
		AdjacentMatrixGraph aGraph = new AdjacentMatrixGraph(labels.length);
		for(String l: labels)
			aGraph.insertVertex(l);//插入这些节点
		//开始插入边共15条
		aGraph.insertEdge(0, 1, 1);
		aGraph.insertEdge(0, 5, 1);
		aGraph.insertEdge(1, 2, 1);
		aGraph.insertEdge(1, 8, 1);
		aGraph.insertEdge(1, 6, 1);
		aGraph.insertEdge(2, 8, 1);
		aGraph.insertEdge(2, 3, 1);
		aGraph.insertEdge(3, 7, 1);
		aGraph.insertEdge(3, 6, 1);
		aGraph.insertEdge(3, 4, 1);
		aGraph.insertEdge(4, 5, 1);
		aGraph.insertEdge(4, 7, 1);
		aGraph.insertEdge(5, 6, 1);
		aGraph.insertEdge(6, 7, 1);
		aGraph.insertEdge(3, 8, 1);
		
		
		System.out.println("深度优先遍历");
		aGraph.DFS();
		
		System.out.println("广度优先遍历");
		aGraph.BFS();
		
		
	}

	/**
	 * 邻接矩阵简介：
	 * 	表示节点，我们可以用一维数组来表示，但是表示一条边，无法用一维数组，可以采用二维数组，也就是矩阵的形式Matrix
	 * 	假设A是一个二维数组，那么A的一个元素aij,不仅体现了节点vi和vj具有关系，aij的值还可以表示权值的大小
	 * 
	 * 邻接表简介：
	 * 	说说邻接矩阵的缺点：邻接矩阵是一个n*n的矩阵，当边很少的时候是个系数矩阵，很浪费空间！
	 * 	邻接表：每一个节点后面所连接的节点都是他的邻接节点
	 * 
	 */
	
	private ArrayList vertexList; //存储点的数组 list
	private int[][] edges; //存储边
	private int numofEdges; //边的数目
	
	/** 邻接矩阵的深度优先
	 * 深度优先遍历内部方法
	 * 思路：深度优先，每次访问完当前节点后，首先访问当前节点的第一个邻接点
	 * 是具有回溯的，回溯防止漏下未访问的节点，回溯很容易实现，递归，递的过程是访问，归的时候就实现回溯
	 * @param visited
	 * @param i
	 */
	private void depthFirstSerch(boolean[] visited, int i){
		visited[i]=true; //设置该节点已经访问过
		System.out.println(vertexList.get(i)+" "); //访问该顶点
		
		for(int j=0; j< vertexList.size(); j++){//当前访问了i顶点
			if(edges[i][j]!=0 && !visited[j]){ 
				//edges[i][j]!=0表明i和j节点有连边，j从0开始，首先访问第一个，
				depthFirstSerch(visited, j);
			}
		}
	}
	
	/**深度优先有点类似于树的前序遍历，根左右，
	 * 邻接矩阵深度优先实现
	 */
	public void DFS(){
		boolean[] visited = new boolean[vertexList.size()];
		for(int i=0; i<vertexList.size(); i++){
			visited[i]=false;
		}
		
		for(int j=0; j<vertexList.size(); j++){
			if(!visited[j]){// 对于未访问过的顶点调用深度访问，若是连通图只会执行一次
				depthFirstSerch(visited, j);
			}
			System.out.println("DFS： j="+j);
		}
		
	}
	
	
	/**
	 * 广度优先遍历： 有点类似于树的层序遍历了
	 *  思路：访问完每一个节点，接着访问该节点的每一个邻接点
	 *  用队列来实现：遍历过程中，先被访问的顶点，其邻接点也先被访问。
	 * 
	 * 算法流程：初始顶点vi访问后进入队列，以后每从队列中删除一个元素，就依次访问该元素的未被访问的邻接点
	 * 并将这些邻接点如队列。这样当队列为空的时候，表明所有与初始vi想通的顶点全部被访问了。
	 */
	/**
	 * 广度优先搜索的私有函数
	 * @param visited
	 * @param i
	 */
	private void broadFS(boolean[] visited, int i){
		
		LinkedList<Integer> queue = new LinkedList<Integer>();
		System.out.println(vertexList.get(i)+ " "); //访问该节点
		visited[i]=true; //设置为true
		queue.addLast(i);//将这个访问的加入到队列中
		int u;//这个是刚刚出队的元素，要接着访问出队元素的所有邻接点
		while(!queue.isEmpty()){//只要队列不为空
			u = queue.removeFirst();//将队列中的元素出队列，并赋值给u；
			for(int j=0; j<vertexList.size(); j++){
				if(edges[u][j]!=0 && !visited[j]){
					visited[j]=true;
					System.out.println(vertexList.get(j)+" ");
					queue.addLast(j);//将这个入队
				}
			}
		}
	}
	
	/**
	 * 广度优先搜索，公开函数
	 */
	public void BFS(){
		boolean[] visited = new boolean[vertexList.size()];
		for(int i=0; i<vertexList.size(); i++){
			visited[i]=false;
		}//上面先初始化都没访问
		for(int j=0; j<vertexList.size(); j++){
			if(!visited[j]){//如果没有被访问，就访问
				broadFS(visited, j);
			}
			System.out.println("BFS j="+ j);
		}
		
	}
	
	/**
	 * 先构造一个图，然后测试深度优先和广度优先算法
	 */
	public AdjacentMatrixGraph createGraph(){
		String[] labels = {"A", "B", "C", "D", "E", "F", "G", "H", "I"};//9个顶点,15条边
		AdjacentMatrixGraph aGraph = new AdjacentMatrixGraph(labels.length);
		for(String l: labels)
			aGraph.insertVertex(l);//插入这些节点
		//开始插入边共15条
		aGraph.insertEdge(0, 1, 1);
		aGraph.insertEdge(0, 5, 1);
		aGraph.insertEdge(1, 2, 1);
		aGraph.insertEdge(1, 8, 1);
		aGraph.insertEdge(1, 6, 1);
		aGraph.insertEdge(2, 8, 1);
		aGraph.insertEdge(2, 3, 1);
		aGraph.insertEdge(3, 7, 1);
		aGraph.insertEdge(3, 6, 1);
		aGraph.insertEdge(3, 4, 1);
		aGraph.insertEdge(4, 5, 1);
		aGraph.insertEdge(4, 7, 1);
		aGraph.insertEdge(5, 6, 1);
		aGraph.insertEdge(6, 7, 1);
		aGraph.insertEdge(3, 8, 1);
		
		return aGraph;
	}
	
	
	
	//初始化邻接矩阵
	public AdjacentMatrixGraph(int n){
		edges = new int[n][n];
		vertexList = new ArrayList(n);
		numofEdges=0;
	}
	
	//得到index节点的第一个邻接节点下标
    public int getFirstNeighbor(int index) {
        for(int j=0;j<vertexList.size();j++) {
            if (edges[index][j]>0) {
                return j;
            }
        }
        return -1;
    }
	
  //根据前一个邻接结点的下标来取得下一个邻接结点
    public int getNextNeighbor(int v1,int v2) {
        for (int j=v2+1;j<vertexList.size();j++) {
            if (edges[v1][j]>0) {
                return j;
            }
        }
        return -1;
    }
    
	/**
	 * 在邻接矩阵中插入节点
	 * @param vertex
	 */
	@SuppressWarnings("unchecked")
	public void insertVertex(Object vertex){
		vertexList.add(vertexList.size(), vertex);
	}
	//插入边
    public void insertEdge(int v1,int v2,int weight) {
        edges[v1][v2]=weight;
        edges[v2][v1]=edges[v1][v2];//无向图，矩阵对称
        numofEdges++;
    }

    //删除边
    public void deleteEdge(int v1,int v2) {
        edges[v1][v2]=0;
        numofEdges--;
    }
	
	//得到结点的个数
    public int getNumOfVertex() {
        return vertexList.size();
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numofEdges;
    }
	
    //返回结点i的数据
    public Object getValueByIndex(int i) {
        return vertexList.get(i);
    }
    
    //返回v1,v2的权值
    public int getWeight(int v1,int v2) {
        return edges[v1][v2];
    }
}
