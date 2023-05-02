package com.huaweiOD.od2023.s60e79;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-21 9:35
 * @Description:
 */
public class JianyiNeicunchi {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      MemoryModel memoryModel = new MemoryModel();
      int n = Integer.valueOf(in.nextLine());
      String[][] lines = new String[n][2];
      for(int i = 0; i < n; i++){
        lines[i] = in.nextLine().split("=");
        if(lines[i][0].startsWith("REQUEST")){
          System.out.println(memoryModel.request(Integer.parseInt(lines[i][1])));
        } else {
          boolean result = memoryModel.release(Integer.parseInt(lines[i][1]));
          if(!result){
            System.out.println("Error");
          }
        }
      }
    }
  }

}

class MemoryModel{
  TreeMap<Integer, Integer> hasRequestTreeMap;

  public MemoryModel(){
    hasRequestTreeMap = new TreeMap<>();
  }

  public String request(int length){
    int defaultHeadAdress = 0;
    int headAddress = defaultHeadAdress;
    if(length > 100 || length <= 0){
      return "error";
    }
    if(hasRequestTreeMap == null){
      return "error";
    }
    if(hasRequestTreeMap.isEmpty()){
      hasRequestTreeMap.put(defaultHeadAdress, length);
    } else {
      List<Integer> headList = new ArrayList<>(hasRequestTreeMap.keySet());
      for(int head : headList) {
        if (head - headAddress >= length) {
          hasRequestTreeMap.put(headAddress, headAddress + length);
        } else {
          headAddress = hasRequestTreeMap.get(head);
        }
      }
      int addressDefaulteEnd = 100;
      if(addressDefaulteEnd - headAddress >= length){
        hasRequestTreeMap.put(headAddress, headAddress + length);
      } else {
        return "error";
      }
    }
    return String.valueOf(headAddress);
  }

  boolean release(int startAddress){
    if(hasRequestTreeMap == null){
      return false;
    }
    if(hasRequestTreeMap.containsKey(startAddress)){
      hasRequestTreeMap.remove(startAddress);
      return true;
    }
    return false;
  }
}
