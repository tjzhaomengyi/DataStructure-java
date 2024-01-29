package com.book.point2offerspecial.ten_PreTree;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-13 10:02
 * @Description:
 */
public class PTOS063_ReplaceWords {
  public static class TrieNode{
    public TrieNode[] children;
    public boolean isWord;

    public TrieNode(){
      children =  new TrieNode[26];
    }
  }

  private TrieNode root;

  class TrieTree{
    public TrieTree(){
      root = new TrieNode();
    }
  }

  //根据前缀字典，每个结束用true
  public TrieNode buildTrie(List<String> dict){
    TrieNode root = new TrieNode();
    for(String word : dict){
      TrieNode node = root;
      for(char ch : word.toCharArray()){
        if(node.children[ch - 'a'] == null){
          node.children[ch - 'a'] = new TrieNode();
        }
        node = node.children[ch - 'a'];
      }
      node.isWord = true;
    }
    return root;
  }

  public String findPrefix(TrieNode root, String word){
    TrieNode node = root;
    StringBuilder sb = new StringBuilder();
    for(char ch : word.toCharArray()){
      if(node.isWord || node.children[ch - 'a'] == null){
        break;
      }
      sb.append(ch);
      node = node.children[ch - 'a'];
    }
    return node.isWord ? sb.toString() : word;//前缀字典中有这个字符串中的前缀，返回该字符串
  }


  public String replaceWords(List<String> dictionary, String sentence) {
    TrieNode root = buildTrie(dictionary);
    StringBuilder builder = new StringBuilder();

    String words[] = sentence.split(" ");
    for(int i = 0; i < words.length; i++){
      String prefix = findPrefix(root, words[i]);
      if(prefix != null){
        words[i] = prefix;
      }
    }
    return String.join(" ", words);
  }

  public static void main(String[] args) {
    List<String> dict = new ArrayList<String>(Arrays.asList("catt","cat","bat","rat"));
    String sentence = "the cattle was rattled by the battery";
    String ans = new PTOS063_ReplaceWords().replaceWords(dict, sentence);
    System.out.println(ans);
  }
}
