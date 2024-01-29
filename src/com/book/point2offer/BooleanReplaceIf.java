package com.book.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-12 17:11
 * @Description:从1到n求和，不适用if，for这些，使用boolean进行短路操作
 */
public class BooleanReplaceIf {
  int res =0;
  public int sumNums(int n){
    boolean x=n>1 && sumNums(n-1)>0;
    res = res+n;
    return res;
  }

  public static void main(String[] args) {
    new BooleanReplaceIf().sumNums(55);
  }
}
