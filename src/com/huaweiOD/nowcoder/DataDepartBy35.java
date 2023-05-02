package com.huaweiOD.nowcoder;

import com.mikemyzhao.TreeOperations_2.bigshua.MinCameraCover_7;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-13 11:13
 * @Description:
 */
public class DataDepartBy35 {
  public static void main(String[] args) throws IOException {
//    Scanner in = new Scanner(System.in);
    BufferedReader read = new BufferedReader(new InputStreamReader(System.in));
    String input;
    // 注意 hasNext 和 hasNextLine 的区别
    //while (in.hasNextInt()) { // 注意 while 处理多个 case
    while ((input = read.readLine()) != null){
      //int len = in.nextInt();
      int len = Integer.parseInt(input);
      int[] nums = new int[len];
      String[] strnums = read.readLine().split(" ");
      int[] visited = new int[len]; //如果是5的倍数放入了标记5， 如果是3的倍数放入标记3，其他1
      int total = 0;
      int[] dict_cnt_flag = new int[4];
      for (int i = 0; i < len; i++) {
//        nums[i] = in.nextInt();
        nums[i] = Integer.parseInt(strnums[i]);
        total += nums[i];
        if(nums[i] != 0){
          if(nums[i] % 3 == 0){
            dict_cnt_flag[0]++;
          }
          if(nums[i] % 5 == 0){
            dict_cnt_flag[1]++;
          }
        }
      }

      if (Math.abs(total) % 2 != 0) {
        System.out.println("false");
      } else {
        int target = total / 2;
        Stack<Integer> stack = new Stack<>();
        boolean ans = dfs(nums, 0, 0, target, visited, stack, dict_cnt_flag);
        System.out.println(ans==true ? "true" : "false");
      }
    }
  }

  public static boolean dfs(int[] nums, int index, int tmp, int target,
                            int[] visited, Stack<Integer> stack,int[] dict_cnt) {
    if (index >= nums.length && target == tmp) {
      if(stack.isEmpty()){
        return (dict_cnt[0] > 0 && dict_cnt[1] > 0) ? false : true;
      } else {
        int cnt_3 = dict_cnt[0];
        int cnt_5 = dict_cnt[1];
        return (cnt_3 > 0 && cnt_5 > 0) ? false : true;
      }
    }  else if (index >= nums.length && target != tmp) {
      return false;
    }

    boolean p1 = false, p2 = false, p3 = false, p4 = false;

    if ((nums[index] % 5 == 0 && dict_cnt[2] == 3)) {
      visited[index] = 1;
      p1 = dfs(nums, index + 1, tmp, target, visited, stack,dict_cnt);
    } else if (nums[index] % 3 == 0 && dict_cnt[3] == 5) {
      visited[index] = 1;
      p2 = dfs(nums, index + 1, tmp, target, visited, stack,dict_cnt);
    } else {
      visited[index] = 1;
      p3 = dfs(nums, index + 1, tmp, target, visited, stack,dict_cnt);
      stack.push(nums[index]);
      if(nums[index] % 3 == 0 ){
        dict_cnt[0]--;
        dict_cnt[2] = 3; //如果添加进来的是3的倍数，把当前集合做个标记，后续不能进5的倍数
      } else if(nums[index] % 5 == 0){
        dict_cnt[1]--;
        dict_cnt[3] = 5; //如果当前添加进来是5的倍数，把当前集合标记为5，后续3的倍数不能进来
      }
      p4 = dfs(nums, index + 1, tmp + nums[index], target, visited, stack, dict_cnt);

      //回溯:并动态调整资源。恢复现场。
      int pop = stack.pop();
      if(pop % 3 == 0 ){
        dict_cnt[0]++;
        dict_cnt[2] = 0;
      } else if(pop % 5 == 0){
        dict_cnt[1]++;
        dict_cnt[3] = 0;
      }
    }
    visited[index] = 0;
    return p1 || p2 || p3 || p4;
  }

}
