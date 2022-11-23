package com.mikemyzhao.SegmentTree_IndexTree_9;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-13 15:30
 * @Description:线段树类
 */
public class SegmentTree {
  private int MAXN;//
  private int[] arr;
  private int[] sum;//线段树维护的区间和
  private int[] lazy;//累加的懒惰标记
  private int[] change;//更新的值
  private boolean[] update;//更新懒惰标记

  //注意线段树一定要从1开始才能满足
  public SegmentTree(int[] origin){
    MAXN = origin.length + 1;
    arr = new int[MAXN];
    for(int i = 1; i < MAXN; i++){
      arr[i] = origin[i-1];
    }
    sum = new int[MAXN << 2];//某一个累加和的范围

    lazy = new int[MAXN << 2];
    change = new int[MAXN << 2];
    update = new boolean[MAXN << 2];
  }
  //rt表示当前的根节点
  public void pushUp(int rt){
    sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
  }

  //之前的所有懒增加，和懒更新要分发给两个子范围，ln表示左侧节点个数，rn表示右侧节点个数
  private void pushDown(int rt, int ln, int rn){
    //从根节点rt往子节点上分发update,update一般是指一部分范围的更新
    if(update[rt]){
      update[rt<<1] = true;
      update[rt<<1 | 1] = true;
      change[rt << 1] = change[rt];
      change[rt << 1 | 1] = change[rt];
      //如果是更新，把这个点的两个子节点的lazy也置空，新的要来了，因为已经往下push了update和change所以这个lazy要变0
      //update和lazy是相对的，只要是update子节点就不可能是lazy;前面lazy的值因为更新全清零
      lazy[rt << 1] = 0;
      lazy[rt << 1 | 1] = 0;
      sum[rt << 1] = change[rt] * ln;//因为是ln范围的更新，这个范围的值全变为change[rt]的值
      sum[rt << 1 | 1] = change[rt] * rn;
      update[rt] = false;
    }
    if(lazy[rt] != 0){
      lazy[rt << 1] += lazy[rt];
      sum[rt << 1] += lazy[rt] * ln;
      lazy[rt << 1 | 1] += lazy[rt];
      sum[rt << 1 | 1] += lazy[rt] * rn;
      lazy[rt] = 0;//当前rt清零好让后续的lazy进来
    }
  }

  //初始化阶段把sum数组填好，在arr[l~r]范围上，build[1~n]
  //rt:这个范围在sum中的下标
  public void build(int l,int r,int rt){
    if(l == r){
      sum[rt] = arr[l];
      return;
    }
    int mid = (l+r) >> 1;
    build(l,mid,rt << 1);
    build(mid+1,r,rt << 1|1);
    pushUp(rt);
  }

  //对某个范围进行值的更新,LR是任务的范围，开始的时候就是rt=1，l=1，r=n，rt可以把所有范围的点都扎到
  public void update(int L,int R,int C,int l,int r,int rt){
    //任务范围LR可以覆盖lr
    if(L<=l && r<=R){
      update[rt] = true;
      change[rt] = C;
      sum[rt] = C*(r-l+1);
      lazy[rt] = 0;
      return;
    }
    //当前任务无法懒更新，往下发
    int mid = (l+r) >> 1;
    pushDown(rt,mid-l+1,r-mid);//完不成的往下推！
    if(L<=mid){
      update(L,R,C,l,mid,rt<<1);
    }
    if(R > mid){
      update(L,R,C,mid+1,r,rt<<1 | 1);
    }
    pushUp(rt);
  }

  //L..R任务范围，所有值加上C
  //l,r->表达的范围
  // rt去哪找l，r范围上的信息，开始的时候就是rt=1，l=1，r=n，rt可以把所有范围的点都扎到
  public void add(int L,int R, int C, int l, int r, int rt){
    //任务被覆盖到最好
    if(L<=l && r <= R){
      sum[rt] += C*(r-l+1);
      lazy[rt] += C;
      return;
    }
    //LR没有把lr包住，任务往下发
    int mid = (l+r) >> 1;
    pushDown(rt,mid-l+1,r-mid);//完不成的往下推
    //左边孩子需要接到任务
    if(L <= mid){
      add(L, R, C, l, mid, rt << 1);
    }
    //右边孩子需要接到任务
    if(R > mid){
      add(L, R, C, mid+1, r, rt <<1 | 1);
    }
    //左右孩子完成任务汇总当前节点的信息
    pushUp(rt);
  }

  //查询1~N的累加和是多少
  public long query(int L, int R, int l, int r, int rt){
    if( L <= l && r <= R) {
      return sum[rt];
    }

    int mid = (l+r) >> 1;
    pushDown(rt, mid-l+1, r-mid);//完不成的往下推！
    long ans = 0;
    if(L <= mid){
      ans += query(L, R, l, mid, rt << 1);
    }
    if(R > mid){
      ans += query(L, R, mid+1, r, rt << 1 | 1);
    }
    return ans;
  }
}
