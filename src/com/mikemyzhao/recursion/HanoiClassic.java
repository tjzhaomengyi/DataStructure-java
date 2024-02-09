package com.mikemyzhao.recursion;

import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-02 15:39
 * @Description:
 */
public class HanoiClassic {
  public void hanota(List<Integer> A, List<Integer> B, List<Integer> C) {
    if(A == null || A.size() == 0){
      return;
    }
    movePlates(A.size(), A, C, B);
  }

  public void movePlates(int n, List<Integer> from, List<Integer> to, List<Integer> other){
    if(n == -1){
      return;
    }
    if(n == 1){
      to.add(from.get(from.size() - 1));
      from.remove(from.size() - 1);
      return;
    }
    movePlates(n - 1, from, other, to);
    to.add(from.get(from.size() - 1));
    from.remove(from.size() - 1);
    movePlates(n - 1, other, to, from);
  }
}
