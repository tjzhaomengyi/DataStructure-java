package com.book.zuoshen.InterviewGuide.chpt4.LISProblems;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @Author: zhaomengyi
 * @Date: 2024/2/5 10:16
 * @Description:{3,4},{2,3},{4,5},{1,3},{2,2},{3,6},{1,2},{3,2},{2,4}
 * 对高度从小到大排序      {1,1,2,2,2,3,3,3,4}
 * 高度相等宽度从大到小排序 {3,2,4,3,2,6,4,2,5}
 * 信封排序好后，在高度从小到大，内部宽度从大到小的情况下，找到高度的最长子序列即可
 */
public class MailEnvelope {
    public class Envelope {
        public int len;
        public int wid;

        public Envelope(int weight, int height){
            len = weight;
            wid = height;
        }
    }

    public class EnvelopeComparator implements Comparator<Envelope>{

        @Override
        public int compare(Envelope o1, Envelope o2) {
            return o1.len != o2.len ? o1.len - o2.len : o2.wid - o1.wid;
        }
    }

    public Envelope[] getSortedEnvelopes(int[][] matrix){
        Envelope[] res = new Envelope[matrix.length];
        for(int i = 0; i < matrix.length; i++){
            res[i] = new Envelope(matrix[i][0], matrix[i][1]);
        }
        Arrays.sort(res, new EnvelopeComparator());
        return res;
    }

    public int maxEnvelopes(int[][] matrix){
        Envelope[] envelopes = getSortedEnvelopes(matrix);
        int[] ends = new int[matrix.length];
        ends[0] = envelopes[0].wid;
        int right = 0;//记录ends有效区的最右部分
        int l = 0;
        int r = 0;
        int m = 0;
        for(int i = 1; i < envelopes.length; i++){
            l = 0;
            r = right;
            while(l <= r){
                m = (l + r) / 2;
                if(envelopes[i].wid > ends[m]){
                    l = m + 1; //ends的最右可能会往右推
                } else {
                    r = m - 1;
                }
            }
            right = Math.max(right, l);//right可能忘右侧推，这个时候l会到新的位置
            ends[l] = envelopes[i].wid;
        }
        return right + 1;//根据ends数组的表示：ends[i]是长度为i+1数组的最长递增子序列的最后一个最小值（在这个递增子序列中是最大。）
    }
}
