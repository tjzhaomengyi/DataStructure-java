package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-06 21:15
 * @Description:
 */
public class OrderPrintMatrix_29 {
  public int[] spiralOrder(int[][] matrix) {
    if(matrix.length==0) return new int[0];
    int l=0,r=matrix[0].length-1;//列的范围
    int t=0,b=matrix.length-1;//行的范围
    int x=0;//保存结果的下标
    int[] res = new int[(r+1)*(b+1)];
    while(true){
      for(int i=l;i<=r;i++) {res[x]=matrix[t][i];x++;}//从左到右
      t=t+1;//上边界收缩
      if(t>b) break;

      for(int j=t;j<=b;j++) {res[x]=matrix[j][r];x++;}//从上往下
      r=r-1;//右边界压缩
      if(r<l) break;

      for(int i=r;i>=l;i--) {res[x]=matrix[b][i];x++;}//从右往左
      b=b-1;//下边界收缩
      if(b<t) break;

      for(int j=b;j>=t;j--) {res[x]=matrix[j][l];x++;}//从下往上
      l=l+1;
      if(l>r) break;
    }

    return res;
  }
}
