package com.book.point2offerspecial.eight_tree.four_treemap_treeset;

import java.util.Map;
import java.util.TreeMap;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-12 15:04
 * @Description:
 */
public class PTOS058_MyCalendar {
  //技巧：使用TreeMap存储开始和结束时间
  private TreeMap<Integer, Integer> events;
  public PTOS058_MyCalendar() {
    events = new TreeMap<>();
  }

  public boolean book(int start, int end) {
    Map.Entry<Integer, Integer> event = events.floorEntry(start);
    if(event != null && event.getValue() > start){
      return false;
    }
    event = events.ceilingEntry(start);
    if(event != null && event.getKey() < end){
      return false;
    }
    events.put(start, end);
    return true;
  }
}
