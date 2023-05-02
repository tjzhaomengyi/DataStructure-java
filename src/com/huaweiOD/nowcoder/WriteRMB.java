package com.huaweiOD.nowcoder;

import java.util.HashMap;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-13 17:43
 * @Description:
 */
public class WriteRMB {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    // 注意 hasNext 和 hasNextLine 的区别
    String[] num_dict = new String[]{"零","壹","贰","叁","肆","伍","陆","柒","捌","玖"};
    String[] part_sign = new String[]{"","拾","佰","仟","万","亿"};
    String[] money_sign  = new String[]{"元","角","分"};
    while (in.hasNextDouble()) { // 注意 while 处理多个 case
      double rmb = in.nextDouble();
      String rmbstr = String.format("%.2f",rmb);
      String ans = "人民币";
      String big_str = rmbstr.split("\\.")[0];
      String small_str = rmbstr.split("\\.")[1];

      int m  = big_str.length();
      int n = small_str.length();


        for(int i = 0; i < m ; i++){
          int tr = m - 1 - i;
          int part_index = tr / 4;
          //todo:
          int inner_index = tr % 4;
          char c = big_str.charAt(i);

          String write_num = num_dict[Character.getNumericValue(c)];
          if(inner_index == 0 && c == '0' && i - 1 >= 0){
            if(big_str.charAt(i - 1) == '1'){
              write_num = "";
            }
          }
          if(inner_index == 1 && c == '1' && i + 1 <= m - 1){
            write_num = "";
          }
          String write_in_sign = part_sign[inner_index];
          if(c == '0'){
            write_in_sign = "";
          }
          ans = ans + write_num + write_in_sign;


          if(inner_index == 0 && part_index >= 1){
            String write_part_sign = part_index == 1 ? part_sign[4] : part_sign[5];
            ans = ans + write_part_sign;
          }
        }
        if(ans.replace("零","").length() == 3){
          ans = "人民币";
        } else {
          ans = ans + money_sign[0];
        }
        if(small_str.charAt(0) == '0' && small_str.charAt(1) == '0'){
          ans = ans + "整";
          System.out.println(ans);
        } else if(small_str.charAt(0) == '0' && small_str.charAt(1) != '0'){
          ans = ans +  num_dict[Character.getNumericValue(small_str.charAt(1))] + money_sign[2];
          System.out.println(ans);
        } else if(small_str.charAt(0) != '0' && small_str.charAt(1) =='0'){
          ans = ans + num_dict[Character.getNumericValue(small_str.charAt(0))] + money_sign[1] ;
          System.out.println(ans);
        } else {
          ans = ans + num_dict[Character.getNumericValue(small_str.charAt(0))] + money_sign[1]
              + num_dict[Character.getNumericValue(small_str.charAt(1))] + money_sign[2];
          System.out.println(ans);
        }
      }


  }


}
