package com.book.zuoshen.InterviewGuide.chpt9.happylife;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 15:27
 * @Description:蓄水池摇奖问题
 */
public class XushuichiRandom {
    //一个简单的随机数，决定一件事做还是不做
    public int rand(int max){
        return (int) (Math.random() * max + 1);
    }

    /**
     * 让k个数每个数都等概率获得
     * @param k
     * @param max，蓄水池的大小
     * @return
     */
    public int[] getKRand(int k, int max){
        if(max < 1 || k < 1){
            return null;
        }
        int[] res = new int[Math.min(k, max)];
        for(int i = 0; i < res.length; i++){
            res[i] = i + 1; //直接进袋子
        }
        for(int i = k + 1; i < max + 1; i++){
            if(rand(i) <= k){ //要进袋子
                res[rand(k) - 1] = i; //替换
            }
        }
        return res;
    }
}
