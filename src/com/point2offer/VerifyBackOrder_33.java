package com.point2offer;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-07 13:36
 * @Description:给定数组判断是否是二叉搜索树的后续遍历,注意这个是给数组作为参数，
 */
public class VerifyBackOrder_33 {

  //todo:值得重刷，使用递归
  public boolean verifyPostorder(int[] postorder) {
    return recur(postorder,0,postorder.length-1);
  }

  /**整体思路把握住后续遍历:L->R->Root,和搜索树左子树<根节点<右子树
   * 递归思路：1、当i>=j的时候，说明此子树的节点数量小于1，无需判断了，直接返回true
  2、递推工作：(1)划分左右子树遍历后续遍历的[i,j]元素，寻找到第一个大于根节点的节点，记为m,[num[j]肯定为根节点，m为右子树第一个]
    此时，划分左子树区间为[i,m-1],右侧子树区间就是[m,j-1]
    (2)使用下标p辅助判断到m左侧/右侧是否都是小于/大于根节点nums[j]
      判断左子树是否为二叉搜索树，左子树区间保证[i,m-1]元素都小于nums[j];
      判断右子树是否为二叉搜索树，右子树区间保证[m,j-1]元素都大于nums[j];
      当遇到p滑动到j的时候表示完成，最终进行判断 p==j&&recur(postorder,i,m-1)【左子树部分】&&recur(postorder,m,j-1)
   **/
  boolean recur(int[] postorder,int i,int j){
    if(i>=j) return true;//递归思路1
    int p =i;
    while(postorder[p]<postorder[j]){
      p++;//此时都是小于根节点的左子树
    }
    int m=p;
    while(postorder[p]>postorder[j]){
      p++;//此时都是大于根节点的右子树
    }
    return p==j&&recur(postorder,i,m-1)&&recur(postorder,m,j-1);
  }

}
