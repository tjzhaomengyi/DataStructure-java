package com.mikemyzhao.SomeSkills_0.YangHuiSanjiao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 11:53
 * @Description:
 */
public class LC_0118_YangHuiTriangle {
  public static List<List<Integer>> generate(int numRows) {
    List<List<Integer>> ans = new ArrayList<>();
    for(int i = 0; i < numRows; i++){
      ans.add(new ArrayList<>());
      ans.get(i).add(1);//技巧:先把数组都开开，第一个1都加上
    }
    for(int i = 1; i < numRows; i++){
      for(int j = 1; j < i; j++){
        ans.get(i).add(ans.get(i - 1).get(j - 1) + ans.get(i - 1).get(j));//每错一位加1
      }
      ans.get(i).add(1);
    }

    return ans;
  }


}
