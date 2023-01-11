package com.hotinterview.structure;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-06 12:39 下午
 * @Description:
 */

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * // This is the interface that allows for creating nested lists.
 * // You should not implement it, or speculate about its implementation
 */

interface NestedInteger {

     // @return true if this NestedInteger holds a single integer, rather than a nested list.
     public boolean isInteger();

     // @return the single integer that this NestedInteger holds, if it holds a single integer
     // Return null if this NestedInteger holds a nested list
     public Integer getInteger();

     // @return the nested list that this NestedInteger holds, if it holds a nested list
     // Return empty list if this NestedInteger holds a single integer
     public List<NestedInteger> getList();
 }

 //思路：使用栈 + 递归
public class Code0341_NestedListIterator implements Iterator<Integer> {
  private List<NestedInteger> list;
  private Stack<NestedInteger> stack;
  private boolean used;
  public Code0341_NestedListIterator(List<NestedInteger> nestedList) {
    //
    for(int i = nestedList.size() - 1; i >= 0; i--){
      NestedInteger item = nestedList.get(i);
      stack.push(item);//这里也不判断了，直接就往里塞
    }
  }

  @Override
  public Integer next() {
    return hasNext() ? stack.pop().getInteger() : -1;
  }

  @Override
  public boolean hasNext() {
    if(stack.isEmpty()){
      return false;
    } else{
      NestedInteger item = stack.peek();
      if(item.isInteger()){//只看顶不弹出
        return true;
      } else {
        //看完弹出再往里塞
        item = stack.pop();
        List<NestedInteger> list = item.getList();
        for(int i = list.size() - 1; i >= 0; i--){
          stack.push(list.get(i));
        }
      }
    }
   return hasNext();//就是给else准备的递归
  }



}
