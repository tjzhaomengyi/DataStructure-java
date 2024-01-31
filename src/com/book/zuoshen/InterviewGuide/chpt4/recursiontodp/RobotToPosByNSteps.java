package com.book.zuoshen.InterviewGuide.chpt4.recursiontodp;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/31 20:27
 * @Description:
 */
public class RobotToPosByNSteps {
    //递归方法

    /**
     * @param N  N位置为1到N，固定参数，
     * @param cur，当前在cur位置，可变参数
     * @param rest，还剩rest步数没走，可变参数
     * @param P，最终目标位置，固定参数
     * @return 从cur位置开始在1到N上移动rest步数，最终能到达P的方法数
     */
    public int walk(int N, int cur, int rest, int P){
        //如果没有剩余步数了，当前的cur位置就是最后的位置
        //如果最后的位置停在P上，那么之前做的动作都是有效的
        //如果最后的位置没在P上，那么之前做的动作都是无效的
        if(rest == 0){
            return cur == P ? 1 : 0;
        }

        //在最左侧的位置只能往右走
        if(cur == 1){
            return walk(N, 2, rest - 1, P);
        }
        //如果还有rest步要走，
    }

}
