package com.mikemyzhao.SomeSkills_0.tricks;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-27 10:30
 * @Description: 给定一个数组，让数组的子数组累加和可以把一个范围的数都搞定，求缺几个数可以把这些
 * 范围内所有的数搞定
 *
 */
public class ArrSumToaRangeGoal_16 {
  //数组排序，让数组中每个数尽量发挥最大的效用：
  //todo:如何理解这个最大效用，就是让前面range扩住此时的arr[i]
  //todo：这个思路可以参考FixOneToFindCantsumMinPostive_16这道题
  public static int minPatches(int[] arr, int aim){
    int patches = 0;
    long range = 0;
    Arrays.sort(arr);
    for(int i = 0; i != arr.length; i++){
      while(arr[i] - 1 > range){
        //range + 1就是缺的数字
        range += range + 1;
        patches++;
        if(range >= aim){
          return patches;
        }
      }
      //此时range满足
      range += arr[i];
      if(range >= aim){
        return patches;
      }
    }
    //到了数组结尾还不能达到目标，range = range + range + 1往后续吧
    while (aim >= range + 1){
      range += range + 1;
      patches++;
    }
    return patches;
  }

}
