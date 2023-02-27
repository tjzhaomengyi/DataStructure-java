package com.hotinterview.zhijiegan;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-20 5:08 下午
 * @Description:
 */
public class Code0268_MissingNumber {
  //儿童趣味益智：使用异或的性质XOR，如果是0表示都是奇数个，XOR的结果就是缺的那个数
  public int missingNumber(int[] nums) {
    int N = nums.length;
    int XOR = 0;
    for(int i = 0; i < N; i++){
      XOR = XOR ^ nums[i];
    }
    for(int i = 0; i < N + 1;i++){
      XOR = XOR ^ i;
    }
    return XOR;
  }
}
