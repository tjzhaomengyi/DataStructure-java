package com.book.point2offerspecial.thirteen_track_in_time;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-15 20:38
 * @Description:
 */
public class PTOS085_GenerateParenthesis {
  //太难了~直接记住例子吧 ： 生成括号的规则只有两条：如果待生成的括号有2n个（1）左右括号的数量不能超过n个
  // （2）在任意步骤生成的右括号的数量不能大于左括号的数量
  public List<String> generateParenthesis(int n) {
    //注意：n表示生成括号的对数，
    List<String> ans = new LinkedList<>();
    process(n, n, "" , ans);//n表示左右括号的数量
    return ans;
  }

  public void process(int left, int right, String path, List<String> ans){
    if(left == 0 && right == 0){
      ans.add(path);
    }
    if(left < right){//如果剩下的左括号小于右括号的数量（字符串中左括号大于右括号的数量）
      //放入右括号
      process(left, right - 1, path + ")" , ans);
    }
    if(left > 0){
      process(left - 1, right, path + "(", ans);
    }
  }
}
