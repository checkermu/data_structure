package com.uestc.graph;

import java.util.Arrays;

/**
 * author: checkermu email:guotaoleng@163.com
 * time: 2015年8月15日下午7:35:16
 * 邻接矩阵的最小生成树
 * 
 * Prim 算法，思想：一个顶点集合V,一个是我们需要的集合U
 * 从V中往U中选择顶点加入：
 * 1、先取V0加入到U中
 * 2、V0的邻接点中选取Vn，使得(v0, vn)这条边权最小，将Vn加入到U中
 * 3、从（V-U）的集合中选取与V0,Vn邻接的权最小的边加入到U中
 * 4、重复3步骤。
 */
public class Prim {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Prim prim = new Prim();
		AdjacentMatrixGraph aGraph = prim.createGraph();
		
		prim.minSpanTree_prim(aGraph);
	}

	
	/**
	 * 最小生成树
	 * @param aGraph
	 */
	public void minSpanTree_prim(AdjacentMatrixGraph aGraph){
		int n = aGraph.vertexList.size();
		int[] adjvex = new int[n];/*保存相关顶点下标*/
		int[] lowcost = new int[n];	//保存相关顶点间的权值
		lowcost[0]=0; //初始化第一个顶点的权值为0，即V0加入生成树
		//lowcost的值为0，就表明此下标的顶点已经加入生成树
		
		adjvex[0]=0;	//初始化第一个顶点的下标为0
		int j,k, min;
		for(int i=1; i<n; i++){ //循环除了下标为0的所有顶点
			lowcost[i]=aGraph.edges[0][i]; //将V0与之有边的全部存入
			adjvex[i]=0; //初始化都是v0的下标
		}
		
		for(int i=1; i<n; i++){
			min = 65536;//最小权值初始化
			j=1; k=0;
			
			//这个while的左右是寻找数组lowcost中的最小的数
			while(j<n){//循环全部顶点
				if(lowcost[j]!=0 && lowcost[j]<min){
					min = lowcost[j];	//小则成为当前最小的权值
					k=j;	//记录下来对应的顶点
				}
				j++;
			}
			
			System.out.println(adjvex[k]+" "+k);
			
			lowcost[k]=0; //将当前顶点的权值设置为0，表示此顶点已经完成任务
			
			//下面这个for循环是更新整个lowcost
			for(j=1; j<n; j++){//循环所有顶点
				//若下标为k的顶点各边权值小于此前这些顶点而么有加入生成树权值
				if(lowcost[j]!=0 && aGraph.edges[k][j]<lowcost[j]){
					lowcost[j]=aGraph.edges[k][j];
					adjvex[j]=k;	//将下标为k的顶点存入adjvex
				}
			}
		}
		
		System.out.println(Arrays.toString(adjvex));
		System.out.println(Arrays.toString(lowcost));
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
		aGraph.insertEdge(0, 1, 10);
		aGraph.insertEdge(0, 5, 11);
		aGraph.insertEdge(1, 2, 18);
		aGraph.insertEdge(1, 8, 12);
		aGraph.insertEdge(1, 6, 16);
		aGraph.insertEdge(2, 8, 8);
		aGraph.insertEdge(2, 3, 22);
		aGraph.insertEdge(3, 7, 16);
		aGraph.insertEdge(3, 6, 24);
		aGraph.insertEdge(3, 4, 20);
		aGraph.insertEdge(4, 5, 26);
		aGraph.insertEdge(4, 7, 7);
		aGraph.insertEdge(5, 6, 17);
		aGraph.insertEdge(6, 7, 19);
		aGraph.insertEdge(3, 8, 21);
		
		for(int i=0; i<labels.length; i++){
			for(int j=0; j<labels.length; j++){
				if(aGraph.edges[i][j]==0){
					aGraph.edges[i][j]=65535;
				}
			}
		}
		return aGraph;
	}
}
