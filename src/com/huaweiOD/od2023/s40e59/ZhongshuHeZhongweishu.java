package com.huaweiOD.od2023.s40e59;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-23 16:09
 * @Description:
 */
public class ZhongshuHeZhongweishu {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(" ");
      int[] nums = new int[str.length];
      for(int i = 0; i < str.length; i++){
        nums[i] = Integer.valueOf(str[i]);
      }
      System.out.println(getNums(nums));
    }
  }

  public static int getNums(int[] nums){
    TreeMap<Integer, Integer> cnt_dict = new TreeMap<>();
    Arrays.sort(nums);
    int n = nums.length;
    for(int i = 0; i < n; i++){
      cnt_dict.put(nums[i], cnt_dict.getOrDefault(nums[i], 0) + 1);
    }
    //技巧:TreeMap只能根据key进行排序，不能根据value进行排序，我们需要借助ArrayList对Map进行排序
    List<Map.Entry<Integer, Integer>> mapList = new ArrayList<>(cnt_dict.entrySet());
    Collections.sort(mapList, (o1, o2) -> {
      return o2.getValue() - o1.getValue();
    });
    int zhongshugeshu = mapList.get(0).getValue();
    int zhongshu = mapList.get(0).getKey();
    ArrayList<Integer> newArray = new ArrayList();
    for(int i = 0; i < mapList.size(); i++){
      if(mapList.get(i).getValue() == zhongshugeshu){
        for(int j = 0; j < zhongshugeshu; j++){
          newArray.add(mapList.get(i).getKey());
        }
      }
    }
    return newArray.size() % 2 == 1 ? newArray.get((newArray.size() - 1)/2) :
        (newArray.get(newArray.size() / 2) + newArray.get((newArray.size() - 1)/2)) / 2;
  }
}
