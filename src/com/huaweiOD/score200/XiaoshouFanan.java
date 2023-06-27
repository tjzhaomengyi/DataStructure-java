package com.huaweiOD.score200;

import java.lang.reflect.Array;
import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-30 16:00
 * @Description:
 */
public class XiaoshouFanan {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int amount = Integer.valueOf(in.nextLine());
      String line = in.nextLine();
      String[] tmp = line.substring(1, line.length() - 1).split(",");
      int[] nums = new int[tmp.length];
      for(int i = 0; i < tmp.length; i++){
        nums[i] = Integer.valueOf(tmp[i].trim());
      }
      ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
        process(amount, nums, 0, new ArrayList<Integer>(), ans);//技巧:从index开始能凑的结果，这里一定要含着index位置的数
      for(int i = 0; i < ans.size(); i++){
        List<Integer> tmp_lst  = ans.get(i);
        for(int j = 0; j < tmp_lst.size(); j++){
          System.out.print(tmp_lst.get(j) + ",");
        }
        System.out.println();
      }

    }
  }


  public static void process(int amount, int[] nums, int index, ArrayList<Integer> tmp, ArrayList<ArrayList<Integer>> ans){
    if(amount == 0){
      ans.add(new ArrayList<>(tmp));//这里要复制过去！
      return;
    } else if(amount < 0){
      return;
    } else if(index > nums.length - 1 && amount != 0){
      return;
    }

      process(amount, nums, index + 1, tmp, ans);//思路:不使用index这个数，从下一个index继续计算结果合集
      tmp.add(nums[index]);
      process(amount - nums[index], nums, index, tmp, ans); //思路:放进去，但是仍然从index开始，现在让index位置这个数
      tmp.remove(tmp.size() - 1);//技巧:回溯，要重置一下

  }

}
