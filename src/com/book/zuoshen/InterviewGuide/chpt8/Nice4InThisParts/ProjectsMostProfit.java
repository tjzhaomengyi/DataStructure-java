package com.book.zuoshen.InterviewGuide.chpt8.Nice4InThisParts;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/26 14:50
 * @Description:给定项目花费和项目利润求项目最大收益，就是体系版IPO问题,这是一道小贪心
 */
public class ProjectsMostProfit {
    public class Program {
        public int cost; //项目花费
        public int profit; //项目利润
        public Program(int cost, int profit){
            this.cost = cost;
            this.profit = profit;
        }
    }

    //先来一个小根堆，放入小根堆放入项目，按照cost大小组织小根堆
    //再来一个大根堆，然后从小根堆按照顺序弹出，直到不能弹出为止，结算大根堆（做这个），并获得新的资金数
    // 然后再继续大根堆，过程直到完成K个为止。宗旨：先做cost小profit大的

    public class CostMinComp implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o1.cost - o2.cost;
        }
    }

    public class ProfitMaxComp implements Comparator<Program>{

        @Override
        public int compare(Program o1, Program o2) {
            return o2.profit - o1.profit;
        }
    }

    /**
     *
     * @param W 启动资金
     * @param K 要完成K个项目
     * @param costs
     * @param profits
     * @return
     */
    public int getMaxMoney(int W, int K, int[] costs, int[] profits){
        if(W < 1 || K < 0 || costs == null || profits == null || costs.length != profits.length){
            return W;
        }
        //项目花费的小根堆
        PriorityQueue<Program> costMinHeap = new PriorityQueue<>(new CostMinComp());
        //项目利润的大根堆
        PriorityQueue<Program> profitMaxHeap = new PriorityQueue<>(new ProfitMaxComp());
        for(int i = 0; i < costs.length; i++){
            costMinHeap.add(new Program(costs[i], profits[i]));
        }
        //从小根堆里拿出项目
        for(int i = 1; i <= K; i++){
            while(!costMinHeap.isEmpty() && costMinHeap.peek().cost < W){
                profitMaxHeap.add(costMinHeap.poll());// 把小根堆里最小的拿出来放入大根堆
            }
            if(profitMaxHeap.isEmpty()){ //大根堆始终没有项目能够进来直接结束
                return W;
            }
            W += profitMaxHeap.poll().profit;
        }
        return W;
    }
}
