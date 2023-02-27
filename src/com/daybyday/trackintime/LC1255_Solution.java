package com.daybyday.trackintime;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-26 20:19
 * @Description:
 */
class Solution {

  public int maxScoreWords(String[] words, char[] letters, int[] score) {
    //首先可以获取words中每个单词的分数，对于无法用letters凑出来的单词其分数定为负数
    //考虑words的长度，可以在words上进行dfs遍历来获取所有满足要求的集合情况，然后获取其中的最大分数
    int n;
    int[] word_score;
    int[] dict_cnt;
    int[] ans = new int[1];

    n=words.length;
    word_score=new int[n];     //fs[i]存储words[i]对应的分数
    dict_cnt=new int[26];
    for(char c:letters) dict_cnt[c-'a']++;

    for(int i=0;i<n;i++){
      int[] tmp=new int[26];
      for(char c:words[i].toCharArray()){
        tmp[c-'a']++;
        if(tmp[c-'a']> dict_cnt[c-'a']){
          word_score[i]=-1;       //无法用letters凑出来的单词其分数定为-1
          break;
        }
        word_score[i]+=score[c-'a'];
      }
    }
    dfs(words,0,0,new int[26],ans, dict_cnt, word_score);
    return ans[0];
  }
  private void dfs(String[] words,int p,int sum,int[] usedLetters, int[] ans, int[] dict_cnt,
                   int[] word_score){
    if(p==words.length){ //todo
      ans[0]=Math.max(ans[0],sum); //todo
      return;
    }
    dfs(words,p+1,sum,usedLetters,ans, dict_cnt, word_score);   //不选择当前单词
    boolean added=true;
    for(char c:words[p].toCharArray()){   //选择当前单词，但是选择的前提是剩下的letters能凑出当前单词
      int index=c-'a';
      usedLetters[index]++;
      if(usedLetters[index]>dict_cnt[index])added=false;
    }
    if(added)dfs(words,p+1,sum+word_score[p],usedLetters, ans, dict_cnt, word_score);    //若能凑出当前单词，则将当前单词的分数加到统计结果中
    for(char c:words[p].toCharArray())usedLetters[c-'a']--;   //回溯时记得重置状态
  }
}