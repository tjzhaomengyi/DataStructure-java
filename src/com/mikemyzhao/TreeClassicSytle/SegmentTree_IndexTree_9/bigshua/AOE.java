package com.mikemyzhao.TreeClassicSytle.SegmentTree_IndexTree_9.bigshua;

import com.mikemyzhao.TreeClassicSytle.SegmentTree_IndexTree_9.SegmentTree;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-13 15:30
 * @Description:
 */
public class AOE {
  // 贪心策略和方法二一样，但是需要用线段树，可优化成O(N * logN)的方法，
  public static int minAoe3(int[] x, int[] hp, int range) {
    int N = x.length;
    // coverLeft[i]：如果以i为中心点放技能，左侧能影响到哪，下标从1开始，不从0开始
    // coverRight[i]：如果以i为中心点放技能，右侧能影响到哪，下标从1开始，不从0开始
    // coverLeft和coverRight数组，0位置弃而不用
    // 举个例子，比如 :
    // x = [1,2,5,7,9,12,15], range = 3
    // 下标: 1 2 3 4 5 6 7
    // 以1位置做中心点: 能覆盖位置:1,2 -> [1..2]
    // 以2位置做中心点: 能覆盖位置:1,2,3 -> [1..3]
    // 以3位置做中心点: 能覆盖位置:2,3,4 -> [2..4]
    // 以4位置做中心点: 能覆盖位置:3,4,5 -> [3..5]
    // 以5位置做中心点: 能覆盖位置:4,5,6 -> [4..6]
    // 以6位置做中心点: 能覆盖位置:5,6,7 -> [5..7]
    // 以7位置做中心点: 能覆盖位置:6,7 -> [6..7]
    // 可以看出如果从左往右，依次求每个位置的左边界(left)和左边界(right)，是可以不回退的！
    int[] coverLeft = new int[N + 1];
    int[] coverRight = new int[N + 1];
    int left = 0;
    int right = 0;
    // 从左往右，不回退的依次求每个位置的左边界(left)和左边界(right)，记录到coverLeft和coverRight里
    for (int i = 0; i < N; i++) {
      while (x[i] - x[left] > range) {
        left++;
      }
      while (right < N && x[right] - x[i] <= range) {
        right++;
      }
      coverLeft[i + 1] = left + 1;
      coverRight[i + 1] = right;
    }
    // best[i]: 如果i是最左边缘点，选哪个点做技能中心点最好，下标从1开始，不从0开始
    // 与上面同理，依然可以不回退
    int[] best = new int[N + 1];
    int trigger = 0;
    for (int i = 0; i < N; i++) {
      while (trigger < N && x[trigger] - x[i] <= range) {
        trigger++;
      }
      best[i + 1] = trigger;
    }
    SegmentTree st = new SegmentTree(hp);
    st.build(1, N, 1);
    int ans = 0;
    // 整体思路：
    // 当前左边缘点从i位置开始(注意0位置已经弃而不用了)，
    // 目标是把左边缘的怪物杀死，但是放技能的位置当然是尽可能远离左边缘点，但是又保证能覆盖住
    // best[i] : 放技能的位置当然是尽可能远离左边缘点i，但是又保证能覆盖住，
    // 请问这个中心在哪？就是best的含义，之前求过了。
    // 然后在这个中心点，放技能，放几次技能呢？左边缘点还剩多少血，就放几次技能，
    // 这样能保证刚好杀死左边缘点。
    // 然后向右继续寻找下一个没有死的左边缘点。
    for (int i = 1; i <= N; i++) {
      // 查询当前i位置，还有没有怪物存活
      long leftEdge = st.query(i, i, 1, N, 1);
      // 如果还有血量(leftEdge > 0)，说明有存活。此时，放技能
      // 如果没有血了(leftEdge <= 0)，说明当前边缘点不需要考虑了，换下一个i
      if (leftEdge > 0) {
        // t = best[i]: 在哪放技能最值
        // l = coverLeft[t]: 如果在t放技能的话，左边界影响到哪
        // r = coverRight[t]: 如果在t放技能的话，右边界影响到哪
        // 就在t放技能，放leftEdge次，这样左边缘点恰好被杀死
        ans += leftEdge;
        int t = best[i];
        int l = coverLeft[t];
        int r = coverRight[t];
        // 同时[l...r]整个范围，所有的怪物都会扣除掉leftEdge的血量，因为AOE嘛！
        st.add(l, r, (int) (-leftEdge), 1, N, 1);
      }
    }
    return ans;
  }
}
