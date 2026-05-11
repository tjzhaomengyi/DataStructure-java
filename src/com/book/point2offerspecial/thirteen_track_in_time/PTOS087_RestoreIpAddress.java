package com.book.point2offerspecial.thirteen_track_in_time;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2023-02-16 12:35
 * @Description:
 */
public class PTOS087_RestoreIpAddress {
  public List<String> restoreIpAddresses(String s) {
    List<String> ans = new LinkedList<String>();
    process(s, 0, 0, "","", ans);
    return ans;
  }

  //技巧：ip地址：（1）总共分四段 （2）每段数不能大于255。这道题是条件式回溯，不是挑选那种，所以不用回退
  // segI表示当前IP的分段下标,ip表示当前合成的字符串，就是之前的sub,seg表示当前可以取到的字符串
  private void process(String s, int i, int segI, String seg,String ip, List<String> ans){
    //seqI记录前面分成了几段
    if(i == s.length() && segI == 3 && isValidSeg(seg)){
      ans.add(ip + seg);
    } else if(i < s.length() && segI <= 3){
      char ch = s.charAt(i);
      if(isValidSeg(seg + ch)) { //当前seg串 + ch字符组成的新seg合理，更新seg
        process(s, i + 1, segI, seg + ch, ip, ans);
      }
      if(seg.length() > 0 && segI < 3){//用当前字符开辟新的seg，更新ip
        process(s, i + 1, segI + 1, "" + ch, ip + seg + ".", ans);
      }
    }
  }

  private boolean isValidSeg(String seg){
    return Integer.valueOf(seg) <= 255 && (seg.equals("0") || seg.charAt(0) != '0');
  }

  //方法二 ： 使用标准回溯法

  ArrayList<String> res = new ArrayList<>();
  ArrayList<String> path = new ArrayList<>();

  public ArrayList<String> restoreIpAddress(String s){
    backtrack(s, 0, 0);
    return res;
  }

  // start表示当前从那个字符位置开始切，part表示切了几段
  private void backtrack(String s, int start, int part){
    // 【这里判断是否递归成功】成功生成了一个IP， 切了4个部分，而且正好全用完
    if(part == 4 && start == s.length()){
      res.add(String.join(".", path));
      return;
    }
    // 剪枝，【这里判断是否还能继续递归,要把边界卡住】已经切了四段的时候，不管到哪了，不能继续切了
    // 当字符串已经用完了也不能再切了
    if(part == 4 || start == s.length()){
      return;
    }

    // 每段最多3位
    for(int len = 1; len <= 3; len++){
      if(start + len > s.length()){
        break;
      }
      String sub = s.substring(start, start + len);

      // 前导0, 不成立直接跳过
      if(sub.length() > 1 && sub.charAt(0) == '0'){
        break;
      }
      // 数值大于255，跳过
      if(Integer.parseInt(sub) > 255){
        break;
      }

      // 做选择
      path.add(sub);
      backtrack(s, start + len, part + 1); //左闭右开
      // 撤销选择
      path.remove(path.size() - 1);
    }

  }


}
