package com.daybyday.dp;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-26 12:12
 * @Description:
 */
public class LC1255_MaxScoreWords {

  public int maxScoreWords(String[] words, char[] letters, int[] score) {
    int[] ans = new int[1];
    int[] dict_cnt = new int[26];
    int[] word_score = new int[14];
    int[] word_cnt = new int[26];
    //生成letters数量字典：dict_cnt
    for(char c : letters){
      dict_cnt[c - 'a']++;
    }

    //生成words_score和word_cnt
    for(int i = 0; i < words.length; i++){
      char[] str = words[i].toCharArray();
      for(char c : str){
        int[] tmp = new int[26];//技巧：每个tmp记录本身的单词，字符数一旦超了就pass，这个word不符合条件
        word_cnt[c - 'a']++;
        if(tmp[c - 'a'] > dict_cnt[c - 'a']){
          word_score[i] = -1;
          break;
        }
        word_score[i] += score[c - 'a'];
      }
    }
    //
    dfs(words, 0, 0, new int[26], ans, dict_cnt, word_score);
    return ans[0];
  }

  //p表示当前words遍历的下标,sum表示当前的结果，技巧；用uesedLetters记录使用的字母，就是path
  public void dfs(String[] words, int p, int sum, int[] usedLetters, int[] ans,
                  int[] dict_cnt, int[] word_score){
    if(p == words.length){
      ans[0] = Math.max(ans[0], sum);
      return;
    }

    dfs(words, p + 1, sum, usedLetters, ans, dict_cnt, word_score);//不凑当前的单词

    boolean added = true;
    for(char c : words[p].toCharArray()){
      int index = c - 'a';
      usedLetters[index]++;
      if(usedLetters[index] > dict_cnt[index]){
        added = false; //要凑的单词的字符数可以
      }
    }
    if(added){
      dfs(words,p + 1, sum + word_score[p], usedLetters, ans, dict_cnt, word_score);
    }
    for(char c : words[p].toCharArray()) { //dfs回溯清空当前情况
      usedLetters[c - 'a']--;
    }

  }

}
