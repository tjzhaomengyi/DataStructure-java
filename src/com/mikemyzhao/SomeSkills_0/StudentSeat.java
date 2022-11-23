package com.mikemyzhao.SomeSkills_0;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-14 15:29
 * @Description:给学生排座，要求新插进来的离之前来的距离最远
 * 思路：1、插中间就完了,
 *  2、在动态过程中求最值，最好使用二叉堆和平衡二叉搜索树。二叉堆在实现的过程中使用的优先级队列,数组实现，时间复杂度是O(logn),但是只能删除最大值，二叉堆的最大特点是数据时序有序。
 *  平衡二叉树也可以取最值，也可以修改，可以删除任意一个值，时间复杂度也是O(logN).两者区别：https://blog.csdn.net/weixin_43342105/article/details/105238455
 *  所以，二叉堆不满足要求，使用平衡二叉树，java中treeset使用平衡二叉树实现，底层是红黑树，特性是维护其中元素的顺序，是一种"有序集合/映射"，可以实现数据的快速查找、删除和插入
 * */
public class StudentSeat {


  class ExamRoom{
    private Map<Integer,int[]> startMap;//将端点p映射到以p为左端的线段
    private Map<Integer,int[]> endMap;//将端点p映射到以p为右端点的线段
    private TreeSet<int[]> pq;//根据线段长度从小到大存放所有线段
    private int N;
    //构造函数传入座位总数
    public ExamRoom(int N) {
      this.N=N;
      startMap = new HashMap<>();
      endMap = new HashMap<>();
      pq = new TreeSet<>((a,b)->{
        //算出两个线段的长度
        int distA = distance(a);
        int distB = distance(b);
        if(distA==distB){
          return b[0]-a[0];
        }
        //长度更长的，排后面
        return distA-distB;
      });
      addInterval(new int[]{-1,N});
    }
    //去除线段
    private void removeInterval(int[] intv){
      pq.remove(intv);
      startMap.remove(intv[0]);
      endMap.remove(intv[1]);
    }
    //增加线段
    private void addInterval(int[] intv){
      pq.add(intv);
      startMap.put(intv[0],intv);
      endMap.put(intv[1],intv);
    }
    private int distance(int[] intv){
//      return intv[1]-intv[0]-1;
      int x=intv[0];
      int y = intv[1];
      if(x==-1) return y;
      if(y==N) return N-1-x;
      return (y-x)/2;//找到和重点最近的
    }
    //来了一名考生，返回分配的座位
     public int seat(){
        //从有序集合拿出最长的线段
       int[] longest = pq.last();
       int x = longest[0];
       int y = longest[1];
       int seat;
       if(x==-1){
         //情况1，最左边没人的话肯定坐最左边
         seat =0;
       }else if(y==N){
         seat=N-1;//最右边没人坐最右边
       }else{
         seat = (y-x)/2+x;//坐中间，防止溢出写法
       }
       //将最长的线段分隔成两段
       int[] left = new int[]{x,seat};
       int[] right = new int[]{seat,y};
       removeInterval(longest);
       addInterval(left);
       addInterval(right);
       return seat;
     }
     //坐在p位置的考生离开了
    public void leave(int p){
      //将p左右的线段找出来
      int[] right = startMap.get(p);
      int[] left = endMap.get(p);
      //合二为一
      int[] merged = new int[]{left[0],right[1]};
      //删除旧的线段，添加新的线段
      removeInterval(left);
      removeInterval(right);
      addInterval(merged);
    }


  }



}
