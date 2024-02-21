package com.book.zuoshen.InterviewGuide.chpt5;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/14 12:47
 * @Description:
 */
public class JudgeCharOnce {
    public boolean isUnique(char[] chs){
        if(chs == null){
            return true;
        }
        boolean[] map = new boolean[256];
        for(int i = 0; i < chs.length; i++){
            if(map[chs[i]]){
                return false;
            }
            map[chs[i]] = true;
        }
        return true;
    }
}
