package com.hotinterview.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-30 5:11 下午
 * @Description:
 */

//转成求数组的最长递增子序列:
// 例子:3 2 1 2 3 0 4 6  2 7，ends数组表示i+1长度最长子序列的最后一个值的最小值
// (1)dp[0] = 1, ends[0] = 3; ends=[3]
// (2) dp[1] = 1, ends[0] = 2; ends=[2]
// (3) dp[2] = 1，end[0] = 1; ends=[1]
// (4) dp[3] = 2 end[1] = 2; ends = [1,2]
// (5) dp[4] = 3 end[2] = 3; ends = [1,2,3]
// (6) dp[5] = 1 end[0] = 0;长度为1的最小递增子序列的末尾最小值改为1；[0,2,3]
//（7）dp[6] = 4  end[3] = 4; [0,2,3,4]
// (8) dp[7] = 5  end[4] = 6;[0,2,3,4,6]
// (9) dp[8] = 2   end不改 ;
// (10)dp[9] = 6   end[5] = 7

public class Code334_IncreaseTriplelist {

  public boolean increasingTriplet(int[] nums) {
    if(nums.length < 3 || nums == null) return false;
    int N = nums.length;
    int[] dp = new int[N];////记录到当前位置的最大子序列长度
    int[] ends = new int[N];//记录为i+1长度的递增子序列的最后一个值的大小
    dp[0] = 1;
    ends[0] = nums[0];
    //思路：这里用二分的思路非常巧妙，（1）首先二分查找的最终结果是l和r指针相等，一定的
    // （2）如果在ends有效区中，l就可以直接用当前数值进行更新,因为如果找到有效区某个位置，说明比当前位置更小或者相等
    // (3) 如果ends在有效期外，那么就直接m+1超出了有效区
    // 太难了~直接记住例子吧：就是思路（2） （3）这道题在二分上非常巧妙的用法。
    int right = 0; //right表示有效区，ends只有0到right是有效区，right右边是无效区
    int l = 0;
    int r = 0;
    int m = 0;
    int max = 1;
    for(int i = 1; i < nums.length; i++){
      l = 0;
      r = right;//让r指向right有效区最后位置
      while(l <= r){
        m = (l + r) / 2;
        if(nums[i] > ends[m]){
          l = m + 1;//太难了~直接记住例子吧 如果在有效区间没有，就是right+1！！！！
        } else {//等于和小于往左边找
          r = m - 1;
        }
      }
      //太难了~直接记住例子吧 二分的最后肯定是l=r,所以比较right 和 l 谁大
      right = Math.max(right, l);
      ends[l] = nums[i];;//这个时候如果l超出有效区，那么自动就是大1位的。如果是在有效期就更新。
      dp[i] = l + 1;//修改当前下标的最大递增子序列长度。
      max = Math.max(max, l + 1);//记录最长子序列
    }
    return max >= 3 ;

  }
}
