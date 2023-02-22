package com.point2offerspecial.ten_PreTree;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 10:49
 * @Description:
 */
public class PTOS064_MagicDictionary {
  /** Initialize your data structure here. */
  public class TrieNode{
    TrieNode[] children;
    boolean isWord;

    public TrieNode(){
      children = new TrieNode[26];
    }
  }

  private TrieNode root;
  public PTOS064_MagicDictionary() {
    root = new TrieNode();
  }

  public void buildDict(String[] dictionary) {
    for(String word : dictionary){
      TrieNode node = root;
      for(char ch : word.toCharArray()){
        if(node.children[ch - 'a'] == null){
          node.children[ch - 'a'] = new TrieNode();
        }
        node = node.children[ch - 'a'];
      }
      node.isWord = true;
    }
  }

  //技巧：查找在给出的字符串中只修改一个字符
  public boolean search(String searchWord) {
    return dfs(root, searchWord, 0, 0);
  }

  //i表示起始位置，edit表示表示当前修改字符的个数
  private boolean dfs(TrieNode root, String searchword, int i, int edit){
    if(root == null) return false;
    if(root.isWord && i == searchword.length() && edit == 1){ //成立条件
      return true;
    }
    if(i < searchword.length() && edit <= 1){//没有执行编辑，编辑了一次都可以，只要还没到word的长度，就继续递归进行判断
      boolean found = false;
      for(int j = 0; j < 26 && !found; j++){
        //技巧：下面这两步巧妙的避开了searchword中的字符和字典树中字符判断的操作
        int next_edit = j == searchword.charAt(i) - 'a' ? edit : edit + 1; //用26个字母替换当前位置的字母
        //太难了~直接记住例子吧：这里是这道题的解题另一个关键点
        found = dfs(root.children[j], searchword, i + 1, next_edit);//技巧：以children[j]为头检查是否符合dfs的判断条件
      }
      return found;
    }
    return false;
  }

  public static void main(String[] args) {
    PTOS064_MagicDictionary magicDictionary = new PTOS064_MagicDictionary();
    magicDictionary.buildDict(new String[]{"hello", "leetcode"});
   // magicDictionary.search("hello"); // 返回 False
    magicDictionary.search("hhllo"); // 将第二个 'h' 替换为 'e' 可以匹配 "hello" ，所以返回 True
    //magicDictionary.search("hell"); // 返回 False
    //magicDictionary.search("leetcoded"); // 返回 False
  }

}
