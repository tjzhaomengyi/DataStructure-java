package com.huaweiOD.od2023.s1e19;

import javax.swing.text.html.InlineView;
import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2023-04-26 15:31
 * @Description:
 */
public class LiangshuZhiheZuixiao {
  public static void main(String[] args) {
    Scanner in = new Scanner(System.in);
    while (in.hasNextLine()) {
      String[] str = in.nextLine().split(" ");
      List<Integer> pLst = new ArrayList<>();
      List<Integer> nLst = new ArrayList<>();
      for(int i = 0; i < str.length; i++){
        if(str[i].contains("-")){
          nLst.add(Integer.valueOf(str[i]));
        } else {
          pLst.add(Integer.valueOf(str[i]));
        }
      }
      int ans = 0;
      if(pLst.isEmpty()){
        Collections.sort(nLst);
        ans = nLst.get(nLst.size() - 1) + nLst.get(nLst.size() - 2);
        System.out.print(nLst.get(nLst.size() - 1) + " " +nLst.get(nLst.size() - 2) + " " +ans);
      } else if(nLst.isEmpty()){
        Collections.sort(pLst);
        ans = pLst.get(0) + pLst.get(1);
        System.out.println(pLst.get(0) + " " + pLst.get(1) + " " + ans);
      } else {
        Collections.sort(pLst);
        Collections.sort(nLst);
        TreeMap<Integer, int[]> tm = new TreeMap<>();
        int ans1 = Math.abs(pLst.get(0) + nLst.get(nLst.size() - 1));
        tm.put(ans1, new int[]{nLst.get(nLst.size() - 1), pLst.get(0)});
        int ans2 = Math.abs(pLst.get(0) + nLst.get(0));
        tm.put(ans2, new int[]{nLst.get(0), pLst.get(0)});
        int ans3 = Math.abs(pLst.get(pLst.size() - 1) + nLst.get(nLst.size() - 1));
        tm.put(ans3, new int[]{nLst.get(nLst.size() - 1), pLst.get(pLst.size() - 1)});
        int ans4 = Math.abs(pLst.get(pLst.size() - 1) + nLst.get(0));
        tm.put(ans4, new int[]{nLst.get(0), pLst.get(pLst.size() - 1)});
        ans = tm.firstKey();
        System.out.println(tm.get(ans)[0] + " " + tm.get(ans)[1] + " " + ans);
      }
    }
  }



}
