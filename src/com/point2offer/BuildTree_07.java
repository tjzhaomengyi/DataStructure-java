package com.point2offer;

import com.mikemyzhao.trees.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2021-10-04 16:54
 * @Description:给前序遍历和中序遍历，构建二叉树
 */
public class BuildTree_07 {
  //思路先找出根节点，在利用递归遍历二叉树
  public TreeNode buildTree(int[] pre, int[] in) {
    if(pre==null||in==null||pre.length==0||in.length==0){
      return null;
    }
    if(pre.length!=in.length){
      return null;
    }
    TreeNode root = new TreeNode(pre[0]);
    for(int i=0;i<pre.length;i++){
      if(pre[0]==in[i]){
        root.left = buildTree(Arrays.copyOfRange(pre,1,i+1),Arrays.copyOfRange(in,0,i));
        root.right=buildTree(Arrays.copyOfRange(pre,i+1,pre.length),Arrays.copyOfRange(in,i+1,in.length));
      }
    }
    return root;
  }

  public static void main(String[] args) {
    new BuildTree_07().buildTree(new int[]{3,9,20,15,7},new int[]{9,3,15,20,7});
  }
}
