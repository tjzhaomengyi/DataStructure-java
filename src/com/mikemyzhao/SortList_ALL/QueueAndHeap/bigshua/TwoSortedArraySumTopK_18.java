package com.mikemyzhao.SortList_ALL.Queue.bigshua;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-03 10:17
 * @Description:求两个有序数组元素相加和的topK,数组里的数可以连续用
 */
public class TwoSortedArraySumTopK_18 {
  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    int N = sc.nextInt();
    int K = sc.nextInt();
    int[] arr1 = new int[N];
    int[] arr2 = new int[N];
    for (int i = 0; i < N; i++) {
      arr1[i] = sc.nextInt();
    }
    for (int i = 0; i < N; i++) {
      arr2[i] = sc.nextInt();
    }
    int[] topK = topKSum(arr1, arr2, K);
    for (int i = 0; i < K; i++) {
      System.out.print(topK[i] + " ");
    }
    System.out.println();
    sc.close();
  }
  //大根堆组织
  public static class Node{
    public int i1;
    public int i2;
    public int sum;

    public Node(int i, int j, int s){
      i1 = i;
      i2 = j;
      sum = s;
    }
  }

  public static class MaxHeapCompare implements Comparator<Node> {
    @Override
    public int compare(Node o1, Node o2) {
      return o2.sum - o1.sum;
    }
  }

  public static int[] topKSum(int[] arr1, int[] arr2, int K){
    if(arr1 == null || arr2 == null || K < 1){
      return null;
    }
    int N = arr1.length;
    int M = arr2.length;
    K = Math.min(K, N*M);//两个数组中的数可以重复使用，所以可能性就是M*N
    int[] ans = new int[K];
    int ansIndex = 0;
    PriorityQueue<Node> maxHeap = new PriorityQueue<>(new MaxHeapCompare());
    HashSet<Integer> set = new HashSet<>();//记录当前这两个位置时候加进来过，可以把组合想象成一个二维表
    int i1 = N - 1;
    int i2 = M - 1;

    //最后两个位置肯定最大
    maxHeap.add(new Node(i1, i2,arr1[i1] + arr2[i2]));//思路：这里起始是用二维表转成一维序列来处理的
    set.add(transIndex(i1, i2, M));
    while(ansIndex != K){//技巧：注意边界，从0开始用index++的方式找到k个就用这个方法！！
      Node curNode = maxHeap.poll();
      ans[ansIndex++] = curNode.sum;
      i1 = curNode.i1;
      i2 = curNode.i2;
      if(i1 - 1 >= 0 && !set.contains(transIndex(i1 - 1, i2, M))){
        set.add(transIndex(i1 - 1, i2, M));
        maxHeap.add(new Node(i1 - 1, i2, arr1[i1 - 1] + arr2[i2]));
      }
      if(i2 - 1 >= 0 && !set.contains(transIndex(i1, i2 - 1, M))){
        set.add(transIndex(i1 , i2 - 1, M));
        maxHeap.add(new Node(i1, i2 - 1, arr1[i1] + arr2[i2 - 1]));
      }
    }

    return ans;
  }

  public static int transIndex(int i1, int i2, int M){
    return i1 * M + i2;
  }
}
