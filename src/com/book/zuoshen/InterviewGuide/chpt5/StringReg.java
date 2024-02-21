package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/20 13:47
 * @Description:带有*和.的字符串匹配一个完整的字符串，规定两个*不能相邻，*不能是首字符，这道题*只能匹配前面一个字符，不能像Leetcode那个一样匹配多个
 */
public class StringReg {
    public boolean isValid(char[] s, char[] e){
        for(int i = 0; i < s.length; i++){
            if(s[i] == '*' || s[i] == '.'){
                return false;
            }
        }
        for(int i = 0; i < e.length; i++){
            if(s[i] == '*' && (i == 0 || e[i - 1] == '*')){
                return false;
            }
        }
        return true;
    }

    //字符串这个正则匹配一定要用长度作为dp描述
   public boolean isMatch(String str, String exp){
      if(str == null || exp == null){
          return false;
      }
      char[] s = str.toCharArray();
      char[] e = exp.toCharArray();
      if(!isValid(s, e)){
          return false;
      }
      boolean[][] dp = initDP(s, e);
      for(int i = s.length - 1; i >= 0; i--){
          for(int j = e.length - 2; j >= 0; j--){
              if(e[j + 1] != '*') {
                  dp[i][j] = (s[i] == e[j] || e[j] == '.') && dp[i + 1][j + 1];
              } else {
                  int si = i;
                  while(si != s.length && (s[si] == e[j] || e[j] == '.')){
                      if(dp[si][j + 2]){
                          dp[i][j] = true;
                          break;
                      }
                      si++;
                  }
                  if(dp[i][j] != true){
                      dp[i][j] = dp[si][j + 2];
                  }
              }
          }
      }
      return dp[0][0];
   }

   public boolean[][] initDP(char[] s, char[] e){
        int slen = s.length;
        int elen = e.length;
        boolean[][] dp = new boolean[slen + 1][elen + 1];
        dp[slen][elen] = true; //已经没有可以匹配的字符了，两个字符串都是""，所以是true
        //让e两步两步往前走
       for(int j = elen - 2; j >=0; j = j - 2){
            if(e[j] != '*' && e[j + 1] == '*'){ //e[j]和e[j+1]是字符+*
                dp[slen][j] = true;
            } else {
                break;
            }
        }
       if(slen > 0 && elen > 0){
           if((e[elen - 1] == '.' || s[slen - 1] == e[elen - 1])){
               dp[slen - 1][elen - 1] = true;
           }
       }
       return dp;
   }
}
