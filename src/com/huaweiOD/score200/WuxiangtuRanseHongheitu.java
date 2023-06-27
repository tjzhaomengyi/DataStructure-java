package com.huaweiOD.score200;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-31 10:27
 * @Description: todo:这题答案没看懂,解法是什么意思？
 */
public class
WuxiangtuRanseHongheitu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int numVertices = in.nextInt();
      int numEdges = in.nextInt();

      int maxEdges = numVertices * (numVertices - 1) / 2;
      if(numEdges <= 0 || numEdges > maxEdges){
        return;
      }

      List<int[]> edgeList = new ArrayList<>();
      for(int i = 0; i < numEdges; i++){
        edgeList.add(new int[]{in.nextInt(), in.nextInt()});
      }

      int total = 1 << numVertices;
      int res = total;
      BitSet vertexSubset = new BitSet(numVertices); //技巧:使用BitSet统计每个点的可达其他点的数量
      for(int i = 0; i < total; i++){
        vertexSubset.clear();
        int temp = i;
        for(int j = 0; j < numVertices; j++){
          if((temp & 1) == 1){//如果是奇数的
            vertexSubset.set(j);//把bitset设置为1
          }
          temp >>= 1;//temp除以2
        }
        for(int[] edge : edgeList){
          if(!vertexSubset.get(edge[0]) && !vertexSubset.get(edge[1])){
            res--;
            break;
          }
        }
      }
      System.out.println(res);
    }
  }
}
