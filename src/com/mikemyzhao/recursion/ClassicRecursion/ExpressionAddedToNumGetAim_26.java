package com.mikemyzhao.recursion.ClassicRecursion;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-09 14:36
 * @Description:给定一个数字的数组，和一个数字，用数组中的数能不能按照顺序在中间使用加减乘，或者不加任何
 * 运算符，最终得到目标数字
 * // 本题测试链接 : https://leetcode.com/problems/expression-add-operators/
 *
 */
public class ExpressionAddedToNumGetAim_26 {
  //思路：1、来到当前一个数字，可以在这个数前面增加运算符，也可以不加任何操作
  //     2、在增加运算符之前，可以确定这个表达式的左侧固定结果是多少【left】，当前字符串的最后一个数字前面加上一个运算符的结果是多少【cur】，
  //     此时的计算结果就是left+cur.通过这样的方法即得到每种运算符的结果，又规避了运算优先级的问题
  //    3、当循环到一个新的数字时候，这个时候采用以该数字开头往后延伸多长来代替“不在这两个数字间增加运算符”的处理
  public static List<String> addOperators(String num, int target){
    List<String> ret = new LinkedList<>();
    if(num.length() == 0){
      return ret;
    }
    //技巧：重要的事情说三遍：DFS记Path，记Path，记Path
    char[] path = new char[num.length() * 2 - 1];//每个数字间都添加运算符，最长的表达式
    char[] digits= num.toCharArray();
    long n = 0;
    //技巧：看看这里的逻辑和递归里面有点重叠
    for(int i = 0; i < digits.length; i++){//cur
      n = n * 10 + (digits[i] - '0');//数字字符转数字减掉0的asc码
      path[i] = digits[i];
      dfs(ret, path, i + 1, 0, n, digits, i + 1, target);
      if(n == 0){
        break;
      }
    }
    return ret;
  }

  //思路：又一个使用DFS深度遍历的递归,
  //固定参数：nums固定参数，原始输入;target 目标
  //可变参数：path：之前做的决定，从左往右填写的字符
  //len，path填好的长度，[0...len-1]
  //pos,digits用到了哪个数字
  //left：前面固定部分，cur：当前要加运算符的这一部分 1 + 2 + 3【1+2是left，3是right待定要看当前[len]的符号和】
  public static void dfs(List<String> res, char[] path, int len, long left, long cur, char[] nums, int index, int aim){
    if(index == nums.length){
      //遍历完了
      if(left + cur == aim){
        res.add(new String(path, 0, len));
      }
      return;
    }
    long n = 0;
    int j = len + 1;//技巧：path[] 1+2+3 (len=5), nums的index来到4。运算符在path中待填写位置len，当前数组填写位置len+1
    for(int i = index; i < nums.length; i++){
      n = n * 10 + (nums[i] - '0');//当前位置这个数塞进去
      //前面三种符号
      path[j++] = nums[i];//先把path的最后一位填上，往后推一下纯字符串
      path[len] = '+';
      dfs(res, path, j,left + cur, n, nums, i + 1, aim);//1+2)+3+4
      path[len] = '-';
      dfs(res, path, j, left + cur, -n, nums, i + 1, aim);
      path[len] = '*';
      dfs(res, path, j, left, cur * n, nums, i + 1, aim);
      if(nums[index] == '0'){
        break;
      }
    }
  }
}
