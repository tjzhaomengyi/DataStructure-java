package com.huaweiOD.od2023.s120e138;

import java.util.*;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-17 18:42
 * @Description:https://dream.blog.csdn.net/article/details/129191246
 */
public class XuanshouPIngfen {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String str = in.nextLine();
      String[] nums = str.split(",");
      //
      int jiaolian = Integer.parseInt(nums[0]);
      int xuanshou = Integer.parseInt(nums[1]);
      if(jiaolian > 10 || jiaolian < 3 || xuanshou > 100 || xuanshou < 3){
        System.out.println(-1);
        return;
      }

      List<String[]> myList = new ArrayList<>();
      for(int i = 0; i < jiaolian; i++){
        myList.add(in.nextLine().split(","));
      }

      //收集选手信息
      List<Person> persons = new ArrayList<>();
      for(int i = 0; i < xuanshou; i++){
        int total = 0;
        List<Integer> scoreList = new ArrayList<>();
        for(int j = 0; j < jiaolian; j++){
          String[] strings = myList.get(j);
          int score = Integer.parseInt(strings[i]);
          if(score < 0 || score > 10){
            System.out.println(-1);
            return;
          }
          scoreList.add(score);
          total += score;
        }
        persons.add(new Person(i, total, scoreList));
      }
      Collections.sort(persons);
      for(int i = 0; i < 3; i++){
        if(i == 2){
          System.out.println(persons.get(i).id + 1);
        } else {
          System.out.print(persons.get(i).id + 1 + ",");
        }
      }

    }
  }

}


class Person implements Comparable<Person>{
  int id;
  int total;
  List<Integer> scores;

  public Person(int id, int total, List<Integer> scores){
    this.id = id;
    this.total = total;
    this.scores = scores;
  }

  private int checkOut(List<Integer> list, int count){
    int cou = 0;
    for(int i = 0; i < list.size(); i++){
      if(list.get(i) == count){
        cou++;
      }
    }
    return cou;
  }



  @Override
  public int compareTo(Person ply) {
    if(ply.total != this.total){
      return ply.total - this.total;
    } else {
      List<Integer> scPlyList = ply.scores;
      List<Integer> scores = this.scores;
      for(int i = 10; i > 0; i--){
        int ipl = checkOut(scPlyList, i);
        int ith = checkOut(scores, i);
        if(ipl != ith){
          return ipl - ith;
        }
      }
    }
    return 0;
  }
}