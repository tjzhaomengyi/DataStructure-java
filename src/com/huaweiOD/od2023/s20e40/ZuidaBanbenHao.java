package com.huaweiOD.od2023.s20e40;

import com.sun.deploy.util.StringUtils;

import java.util.Comparator;
import java.util.Scanner;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-24 12:12
 * @Description:
 */
public class ZuidaBanbenHao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String ver1 = in.nextLine().replaceAll("-", ".");
      String ver2 = in.nextLine().replaceAll("-", ".");

      String[] ver1_arr = ver1.split("\\.");
      String[] ver2_arr = ver2.split("\\.");
      Version v1 = makeVersion(ver1_arr, ver1);
      Version v2 = makeVersion(ver2_arr, ver2);
      //技巧:因为是从大到小排列，所以下面写好是后面-前面，然后这个结果大于0就是后面的大
      String ans = v1.compareTo(v2) > 0 ? v2.verName : v1.verName;
      System.out.println(ans);
    }
  }

  public static Version makeVersion(String[] arr, String ver) {
    String main = "";
    String sec = "";
    String add = "";
    String mile = "";
    String verName = ver;
    for (int i = 0; i < arr.length; i++) {
      if (i == 0) {
        main = arr[0];
      } else if (i == 1) {
        sec = arr[1];
      } else if (i == 2) {
        add = arr[2];
      } else if (i == 3) {
        mile = arr[3];
      }
    }
    return new Version(main, sec, add, mile, verName);
  }

}


class Version implements Comparable<Version> {
  public String main;
  public String second;
  public String add;
  public String mileStone;
  public String verName;


  public Version(String main, String second, String add, String mileStone, String verName) {
    this.main = main;
    this.second = second;
    this.add = add;
    this.mileStone = mileStone;
    this.verName = verName;
  }


  //技巧:从大到小排序，如果成立的话compareTo返回>0，注意字符比较返回是两个字母的ASCII码的差值。。。。无语
  @Override
  public int compareTo(Version o) {
    if (!this.main.equals(o.main)) {
      return o.main.compareTo(this.main);
    } else if (!this.second.equals(o.second)) {
      return o.second.compareTo(this.second);
    } else if (!this.add.equals(o.add)) {
      return o.add.compareTo(this.add);
    } else if (!this.mileStone.equals(o.mileStone)) {
      return o.mileStone.compareTo(this.mileStone);
    } else {
      return o.verName.length() - this.verName.length();
    }
  }
}

