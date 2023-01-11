package com.hotinterview.erfenchazhao;

/**
 * @Author: zhaomengyi
 * @Date: 2023-01-06 4:40 下午
 * @Description:
 */
public class Code0378_KthMinNum {
  //思路：转换：把求第k小的数字，转为为 求小于num（指标）有多少个，然后根据这个数据进行第k大小数的查找
  // 如果没有这个数，就找小于这个num（标准）最近的一个数。所以要收集两个信息，一共多少个数，最近的数是多少
  public class Info {
    public int near;
    public int cnt;

    public Info(int n1, int n2){
      near = n1;
      cnt = n2;
    }
  }


  public int kthSmallest(int[][] matrix, int k) {
      int N = matrix.length;
      int M = matrix[0].length;
      int left = matrix[0][0];
      int right = matrix[N - 1][M - 1];
      int ans = 0;
      while(left <= right){
        int mid = left + ((right - left) >> 1);
        //找小于mid的数有多少个 还有 最近数的信息
        Info info = noMoreNum(matrix, mid);
        if(info.cnt < k){
          left = mid + 1;//往右侧继续找
        } else {
          ans = info.near;
          right = mid - 1;
        }
      }
    return ans;
  }

  public Info noMoreNum(int[][] matrix, int value){
    int near = Integer.MIN_VALUE;
    int cnt = 0; // 统计小于等于mid的数有多少
    int N = matrix.length;
    int M = matrix[0].length;
    int row = 0;
    int col = M - 1;//从每行最大的开始看，如果最大的小于了value，那么这一行全小于value
    //太难了~直接记住例子吧：这玩意就是从0行col列开始往下撸，一旦m[r][c]大于了value，就往左移动
    while( row < N && col >= 0){
      if(matrix[row][col] <= value){
        near = Math.max(near, matrix[row][col]);
        cnt += col + 1;
        row++;//判断下一行
      } else {
        col--;//看前面的是不是小于
      }
    }
    return new Info(near, cnt);
  }
}
