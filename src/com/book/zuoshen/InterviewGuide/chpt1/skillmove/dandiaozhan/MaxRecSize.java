package com.book.zuoshen.InterviewGuide.chpt1.skillmove.dandiaozhan;

import java.util.Stack;

/**
 * @Author: zhaomengyi
 * @Date: 2024/1/12 12:31
 * @Description: 求柱形围成的最大面积，有两点注意：（1）这里有那个讲课时候传说的错了就错了的地方，就是在进单调栈的时候，如果两个数相等，那么第一次计算
 * 这两个相等数构成的面积时候本来是错的，但是在计算该相等柱第二次的时候会计算成正确的。
 * （2）这里有重复值，使用没重复的单调栈处理方法，直接把前一个算完构成的矩形大小后，出栈，然后再把这个重复值进栈.
 */
public class MaxRecSize {
    public int maxRecSize(int[][] map){
        if (map == null || map.length == 0 || map[0].length == 0){
            return 0;
        }
        int maxArea = 0;
        int[] height = new int[map[0].length];
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                height[j] = map[i][j] == 0 ? 0 : height[j] + 1;
            }
            maxArea = Math.max(maxRecFromBottom(height), maxArea);
        }
        return maxArea;
    }

    public int maxRecFromBottom(int[] height){
        if(height == null || height.length == 0){
            return 0;
        }
        int maxArea = 0;
        Stack<Integer> stack = new Stack<Integer>(); //辅助单调栈
        for(int i = 0; i < height.length; i++){
            while(!stack.empty() && height[stack.peek()] >= height[i]){//找到了stack.peek的右边界
                int j = stack.pop();//代计算可以扩展的立柱
                int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
                //j可以扩展出来的最大面积
                int curArea = (i - leftLessIndex - 1) * height[j];
                maxArea = Math.max(curArea, maxArea);
            }
            //即使有重复值，也放入最新的值
            stack.push(i);
        }
        while(!stack.isEmpty()){
            int j = stack.pop();
            int leftLessIndex = stack.isEmpty() ? -1 : stack.peek();
            int curArea = (height.length - leftLessIndex - 1) * height[j];
            maxArea = Math.max(curArea, maxArea);
        }
        return maxArea;
    }
}
