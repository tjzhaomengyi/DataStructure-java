package com.mikemyzhao.TreeOperations_2.bigshua.traverse;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-03 17:05
 * @Description:给出前序数组和中序数组求后续数组，元素值不同
 * 技巧：使用一个map集合记录中序遍历的数组
 */
public class PreAndInOrderToPostOrder_20 {
  //前序：abdecf
  //中序：dbeacf
  //后续：左右中
  public static int[] getPostOrder(int[] preOrder, int[] inOrder){
    if(preOrder == null || inOrder == null || preOrder.length != inOrder.length){
      return null;
    }
    int N = preOrder.length;
    HashMap<Integer, Integer> inMap = new HashMap<>();
    for(int i = 0; i < N; i++){
      inMap.put(inOrder[i], i);
    }
    int[] posOrder = new int[N];
    process(preOrder, 0, N - 1, inOrder, 0, N - 1, posOrder, 0, N - 1, inMap);
    return posOrder;
  }

  public static void process(int[] pre, int L1, int R1, int[] in, int L2, int R2, int[] pos, int L3, int R3, HashMap<Integer, Integer> inMap){
    if(L1 > R1){
      return;
    }
    if(L1 == R1){//pre区间只有1个说明L2和R2也只有一个，直接填入pos[L3]
      pos[L3] = pre[L1];
    } else {
      pos[R3] = pre[L1];
      //找到根节点在中序遍历中的位置
      int index = inMap.get(pre[L1]);//搞前序的左树：pre+1,
      int leftLen = index - L2;//左子树长度
      int rightLen = R2 - index;
      process(pre, L1 + 1, L1 + index - L2, in, L2, index - 1,pos, L3, L3 + index - L2 - 1,inMap);//注意l3的下标
      process(pre, L1 + index - L2 + 1, R1, in, index + 1, R2, pos, L3 + index - L2 , R3 - 1, inMap);
    }
  }
}
