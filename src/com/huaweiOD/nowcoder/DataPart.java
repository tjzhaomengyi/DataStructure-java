package com.huaweiOD.nowcoder;

import java.util.Scanner;

import java.util.*;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class DataPart {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      String strNums = in.nextLine();
      String[] arrNums = strNums.split(" ");
      String strRules = in.nextLine();
      String[] arrRules = strRules.split(" ");
      int numLen = Integer.valueOf(strNums.split(" ")[0]);
      // System.out.println(numLen);
      int ruleLen = Integer.valueOf(strRules.split(" ")[0]);
      // System.out.println(ruleLen);
      int[] nums = new int[numLen];
      int[] rules = new int[ruleLen];
      for(int i = 1; i < arrNums.length; i++){
        nums[i - 1] = Integer.valueOf(arrNums[i]);
        // System.out.print(nums[i - 1] + " ");
      }
      // System.out.println();
      for(int i = 1; i < arrRules.length; i++){
        rules[i - 1] = Integer.valueOf(arrRules[i]);
        // System.out.print(rules[i - 1] + " ");
      }
      // System.out.println();

      TreeMap<Integer,ArrayList<String>> ans = new TreeMap<>();

      //从nums中遍历rules的规则，treemap自动排序
      for(int i = 0; i < ruleLen; i++){
        int r = rules[i];
        if(ans.keySet().contains(r)){
          continue;
        }
        String sr = String.valueOf(r);
        for(int j = 0; j < numLen; j++){
          int n = nums[j];
          String sn = String.valueOf(n);
          if(sn.contains(sr)){
            String pos_num_ans = String.valueOf(j) + " " + sn;
            if(!ans.keySet().contains(r)){
              ArrayList<String> records = new ArrayList<>();
              records.add(pos_num_ans);
              ans.put(r, records);

            } else {
              ArrayList<String> records = ans.get(r);
              records.add(pos_num_ans);
              ans.put(r, records);

            }
          }
        }
      }

      StringBuilder sb = new StringBuilder();
      for(int key : ans.keySet()){
        ArrayList<String> col = ans.get(key);
        sb.append(String.valueOf(key) + " ");

        int colsize = col.size();
        sb.append(colsize + " ");

        String colstr = "";
        for(int i = 0; i < colsize; i++){
          colstr = col.get(i);
          sb.append(colstr +" ");
        }

      }
      String strans = sb.toString();
      int total =strans.split(" ").length;
      System.out.println(total + " " + strans);
    }
  }
}

class Record{
  int rule;
  int cnt;
  int pos;
  String num;
  public Record(int rule, int cnt, int pos, String num){
    this.rule = rule;
    this.cnt = cnt;
    this.pos = pos;
    this.num = num;
  }
}