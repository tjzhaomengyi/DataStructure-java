package com.huaweiOD.od2023.s100e119;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-18 13:09
 * @Description:
 */
public class ShexingJuzhen {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextInt()) {
      int len = in.nextInt();
      int[][] ans = new int[len][];
      int t = 1;
      for(int i = 0; i < len; i++){
        ans[i] = new int[len - i];
        for(int j = 0; j <= i; j++){ // 填写对角线
          ans[i - j][j] = t;
          t++;
        }
      }
      for(int[] a : ans){
        for(int n : a){
          System.out.print(n + " ");
        }
        System.out.println();
      }
    }
  }




}
