package com.hots100.huiwen;


/**
 * @Author: zhaomengyi
 * @Date: 2022-12-07 3:28 下午
 * @Description:
 */
public class Code0005_LongestPalindrome {

  //技巧：标准的Manacher问题。脑子里有三个条件，但是写的时候直接写结论式的写法
  public String longestPalindrome(String s) {
    if(s == null || s.length() == 0) return null;
//    if(s.length() == 1) return s;
    char[] str = generateString(s);
    int[] pArr = new int[str.length];//存储回文半径
    //老两口子
    int C = -1;//长回文的中心节点
    int R = -1;//长回文结束为止的下一位
    int max = Integer.MIN_VALUE;//长回文的长度
    int max_pos_start = -1;
    boolean[] yanchang = new boolean[str.length];
    //思路：Manacher就是基本的从前向后遍历
    for(int i = 0; i < str.length; i++){
      //1、i在R外面，即i>=R，pArr[i]=1
      //另外两种情况，都是i在R的内部，三种情况：【注意并且只有 "情况4" 需要向外扩展R！】
      //2、i 和 对称的i'小回文半径都在长回文【L..R】的内部，i的回文半径 等于 i' 的回文半径
      //3、对称的i' 的回文半径在长回文【L..R】外部（L的左侧）。此时i的回文半径 等于 R-i（稍微想一下）
      //4、对称的i' 的回文半径压线（正好在L上），那么 i 的回文半径还是（R - i）
      pArr[i] = i < R ? Math.min(R - i, pArr[2 * C - i]) :1;//统计当前字符的回文半径长度
      //技巧：讨巧的写法，用当前的半径来更新当前节点是否需要向外扩展。并且省略掉判断情况2 、3 不需要扩展的情况
      while(i + pArr[i] < str.length && i - pArr[i] > -1){//保证不越界即可
        if(str[i + pArr[i]] == str[i - pArr[i]]){//情况4：把半径向外尝试一步一步扩展
            pArr[i]++;
        } else {
          break;
        }
      }
      //上面扩展完之后，更新老两口
      if(i + pArr[i] > R){
        R = i + pArr[i];
        C = i;
      }
      //更新记录位置
      max_pos_start =  pArr[i] > max ? i : max_pos_start;
      max = Math.max(max, pArr[i]);
    }
    String tmp = String.valueOf(str).substring(max_pos_start - max + 1, max_pos_start + max - 1);
    return tmp.replaceAll("#","");
  }

  public char[] generateString(String raw){
    StringBuilder tmp = new StringBuilder();
    for(char ch:raw.toCharArray()){
      tmp.append("#" + ch);
    }
    tmp.append('#');
    return tmp.toString().toCharArray();
  }

  public static void main(String[] args) {
    String res = new Code0005_LongestPalindrome().longestPalindrome("cbbd");
    System.out.println(res);
  }
}
