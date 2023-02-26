package com.daybyday.list;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-25 07:48
 * @Description:
 */
public class LC1656_OrderStream {
  String[] store;
  int ptr;
  int len;
  public LC1656_OrderStream(int n) {
    store = new String[n];
    ptr = 1;
    len = n;
  }

  //找顺子，顺子出牌，必须大于1个
  public List<String> insert(int idKey, String value) {
    List<String> ans = new ArrayList<>();
    store[idKey] = value;
    for(; ptr < len && store[ptr] != null; ptr++){//不要
      ans.add(store[ptr]);

    }
    return ans;
  }
}
