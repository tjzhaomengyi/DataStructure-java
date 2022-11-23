package com.mikemyzhao.SomeSkills_0.YangHuiSanjiao;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-17 12:23
 * @Description:
 */
public class LC_0019_YanghuiTriangleII {
  //递推出杨辉三角第n行
  public List<Integer> getRow(int rowIndex){
    List<Integer> ans = new ArrayList<>();
    for(int i = 0; i <= rowIndex; i++){
      for(int j = i - 1; j > 0; j--){
        ans.set(j,ans.get(j - 1) + ans.get(j));
      }
      ans.add(1);
    }
    return ans;
  }
}
