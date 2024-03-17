package com.book.zuoshen.InterviewGuide.chpt9.math;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 13:46
 * @Description:N的阶乘末尾有几个0，N里面有几个5的因子，因为2因子个数比5多，所以如果有5因子肯定可以配对上2的因子。
 * 每5个数至少有一个5的因子，1到25其中25会多出来一个5的因子，推出来，每25个数多出来一个5的因子,125一组又多出一个5
 */
public class NStageTailZero {
    public int tailZero(int num){
        if(num < 0){
            return 0;
        }
        int res = 0;
        while(num != 0){
            res += num / 5;
            num = num / 5;
        }
        return res;
    }
    //进阶问题：求N!的二进制数中最后一个1在第几位,只有2这个因子会让1左移动一位
    public int rightOne(int num){
        if(num < 1){
            return -1;
        }
        int res = 0;
        while(num != 0){
            num >>>= 1;
            res += num;
        }
        return res;
    }

}
