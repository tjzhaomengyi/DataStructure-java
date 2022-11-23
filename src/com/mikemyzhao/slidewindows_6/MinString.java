package com.mikemyzhao.slidewindows_6;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-03 12:56
 * @Description:滑动窗口最终模板，代码很对称！两个while
 * @NO:LC76，最小字符串在字符串s中找出符合字符串t的最小字符串，这个是一个明显的滑动窗口模板
 * 例子最小子串思路
 * (1)在字符串s中使用双指针的左右指针技巧，初始化left=right=0，把索引设置为左闭右开区间[left,right)称为一个窗口。
 * (2)我们先不断增加right指针扩大窗口[left,right),知道窗口中的字符串符合要求(包含了T中所有字符)
 * (3)此时，我们停止增加right，转而不断增加left指针，缩小窗口[left,right)，直到窗口中的字符串不再符合要求(不包含T中的所有字符了)。同时每次增加left，我们都要更新一轮结果
 * (4)重复(2)和(3)知道right到达s字符串的尽头
 * 其中，第(2)步相当于在寻找一个可行解，然后第(3)步在优化这个可行解，最终找到最优解，也就是最短的覆盖子串。左右指针轮流前进，窗口大小增增减减，窗口不断向右滑动。
 */
public class MinString {
  /**
   int left=0,right=0;
   int valid = 0;
   unordered_map<char,int>need,window
   for(char c:t) need[c]++;
   while(right<s.size()){
   //c是将移入窗口的字符
   char c = s[right];
   //右移窗口
   right++；
   //进行窗口一些列更新
   ....
   //debug输出
   printf("window:%d,%d",left,right)
   while(window needs shrink){
   //d是将移出窗口的字符
   cher d = s[left];
   //左移窗口
   left++;
   //进行窗口内的数据的一系列更新
   }
   }
   **/
  //s是被查找的长串，t是目标字符串
  String minWindow(String s,String t) {
    //1、初始化window和need两个哈希表，记录窗口中的字符和需要凑齐的字符
    Map<Character, Integer> need = new HashMap<Character, Integer>();//存储t中字符和个数
    Map<Character,Integer> window = new HashMap<Character, Integer>();//存储窗口中的char和个数
    for (char c :t.toCharArray()) {
      need.put(c,need.getOrDefault(c,0)+1);
    }
    int left =0,right=0;//窗口左右指针
    int valid = 0;//表示window中包含need字符的个数
    int start=0,len=Integer.MAX_VALUE;//存储最终结果的起始下标和长度，这个是最后要的结果
    /**四个问题：(1)当移动right扩大窗口，即加入字符时，应该更新哪些数据？
     * (2)什么条件下，窗口应该暂停扩大，开始移动left缩小窗口
     * (3)当移动left缩小窗口时，即移出字符时，应该更新哪些数据
     * (4)我们要的结果应该在扩大窗口还是缩小窗口时进行更新
     * 答：一般来说，如果一个字符进入窗口，应该增加window计数器；如果一个字符将移出窗口，应该减少window计数器；
     * 当valid满足need时应该收缩窗口；收缩窗口的时候应该更新最终结果
     * **/

    while(right<s.length()){
      char c = s.charAt(right);//取当前位置s的字符
      /**重点：开始更新右侧指针扫描到的字符，(1)判断是否加入窗口，(2)更新valid值**/
      right++;//右移动窗口
      if(need.containsKey(c)){//如果字符串t包含当前字符c，更新窗口中的字符
        window.put(c,window.getOrDefault(c,0)+1);
        if(window.get(c).equals(need.get(c))){//只有window中与need同时包含该字符，个数也相同，才更新valid
          valid++;
        }
      }

      while(valid == need.size()){//此时window已经包含了t的所有字符，但是要收缩left求最小字符
        /**移动左指针前先更新**/
        if(right - left < len){//如果小于当前len，更新start和len
          start = left;
          len = right-left;
        }
        char remove = s.charAt(left);//窗口最左端字符
        left++;
        //左指针缩小窗口
        if(need.containsKey(remove)){//如果t对应need包含左侧要删除的字符，并且window和need中该字符相等，那么就可以缩小窗口
          /**注意：这里的valid--和windows元素减少不要颠倒顺序，先减少距离再更新window元素个数**/
          if(window.get(remove).equals(need.get(remove))){
            //如果是window这个值和need[t]中的值一样，然后还减少了，就-1。说明windows中删除了一个need中的值，这样window刚好对应need的开头，前面没有富裕，跳出while，返回start
            valid--;
          }
          window.put(remove,window.get(remove)-1);//不断增加left使滑动窗口缩小，将不必要的字符排除，长度减小
        }
      }

    }
    return len==Integer.MAX_VALUE?"":s.substring(start,start+len);
  }

  public static void main(String[] args) {
    System.out.print(new MinString().minWindow("ADOBECODEBANC","ABC"));
  }

}
