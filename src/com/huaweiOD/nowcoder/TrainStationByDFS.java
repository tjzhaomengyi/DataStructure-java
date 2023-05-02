package com.huaweiOD.nowcoder;

import java.util.*;


/***
 * 出栈的所有可能
 */
public class TrainStationByDFS {
  public static void dfs(Stack<Integer> stack, List<Integer> path, List<Integer> step, int index, int n,
                         List<String> ans) {
    if (index == n && stack.empty()) {
      String str = "";
      for (int i : path) {
        str += Integer.toString(i) + " ";
      }
      ans.add(new String(str));
      return;
    }
    if (index < n) {
      stack.push(step.get(index)); //放入栈中
      dfs(stack, path, step, index + 1, n, ans);
      stack.pop();
    }
    if (stack.size() > 0) {
      int tmp = stack.peek();
      path.add(tmp);
      stack.pop();
      dfs(stack, path, step, index, n, ans);
      stack.push(tmp);
      path.remove(path.size() - 1);
    }
  }

  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    int n = in.nextInt();
    Stack<Integer> stack = new Stack<Integer>();
    List<Integer> path = new ArrayList<Integer>();
    List<Integer> step = new ArrayList<Integer>();
    List<String> ans = new ArrayList<String>();
    for (int i = 0; i < n; i++) {
      step.add(in.nextInt());
    }
    stack.push(step.get(0));
    dfs(stack, path, step, 1, n, ans);//这个初始化时关键
    Collections.sort(ans);
    for (String i : ans) {
      System.out.println(i);
    }
  }
}