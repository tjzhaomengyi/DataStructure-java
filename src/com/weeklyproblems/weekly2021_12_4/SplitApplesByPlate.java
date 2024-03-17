package com.weeklyproblems.weekly2021_12_4;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/8 08:43
 * @Description:n个苹果，m个盘子，求不同分法，苹果和盘子没有差异
 */
public class SplitApplesByPlate {
    public static int ways(int apples, int plates){
        return process1(1, apples, plates);//第一步再分的时候不能比1小，只能比1大
    }

    //pre:上一个盘子分到苹果的数量，当前盘子分到的数量不能小于pre；apples剩余的苹果数量，plates:剩余的盘子数
    //使用pre就是避免重复，非常好的技巧；131不对的，这个递归只能113
    public static int process1(int pre, int apples, int plates){
        if(apples == 0){ //苹果分好了，剩盘子没事，可以有空盘子
            return 1;
        }
        if(plates == 0){ //apple!=0,盘子没了
            return 0;
        }
        if(pre > apples){ //apple！=0，plate！=0， 1,6,1这个不行！，应该被1，1，6这个找到，前一个数量pre大于剩余苹果，这个方法无效
            return 0;
        }
        //apple!=0 && plate!=0 && pre < apples
        int way = 0;
        for(int cur = pre; cur <= apples; cur++){
            way += process1(cur, apples - cur, plates - 1);
        }
        return way;
    }

    //方法2，7个苹果7个盘子和7个苹果100个盘子分法一样多，把剩下93个盘子砸了。
    // 苹果数大于等于盘子数量，7个苹果4个盘子，4个盘子全用方法数a，4个盘子不全用方法数b，最后的方法数a+b
    public static int way2(int apples, int plates){
        if(apples == 0){
            return 1;
        }
        if(plates == 0){
            return 0;
        }
        if(plates > apples){
            return ways(apples, apples);
        } else {
            //如果苹果大于盘子, 7苹果，4盘子 f(7,4)4个盘子全用，每个盘子分1个，然后看后面多少个方法；
            //4个盘子不全用，砸1个！后续就是挨个砸
            return way2(apples, plates - 1) + way2(apples - plates, plates);//不全用+全用
        }
    }
}
