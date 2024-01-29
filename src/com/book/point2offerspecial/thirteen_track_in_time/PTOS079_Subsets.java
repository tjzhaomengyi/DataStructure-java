package com.book.point2offerspecial.thirteen_track_in_time;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-14 19:42
 * @Description:
 */
public class PTOS079_Subsets {
  public List<List<Integer>> subsets(int[] nums) {
    List<List<Integer>> ans = new LinkedList<>();
    if(nums.length == 0) return ans;
    List<Integer> subset = new LinkedList<Integer>();
    process(nums, 0, (LinkedList<Integer>) subset, ans);
    return ans;
  }


  public void process(int[] nums, int index, LinkedList<Integer> subset, List<List<Integer>> ans){
    if(index == nums.length){
      ans.add(new LinkedList<>(subset)); //技巧：复制贱人！
    } else if(index < nums.length){//扣边界：因为上面只有等于，大于还是不行
      process(nums, index + 1, subset, ans);//不加当前index的情况
      subset.add(nums[index]);
      process(nums, index + 1, subset, ans);
      subset.removeLast();
    }
  }
}
