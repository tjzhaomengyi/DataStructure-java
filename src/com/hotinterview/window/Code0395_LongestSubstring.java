package com.hotinterview.window;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-06 6:22 下午
 * @Description:
 */
public class Code0395_LongestSubstring {
  //思路：题目要求最长的字符串中，每个字符都 >= k个，
  public int longestSubstring(String s, int k) {
    char[] str = s.toCharArray();
    int N = str.length;
    int max = 0;
    for(int require = 1; require<=26; require++){
      int[] count = new int[26];
      int collect = 0; //目前窗口收集了几种字符
      int satify = 0;//目前窗口出现次数>=k
      int R = -1;//窗口右边界
      for(int L = 0; L < N; L++){//左边界每个位置都动一下
        //下面分两步：（1）计算collect 和 satify
        //思路：窗口的特性：窗口变大字符种类会变多或者不变,太难了~直接记住例子吧：就是后面这个条件
        while(R + 1 < N && !(collect == require && count[str[R + 1] - 'a'] == 0)) {//如果收集的种类collect等于需要的字母种类数量require，并且新来的是没出现过得
          R++;
          if(count[str[R] - 'a'] == 0){
            collect++;
          }
          count[str[R] - 'a']++;
          if(count[str[R] - 'a'] == k){
            satify++;
          }
          if(satify == require){//此时require的个数等于satify的个数
            max = Math.max(max, R - L + 1);
          }
        }

        //（2）为L的下一次移动准备
        if (count[str[L] - 'a'] == 1){
          collect--;
        }
        if(count[str[L] - 'a'] == k ){
          satify--;
        }
        count[str[L] - 'a']--;
      }
    }
    return max;
  }
}
