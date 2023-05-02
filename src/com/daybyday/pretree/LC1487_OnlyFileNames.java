package com.daybyday.pretree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-03-03 19:37
 * @Description:
 */
public class LC1487_OnlyFileNames {
  public static class TrieNode{
    public TrieNode[] children;
    public boolean isWord;

    public TrieNode(){
      this.children = new TrieNode[26];
    }

    private static TrieNode root;

     static class TrieTree{
      public TrieTree() {
        root = new TrieNode();
      }
    }

    public TrieNode buildTrie(List<String> dict){
      TrieNode root = new TrieNode();
      for(String word :dict){
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
      return node.isWord ? sb.toString() : word;
    }

    public static boolean isWord(TrieNode root, String word){
       TrieNode node = root;
       for(char ch : word.toCharArray()){
         if(node.isWord){
           return true;
         }
         node = node.children[ch - 'a'];
       }
       return false;
    }
  }


  public String[] getFolderNames(String[] names) {
      int N = names.length;
      List<String> ans = new ArrayList<>();
      TrieNode root = new TrieNode().buildTrie(Arrays.asList(names));
      for(String name : names){
        if(new TrieNode().isWord(root, name)){
          ans.add(name);
        } else {
          int i = 0;
          StringBuilder tmp = new StringBuilder(name);
          tmp.append('(').append(i).append(')');
          while(new TrieNode().isWord(root, tmp.toString())){
            i++;
            tmp.replace(tmp.length()-2, tmp.length() - 1, String.valueOf(i));
          }
        }
      }
      String[] res = new String[ans.size()];
      for(int i = 0; i < res.length; i++){
        res[i] = ans.get(i);
      }

      return res;
  }
}
