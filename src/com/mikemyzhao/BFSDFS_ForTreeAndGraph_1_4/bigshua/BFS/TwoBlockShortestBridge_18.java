package com.mikemyzhao.BFSDFS_ForTreeAndGraph_1_4.bigshua.BFS;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-02 16:15
 * @Description:一个矩阵中有两块分割的1，求两块区域连通起来最短的距离，只能横竖连
 * @todo:感觉这个解法太大了，起始可以简单一些
 * @解法：先把二维转一维，然后在二维上标记，在一维上记录curs队列和records广播距离
 * @注意：每次dfs和infect的时候返回记录长度，否则超时
 */
public class TwoBlockShortestBridge_18 {
  //todo:使用BFS得到连通区的最短距离
  //把已知的联通区域向外进行扩散，然后在相交的地方求得距离
  //扩散的时候把自己周围的1标记为2，自己本身为1，然后再从2扩散的时候将外围的1设置为3以此类推，然后矩阵中两个位置相加减3就是最小距离

  public static int shortestBridge(int[][] m){
    //将二维数组变成一维数组
    int N = m.length;
    int M = m[0].length;
    int all = N * M;
    int island = 0;
    int[] curs = new int[all]; //【记录位置】标记二维数组中为1的位置，这个就是一个做感染标记的队列，因为是BFS，所以用数组代替一个队列
    int[] nexts = new int[all];//下一个dfs遍历时候的队列，在外围用nexts替换curs
    int[][] records = new int[2][all];//【记录每个位置的感染距离】记录本来就为1的感染区域

    for(int i = 0; i < N; i++){
      for(int j = 0; j < M; j++){
        if(m[i][j] == 1){
          //当前位置是1
          //感染过程：todo:当前发现了1，此时(0)把当前1改为2(在原矩阵中改)，同时：(1)抓上来这一片1组成的初始队列放在cur中;(2)把自己到自己的距离设置为1，把周围的1到自己的距离设置成1，放在record中
          //其实这个感染过程已经把岛1所有的点全感染了，就是岛屿1所有点都标记上来，注意这个返回值，curs当前增加到queueSize大小
          int queueSize = infect(m, i, j, N, M, curs, 0, records[island]);//records[0]表示第一个感染的小岛
          //todo:BFS计算距离过程，curs中所有的点向上走过的点走，往上下左右走
          int V = 1;
          while(queueSize != 0){
            V++;
            queueSize = bfs(N, M, all, V, curs, queueSize, nexts, records[island]);//只遍历到queueSize-1位置
            //把当前的nexts替换掉curs
            int[] tmp = curs;
            curs = nexts;
            nexts = tmp;
          }
          island++;//bfs完事就可以轮到第二块区域继续算这个过程了。这个时候岛1标记完，广播完，轮到岛2进行上述过程
        }
      }
    }

    int min = Integer.MAX_VALUE;
    for (int i = 0; i < all; i++) {
      min = Math.min(min, records[0][i] + records[1][i]);
    }
    return min - 3;
  }


  //todo:这个其实是一个四维的dfs，找到第一个感染的岛区域
  //当前来到m[i][j]位置，m大小为N*M
  //m[i][j]向外感染，找到这片岛四个方向上的所以为1的点，进行+1操作，然后放入curs队列中。
  //返回当前index，设置当前自己的距离为1
  public static int infect(int[][] m, int i, int j, int N, int M, int[] curs, int index, int[] record){
    if(i < 0 || i == N || j < 0 || j == M || m[i][j] != 1){
      return index;//四个边缘的都不用考虑，因为后续周边的会把这个点感染
    }
    //todo:1、处理自己在原矩阵中的标记，设置成2表示遍历过了即可
    //m[i][j]一定不越界且等于1
    m[i][j] = 2;//标记为2表示已经遍历过了，如果不改的话递归完成不了，这其实就是一个深度遍历

    //todo:2、自己在一维表中的属性
    int p = i * M + j;//找到对应的一维下标
    //todo:2-(1)自己在record记录中自己到自己的距离是1
    record[p] = 1;//自己的距离就是1
    //todo:2-(3)放到队列中，进行广播
    curs[index++] = p;
    //向自己的四个方向上进行感染
    index = infect(m, i - 1, j, N, M, curs, index, record);
    index = infect(m, i + 1, j, N, M, curs, index, record);
    index = infect(m, i, j - 1, N, M, curs, index, record);
    index = infect(m, i, j + 1, N, M, curs, index, record);
    return index;//todo:返回最后队列成长到哪里,这里表示队列的长度，最后一个是index-1
  }

  //二维原始矩阵：M，N，all
  //v当前BFS广播要到第几层
  //record里面拿距离，todo:record为0的点就是没有遍历或者广播到的点，如果广播到这个点变成V
  //todo:把下一层要广播的点放在nexts队列中，然后在主程序的外围和curs队列进行交换
  public static int bfs(int N, int M, int all, int V, int[] curs, int size, int[] nexts, int[] record){
    int nexti = 0;//生成队列的位置
    for(int i = 0; i < size; i++){
      //在当前curs队列中进行bfs，往当前节点的上下左右看看是不是合适位置
      int up = curs[i] < M ? -1 : curs[i] - M;//如果是第一行肯定curs[i]小于列的个数
      int down = curs[i] + M >= all ? -1 : curs[i] + M;
      int left = curs[i] % M == 0 ? -1 : curs[i] - 1;
      int right = curs[i] % M == M - 1 ? -1 : curs[i] + 1;

      if(up != -1 && record[up] == 0){
        //上位置合法且没有遍历过
        record[up] = V;//广播出去啦！！哈哈哈
        nexts[nexti++] = up;//下次广播的队列其中一员，光荣！
      }
      if(down != -1 && record[down] == 0){
        record[down] = V;
        nexts[nexti++] = down;
      }
      if(left != -1 && record[left] == 0){
        record[left] = V;
        nexts[nexti++] = left;
      }
      if(right != -1 && record[right] == 0){
        record[right] = V;
        nexts[nexti++] = right;
      }
    }
    return nexti;//返回当前遍历到的节点
  }

  public static int getIndex(int i, int j, int col){
    return i * col + j;
  }
}


