package com.hotinterview.Nanti;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-09 8:06 下午
 * @Description:
 */
public class Code0010_RegularExpMatch {
  //从递归挂动态规划进行优化
  public boolean isMatch(String str, String exp){
    if(str == null || exp == null){
      return false;
    }
    char[] s = str.toCharArray();
    char[] e = exp.toCharArray();
    if(!isValid(s, e)){
      return false;
    }
    //思路：这个dp[i][j]比较特殊，表示是否计算过（s 从 si开始，e从ei开始是否能完全匹配）
    // 1 表示计算过，且匹配
    // -1 表示计算过，不匹配
    // 0 表示没有计算过
    int[][] dp = new int[s.length + 1][e.length + 1];//这个dp的作用就是傻缓存
    return isValid(s, e) && process(s, e, 0, 0, dp);
  }

  //1、先验证s字符串和e字符串是否合法，s不能有*和. 。e字符串两个*不可以连着，并且不能以*开头
  public boolean isValid(char[] s, char[] e){
    for(int i = 0; i < s.length; i++){
      if(s[i] == '*' || s[i] == '.'){
        return false;
      }
    }
    for(int i = 0; i < e.length; i++){
      if(e[i] == '*' && (i == 0 || e[i - 1] == '*')){
        return false;
      }
    }
    return true;
  }


  //太难了~直接记住例子吧 ：只要是正则匹配都可以用这套，Code0044参考，主要是while替换这块，非常方便
  //2、递归，表示 从s的si开始、从e的ei开始，这两段字符串能否可以匹配
  public boolean process(char[] s, char[] e, int si, int ei, int[][] dp){
    if(dp[si][ei] != 0){
      return dp[si][ei] == 1;//表示从s的si开始 和 从e的ei开始都计算过
    }
    boolean ans = false;
    //1、结束条件
    if(ei == e.length){//太难了~直接记住例子吧 长度条件1、ei到结束位
      //匹配串结束了，没有能用的匹配了，要求s也结束
      return ans = si == s.length;
    } else { //思路：后面要讨论的主要是1、[ei + 1]是否结束或者是否为'*'；2、如果为'*'，那么如果此时si连着相同的话是可以优化的
      //如果[ei + 1] 是结束位
      if(ei + 1 == e.length || e[ei + 1] != '*'){// 太难了~直接记住例子吧 长度条件2、还是ei + 1到达长度 || [ei + 1] 不是通配符
        //思路：这个条件就要保证，si没有结束 并且 [si] 和 [ei]相等，或者[ei]为. 。 并且后续还一直可以成立。
        ans = si != s.length && (s[si] == e[ei] || e[ei] == '.') && process(s, e, si + 1, ei + 1, dp);
      } else { //此时就是在中间的时候e[ei + 1] = '*'
        if(si == s.length){//（1）太难了~直接记住例子吧 长度条件3、si到达结束位
          ans = process(s, e, si, ei + 2, dp);//让直接跳过 [ei][ei+1]认为这俩长度为0
        } else {//si没结束，太难了~直接记住例子吧 优化掉while
          if(s[si] != e[ei] && e[ei] != '.'){
            //a a b 和 b * a XXX，假如这两个都是从头开始进行比较，就把b*舍弃掉
            ans = process(s, e, si, ei + 2, dp);
          } else { //[si] = [ei]（或者[ei]='.'） 且 [ei + 1] = '*'
            //太难了~直接记住例子吧：这个是最牛逼的情况了，就是：a a a b 和 a * a b XXX这种情况，a连续，且a*能匹配上，存在优化见稿纸
            //思路：1、可以不要这个a *让这个长度为0，从ei + 2继续匹配 si || 2、优化之前得到的结果直接拿来用即可(通过观察得来的，参考稿纸)
            ans = process(s, e, si, ei + 2 ,dp) || process(s, e, si + 1, ei, dp);

          }
        }
      }
    }
    dp[si][ei] = ans ? 1 : -1;
    return ans;
  }

  public static void main(String[] args) {

  }
}
