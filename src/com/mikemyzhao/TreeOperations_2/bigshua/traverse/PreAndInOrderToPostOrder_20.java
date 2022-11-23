package com.mikemyzhao.TreeOperations_2.bigshua.traverse;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-03 17:05
 * @Description:给出前序数组和中序数组求后续数组，元素值不同
 */
public class PreAndMidOrderToPostOrder_20 {
  //前序：abdecf
  //中序：dbeacf
  //后续：左右中
  public static int[] getPostOrder(int[] preOrder, int[] midOrder){
    if(preOrder == null || midOrder == null || preOrder.length != midOrder.length){
      return null;
    }
    int[] res = process(preOrder, 0, preOrder.length - 1, midOrder, 0, midOrder.length - 1);
  }

  public static int[] process
}
