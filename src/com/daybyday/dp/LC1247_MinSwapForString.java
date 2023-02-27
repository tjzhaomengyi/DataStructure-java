package com.daybyday.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-26 09:49
 * @Description:
 */
public class LC1247_MinSwapForString {
  //数学结论：如果是偶数
  public int minimumSwap(String s1, String s2) {
    int[] cnt = new int[2];
    for (int i = 0, n = s1.length(); i < n; ++i)
      if (s1.charAt(i) != s2.charAt(i))
        ++cnt[s1.charAt(i) % 2]; // x 和 y ASCII 值的二进制最低位不同
    int d = cnt[0] + cnt[1];
    return d % 2 != 0 ? -1 : d / 2 + cnt[0] % 2;

  }
}
