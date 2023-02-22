package com.mikemyzhao.TrackInTime.recursion.ClassicRecursion;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-09 11:24
 * @Description:给定一个二维数组作为字典(只可以通过上下左右四个方向)，给定要查找的字符串数组，得到能够从二维数组字典中找到的字符串集合
本题测试链接 : https://leetcode.com/problems/word-search-ii/
 */
public class WordSearch_26 {
  //思路：使用前缀树把所有待查的字符串接住。
  // 技巧：1、用end标记该节点是不是一个字符串的结尾。2、用pass记录有多少字符串用过该节点(这个主要是限制dfs在返回时候计算的次数！！！限制常数时间十分有用)
  public static class TrieNode {
    public TrieNode[] nexts;
    public int pass;
    public boolean end;

    public TrieNode() {
      nexts = new TrieNode[26];
      pass = 0;
      end = false;
    }
  }

  public static List<String> findWords(char[][] boards,String[] words){
    TrieNode head = new TrieNode();
    HashSet<String> set = new HashSet<>();
    for(String word : words){
      if(!set.contains(word)){
        fillWords(head,word);
        set.add(word);
      }
    }
    LinkedList<Character> path = new LinkedList<>();
    List<String> ans = new ArrayList<>();
    for(int row = 0; row < boards.length; row++){
      for(int col = 0; col < boards[0].length; col++){
        process(boards, row, col, path, head, ans);
      }
    }
    return ans;
  }

  //技巧：建立前缀树.把待查的字符串填入到前缀树中，注意：只要到达这个节点就把pass++，比如开始的heed
  public static void fillWords(TrieNode head, String word){
    head.pass++;
    char[] chs = word.toCharArray();
    int index = 0;
    TrieNode node = head;
    for(int i = 0; i < chs.length; i++){
      index = chs[i] - 'a';
      //如果没有这个节点，建出来
      if(node.nexts[index] == null){
        node.nexts[index] = new TrieNode();
      }
      //技巧：如果下一个节点有这个节点，往下推，当前节点node = next
      node = node.nexts[index];
      node.pass++;//技巧：这个next节点既然已经来了
    }
    node.end = true;
  }

  //思路：DFS的穷逼事情多，因为是递归要回去，所以把用的变量还得还原成他妈的开始时候的样子
  //当前来到board[row][col]，当前走过的路径是path，cur节点[走到的前缀树节点]是当前还没有记录在path中的节点，待检查能不能消除掉前缀树一个节点的计数
  // 通过TrieNode判断当前是否可以成为一个结果，如果是的话放入res中
  //技巧：返回从board[row][col]出发找到了多少str
  //技巧：使用DFS，把走过的位置用0标记，然后每次走完再还原回去
  public static int process(char[][] board, int row, int col, LinkedList<Character> path, TrieNode cur, List<String> res){
    char cha = board[row][col];
    if(cha == 0){
      return 0;
    }
    //技巧：一、先判断当前字典找到的cha是否有效，当前数的cur节点能否到这个cha，如果能到cha，在从这个cha位置向四周扩散
    //技巧：一|1 此时[row][col]是遍历的有效节点
    int index = cha - 'a';
    //往当前节点的后面看
    //技巧：一|2 如果没有路或者这个字符串在之前加入过
    if(cur.nexts[index] == null || cur.nexts[index].pass == 0){
      return 0;
    }
    //技巧：一|3 这里隐含的就是cur.nexts[index]存在，其没有被遍历过
    cur = cur.nexts[index];//技巧：前缀树走：当前节点可以顺着前缀树往下出溜。
    path.addLast(cha);

    //技巧：二、当前这个cha已经可以让cur往下顺了，说明可以从这个cha往四周找，继续然这个前缀树节点往下出溜
    int fix = 0;//统计从当前节点可以覆盖多少个字符串
    //技巧 二|0先把当前节点处理了，看看是不是收尾节点
    if(cur.end){//如果当前是end
      res.add(generatePath(path));
      cur.end = false;//技巧：二|1 统计完的end就给他清了.....好烦啊各种踩点
      fix++;
    }
    board[row][col] = 0;//技巧： 二|1 当前字典的字处理完了，标记！！！
    //技巧： 二|2 字典走往四个方向递归去吧
    if(row > 0){
      fix += process(board, row - 1, col, path, cur, res);
    }
    if(row < board.length - 1){
      fix += process(board, row + 1, col, path, cur, res);
    }
    if(col > 0){
      fix += process(board, row, col - 1, path, cur, res);
    }
    if(col < board[0].length - 1){
      fix += process(board, row, col + 1, path, cur, res);
    }
    //技巧：三、因为是DFS，所以要还原下现场，
    board[row][col] = cha;//技巧：这个没的说，得把自己节点换回去
    path.pollLast();//技巧：每次走的位置也要还回去，因为这个path最后一个点可能是别的字符走
    cur.pass -= fix;//技巧：当前节点消掉了那些
    return fix;

  }

  public static String generatePath(LinkedList<Character> path){
    char[] str = new char[path.size()];
    int index = 0;
    for(char c : path){
      str[index++] = c;
    }
    return String.valueOf(str);
  }
}
