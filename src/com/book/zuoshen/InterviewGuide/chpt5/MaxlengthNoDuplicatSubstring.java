package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/19 20:45
 * @Description:这道题是一个重要的思维传统，以arr[i]位置往左推有多少
 */
public class MaxlengthNoDuplicatSubstring {
    public int maxUnique(String str){
        if(str == null || str.equals("")){
            return 0;
        }
        char[] chs = str.toCharArray();
        int[] map = new int[256];
        for(int i = 0; i < 256; i++){
            map[i] = -1; //某个字符上一次出现的位置
        }
        int ans = 1;
        int pre = 1; //上一个位置向左推了多长
        map[chs[0]] = 0; //从0位置开始的，所以记录一下
        for(int i = 1 ; i < chs.length; i++){
            //两种情况 1) a ......... a
            //          17...........19
            //          a..........a
            //       -1 0          4
            //       2)跟着上一个结果往下推一位pre + 1
            pre = Math.min(i - map[chs[i]], pre + 1);//这题就这一个位置最关键，没了....
            ans = Math.max(ans, pre);
            map[chs[i]] = i;
        }
        return ans;
    }
}
