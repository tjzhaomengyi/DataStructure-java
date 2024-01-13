package com.book.zuoshen.InterviewGuide.chpt1.stack;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/11 15:21
 * @Description:使用三个stack完成汉诺塔,这里其实是汉诺塔的终极结论,todo:这道题可以按照Leetcode上面的写法在改成直接以链表为参数的形式
 */
public class HanoiByStack {
    //使用三个栈LS、MS和RS玩汉诺塔有三个原则：
    // (1)只有四个动作L->M、M->L、M->R和R->M;(2)L->M和M->L为互逆过程，同理，M->R和R->M；（3）在修改汉诺塔的游戏中，如果想走出最少步数，
    //那么任何相邻两个相邻的动作都不是互为逆过程的。比如上一步走了L->M，那么下一步绝不可能是M->L，因为这样是把顶层从左栈移动到了中栈，但是
    //如果再移动回去这个操作就是不能形成最小步数的玩法.
    //根据三个原则得到两个结论：（1）游戏的第一个动作一定是L->M.
    // 汉诺塔的终极结论：(2)在走出最少步数过程中的任何时刻，4个动作只有一个动作不违反小压大和相邻不可逆原则，另外三个动作一定都会违反。
    public enum Action {
        No, LToM, MToL, MToR, RToM
    }

    public int hanoiProblem(int num, String left, String mid, String right){
        Stack<Integer> lS = new Stack<Integer>();
        Stack<Integer> mS = new Stack<Integer>();
        Stack<Integer> rS = new Stack<Integer>();
        lS.push(Integer.MAX_VALUE);
        mS.push(Integer.MAX_VALUE);
        rS.push(Integer.MAX_VALUE);
        for (int i = num; i > 0; i--){
            lS.push(i);
        }
        Action[] record = {Action.No};
        int step = 0;
        while (rS.size() != num + 1){
            step += fStackToStack(record, Action.MToL, Action.LToM, lS, mS, left, mid);
            step += fStackToStack(record, Action.LToM, Action.MToL, mS, lS, mid, left);
            step += fStackToStack(record, Action.RToM, Action.MToR, mS, rS, mid, right);
            step += fStackToStack(record, Action.MToR, Action.RToM, rS, mS, right, mid);
        }
        return step;
    }


    /**
     *
     * @param record, 移动的动作记录
     * @param preNoAct，前一个动作不可能的动作，例如当前是LtoM，那么前一个肯定不是MToL,所以如果上一个动作不是perNoAct当前这个nowAct就有
     *                可能作为当前执行的动作
     * @param nowAct， 当前可能会执行的动作
     * @param fStack，当前栈
     * @param tStack， 目标移动栈
     * @param from， 从哪来
     * @param to， 到哪去
     * @return
     */
    public static int fStackToStack(Action[] record, Action preNoAct,Action nowAct, Stack<Integer> fStack, Stack<Integer> tStack,
                                    String from, String to){
        //可以移动
        if(record[0] != preNoAct && fStack.peek() < tStack.peek()){
            tStack.push(fStack.pop());
            System.out.println("Move" + tStack.peek() + " from " + from + " to " + to);
            record[0] = nowAct;
            return 1;
        }
        return 0;
    }

    public static void main(String[] args) {
        HanoiByStack play = new HanoiByStack();
        int steps = play.hanoiProblem(3, "left", "mid", "right");
        System.out.println(steps);

    }
}
