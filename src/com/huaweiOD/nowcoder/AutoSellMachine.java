package com.huaweiOD.nowcoder;


import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-13 20:39
 * @Description:
 */
public class AutoSellMachine {
  // 注意类名必须为 Main, 不要有任何 package xxx 信息
  static TreeMap<Integer, Integer> moneybox = new TreeMap<>((o1, o2) -> o1.intValue() - o2.intValue());
  static int[] moneydict = new int[]{1, 2, 5, 10};
  static HashMap<String, Good> goodsbox = new HashMap<String, Good>();
  static int get_money_tobuy = 0;
  static int rest_money_tobuy = 0;

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    while (in.hasNextLine()) { // 注意 while 处理多个 case
      String cmds = in.nextLine();
      String[] cmd_arr = cmds.split("\\;");
      for(int i = 0; i < cmd_arr.length; i++){
        String cmd = cmd_arr[i];
        String cmd_0 = cmd_arr[i].split(" ")[0];
        if(cmd_0.startsWith("r")){
         String ans = r_cmd(cmd);
         System.out.println(ans);
        } else if(cmd_0.startsWith("p")){
          String ans = p_cmd(cmd);
          System.out.println(ans);
        } else if(cmd_0.startsWith("b")){
//          get_money_tobuy = Integer.valueOf(cmd.split(" ")[2]);
         String ans = b_cmd(cmd);
          System.out.println(ans);
        } else if(cmd_0.startsWith("c")){
         String ans =  c_cmd(cmd);
          System.out.println(ans);
        } else if(cmd_0.startsWith("q")){
          q_cmd(cmd);
        }
      }
    }
  }

  public static String r_cmd(String cmd) {
    String p1 = cmd.split(" ")[1];
    String p2 = cmd.split(" ")[2];
    String[] goods = p1.split("-");
    String[] moneys = p2.split("-");
    int total = 0;
    for (int i = 0; i < goods.length; i++) {
      String name = "A" + String.valueOf(i + 1);
      int price = 0;
      if (i + 1 == 5) {
        price = 8;
      } else if (i + 1 == 6) {
        price = 6;
      } else {
        price = i + 2;
      }
      Good newGood = new Good(i, name, price, Integer.valueOf(goods[i]));
      goodsbox.put(name, newGood);
      total += Integer.valueOf(goods[i]);
    }
    goodsbox.put("total", new Good( 7,"total", -1, total));
    for (int j = 0; j < moneys.length; j++) {
      moneybox.put(moneydict[j], Integer.valueOf(moneys[j]));
    }
    return "S001:Initialization is successful";
  }

  public static String p_cmd(String cmd) {
    String money = cmd.split(" ")[1];
    get_money_tobuy = Integer.valueOf(money);

    if (money.equals("1") && money.equals("2") && money.equals("5") && money.equals("10")) {
      return "E002:Denomination error";
    }
    int one_yuan_cnt = moneybox.get(1);
    int two_yuan_cnt = moneybox.get(2);
    if (one_yuan_cnt * 1 + two_yuan_cnt * 2 < get_money_tobuy && get_money_tobuy > 2) {
//      get_money_tobuy = 0;
      rest_money_tobuy += 0;
      return "E003:Change is not enough, pay fail";
    } else {
      rest_money_tobuy += get_money_tobuy;
    }
    if (goodsbox.get("total").cnt == 0) {
      return "E005:All the goods sold out";
    }
    int cnt = moneybox.get(get_money_tobuy);
    cnt++;
    moneybox.put(get_money_tobuy, cnt);
    return "S002:Pay success,balance=" + rest_money_tobuy;
  }

  public static String b_cmd(String cmd) {
    String good_name = cmd.split(" ")[1];
    if (!goodsbox.keySet().contains(good_name)) {
      return "E006:Goods does not exist";
    }
    if (goodsbox.get(good_name).cnt == 0) {
      return "E007:The goods sold out";
    }
    if (rest_money_tobuy < goodsbox.get(good_name).p) {
      return "E008:Lack of balance";
    }
    //todo:修改get_money_toBuy
//    rest_money_tobuy = rest_money;
    rest_money_tobuy = rest_money_tobuy - goodsbox.get(good_name).p;
    return "S003:Buy success,balance=" + rest_money_tobuy;
  }

  public static String c_cmd(String cmd) {
    if (rest_money_tobuy == 0) {
      return "E009:Work failure";
    }
//    int[] zhangs = new int[]{moneybox.get(1), moneybox.get(2), moneybox.get(5), moneybox.get(10)};
    int[] res = backMoney(rest_money_tobuy, moneybox);
    String ans = "1 yuan coin number=" + res[0] + "\n2 yuan coin number=" + res[1] +
        "\n5 yuan coin number=" + res[2] + "\n10 yuan coin number=" + res[3];
    return ans;
  }

  public static void q_cmd(String cmd) {
    if(!cmd.contains(" ")){
      System.out.println("E010:Parameter error");
      return;
    }
    String q = cmd.split(" ")[1];

    if (q.equals("0")) {
      PriorityQueue<Good> tmp = new PriorityQueue<Good>((o1, o2) -> o2.cnt - o1.cnt > 0 ? o2.cnt - o1.cnt : o1.index - o2.index);
      for(String key : goodsbox.keySet()){
        if(key.equals("total")){
          continue;
        }
        tmp.offer(goodsbox.get(key));
      }
      for(int i = 0; i < 6; i++){
        System.out.println(tmp.poll().name + " " + tmp.poll().p + " " + tmp.poll().cnt );
      }
    } else if(q.equals("1")){
      for(int i = 0; i < 4; i++){
        System.out.print(moneydict[i] + "yuan coin number=" + moneybox.get(moneydict[i]));
      }
    } else{
      System.out.println("E010:Parameter error");
    }
  }

  public static int[] backMoney(int rest_money, TreeMap<Integer, Integer> moneybox){
      int rest = rest_money;
      int[] ans = new int[4];
      for(int i = 3; i >= 0; i--){
        int mianzhi = moneydict[i];
        int cnt = moneybox.get(mianzhi);
        if(cnt == 0){continue;}
        if(rest % mianzhi == 0 && cnt >= rest / mianzhi){
          ans[i] = rest / mianzhi;
          cnt = cnt - ans[i];
          rest = 0;
          moneybox.put(mianzhi, cnt);
          rest_money_tobuy = 0;
          break;
        } else if (rest % mianzhi != 0){
          int need = rest / mianzhi;
          if(need <= cnt){
            cnt = cnt - need;
            rest = rest - mianzhi * need;
            ans[i] = need;
            moneybox.put(mianzhi, cnt);
          } else {
            cnt = 0;
            rest = rest - mianzhi * cnt;
            ans[i] = cnt;
            moneybox.put(mianzhi, cnt);
          }
        }
      }
      //一圈完事了，rest还剩余
      if(rest > 0) {
        int rest_need = 0;
        for (int i = 3; i >= 0; i--) {
          int mianzhi = moneydict[i];
          if(rest / mianzhi <= moneybox.get(mianzhi)){
            ans[i] = ans[i] + rest /mianzhi;
            rest = rest - rest / mianzhi * mianzhi;
            moneybox.put(mianzhi, moneybox.get(mianzhi) - rest / mianzhi);
          }
        }
      }
      rest_money_tobuy = rest;
      return ans;
  }

}


class Good {
  int index;
  String name;
  int p;
  int cnt;

  public Good(int index,String name, int p, int cnt) {
    this.index = index;
    this.name = name;
    this.p = p;
    this.cnt = cnt;
  }
}
