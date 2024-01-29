package com.book.point2offerspecial.thirteen_track_in_time;

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
}
