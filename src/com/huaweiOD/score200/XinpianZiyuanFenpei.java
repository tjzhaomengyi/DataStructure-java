package com.huaweiOD.score200;

import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-22 10:04
 * @Description:
 */
public class XinpianZiyuanFenpei {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      int board_cap = Integer.valueOf(in.nextLine());
      int number_of_chips = Integer.valueOf(in.nextLine());
      String chip_configs = in.nextLine();

      int[] chip_capacity = new int[number_of_chips];
      int configuration_capacity;
      for(int i = 0; i < chip_configs.length(); i++){
        char chip_type = chip_configs.charAt(i);
        switch (chip_type){
          case 'A':
            configuration_capacity = 1;
            break;
          case 'B':
            configuration_capacity = 2;
            break;

          default:
            configuration_capacity = 8;
            break;
        }
        for(int j = 0; j < number_of_chips; j++){
          int used_capacity = chip_capacity[j];
          if(board_cap - used_capacity >= configuration_capacity){
            chip_capacity[j] += configuration_capacity;
            break;
          }
        }
      }
      for(int i = 0; i < number_of_chips; i++){
        StringBuilder chip_configuration = new StringBuilder();
        int used_capacity = chip_capacity[i];
        for(int j = 0; j < board_cap; j++){
          chip_configuration.append(j < used_capacity ? "1" : "0");
        }
        System.out.println(chip_configuration);
      }
    }
  }


}
