package com.mikemyzhao.divide_and_conquer_10.bigshua;

import java.util.Arrays;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-09 19:10
 * @Description:给定一个n*3的二维数组，三元组分别表示一个项目：[乐队1参加项目，乐队2参加项目，请这组乐队的花费].
 *  现在有n个项目programs，要求用 2n 个不同的乐队完成，即一个乐队不能参与两个项目，每个项目由两个不同的乐队完成.
 *  求完成这n个项目的最小花费
 *  限制：(这道题关键在这个限制上)(1)要完成的项目数量n < 9,(2)可以组成的programs数量小于 500 ，(3)乐队数量等于 2*n 支
 */
public class PickBrands_27 {
  //数学结论：根据题意的限制：
  // (1)最多要完成的项目数量只有8个，所以最多要16只乐队即可，如果两两组合的话那么可能的组成就是C(16,2) = 120种可以选择的乐队组合方式，
  //    那么题目中给出的500只可能的组合队伍，就可以过滤掉许多无用的。
  // (2)所以由结论(1)可以推出在最多要完成的项目情况下，在120可能的项目中再选择满足要求待完成项目的8只乐队，那么就是C(120,8)。
  //   这个数量级是明显大于10^8,我们心里知道java可以承受的最大数量级
  // (3)由结论(2)我们给出一个分治的算法

  //思路：1清洗无效的乐队组合数据：(1)把编号小的放在前面，然后把两只乐队一样的组合，只选取cost最小的
  // (2)都在原有的programs数组中改动，返回programs的有效长度
  public static int clean(int[][] programs){
    int x = 0;
    int y = 0;
    for(int[] p : programs){
      x = Math.min(p[0], p[1]);
      y = Math.max(p[0], p[1]);
      p[0] = x;
      p[1] = y;
    }
    Arrays.sort(programs, (a, b) -> a[0] != b[0] ? (a[0] - b[0]) : (a[1] != b[1] ? (a[1] - b[1]) : (a[2] - b[2])));
    x = programs[0][0];
    y = programs[0][1];
    int n = programs.length;
    for(int i = 1; i < n; i++){
      if(programs[i][0] == x && programs[i][1] == y){
        programs[i] = null;//把后面的[0][1]位置等于前面的挂成空
      } else {
        x = programs[i][0];
        y = programs[i][1];
      }
    }
    int size = 1;
    for(int i = 1; i < n; i++){
      if(programs[i] != null){
        //技巧：为了省这点空间就把原来的数组缩了，真是蛋疼
        programs[size++] = programs[i];
      }
    }
    return size;//返回programs的有效长度
  }

  //把每个位置都初始化最大值，每位表示请这个乐队最少花多钱
  public static int[] init(int size) {
    int[] arr = new int[size];
    for(int i = 0; i < size; i++){
      arr[i] = Integer.MAX_VALUE;
    }
    return arr;
  }

  //思路：3:
  //size表示programs的有效长度，因为是清洗过的
  //index当前遍历到的项目，status表示当前选了哪些乐队，用整数的二进制位表示，cost表示花费多少，rest表示还有多个项目没完成，
  // map表示选择乐队花费的钱数，如果还为Integer.MaxValue表示这个乐队没有选
  public static void f(int[][] programs, int size, int index, int status, int cost, int rest, int[] map){
    if (rest == 0){
      map[status] = Math.min(map[status], cost);//status肯定是当前全选了的状态
    } else {
      if(index != size){
        f(programs, size, index + 1, status, cost, rest, map);//当前没有选这个项目组合
        //当前选这个项目组合，找到对应二进制位，变换
        //(2,5) 00100100
        int pick = 0 | 1 << programs[index][0] | 1 <<programs[index][1];
        if((pick & status) == 0){//说明这个组合方式没有选过
          f(programs, size, index + 1, status | pick, cost + programs[index][2], rest - 1, map);
        }
      }
    }
  }

  //思路：2、根据要完成的项目最多有8个，所以最多有16只乐队，所以可以用整型的16位表示。
  //   由于考虑使用使用分治的算法，所以可以拆成对应两部分map，一部分是选的，另一部分是没选。奇数个项目拆成两个不一样的，偶数个的话两个对称
  //
  public static int minCost(int[][] programs, int nums){
    if(nums == 0 || programs == null || programs.length == 0){
      return 0;
    }
    int size = clean(programs);
    int[] map1 = init( 1 << (nums * 2));
    int[] map2 = null;
    if((nums & 1) == 0){
      //偶数个项目,
      //技巧：折半！算一半，如果最多8个要完成的项目，那么只算4个
      f(programs, size, 0, 0 ,0, nums >> 1, map1);
      map2 = map1;
    } else {
      //如果待完成项目是7个，算一半就是3个,nums项目数量正好是乐队数量的一半
      f(programs, size, 0, 0, 0, nums >> 1, map1);
      map2 = init(1 << (nums << 1));
      f(programs, size, 0, 0, 0, nums - (nums >> 1),map2);
    }

    //现在选和没选的开始比划
    int mask = (1 << (nums << 1)) - 1;//掩码先出来
    int ans = Integer.MAX_VALUE;
    for(int i = 0; i < map1.length; i++){
      if(map1[i] != Integer.MAX_VALUE && map2[mask & (~i)] != Integer.MAX_VALUE){
        ans = Math.min(ans,map1[i] + map2[mask & (~i)]);
      }
    }
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }
}
