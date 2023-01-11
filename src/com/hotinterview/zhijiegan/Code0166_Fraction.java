package com.hotinterview.zhijiegan;


import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-12-27 11:27 上午
 * @Description:
 */
public class Code0166_Fraction {
  public String fractionToDecimal(int numerator, int denominator) {
    if(numerator == 0){
      return "0";
    }
    StringBuilder res = new StringBuilder();
    res.append((numerator > 0) ^ (denominator > 0) ? "-" : "");
    long num = Math.abs((long) numerator);
    long den = Math.abs((long) denominator);
    res.append(num / den);
    num %= den;
    if(num == 0){
      return res.toString();
    }

    //带小数点
    res.append(".");
    HashMap<Long, Integer> map = new HashMap<>();
    map.put(num, res.length());
    while(num != 0){
      num *= 10;
      res.append(num / den);
      num %= den;
      if(map.containsKey(num)){//如果有这个数一旦出现一次说明已经出现循环
        int index = map.get(num);
        res.insert(index, "(");
        res.append(")");
        break;
      } else {
        map.put(num, res.length());
      }
    }

    return res.toString();
  }
}
