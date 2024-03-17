package com.book.zuoshen.InterviewGuide.chpt9.happylife;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 15:11
 * @Description:折纸问题，本身就是一个二叉树遍历问题，就是不同位置打印不同的上下弯折
 */
public class FoldPaper {
    public void printAllFolds(int N){
        printProcess(1, N, true);
    }

    public void printProcess(int i, int N, boolean down){
        if(i > N){
            return;
        }
        //就以简单的两折为例子：上下下
        printProcess(i + 1, N, true); //上
        System.out.println(down ? "down " : "up "); //下
        printProcess(i + 1, N, false); //下
    }
}
