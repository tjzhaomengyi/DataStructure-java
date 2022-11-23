package com.mikemyzhao.TreeOperations_2.bigshua;

import com.MCAAlgorithm.base.class33.Hash;
import com.MCAAlgorithm.bigshua.class03.Code08_DistanceKNodes;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-16 13:53
 * @Description:
 */
public class DistanceNode_3 {
  public static class Node {
    public int value;
    public Node left;
    public Node right;

    public Node(int v) {
      value = v;
    }
  }

  //求距离为k的所有树节点
  public static List<Node> distanceKNodes(Node root, Node target, int K){
    HashMap<Node, Node> parents = new HashMap<>();
    parents.put(root,null);
    createParentMap(root,parents);
    Queue<Node> queue = new LinkedList<>();
    HashSet<Node> visited = new HashSet<>();
    queue.offer(target);
    visited.add(target);
    int curLevel = 0;
    List<Node> ans = new ArrayList<>();
    while(!queue.isEmpty()){
      int size = queue.size();
      while (size-- > 0){
        Node cur = queue.poll();
        if(curLevel == K){
          ans.add(cur);
        }
        if(cur.left != null && !visited.contains(cur.left)){
          visited.add(cur.left);
          queue.offer(cur.left);
        }
        if(cur.right != null && !visited.contains(cur.right)){
          visited.add(cur.right);
          queue.offer(cur.right);
        }
        if(parents.get(cur) != null && !visited.contains(parents.get(cur))){
          visited.add(parents.get(cur));
          queue.offer(parents.get(cur));
        }
      }
      curLevel++;
      if(curLevel > K){
        break;
      }
    }
    return ans;
  }

  public static void createParentMap(Node cur, HashMap<Node,Node> parents){
    if(cur == null){
      return;
    }
    if(cur.left != null){
      parents.put(cur.left, cur);
      createParentMap(cur,parents);
    }
    if(cur.right != null){
      parents.put(cur.right, cur);
      createParentMap(cur.right, parents);
    }
  }
}
