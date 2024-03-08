package com.book.zuoshen.InterviewGuide.chpt9.random;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 11:27
 * @Description:给定1-5的随机函数，如果生成一个等概率的1-7的随机函数
 */
public class RandomGenerate {
    public int rand1To5() {
        return (int)(Math.random() * 5) + 1;
    }
    //问题1：生成1到7的等概率发生器
    //第一步：生成一个01发生器，让rand1To5=1/2的时候为0， 让random1to5=3的时候重新生成，让random1to5=4/5时候为1
    public int rand0To1() {
        int ans = 0;
        do {
            ans = rand1To5();
        } while (ans == 3); //如果生成结果位3，那么一直生成，直到不是3为止
        return ans < 3 ? 0 : 1;
    }
    //第二步：生成0到6的随机生成器，想要得到0到6要使用3个二进制位，直接使用上面的rand0to1来随机每个二进制位，每个位置生成0/1的概率都是1/2,
    // 0-6每个数概率是1/8
    public int rand0To7(){
        return (rand0To1() << 2) + (rand0To1() << 1) + rand0To1();
    }
    //第三步如何产生0-6的函数,遇到7就重做
    public int rand0to6(){
        int ans = 0;
        do{
            ans = rand0To7();
        } while(ans == 7);
        return ans;
    }
    //第4步
    public int rand1to7(){
        return rand0to6() + 1;
    }

    //问题2：如果要做17-56等概率，那么就做0-39的等概率，然后 + 17，0-39需要6个二进制位，足够做到，如果roll出来的二进制数大于39，就重roll！
    //直到roll出0-39的数

    //问题3：如果p的概率返回0，1-p概率返回1，使用rand01()roll两次，如果是01和10返回，等概率不要
    public int rand01p(){
        double p = 0.83;
        return Math.random() < p ? 0 : 1;
    }
    public int rnd01(){
        int num ;
        do{
            num = rand01p();
        } while(num == rand01p()); //前后两次roll的结果一样重新roll
        return num;
    }

}
