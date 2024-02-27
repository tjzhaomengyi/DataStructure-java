package com.mikemyzhao.simulate_reality_13.bigshua;



/**
 * @Author: zhaomengyi
 * @Date: 2022-04-07 18:05
 * @Description:，【注意：参考InterviewGuide中GasStation的最新解法】
 * // 本题测试链接 : https://leetcode.com/problems/gas-station/
 * // 注意本题的实现比leetcode上的问法更加通用
 * // leetcode只让返回其中一个良好出发点的位置
 * // 本题是返回结果数组，每一个出发点是否是良好出发点都求出来了
 * // 得到结果数组的过程，时间复杂度O(N)，额外空间复杂度O(1)
 * 数学技巧：通过前缀和数组还原出原始的数值[i-1, i...j] 求arr[i] = pre[i] - pre[i-1]
 */
public class GasStation_25 {
  public static int canCompleteCircuit(int[] gas, int[] cost){
    if(gas == null || gas.length == 0){
      return -1;
    }
    if(gas.length == 1){
      return gas[0] < cost[0] ? -1 : 0;
    }
    boolean[] good = stations(cost, gas);
    for(int i = 0; i < gas.length; i++){
      if(good[i]){
        return i;
      }
    }
    return -1;
  }
  public static boolean[] stations(int[] cost, int[] gas){
    if(cost == null || gas == null || cost.length < 2 || cost.length != gas.length){
      return null;
    }
    int init = changeDisArrayGetInit(cost, gas);
    return init == -1 ? new boolean[cost.length] : enlargeArea(cost, init);
  }

  public static int changeDisArrayGetInit(int[] dis, int[] oil){
    int init = -1;
    for(int i = 0; i < dis.length; i++){
      dis[i] = oil[i] - dis[i];
      if(dis[i] >= 0){
        init = i;
      }
    }
    return init;//技巧：只要随便一个位置大于0就可以
  }

  //思路：扩充连通区，init是找的正数，往顺时针找start，往逆时针找end
  public static boolean[] enlargeArea(int[] dis, int init){
    boolean[] res = new boolean[dis.length];
    int start = init;
    int end = nextIndex(init, dis.length);
    //思路：小车走完连通区的两个重要属性：need和rest
    int need = 0;//技巧：用need表示当前节点如果接上连通区需要多少油
    int rest = 0;//技巧：表示当前车走完连通区剩余多少油


    do{
      //数学结论：当前的start已经在连通区中，可以确定后续的开始点一定无法转完一圈
      if( start != init && start == lastIndex(end, dis.length)){
        break;
      }
      //思路：分两种情况：(1)在整个沿途形成了一部分好的连通区，即使只有一个点，但是从这个点出发，后面某一个点就连不上这个起始点了。
      // (2)转一圈回到起始点，说明这个是一个好的起始点
      //思路(1)的场景
      //技巧：如果不是好的出发点找不到连通区。注意起始点逆时针往后推，找前面的连通区是顺时针
      //例子：比如连通区算完的need=2，此时来到start节点，这个start节点想接上这个need为2的连通区，这个start节点想当连通区新的起始点，这个节点就要把这个need平掉，才能当新的起始点
      // start(5) ->  联通区的头部(7) -> 2
      // start(-2) -> 联通区的头部(7) -> 9
      if(dis[start] < need){//技巧：1、如果当前出发点，无法接到连通区的头部，
        need -= dis[start];//数学结论：(1)dis[start]油不够的话就把need减掉，虽然连不上去，但是要减掉
      } else {//技巧：2、如果当前节点可以扩充进来【dis[start] >= need】
        //数学结论：(2)dis[start]油够，把need清零，然后把这个节点消耗完剩下的添加到整个连通区的rest上
        rest += dis[start] - need;
        need = 0;
        //技巧：3、此时rest>0表示小车还能跑，先扩充尾部，并且让当前的end顶到start上，表示这个start能转一圈，是个好的起始点
        while(rest >= 0 && end != start){
          rest += dis[end];
          end = nextIndex(end, dis.length);
        }
        //技巧：4、如果当前找到的start有结余，赶紧往回拉【顺时针找】，找更好的start！
        if(rest >= 0){
          res[start] = true;
          connectGood(dis, lastIndex(start,dis.length), init, res);//技巧：当前的点是良好出发点，后面只考虑need=0的情况，rest不管了哈哈哈
          break;
        }
      }
      //技巧：5上面那两个条件甭管行不行了，您了受累都往逆时针看看去吧！
      start = lastIndex(start, dis.length);
    } while (start != init);//技巧：6注意这个起始点甭管是不是先他妈的来一圈试试，最后再判断，上面就是do不管这个条件自己先撸一把，反正init真正起始点是他妈正的。
    return res;
  }

  public static int nextIndex(int pos, int n){
    return (pos + 1) % n;
  }
  public static int lastIndex(int pos, int n){
    return pos == 0 ? (n - 1) : pos - 1;
  }

  //技巧：已知start的next方向(顺时针)上有一个良好触发点，此时往逆时针扩充连通区，
  public static void connectGood(int[] dis, int start, int init, boolean[] res){
    int need = 0;
    while(start != init){
      if(dis[start] < need){
        //数学结论：还是不行的就减
        need -= dis[start];
      } else {
        res[start] = true;
        need = 0;
      }
      start = lastIndex(start, dis.length);//技巧：转到当前可能start前面的节点
    }
  }
}
