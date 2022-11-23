package com.mikemyzhao.UnionFind_2_3.bigshua;

import java.util.HashMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-03 20:48
 * @Description:如果最大公因数是大于1的那么它们算作一组，求最大组的大小
 * // 测试链接：https://leetcode.com/problems/largest-component-size-by-common-factor/
 */
public class LargestComponentSizeByCommonFactor_20 {
  //思路：使用并查集，将相同的都放在一个并查集中，然后不断合并
  public static int largestComponentSizeByCommonFactor(int[] arr){
    int N = arr.length;
    UnionFind unionFind = new UnionFind(N);
    //key表示因子，value表示哪位有这个因子
    HashMap<Integer, Integer> factorMap = new HashMap<>();
    for(int i = 0; i < N; i++){
      int num = arr[i];
      //数学结论：如果求一个数是否为质数，在根号x处收敛
      int limit = (int)Math.sqrt(num);
      for(int j = 1; j <= limit; j++){
        if(num % j == 0){
          //技巧：这个数有两个因子，比如 20 / 5 = 4,5和4都是20的因子，所以都加上
          if(j != 1){
            if(!factorMap.containsKey(j)){
              factorMap.put(j, i);
            } else {
              unionFind.union(factorMap.get(j), i);
            }
          }
          int other = num / j;
          if(other != 1){
            if(!factorMap.containsKey(other)){
              factorMap.put(other,i);//头一次进来不需要并查集合并
            } else{
              unionFind.union(factorMap.get(other), i);
            }
          }
        }
      }
    }
    return unionFind.maxSize();
  }

  //最大公约数
  public static int gcd(int a, int b){
    return b == 0 ? a : gcd(b, a % b);
  }

  public static class UnionFind{
    //技巧：并查集老三位
    private int[] parents;
    private int[] sizes;
    private int[] help;

    public UnionFind(int N){
      parents = new int[N];
      sizes = new int[N];
      help = new int[N];
      for(int i = 0; i < N; i++){
        parents[i] = i;
        sizes[i] = 1;
      }
    }

    public int maxSize(){
      int ans = 0;
      for(int size : sizes){
        ans = Math.max(ans, size);
      }
      return ans;
    }

    //技巧：并查集的连查爹带标记爹都在这了
    public int find(int x){
      int hi = 0;
      while (x != parents[x]){
        help[hi++] = x;
        x = parents[x];//技巧：自己等于自己的爹往上窜
      }
      for(hi = hi-1; hi >= 0; hi--){//技巧：这里必须要用index下标来往回推，因为如果用help大小，是从结尾开始，但是help用不了这么多！
        parents[help[hi]] = x;
      }
      return x;
    }

    public void union(int i, int j){
      int f1 = find(i);
      int f2 = find(j);
      if(f1 != f2){
        int big = sizes[f1] >= sizes[f2] ? f1 : f2;
        int small = big == f1 ? f2 : f1;
        parents[small] = big;
        sizes[big] = sizes[f1] + sizes[f2];
      }
    }
  }
}
