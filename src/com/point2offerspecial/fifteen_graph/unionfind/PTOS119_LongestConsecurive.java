package com.point2offerspecial.fifteen_graph.unionfind;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-22 11:49
 * @Description:这题用并查集也没啥意义就是为了用而用
 */
public class PTOS119_LongestConsecurive {

  class Solution {
    public class UnionFind{
      private int[] parents;
      private int[] size;
      private int[] help;
      public int sets;

      public UnionFind(int N){
        this.sets = N;
        this.parents = new int[N];
        this.size = new int[N];
        this.help = new int[N];
        for(int i = 0; i < N; i++){
          size[i] = 1;
          parents[i] = i;
        }
      }

      public void union(int i, int j){
        int f1 = find(i);
        int f2 = find(j);
        if(f1 != f2){
          if(size[f1] >= size[f2]){
            size[f1] += size[f2];
            parents[f2] = f1;
          } else {
            size[f2] += size[f1];
            parents[f1] = f2;
          }
          sets--;
        }
      }

      public int find(int i){
        int hi = 0;
        while(i != parents[i]){
          help[hi] = i;
          hi++;
          i = parents[i];
        }
        for(hi--; hi >= 0; hi--){
          parents[help[hi]] = i;
        }
        return i;
      }
    }

    public int longestConsecutive(int[] nums) {
      if(nums.length == 1) return 1;
      if(nums == null || nums.length ==0) return 0;
      Arrays.sort(nums);
      int ans = 1;
      int n = nums.length;
      UnionFind uf = new UnionFind(n);
      boolean[] pass = new boolean[n];
      for(int i = 0; i < n - 1; i++){
        int j = i + 1;
        if(pass[i] == true){
          continue;
        }
        while(nums[i] == nums[j] && j < n - 1){
          pass[j] = true;
          j++;
        }
        if(nums[i] == nums[j] - 1){
          uf.union(i, j);
          ans = Math.max(ans, uf.size[uf.parents[i]]);
        }
      }
      for(int i = 0; i < n; i++){
        System.out.println(uf.size[i]);
      }
      return  ans;
    }
  }
}
