package com.mikemyzhao.DP.DP.bigshua.complexDP;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-31 11:05
 * @Description:找出3个不重叠的子数组，让三个子数组的累加和最大
 * 思路：固定中间k个长度的和，然后求左右两侧的子数组和，然后再求三个合在一起的
 * 这个思路比较讨巧
 */
public class MaxSum3_k_lens_SubArrays_NoOverlap_22 {
  public static int[] maxSumOfThreeSubarrays(int[] arr, int k){
    int N = arr.length;
    //子数组必须以i位置的数结尾，长度为K，累加和最大是多少
    //初始的时候，数组中开始k长度的累加和
    int sum = 0;
    int[] help = new int[N];//记录当前i位置，向左k长度的子数组累加和
    int[] left = new int[N];//记录当前i位置，向左k长度的子树组起始位置
    left[k - 1] = 0;
    for(int i = 0; i < k; i++){
      sum += arr[i];
    }
    //help数组存每个位置上,以[i]结尾，长度为k的累加和
    help[0] = sum;
    int max = sum;

    //这个是从左往右算每个位置长度为3的最大累加和
    for(int i = k ; i < N; i++){
      sum = sum + arr[i] - arr[i - k];
      help[i - k + 1] = sum;//当前i位置长度为k的累加和
      left[i] = left[i - 1];//todo:想想：先记录当前位置最左侧的累加和位置为前一个位置，因为前一个位置的最左边肯定是当前长度为k累加和最大的！
      if(sum > max){
        //如果此时的sum大于了max，说明当前节点长度为k的时候这段累加和最长,更新
        left[i] = i - k + 1;
        max = sum;
      }
    }

    //现在从右往左再来一遍
    for(int i = N - 1; i > N - k; i--){
      sum += arr[i];
    }
    max = sum;
    int[] right = new int[N];//记录从右往左的三个的起始位置
    right[N - k] = N - k ;//右侧位置，todo:右边起始位置的
    for(int i = N - k - 1; i >= 0; i--){//todo:注意这个下标的写法老操蛋了，先让他挪动
      sum = sum + arr[i] - arr[i + k];
      right[i] = right[i + 1];
      if(sum >= max){
        max = sum;
        right[i] = i;
      }
    }

    int a = 0;
    int b = 0;
    int c = 0;
    max = 0;
    for(int i = k; i < N - k - k + 1; i++){
      int part1 = help[left[i - 1]];
      int part2 = help[i];
      int part3 = help[right[i + k]];
      if(part1 + part2 + part3 > max){
        max = part1 + part2 + part3;
        a = left[i - 1];
        b = i;
        c = right[i + k];
      }
    }
  return new int[]{a, b, c};
  }
}
