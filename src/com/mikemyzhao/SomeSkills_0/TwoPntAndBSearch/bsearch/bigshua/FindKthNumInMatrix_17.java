package com.mikemyzhao.TwoPntAndBSearch.bsearch.bigshua;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 14:29
 * @Description:在一个行有序，列有序，但是整体不保证有序的矩阵中找到第k小的数
 *
 */
public class FindKthNumInMatrix_17 {
  public static class Info{
    public int near;//离当前节点最近的值是多少，小于哈
    public int num;//小于mid出现的个数

    public Info(int n1, int n2){
      near = n1;
      num = n2;
    }
  }
  public static int kthSmallest(int[][] matrix, int K){
    int N = matrix.length;
    int M = matrix[0].length;
    int left = matrix[0][0];
    int right = matrix[N-1][M-1];
    int ans = 0;
    while(left <= right){
      int mid = left + ((right - left) >> 1);
      Info info = noMoreNum(matrix, mid);
      if(info.num < K){
        left = mid + 1;
      } else { //只要马上大于或者等于赶紧记录
        ans = info.near;
        right = mid - 1;
      }
    }
    return ans;
  }

  public static Info noMoreNum(int[][] matrix, int value){
    int near = Integer.MIN_VALUE;
    int num = 0;
    int N = matrix.length;
    int M = matrix[0].length;
    int row = 0;
    int col = M - 1;
    while(row < N && col >= 0){
      if(matrix[row][col] <= value){
        near = Math.max(near,matrix[row][col]);
        num += col + 1;//从右上角往下滑动的过程不断记录小于该value值的个数！
        row++;
      }else{
        col--;
      }
    }
    return new Info(near, num);
  }
}
