package com.uestc.graph;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月15日下午9:59:07
 * 迪杰斯特拉最短路径算法
 * 
 * 思想：贪心算法加动态规划
 * 从源点到其他各个顶点的最短路径依次求出
 * 
 * 
 */
public class Dijkstra {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Dijkstra djs = new Dijkstra();
		AdjacentMatrixGraph aGraph = djs.createGraph();
		djs.shortestPath_dijkstra(aGraph, 0);
	}

	private static final int INFINITY = 65536;
	
	/**
	 * 迪杰斯特拉算法
	 * @param aGraph参数图，起始就是使用的图的权重矩阵，edges
	 * @param start  起始源点
	 */
	public void shortestPath_dijkstra(AdjacentMatrixGraph aGraph, int start){
		int n = aGraph.edges.length; //一共有多少个顶点
		int[] pathWeight = new int[n]; //源点到每个节点的最短路径值 		——大话数据结构中的 *D
		int[] path = new int[n]; //存储源点到其他各点最短路径的表示	——大话数据结构中的 *P,  下标和内容来表示东西，
		
		int[] visited = new int[n]; //标记当前该点的最短路径是否已经求出，1的话表示已经求出
		
		for(int i=0; i<n; i++){
			visited[i]=0;	//开始认为所有的都没有求出
			pathWeight[i] = aGraph.edges[start][i];	//将与start有连线的顶点加上权值
			path[i]=0;  //path数组是用内容和值来表示东西，这里表示的是顶点是下标的前驱是对应下标的内容，比如顶点n-1的前驱是p[n-1]
		}
		
		//初始化，第一个顶点到自己认为已经求出
		pathWeight[start]=0;
		visited[start]=1;
		int i, j, k, min;
		//主for循环，每次求得start到某个i顶点的最短路径
		for( i=1; i<n; i++){
			min = INFINITY;
			k=-1;
			
			for(j=0; j<n; j++){ //寻找距离start最近的顶点
				if(visited[j]!=1 && pathWeight[j]<min){
					k=j;
					min=pathWeight[j];
				}
			}
			
			visited[k]=1;	//将目前找到的最近的顶点置为1
			//这个for循环目的是修正当前最短路径及距离
			for(j=0; j<n; j++){
				/*如果经过k顶点的路径比现在这条路径的长度短的话，比如开始start到其他顶点可能没有边，
				所以距离是无穷大，现在经过k顶点，可能会链接到其他顶点而且距离小于无穷大*/
				if(visited[j]!=1 && (min+aGraph.edges[k][j]<pathWeight[j])){
					//说明找到了更短的路径，修改path和pathWeight
					pathWeight[j] = min+aGraph.edges[k][j]; //修改当前路径长度
					//因为start通过k链接到了现在的顶点j,是的start到j的距离变小了
					//所以明显j的前驱是k
					path[j]=k;
				}
			}
			
		}
		
		System.out.println(Arrays.toString(pathWeight));
		System.out.println(Arrays.toString(path));
		System.out.println(Arrays.toString(visited));
	}
	
	
	/**
	 * 先构造一个图
	 */
	public AdjacentMatrixGraph createGraph(){
		String[] labels = {"V0", "V1", "V2", "V3", "V4", "V5", "V6", "V7", "V8"};//9个顶点,15条边
		AdjacentMatrixGraph aGraph = new AdjacentMatrixGraph(labels.length);
		for(String l: labels)
			aGraph.insertVertex(l);//插入这些节点
		//开始插入边共15条
		aGraph.insertEdge(0, 1, 1);
		aGraph.insertEdge(0, 2, 5);
		aGraph.insertEdge(1, 2, 3);
		aGraph.insertEdge(1, 3, 7);
		aGraph.insertEdge(1, 4, 5);
		aGraph.insertEdge(2, 4, 1);
		aGraph.insertEdge(2, 5, 7);
		aGraph.insertEdge(3, 4, 2);
		aGraph.insertEdge(3, 6, 3);
		aGraph.insertEdge(4, 5, 3);
		aGraph.insertEdge(4, 6, 6);
		aGraph.insertEdge(4, 7, 9);
		aGraph.insertEdge(5, 7, 5);
		aGraph.insertEdge(6, 7, 2);
		aGraph.insertEdge(6, 8, 7);
		aGraph.insertEdge(7, 8, 4);
		
		for(int i=0; i<labels.length; i++){
			for(int j=0; j<labels.length; j++){
				if(aGraph.edges[i][j]==0){
					aGraph.edges[i][j]=INFINITY;
				}
			}
		}
		return aGraph;
	}
	
}
