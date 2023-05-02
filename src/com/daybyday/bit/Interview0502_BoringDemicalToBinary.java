package com.daybyday.bit;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-03-02 9:28
 * @Description:无聊的小数二进制转换
 */
public class Interview0502_BoringDemicalToBinary {
  public String printBin(double num) {
    List<String> ans = new ArrayList<>();
    int count = 0;
    while(num != 0 && count < 32){
      double temp = num * 2;
      if(temp >= 1.0){
        //说明小数点后面可以进位了
        ans.add("1");
        num--;
      } else {
        ans.add("0");
        temp = num;
      }
      count++;
    }
    if(count == 32){
      return "ERROR";
    }
    StringBuilder sb = new StringBuilder("0.");
    for(String c : ans){
      sb.append(c);
    }
    return sb.toString();
  }
}
