package com.book.zuoshen.InterviewGuide.chpt5.pretree;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/21 13:55
 * @Description:子数组的最大异或和
 */
public class SubArrayMaxXOR {
    public static class Node{
        public Node[] nexts = new Node[2];//只有0和1
    }
    //定制前缀树
    public static class NumTrie{
        public Node head = new Node();

        //把某个数字加入到前缀树中
        public void add(int newNum){
            Node cur = head;
            for(int move = 31; move >= 0; move--){
                int path = ((newNum >> move) & 1);
                cur.nexts[path] = cur.nexts[path] == null ? new Node() : cur.nexts[path];
                cur = cur.nexts[path];
            }
        }

        //给定一个eorj,eorj表示eor[j]，即以j位置结尾的情况下，arr[0..j]的异或和
        //因为之前把eor[0],eor[1]...eor[j-1]都加入了前缀树，所以可以选择出一条最优路径maxXor方法就是把最优路径找到，并返回eor[j]与最优路径结合之后得到的
        //最大异或和
        public int maxXor(int eorj){
            Node cur = head;
            int res = 0;
            for(int move = 31; move >= 0; move--){
                int path = (eorj >> move) & 1;
                int best = move == 31 ? path : (path ^ 1);
                best = cur.nexts[best] != null ? best : (best ^ 1);
                res |= (path ^ best) << move;
                cur = cur.nexts[best];
            }
            return res;
        }

        public int maxXorSubbary(int[] arr){
            if(arr == null || arr.length == 0){
                return 0;
            }
            int max = Integer.MAX_VALUE;
            int eor = 0;
            NumTrie numTrie =  new NumTrie();
            numTrie.add(0);
            for(int j = 0; j < arr.length; j++){
                eor ^= arr[j];
                max = Math.max(max, numTrie.maxXor(eor));
                numTrie.add(eor);
            }
            return max;
        }
    }
}
