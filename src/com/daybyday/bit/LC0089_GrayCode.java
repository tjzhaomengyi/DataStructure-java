package com.daybyday.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-23 09:40
 * @Description:n 位格雷码序列 是一个由 2n 个整数组成的序列，其中：
 * 每个整数都在范围 [0, 2n - 1] 内（含 0 和 2n - 1）
 * 第一个整数是 0
 * 一个整数在序列中出现 不超过一次
 * 每对 相邻 整数的二进制表示 恰好一位不同 ，且
 * 第一个 和 最后一个 整数的二进制表示 恰好一位不同
 * 给你一个整数 n ，返回任一有效的 n 位格雷码序列 。
 */
public class LC0089_GrayCode {
  public List<Integer> grayCode(int n) {
    //数学结论：格雷编码的每个位置符合要求的数是 G(i) = i ^ (i >> 1)
    List<Integer> ans = new ArrayList<Integer>();
    ans.add(0);
    for(int i = 1; i < 1 << n; i++){
      ans.add(i ^ (i >> 1));
    }
    return ans;
  }
}
