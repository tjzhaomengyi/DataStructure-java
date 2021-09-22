package com.mikemyzhao.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 10:45
 * @Description:下雨问题，用柱子蓄水，最多蓄水多少
 * 几种办法，暴力，动态规划和双指针
 */
public class RainDrop {
  int drop(int[] height){
    //使用两个数组充当动态规划的保存
    if(height.length==0)return 0;
    int n = height.length;
    int ans = 0;
    int[] lmax = new int[n];
    int[] rmax = new int[n];
    //两个动态规划分别从两边走
    lmax[0] = height[0];
    rmax[n-1] = height[n-1];
    //从左向右计算l_max
    for(int i=1;i<n;i++){
      lmax[i] = Math.max(height[i],lmax[i-1]);
    }
    for(int j=n-2;j>=0;j--){
      rmax[j] = Math.max(height[j],rmax[j+1]);
    }
    for(int i=1;i<n-1;i++){
      ans=ans+Math.min(lmax[i],rmax[i])-height[i];//左右两边的最低高度，再减去自己本身的高度
    }
    return ans;
  }

  /**双指针边走边算，不需要备忘录**/
  int dropTwoPnt(int[] height){
    //lmax和rmax表示height[0..left]的最高柱子高度
    if(height.length==0) return 0;
    int n  = height.length;
    int left =0,right=n-1;
    int ans = 0;
    int l_max = height[0];
    int r_max = height[n-1];
    while (left<=right){
      l_max = Math.max(l_max,height[left]);
      r_max = Math.max(r_max,height[right]);
      if(l_max<r_max){
        ans = ans+(l_max-height[left]);//注意：这个减法，用最高的柱子高度减去当前柱子的高度，就是当前柱子的蓄水高度
        left++;
      }else{
        ans = ans+(r_max-height[right]);
        right--;
      }
    }
    return ans;
  }

  public static void main(String[] args) {
    System.out.println(new RainDrop().drop(new int[]{4,2,0,3,2,5}));
  }

}
