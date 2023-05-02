package com.huaweiOD.nowcoder;



import java.util.HashMap;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Set;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-06 20:06
 * @Description:输入描述：
 * 输入的第 1 行，为两个正整数N，m，用一个空格隔开：
 *
 * （其中 N （ N<32000 ）表示总钱数， m （m <60 ）为可购买的物品的个数。）
 *
 *
 * 从第 2 行到第 m+1 行，第 j 行给出了编号为 j-1 的物品的基本数据，每行有 3 个非负整数 v p q
 *
 *
 * （其中 v 表示该物品的价格（ v<10000 ）， p 表示该物品的重要度（ 1 ~ 5 ）， q 表示该物品是主件还是附件。如果 q=0 ，表示该物品为主件，如果 q>0 ，表示该物品为附件， q 是所属主件的编号）
 *
 *
 *
 *
 * 输出描述：
 *  输出一个正整数，为张强可以获得的最大的满意度。
 *  输入：
 * 50 5
 * 20 3 5
 * 20 3 5
 * 10 3 0
 * 10 2 0
 * 10 1 0
 * 复制
 * 输出：
 * 130
 * 复制
 * 说明：
 * 由第1行可知总钱数N为50以及希望购买的物品个数m为5；
 * 第2和第3行的q为5，说明它们都是编号为5的物品的附件；
 * 第4~6行的q都为0，说明它们都是主件，它们的编号依次为3~5；
 * 所以物品的价格与重要度乘积的总和的最大值为10*1+20*3+20*3=130
 */
public class HJ16_ShoppingCart {

  //模型:背包问题模板,用rest钱数获得最大价值
  public static int bag(int[] prices, int[] values, int index, int rest_moeney){
    //到达index位置获取物品以得到最大价值
    if (rest_moeney < 0){
      return -1;
    }
    if(index == prices.length){
      return 0;
    }
    //不拿index位置的商品
    int p1 = bag(prices, values, index + 1, rest_moeney);
    //拿index位置的商品,但是要注意next不能为-1
    int p2 = -1;
    int next = bag(prices, values, index + 1, rest_moeney - prices[index]);
    if(next != -1){
      //后面整挺好
      p2 = next;
    }

    return Math.max(p1, p2);//二选1
  }

  public static void main(String[] args) {
    Scanner s = new Scanner(System.in);
    while(s.hasNext()){
      int money = s.nextInt();
      int good_total = s.nextInt();
      ArrayList<Good> goods = new ArrayList<>();
      for(int i = 0; i < good_total; i++){
        int index  = i + 1;
        int price = s.nextInt();
        int importance = s.nextInt();
        int main = s.nextInt();
        goods.add(new Good(index, price, importance, main));
      }
      System.out.println(makeHimHigh(money, goods));

    }
  }

  public static int makeHimHigh(int money, ArrayList<Good> goods){
    HashMap<Integer, ArrayList<Good>> teams = makeTeams(goods);
    Set<Integer> ks = teams.keySet();
    int[] keys = new int[ks.size()];
    int i = 0;
    for(Integer key : ks){
      keys[i++] = key;
    }
    //开始执行背包
    int ans = p(keys, teams, 0, money);
    return ans;
  }

  //递归的方法在40的时候超时
  public static int p(int[] keys, HashMap<Integer, ArrayList<Good>> teams, int i, int rest){
    if(rest < 0) return -1;
    if(i == keys.length) return 0;
    int key = keys[i];
    ArrayList<Good> goodlist = teams.get(key);
    int len = goodlist.size();
    Good maingood = goodlist.get(0);
    int ans = 0;
    if(len == 1){// a : a 或者 不要
      //不要主物品
      int p1 = p(keys, teams, i + 1 , rest);
      //要
      int p2 = -1;
      int next = p(keys, teams, i + 1, rest - maingood.price);
      if(next != -1){
        p2 = maingood.price * maingood.importance + next;
      }
      ans = Math.max(p1, p2);
    } else if(len == 2){ // a b : 不要， a, ab
      Good b = goodlist.get(1);
      //不要a
      int p1 = p(keys, teams, i + 1, rest);
      //要a 不要 b
      int p2 = -1;
      int next2 = p(keys, teams, i + 1, rest - maingood.price);
      if(next2 != -1){
        p2 = maingood.price * maingood.importance + next2;
      }
      //要a和b
      int p3 = -1;
      int next3 = p(keys, teams, i + 1, rest - maingood.price - b.price);
      if(next3 != -1){
        p3 = maingood.price * maingood.importance + b.price * b.importance + next3;
      }
      ans = Math.max(p1, Math.max(p2, p3));
    } else if(len == 3){ // a b c : 不要， a, ab, ac, abc
      Good b = goodlist.get(1);
      Good c = goodlist.get(2);
      //不要
      int p1 = p(keys, teams, i + 1, rest);

      //要a
      int p2 = -1;
      int next2 = p(keys, teams, i + 1, rest - maingood.price);
      if(next2 != -1){
        p2 = maingood.price * maingood.importance + next2;
      }

      //要ab
      int p3 = -1;
      int next3 = p(keys, teams, i + 1, rest - maingood.price - b.price);
      if(next3 != -1){
        p3 = maingood.price * maingood.importance + b.price * b.importance + next3;
      }

      //要ac
      int p4 = -1;
      int next4 = p(keys, teams, i + 1, rest - maingood.price - c.price);
      if(next4 != -1){
        p4 = maingood.price * maingood.importance + c.price * c.importance + next4;
      }

      //abc
      int p5 = -1;
      int next5 = p(keys, teams, i + 1, rest- maingood.price - b.price - c.price);
      if(next5 != -1){
        p5 = maingood.price * maingood.importance + c.price * c.importance + b.price * b.importance + next5;
      }
      ans = Math.max(Math.max(Math.max(p1,p2),Math.max(p3, p4)), p5);
    }
    return ans;
  }


  public static class Good{
    //技巧:操您妈的贼无聊，物品的价值就是importance * price，臭傻逼面试
    int index;
    int price;
    int importance;
    int main;
    public Good(int index, int price, int importance, int main){
      this.index = index;
      this.price = price;
      this.importance = importance;
      this.main = main;
    }
  }

  //以主件物品作为分组的key，附件做list
  public static HashMap<Integer,ArrayList<Good>> makeTeams(ArrayList<Good> goods){
    HashMap<Integer, ArrayList<Good>> teams = new HashMap<>();
    for(int i = 0; i < goods.size(); i++){
      Good g = goods.get(i);
      if(g.main == 0){
        ArrayList lst = new ArrayList<Good>();
        lst.add(g);
        teams.put(g.index, lst);//以主部件作为表的key
      }
    }
    for(int i = 0; i < goods.size(); i++){
      Good g  = goods.get(i);
      if(g.main != 0){
        int mainIndex = g.main;
        ArrayList<Good> mainLst = teams.get(mainIndex);
        mainLst.add(g);
        teams.put(mainIndex, mainLst);
      }
    }
    return teams;
  }
}
