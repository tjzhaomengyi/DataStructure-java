package com.weeklyproblems.week2021_12_3;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: zhaomengyi
 * @Date: 2024/3/7 15:27
 * @Description:leetcode675,1是地面，2以上是树
 */
public class Code04_GolfCutTree {
    //问题：以什么样的方式走到下一个树

    //走到下一个点的最快方式：准备一个双端队列，从四个方向找下一个节点，把离目标点近的位置放在双端队列的顶端，优先出队
    public static int cutTree(List<List<Integer>> forest){
        int n = forest.size();
        int m = forest.get(0).size();
        //cells的存储格式 [[3,5，2],[1,9，4],[2,6，10]] (x,y,高度)
        ArrayList<int[]> cells = new ArrayList<>();
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                int val = forest.get(i).get(j);
                if(val > 1){
                    cells.add(new int[] {i, j, val});
                }
            }
        }
        cells.sort((a, b) -> a[2] - b[2]);//根据高度排序
        int ans = 0;
        int lastR = 0;
        int lastC = 0;
        for(int[] cell : cells){
            int step = bestWalk(forest, lastR, lastC, cell[0], cell[1]);//cell[0]和cell[1]是目标坐标
            if(step == -1){
                return -1; //没有能够到达的方法
            }
            ans += step;
            lastR = cell[0];
            lastC = cell[1];
            forest.get(lastR).set(lastC, 1);//把树砍了！
        }
        return ans;
    }

    public static int[] next = {-1, 0, 1, 0, -1};
    public static int bestWalk(List<List<Integer>> forest, int sr, int sc, int tr, int tc){
        int n = forest.size();
        int m = forest.get(0).size();
        boolean[][] seen = new boolean[n][m];
        LinkedList<int[]> deque = new LinkedList<>();
        deque.offerFirst(new int[]{0, sr, sc});//记录到达sr，sc用的步数
        while(!deque.isEmpty()){
            int[] cur = deque.pollFirst();
            int step = cur[0];
            int r = cur[1];
            int c = cur[2];
            if(r == tr && c == tc){
                return step; //正好到达
            }
            seen[r][c] = true;
            //四个方向进行BFS
            for(int i = 1; i < 5; i++){
                int nr = r + next[i - 1];
                int nc = c + next[i];
                if(nr >= 0 && nr < n && nc >= 0 && nc < m && seen[nr][nc] && forest.get(nr).get(nc) > 0){
                    //这树可以砍
                    int[] move = {step + 1, nr, nc};
                    //唯一的技巧点：把离着target点近的放入队头，远的放入队尾,i=1表示向上走，r>tr,表示这个点在tr下面，此时点往上走就是来靠近target
                    if((i == 1 && r > tr) || (i == 2 && c < tc) || (i == 3 && r < tr) || (i == 4 && c > tc)){
                        deque.offerFirst(move);
                    } else {
                        deque.offerLast(move);
                    }
                }
            }
        }
        return -1;
    }
}
