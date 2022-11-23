package com.mikemyzhao.waters;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 9:40
 * @Description:
 */
public class LC_0412_FizzBuzz {
  public List<String> fizzBuzz(int n) {
    List<String> res = new ArrayList<>();
    for(int i = 1; i <=n; i++){
      if(i % 3 == 0 && i % 5 == 0){
        res.add("FizzBuzz");
      }
      else if(i % 3 == 0){
        res.add("Fizz");
      }
      else if(i % 5 == 0){
        res.add("Buzz");
      } else {
        res.add(String.valueOf(i));
      }
    }
    return res;
  }
}
