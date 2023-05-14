package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-12 13:24
 * @Description: 技巧：套用伪蓄水池问题的通用解法
 */
public class TianranXushuichiZuiyoujie {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(" ");
      int[] nums = new int[str.length];
      for (int i = 0; i < nums.length; i++) {
        nums[i] = Integer.valueOf(str[i]);
      }
      int[] ans = trap(nums);
      System.out.println(ans[0] == 0 ? 0 : ans[1] + " " + ans[2] + ":" + ans[0]);

    }
  }

  public static int[] trap(int[] nums) {
    int n = nums.length;
    int L = 1;
    int R = n - 2;
    int leftMax = nums[0];
    int rightMax = nums[n - 1];
    int water = 0;
    int tmp_water = 0;
    int record_L = -1;
    int record_R = - 1;
    //技巧：这个是在全量蓄水池问题TrappingRainWater上修改的
    while (L <= R) {
      if(tmp_water == 0){
        record_L = L - 1;
        record_R = R + 1;
      }
      if (leftMax <= rightMax) {
        if (leftMax - nums[L] > 0) {//技巧：说明当前可以蓄水，加载tmp_water里面
          tmp_water += leftMax - nums[L];
          water = tmp_water > water ? tmp_water : water;
        } else {
          tmp_water = 0;
        }
        leftMax = Math.max(leftMax, nums[L++]);//技巧：看谁更高，谁后面就能帮着蓄水
      } else {
        if (rightMax - nums[R] > 0) {
          tmp_water += rightMax - nums[R];
          water = tmp_water > water ? tmp_water : water;
          record_R = R;
        } else {
          tmp_water = 0;
        }
        rightMax = Math.max(rightMax, nums[R--]);
      }
    }
    return new int[]{water, record_L, record_R};
  }
}
