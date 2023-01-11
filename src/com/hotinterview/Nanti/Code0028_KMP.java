package com.hotinterview.Nanti;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-21 4:59 下午
 * @Description:
 */
public class Code0028_KMP {
  public int strStr(String s1, String s2) {
    if(s1 == null || s1.length() == 0 || s2 == null || s2.length() == 0) return 0;
    char[] str1 = s1.toCharArray();
    char[] str2 = s2.toCharArray();
    int x = 0; //str1 从x位置开始和 str2 对比
    int y = 0;// str2 从y位置开始和 str1 对比
    int[] next = getNextArray(str2);
    while(x < str1.length  && y < str2.length){
      if(str1[x] == str2[y]){
        x++;
        y++;
      } else if(next[y] == -1){//表示当前str2在开始位置，已经跳到最前面了，和str1一个没配上
        x++;
      } else {
        y = next[y]; //这个表示str2 的 [y] 和str1的x没有配上
      }
    }
    return y == str2.length ? x - y : -1;
  }

  public int[] getNextArray(char[] str2){
    //求str2每个位置i的前后缀数组相等的最大长度
    if(str2.length == 1){
      return new int[]{-1};
    }
    int[] next = new int[str2.length];
    next[0] = -1;
    next[1] = 0;
    int i = 2;//比较游历下标
    int cn = 0; //标记前缀数组的最后下标,同时也表示next[i]对应前后缀最大长度，所以对比正好是[cn]和[i-1]比
    while(i < next.length){
      if(str2[i - 1] == str2[cn]){
        //思路：想第一个位置，cn=0，如果[1] == [0]，正好cn+1=1，表示前后缀长度为1，下次往回查的时候正好查到下标[cn]的值
        cn++;//i位置的前后缀长度加一
        next[i] = cn;
        i++;
      } else if(cn > 0){
        //如果两个不匹配，往前窜，检查
        cn = next[cn];
      } else {
        //cn位置不能往前跳了,看下个位置吧
        next[i] = 0;
        i++;
      }
    }
    return next;
  }
}
