package com.mikemyzhao.SomeSkills_0.tricks;

import com.MCAAlgorithm.bigshua.class37.Problem_0406_QueueReconstructionByHeight;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-19 12:10
 * @Description:
 */
public class LC_0406_QueueReconstruct {
  public static class Unit {
    public int h;
    public int k;

    public Unit(int height, int greater) {
      h = height;
      k = greater;
    }
  }

  public static class UnitComparator implements Comparator<Unit> {

    @Override
    public int compare(Unit o1, Unit o2) {
      return o1.h != o2.h ? (o2.h - o1.h) : (o1.k - o2.k);
    }

  }
  public static int[][] reconstructQueue1(int[][] people) {
    int N = people.length;
    Unit[] units = new Unit[N];
    for (int i = 0; i < N; i++) {
      units[i] = new Unit(people[i][0], people[i][1]);
    }
    Arrays.sort(units, new UnitComparator());
    ArrayList<Unit> arrList = new ArrayList<>();
    for (Unit unit : units) {
      arrList.add(unit.k, unit);
    }
    int[][] ans = new int[N][2];
    int index = 0;
    for (Unit unit : arrList) {
      ans[index][0] = unit.h;
      ans[index++][1] = unit.k;
    }
    return ans;
  }
}
