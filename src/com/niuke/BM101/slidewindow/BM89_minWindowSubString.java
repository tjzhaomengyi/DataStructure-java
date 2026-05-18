package com.niuke.BM101.slidewindow;

/**
 * 这个题是滑动窗口 + 账本 ，值得刷，整体的遍历思路还是挺有意思的，
 * 尤其是卡左边界的时候，一边收缩，一边怼账本
 */
public class BM89_minWindowSubString {
    public String minWindow(String s, String t){
        if(s.length() == 0 || t.length() == 0) return "";
        int[] need = new int[128];
        int[] window = new int[128];
        int required = 0; //需要满足的字符串种类数
        for(char c : t.toCharArray()){
            if(need[c] == 0) required++;
            need[c]++;
        }
        int left = 0, right = 0;
        int valid = 0; //最终valid长度应该等于required长度
        int minLen = Integer.MAX_VALUE;
        int start = 0;

        // 动右指针
        while(right < s.length()){
            char c = s.charAt(right);
            right++;
            if(need[c] > 0){
                window[c]++;
                if(window[c] == need[c]){ //凑齐了一个字符
                    valid++;
                }
            }
            //窗口满足条件，尝试收缩，动左指针
            while(valid == required){// 卡住这个条件啊，这里面有合理的长度
                if(right - left < minLen){
                    minLen = right - left;
                    start = left;
                }
                char d = s.charAt(left);
                left++;
                if(need[d] > 0){ //如果是在目标串中的字符，需要调整
                    if(window[d] == need[d]){
                        valid--;
                    }
                    window[d]--;
                }

            }

        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}
