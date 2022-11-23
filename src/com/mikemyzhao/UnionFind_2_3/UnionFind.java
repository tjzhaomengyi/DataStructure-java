package com.mikemyzhao.UnionFind_2_3;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 16:05
 * @Description:就是点与点顺序相连，然后互相有连通性，有关联性
 */
public class UnionFind {
  /**属性**/
  private int count;//记录连通分量
  private int[] parent;//节点x的父节点是parent[x]
  //新增一个数组记录树的重置
  private int[] size;
  /**构造函数**/
  public UnionFind(int n){
    //一开始互相不连通
    this.count = n;
    //父节点初始化指向自己
    parent = new int[n];
    //最初每棵树只有一个节点
    //重量应该初始化为1
    size=new int[n];
    for(int i=0;i<n;i++){
      parent[i]=i;
      size[i]=1;
    }
  }
  /**连通api，连通的性质：自反性(自己和自己相连)、对称性、传递性**/
  //将p和q连通
  public void union(int p,int q){
    int rootP = find(p);
    int rootQ = find(q);
    if(rootP==rootQ){
      return;
    }
    //把树处理平衡,将两颗树合并成一颗
    if(size[rootP]>size[rootQ]){
      parent[rootQ] = rootP;
      size[rootP]+=size[rootQ];
    }else{
      parent[rootP] = rootQ;
      size[rootQ]+=size[rootP];
    }
//    将两棵树合并为一颗
//    parent[rootP]=rootQ;
    count--;
  }
  //判断p和q是否连通
  public boolean connected(int p,int q){
    int rootP= find(p);
    int rootQ = find(q);
    return rootP==rootQ;
  }
  //返回图中有多少个连通分量
  public int count(){
    return count;
  }

  //返回某个节点x的根节点
  private int find(int x){
    while(parent[x]!=x){
      //进行路径压缩
      parent[x]=parent[parent[x]];
      x=parent[x];
    }
    return x;
  }
}
