package com.mikemyzhao.data_structure_design.NextAndHasNext;

import com.MCAAlgorithm.bigshua.class34.Problem_0341_FlattenNestedListIterator;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;



public class LC_0341_FlattenList {
  // 不要提交这个接口类
  public interface NestedInteger {

    // @return true if this NestedInteger holds a single integer, rather than a
    // nested list.
    public boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a
    // single integer
    // Return null if this NestedInteger holds a nested list
    public Integer getInteger();

    // @return the nested list that this NestedInteger holds, if it holds a nested
    // list
    // Return null if this NestedInteger holds a single integer
    public List<NestedInteger> getList();
  }

  class NestedIterator implements Iterator<Integer> {

    //用 栈 + 递归
    Stack<NestedInteger> stack = new Stack();

    public NestedIterator(List<NestedInteger> list) {
      for (int i = list.size() - 1; i >= 0; i--) {
        NestedInteger item = list.get(i);
        stack.push(item);
      }
    }

    @Override
    public boolean hasNext() {
      if(stack.isEmpty()){
        return false;
      } else{
        NestedInteger item = stack.peek();
        if(item.isInteger()){
          return true;
        } else {
          item = stack.pop();
          List<NestedInteger> list = item.getList();
          for(int i = list.size() - 1; i >= 0; i--){
            stack.push(list.get(i));
          }
        }
      }
      return hasNext();
    }

    @Override
    public Integer next() {
      return hasNext() ? stack.pop().getInteger() : -1;
    }
  }
}