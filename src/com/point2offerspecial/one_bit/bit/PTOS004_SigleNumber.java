package com.point2offerspecial.one_bit.bit;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-13 5:16 下午
 * @Description:
 */
public class PTOS004_SigleNumber {
  public int singleNumber(int[] nums) {
    int[] bitCnts = new int[32];
    for(int num : nums){
      for(int i = 0; i < 32; i++){
        bitCnts[i] += (num >> i) & 1;
      }
    }
    int ans = 0;
    for(int i = 0; i < 32; i++){
      if(bitCnts[i] % 3 == 1){
        ans = ans | 1 << i;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    int[] nums = new int[]{2,2,2,3};
    int ans = new PTOS004_SigleNumber().singleNumber(nums);
    System.out.println(ans);
  }
}
