package com.huaweiOD.nowcoder;

import com.MCAAlgorithm.bigshua.class05.Hash;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-12 20:47
 * @Description:
 */
public class Poker24 {
  static HashMap<String, Integer> bigDict = new HashMap<>();
  static HashMap<String, String> backBigDict = new HashMap<>();

  public static void main(String[] args) {
    bigDict.put("A", 1); backBigDict.put("1", "A");
    bigDict.put("J", 11); backBigDict.put("11", "J");
    bigDict.put("Q", 12); backBigDict.put("12", "Q");
    bigDict.put("K", 13); backBigDict.put("13", "K");
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      String str = in.nextLine();
      if (str.toUpperCase().contains("JOKER")) {
        System.out.println("ERROR");
        continue;
      }
      int[] pokers = makeArr(str);
      for(int i = 0 ; i < 4; i++){
        System.out.print(pokers[i] + " ");
      }
      System.out.println();
      Stack<String> path = new Stack<>();
      boolean[] visited = new boolean[4];
      //技巧:注意:谁都有可能做起始点，谁都可能是起始点的就用for来代替里面的i+1
      for (int i = 0; i < 4; i++) {
        visited[i] = true;
        path.push(String.valueOf(pokers[i]));
        if (dfs(pokers, path, pokers[i], visited)) { //每个位置都可以尝试做开始位置
          break;
        }
        visited[i] = false; //但是，回溯，这个太草了
        path.pop();
      }
      if (!path.isEmpty()) {
        String ans = "";
        while(!path.isEmpty()){
          String e = path.pop();
          if(backBigDict.keySet().contains(e)){
            e = backBigDict.get(e);
          }
          ans = e + ans;
        }
        System.out.println(ans);
      } else {
        System.out.println("ERROR");
      }
    }
  }

  public static boolean dfs(int[] nums, Stack<String> path, int ans, boolean[] visited) {
    boolean allVisited = true;
    for (int i = 0; i < 4; i++) {
      if (visited[i] == false) {
        allVisited = false;
      }
    }

    if (allVisited == true) {
      return ans == 24;
    }

    for (int i = 0; i < 4; i++) {
      if (!visited[i]) {
        visited[i] = true;

        path.push("+");
        path.push(String.valueOf(nums[i]));
        int addans = ans + nums[i];
        if (dfs(nums, path, addans, visited)) {
          return true;
        }
        path.pop();
        path.pop();


        path.push("-");
        path.push(String.valueOf(nums[i]));
        int minans = ans - nums[i];
        if (dfs(nums, path, minans, visited)) {
          return true;
        }
        path.pop();
        path.pop();


        path.push("*");
        path.push(String.valueOf(nums[i]));
        int mulans = ans * nums[i];
        if (dfs(nums,  path, mulans, visited)) {
          return true;
        }
        path.pop();
        path.pop();


        path.push("/");
        path.push(String.valueOf(nums[i]));
        int divans = ans / nums[i];
        if (nums[i] != 0 && ans % nums[i] == 0) {
          if (dfs(nums, path, divans, visited)) {
            return true;
          }
        }
        path.pop();
        path.pop();
        //回溯
        visited[i] = false;
      }
    }

    return false;
  }


  public static int[] makeArr(String pk) {
    String[] arr = pk.split(" ");
    int[] pokers = new int[arr.length];
    Set<String> keys = bigDict.keySet();
    for (int i = 0; i < arr.length; i++) {
      if (keys.contains(arr[i])) {
        pokers[i] = bigDict.get(arr[i]);
      } else {
        pokers[i] = Integer.valueOf(arr[i]);
      }
    }
    return pokers;
  }


}


