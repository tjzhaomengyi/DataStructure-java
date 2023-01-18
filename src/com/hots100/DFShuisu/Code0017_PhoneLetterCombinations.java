package com.hots100.DFShuisu;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-18 2:41 下午
 * @Description:
 */
public class Code0017_PhoneLetterCombinations {

  public static char[][] phone = {
      { 'a', 'b', 'c' }, // 2    0
      { 'd', 'e', 'f' }, // 3    1
      { 'g', 'h', 'i' }, // 4    2
      { 'j', 'k', 'l' }, // 5    3
      { 'm', 'n', 'o' }, // 6
      { 'p', 'q', 'r', 's' }, // 7
      { 't', 'u', 'v' },   // 8
      { 'w', 'x', 'y', 'z' }, // 9
  };

  public List<String> letterCombinations(String digits) {
    List<String> res = new ArrayList<>();
    if(digits == null || digits.length() == 0){
      return res;
    }
    char[] target = digits.toCharArray();
    char[] path = new char[target.length];
    process(target, 0, path, res);
    return res;
  }

  public void process(char[] target, int index, char[] path, List<String> res){
    if(index == target.length){
      res.add(String.valueOf(path));
    } else {
      char[] cands = phone[target[index] - '2'];
      for(char cur : cands){
        path[index] = cur;
        process(target, index + 1, path, res);
      }
    }
  }
}
