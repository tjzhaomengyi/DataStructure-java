package com.mikemyzhao.data_structure_design;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-18 22:42
 * @Description:
 */
public class LC_0384_RandomArray {
  class Solution {
    private int[] origin;
    private int[] shuffle;
    private int N;
    public Solution(int[] nums) {
      origin = nums;
      N = nums.length;
      shuffle = new int[N];
      for(int i = 0; i < N; i++){
        shuffle[i] = origin[i];
      }
    }

    public int[] reset() {
      return origin;
    }

    //技巧:给出一个完全随机的数组，随机出来的数肯定不在原来位置
    public int[] shuffle() {
      for(int i = N - 1; i >= 0; i--){
        //数学结论:如何保证最好随机，就是我当前数组多长，我就安排多长的随机性放在这个位置
        int r = (int) (Math.random() * (i + 1));
        int tmp = shuffle[r];
        shuffle[r] = shuffle[i];
        shuffle[i] = tmp;
      }
      return shuffle;
    }
  }
}
