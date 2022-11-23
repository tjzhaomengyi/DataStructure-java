package com.mikemyzhao.Tanxin_2_3.bigshua;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-14 20:33
 * @Description:hard数组表示工作难度，money数组表示工作提供的薪水，每份工作都有无穷多的岗位，求每个人挣钱最多工作安排的数组
 */
public class ChooseJob_2 {
  public static class Job{
    public int money;
    public int hard;

    public Job(int m,int h){
      money = m;
      hard = h;
    }
  }

  //定义工作的比较器，把难度低，工资高的放在前面
  public static class JobComparator implements Comparator<Job> {

    @Override
    public int compare(Job o1, Job o2) {
      return o1.hard != o2.hard?(o1.hard-o2.hard):(o2.money - o1.money);
    }
  }

  //计算工作安排的主函数
  //abiblity表示每个员工的能力
  public static int[] getMoneys(Job[] job,int[] ability){
    Arrays.sort(job,new JobComparator());
    //根据工作难度把工作存在treemap中
    TreeMap<Integer,Integer> map = new TreeMap<>();
    map.put(job[0].hard,job[0].money);//第一个肯定要放工作强度最低，工资在强度最低中最高
    Job pre = job[0];
    for(int i = 1; i < job.length; i++){
      //把相同hard，不同钱的job清除掉，只留最高钱;如果是强度大，但是钱少的肯定也不要
      if(job[i].hard != pre.hard && job[i].money > pre.money){
        pre = job[i];
        map.put(pre.hard,pre.money);
      }
    }

    int[] ans = new int[ability.length];
    for(int i = 0; i < ability.length; i++){
      Integer key = map.floorKey(ability[i]);//找到刚好小于等于当前ability的key
      ans[i] = key != null ? map.get(key) : 0;
    }
    return ans;
  }
}
