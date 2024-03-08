package com.book.zuoshen.InterviewGuide.chpt9.happylife;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/28 20:55
 * @Description:原始数组每个位置代表连接的下一个位置，求到首都距离为下标i的数组,下标循环
 */
public class PosArrayTransToDisCountArray {
    //先求每个点到首都的距离，O(N)时间复杂度在原数组上进行更新，距离用负数表示，正数表示位置
    public void pathsToDistance(int[] paths){
        int cap = 0;
        for(int i = 0; i != paths.length; i++){
            if(paths[i] == i){
                cap = i;
            } else if(paths[i] > -1){
                int curI = paths[i];
                paths[i] = -1;
                int preI = i;
                while(paths[curI] != curI){ //往首都继续推
                    if(paths[curI] > -1){ //如果当前结果还能往下记录
                        int nextI = paths[curI];
                        paths[curI] = preI;
                        preI = curI;
                        curI = nextI;
                    } else {
                        break;
                    }
                }
                int value = paths[curI] == curI ? 0 : paths[curI];//计算值
                while(paths[preI] != -1){ //往回计算距离
                    int lastPreI = paths[preI];
                    paths[preI] = --value;
                    curI = preI;
                    preI = lastPreI;
                }
                paths[preI] = --value;
            }
        }
        paths[cap] = 0;
    }

    public void distanceToNums(int[] disArr){
        for(int i = 0; i < disArr.length; i++){
            int index = disArr[i];
            if(index < 0){
                disArr[i] = 0;
                while(true){
                    index = -index;
                    if(disArr[index] > -1){
                        disArr[index]++;
                        break;
                    } else {
                        int nextIndex = disArr[index];
                        disArr[index] = 1;
                        index = nextIndex;
                    }
                }
            }
        }
        disArr[0] = 1;
    }
    public void pathToNums(int[] paths){
        if(paths == null || paths.length == 0){
            return;
        }
        pathsToDistance(paths);
        distanceToNums(paths);
    }
}
