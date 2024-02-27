package com.book.zuoshen.InterviewGuide.chpt8.Nice4InThisParts;

import java.util.*;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/26 16:22
 * @Description:大楼轮廓线问题
 */
public class BuildingOutLine {
    //记录大楼的起始位置和高度
    public static class Node {
        public int x;
        public boolean isAdd; //开始 true + | 结束 false -
        public int h;

        public Node(int x, boolean isAdd, int h){
            this.x = x;
            this.isAdd = isAdd;
            this.h = h;
        }
    }

    public static class NodeComparator implements Comparator<Node> {

        @Override
        public int compare(Node o1, Node o2) {
            return o1.x - o2.x; //先按照大楼的起始点进行排序
        }
    }

    public static List<List<Integer>> getSkyLine(int[][] matrix){
        Node[] nodes = new Node[matrix.length * 2];
        for(int i = 0; i < matrix.length; i++){
            nodes[i * 2] = new Node(matrix[i][0], true, matrix[i][2]);
            nodes[i * 2 + 1] = new Node(matrix[i][1], false, matrix[i][2]);
        }
        Arrays.sort(nodes, new NodeComparator());
        //两个关键的TreeMap结构
        TreeMap<Integer, Integer> mapHeightTimes = new TreeMap<>();//key高度，该高度出现的次数
        TreeMap<Integer, Integer> mapXHeight = new TreeMap<>(); // 每个位置出现的最大高度
        for(int i = 0; i < nodes.length; i++) {
            if (nodes[i].isAdd) { //如果是添加节点
                if (!mapHeightTimes.containsKey(nodes[i].h)) {
                    mapHeightTimes.put(nodes[i].h, 1);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) + 1);
                }
            } else { //如果是删除节点
                if (mapHeightTimes.get(nodes[i].h) == 1) {
                    mapHeightTimes.remove(nodes[i].h);
                } else {
                    mapHeightTimes.put(nodes[i].h, mapHeightTimes.get(nodes[i].h) - 1);
                }
            }
            if (mapHeightTimes.isEmpty()) {
                mapXHeight.put(nodes[i].x, 0);
            } else { //如果当前不是空，那么当前点的最大高度就是mapHeightTimes的最后一个key
                mapXHeight.put(nodes[i].x, mapHeightTimes.lastKey());//如果times里面有值，那么当前位置就可以看到mapHeighttimes里面最大的值，就是最后一个
            }
        }
        //结算结果
        List<List<Integer>> ans = new ArrayList<>();
        for(Map.Entry<Integer, Integer> entry : mapXHeight.entrySet()){
            int curX= entry.getKey();
            int curMaxHeight = entry.getValue();
            if(ans.isEmpty() || ans.get(ans.size() - 1).get(1) != curMaxHeight){//如果当前ans中的最后一个结果不是curMaxHeight，更新一下
                ans.add(new ArrayList<>(Arrays.asList(curX, curMaxHeight)));
            }
        }
        return ans;
    }
}
