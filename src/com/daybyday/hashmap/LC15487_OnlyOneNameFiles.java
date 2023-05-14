package com.daybyday.hashmap;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-03-03 21:34
 * @Description:
 */
public class LC15487_OnlyOneNameFiles {
  public String[] getFolderNames(String[] names) {
    HashMap<String, Integer> map = new HashMap<>();
    int n = names.length;
    String[] ans = new String[n];
    for(int i = 0; i < n; i++){
      if(!map.containsKey(names[i])){
        ans[i] = names[i];
        map.put(ans[i], 1);
      } else {
        int k = map.get(names[i]);
        while(map.containsKey(addSuffix(names[i], k))){
          k++;
        }
        ans[i] = addSuffix(names[i], k);
        map.put(names[i], k + 1);
        map.put(addSuffix(names[i], k), 1);
      }
    }

    return ans;
  }

  public String addSuffix(String str, int k){
    return str + '(' + k + ')';
  }
}
