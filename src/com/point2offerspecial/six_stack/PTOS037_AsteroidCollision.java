package com.point2offerspecial.six_stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-07 13:18
 * @Description:
 */
public class PTOS037_AsteroidCollision {
  public int[] asteroidCollision(int[] asteroids) {
    if(asteroids == null) return null;
    Stack<Integer> store = new Stack<>();
    for(int ast : asteroids){
      if(ast > 0){
        store.push(ast);
      } else { //ast小于0开始进行比较,新进来的小行星只有在碰到大于0的时候才比较
        while(!store.isEmpty() && store.peek() > 0 && store.peek() < -ast){
          store.pop();
        }
        if(!store.isEmpty() && ast < 0 && store.peek() == -ast){
          store.pop();
        } else if(ast > 0 || store.isEmpty() || store.peek() < 0){
          store.push(ast);
        }
      }
    }


    return store.stream().mapToInt(i -> i).toArray();
  }

}
