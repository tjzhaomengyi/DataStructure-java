package com.mikemyzhao.rawcollections;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-02 14:03
 * @Description:Java常用集合操作
 */
public class Collections_OP {
  public static void main(String[] args) {
    /**
     * 1、数组
     */
    int m=5,n=10;
    //初始化一个大小为10的int数组，默认值为0
    int[] nums = new int[n];

    //初始化一个m*n的二维布尔数组
    //其中元素默认为false
    boolean[][] visited = new boolean[m][n];

    /**
     * 2、字符串,Java字符串不像c++，需要转换成char[]类型后才能修改
     */
    String s1 = "hello world";
    //获取s1[2]中的字符
    char c1 = s1.charAt(2);

    char[] chars = s1.toCharArray();
    chars[1] = 'a';
    String s2 = new String(chars);
    System.out.println(s2);

    //使用equal判断字符串是否相同
    if(s1.equals(s2)){

    }else {

    }
    //不建议使用+做字符串连接，尤其在for循环中
    StringBuilder sb = new StringBuilder();
    for(char c='a';c<'f';c++){
      sb.append(c);
    }
    //append方法支持拼接字符、字符串、数字等类型
    sb.append('g').append("hij").append(123);
    String res = sb.toString();
    System.out.println(res);
  }

  /**
   * 3、动态数组ArrayList
   */
  //isEmpty()、size()、get()、add()
  /**
   * 4、双链表LinkedList,小抄中更多使用LinkedList对于头部和尾部的操作，这样效率最高，只有contains的方法的时间复杂度最高O(n),因为需要遍历整个
   * 题目要求返回List类型，返回ArrayList和LinkedList都可以，因为是子类
   */
  //isEmpty()、size()、contains()、add()、addFirst()、removeFirst()、removeLast()

  /**
   * 5、哈希表HashMap
   */
  //containsKey(Object key)、get(key)、put(key,value),remove(key)、getOrDefault(key,defaultValue)、keySet()、putIfAbsent(key,value)
  /**
   * 6、HashSet
   */
  //add()、contains(o)、remove()
  /**
   * 7、队列Queue()
   */
  //isEmpty()、size()、peek()、poll()、offer()
  /**
   * 8、堆栈Stack
   */
  //isEmpty()、size()、push()、peek()、pop()
}
