package com.book.zuoshen.InterviewGuide.chpt3.dpintree;

import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/30 18:26
 * @Description:
 */
public class MaxHappy {
    class Employee{
        public int happy;
        List<Employee> subordinates;//下级员工
    }

    public static class ReturnData{
        public int yesHeadMax; //
        public int noHeadMax;
        public ReturnData(int yesHeadMax, int noHeadMax){
            this.yesHeadMax = yesHeadMax;
            this.noHeadMax = noHeadMax;
        }
    }

    //该函数返回X来和不来两种情况下的最大快乐值
    public static ReturnData process(Employee X){
        int yesX = X.happy;
        int noX = 0;
        if (X.subordinates.isEmpty()){
            return new ReturnData(yesX, noX);// 没有下属直接返回最大
        } else {
            //枚举X的每一个直接下级员工
            for (Employee next : X.subordinates){
                //得到以next为头结点的子树，在next来和不来情况下获得的最大收益
                ReturnData subTreeInfo = process(next);
                yesX += subTreeInfo.noHeadMax; //next来，计算值
                noX += Math.max(subTreeInfo.yesHeadMax, subTreeInfo.noHeadMax); //next不来，计算值
            }
            return new ReturnData(yesX, noX);
        }
    }

    public int getMaxHappy(Employee boss){
        ReturnData all = process(boss);
        return Math.max(all.noHeadMax, all.yesHeadMax);
    }
}
