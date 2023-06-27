package com.zhousai.zhousai345;

/**
 * @Author: zhaomengyi
 * @Date: 2023-05-14 10:56
 * @Description:
 */
public class Xianglinzhianweiyihuo {
  public boolean doesValidArrayExist(int[] derived) {
    int len = derived.length;
    int[] org0 = new int[len];
    int[] org1 = new int[len];
    org1[0] = 1;
    for(int i = 1; i < len; i++){
      org0[i] = org0[i - 1] ^ derived[i - 1];
      org1[i] = org1[i - 1] ^ derived[i - 1];

    }

    return (derived[len - 1] == (org0[len - 1] ^ org0[0])) || (derived[len - 1] == (org1[len - 1] ^ org1[0]));

  }
}
