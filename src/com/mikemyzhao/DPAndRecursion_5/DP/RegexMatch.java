package com.mikemyzhao.DPAndRecursion_5.DP;

/**
 * @Author: zhaomengyi
 * @Date: 2021-09-07 12:59
 * @Description:正则表达式匹配
 * @NO:LC10 ，
 *
 * 实现只有两种'.'匹配单个字符，'*'匹配0或者多个前面元素
 *  s是待匹配字符串，p是匹配模式
 *  dp[i][j],i和j表示长度！！！！
 *   dp[i-1][j-1]表示在i\j之前已经匹配上对于新一位i/j有以下情况：
 *  1、如果s[i]==p[j]，继续下去i++,j++
 *  2、p[j]是可以是其他东西
 *  (1)p[j]=‘.’:dp[i][j]=dp[i-1][j-1];i++,j++
 *  3、p[j]='*'的话，*表示匹配零个或者多个前面的那一个元素。所以要考虑p[j-1]这个元素。
 *    *跟着其前面一个字符走，要保证p[j-1]匹配上s[i]。如果p[j-1]不匹配s[i]，只能然前一个字符消失，也就是匹配0次前一个字符
 *    所以 当p[j]=='*'的时候
 *    3-1 p[j-1]可以匹配上s[i]的字符的情况可以是:
 *      p[j-1]==s[i]或者p[j-1]='.';
 *    3-2 如果p[j-1]不能匹配上,p[j-1]!=s[i]
 *    (1)*匹配了1一个以上的a：dp[i-1][j]
 *         e  a   a    c   b
 *            i-1 i
 *         e  a   *    c
 *           j-1  j
 *    (2)*匹配了0个:dp[i][j-2]，把a*抹掉
 *    e     c   b
 *        i-1   i
 *    e   a   *   b
 *       i-1  j
 *    dp[i][j]=dp[i][j-2]或者dp[i-1][j]，此时i=j=2
 */
public class RegexMatch {
  boolean isMatch(String s,String p){
    int m = s.length();int n = p.length();
    boolean[][] dp = new boolean[m+1][n+1];
    //base case:(1)s部位空串，p为空串，不匹配；(2)s为空串，p不为空串，只可能是p的右端是*号(3)s和p都是空串，匹配
    dp[0][0]=true;
    /**注意：这里很重要，就是当p末尾只有*的情况，但是s为空**/
    for(int j=2;j<=n;j++){
      if(p.charAt(j-1)=='*'){
        dp[0][j]=dp[0][j-2];//(1)p=a*匹配s为空的字符串
      }
    }
    for(int i=1;i<=m;i++){
      for(int j=1;j<=n;j++){
        if(s.charAt(i-1)==p.charAt(j-1)||p.charAt(j-1)=='.'){
          dp[i][j] = dp[i-1][j-1];
        }else if(p.charAt(j-1)=='*'){
          if(p.charAt(j-2)==s.charAt(i-1) || p.charAt(j-2)=='.'){
            dp[i][j]=dp[i-1][j]||dp[i][j-2];//p[j-1]==s[i]或者p[j-1]='.';
          }else if(p.charAt(j-2)!=s.charAt(i-1)){
            //只能回退到dp[j-2]上了,可以说这是最坏的情况
            dp[i][j]=dp[i][j-2];
          }
        }else{
          dp[i][j]=false;
        }
      }
    }
    return dp[m][n];
  }

  public boolean isMatch2(String s, String p) {
    if (p==null){
      if (s==null){
        return true;
      }else{
        return false;
      }
    }

    if (s==null && p.length()==1){
      return false;
    }

    int m = s.length()+1;
    int n = p.length()+1;

    boolean[][]dp = new boolean[m][n];

    dp[0][0] = true;

    for (int j=2;j<n;j++){
      if (p.charAt(j-1)=='*'){
        dp[0][j] = dp[0][j-2];
      }
    }

    for (int r=1;r<m;r++){
      int i = r-1;
      for (int c=1;c<n;c++){
        int j = c-1;
        if (s.charAt(i)==p.charAt(j) || p.charAt(j)=='.'){
          dp[r][c] = dp[r-1][c-1];
        }else if (p.charAt(j)=='*'){
          if (p.charAt(j-1)==s.charAt(i) || p.charAt(j-1)=='.'){
            dp[r][c] = dp[r-1][c] || dp[r][c-2];
          }else{
            dp[r][c] = dp[r][c-2];
          }
        }else{
          dp[r][c] = false;
        }

      }
    }
    return dp[m-1][n-1];
  }


  public static void main(String[] args) {
    System.out.println(new RegexMatch().isMatch("aa","a*"));
  }
}
