package com.hots100.DFShuisu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-18 3:03 下午
 * @Description:
 */
public class Code0301_RemoveInvalidParentheses {
  public List<String> removeInvalidParentheses(String s) {
    List<String> ans = new ArrayList<>();
    remove(s, ans,0, 0 , new char[]{'(',')'});
    return ans;
  }

  public void remove(String s, List<String> ans, int checkIndex, int deleteIndex, char[] par){
    for(int cnt = 0, i = checkIndex; i < s.length(); i++){
      if(s.charAt(i) == par[0]){
        cnt++;
      }
      if(s.charAt(i) == par[1]){
        cnt--;
      }
      if(cnt < 0) {
        for(int j = deleteIndex; j <= i; ++j){
          if(s.charAt(j) == par[1] && (j == deleteIndex || s.charAt(j - 1) != par[1])){
            //如果j位置是右括号，且j位置是可删除位置或者j的前一位是左括号，删除j位
            remove(s.substring(0, j) + s.substring(j + 1, s.length()), ans, i, j, par);
          }
        }
        return;
      }
    }
    String reversed = new StringBuilder(s).reverse().toString();
    if(par[0] == '('){
      remove(reversed, ans, 0, 0, new char[]{')', '('});
    } else {
      ans.add(reversed);
    }
  }
}
