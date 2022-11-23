package com.mikemyzhao.SomeSkills_0.bigshua.ShuWeiWenTi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-07 14:02
 * @Description:本题测试链接 : https://leetcode.com/problems/ip-to-cidr/
 * 分配IP地址，使用CIDR做子网掩码，求最经济的方式，获得要求数量的子网IP地址数
 */
public class IPToCIDR_25 {
  //网络地址32位
  public static List<String> ipToCIDR(String ip, int n){
    //ip:32位
    int cur = status(ip);
    //cur当前这个二进制表示，最右侧的1，能表示2的几次方
    int maxPowerByR1 = 0;
    //已经解决了多少ip
    int solved = 0;
    int power = 0;//临时变量
    List<String> ans = new ArrayList<>();
    while(n > 0){
      maxPowerByR1 = mostRightPower(cur);
      solved = 1;
      power = 0;
      while((solved << 1) <= n && (power + 1) <= maxPowerByR1){
        solved <<= 1; //把solved个数提升
        power++;//同时power+1
      }
      ans.add(content(cur,power));
      n -= solved;
      cur += solved;
    }
    return ans;
  }
  //技巧：如何把ip地址转为二进制表示
  public static int status(String ip){
    int ans = 0;
    int move = 24;
    for (String str : ip.split("\\.")){
      ans |= Integer.valueOf(str) << move;
      move -= 8;
    }
    return ans;
  }
  //技巧：计算最右侧1是2多几次方,这里把ip地址32位每个1都算一下放到map中,key是2^n,value是n
  public static HashMap<Integer, Integer> map = new HashMap<>();
  public static int mostRightPower(int num){
    if(map.isEmpty()){
      //从左往右
      map.put(0,32);
      for(int i = 0; i < 32; i++){
        map.put(1 << i, i);
      }
    }
    return map.get(num & (-num));
  }

  //把当前1算完的结果还原成字符串格式
  public static String content(int status, int power){
    StringBuilder builder = new StringBuilder();
    for(int move = 24; move >= 0; move -= 8){
      //技巧：tmp = status & (255 << move) 最后一个1前面，先推到当前的高位上，
      // 然后为了转成对应字符串再把这个位置推到低位上！tmp >>> move[无符号推]
      int tmp = status & (255 << move);
      builder.append((tmp >>> move) + ".");
    }
    builder.setCharAt(builder.length() - 1,'/');
    builder.append(32-power);//掩码为32-有效位
    return builder.toString();
  }

}
