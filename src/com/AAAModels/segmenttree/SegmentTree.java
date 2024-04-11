package com.AAAModels.segmenttree;

/**
 * @Author: zhaomengyi
 * @Date: 2022-03-13 15:30
 * @Description:线段树类
 */
public class SegmentTree {
  //数学结论:线段树能干啥，(1)某个区间段的全部加某个数，(2)某个区间全部更新某个值，(3)区间上累计和查询
  //【3，4，6，7】
  //做成树状结构来求和
  //             1-4（20）
  //   1-2（7）             3-4（13）
  //1-1（3） 2-2（4）     3-3（6）  4-4（7）
  //例子2【3，2，1，2，6】
  //          1-5a
  //    1-2b       3-5c
  //1-1d   2-2e  3-3f  4-5g
  //
  // 申请数组arr[],0位置不用，【，a，b，c，d，e，f，g】 我们可以通过i/2找到i位置父节点，通过2i找到i的左孩子，2i+1找到i的右孩子
  // 结论1：arr申请4*数组长度即可
  // 结论2：懒更新，更新什么呢？更新两两节点的和存放在它们的父节点中，需要配合懒更新数组lazy执行，lazy大小也是4*数组长度，
  //    懒更新例子：假如有任务需要更新3-874范围的数值（+5），当前碰到了251-500这个node区间，251-500区间把3-874区间更新揽住了一部分，就把这个更新记录记录在lazy数组中对应位置
  //    这个位置对应arr数组中251-500的节点位置，这个任务揽住了不再往下发了。如果不能全包住的比如1-250，这个时候就要向node节点的子节点往下分发
  //例子 【0，0，0，0】
  // sum【】
  // lazy【】
  // 下标【1-4，1-2，3-4，1-1，2-2，3-3，4-4】
  // 任务1：1-4范围上每个值+3，lazy的1-4把这个任务全可以揽住
  // lazy变为【3，0，0，0，0，0，0】
  // 任务2：1-2范围每个值 +4
  // （1）先看有没有懒任务，如果有懒任务，把前面懒住的任务往下下发一层！！！！自己清空lazy数组【0，3，3，0，0，0，0】
  // （2）1-2任务再进来，发现自己揽住的任务有懒任务【3】（位置1），往下下发，把任务2的更新任务填写到lazy数组中【0，4，3，3，3，0，0】
  // 编写代码步骤：（1）SegmentTree使用的几个4倍长度数组建立，把原始数组填写到以1为开始下标的arr数组中
  // （2）pushUp(),父节点进行子节点的累加和计算
  // （3）build(),使用origin数组构建内部的sum数组
  // （4）pushDown()的lazy[rt]!=0分支，这部分需要把lazy往下下发一层，sum也把下一层进行更新，注意pushDown的参数rt这个范围的节点下标，ln和rn对应rt左右节点的个数

  private int MAXN;//
  private int[] arr; //把源数组拷贝出来，让它从下标1开始
  //add操作辅助数组
  private int[] sum;//线段树维护的区间和
  private int[] lazy;//累加的懒惰标记
  //update操作辅助数组
  private int[] change;//更新的值，每个范围上更新了什么
  private boolean[] update;//更新懒惰标记，这个范围上有没有更新信息

  //注意线段树一定要从1开始才能满足
  public SegmentTree(int[] origin){
    MAXN = origin.length + 1; //从下标1开始
    arr = new int[MAXN];
    for(int i = 1; i < MAXN; i++){
      arr[i] = origin[i-1];
    }
    sum = new int[MAXN << 2];//某一个累加和的信息

    lazy = new int[MAXN << 2];//某一个范围往下传递的叠加任务
    change = new int[MAXN << 2];
    update = new boolean[MAXN << 2];
  }
  //1、根节点求和，rt表示当前的根节点
  public void pushUp(int rt){
    sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
  } //root节点的累加和 等于 左孩子2*i + 右孩子2i+1

  //4、（1）add把揽更新往下推，走分支lazy[rt]!=0
  //5、（2）update[rt]有更新信息
  //之前的所有揽增加，和揽更新要分发给两个子范围，ln表示左侧节点个数，rn表示右侧节点个数
  private void pushDown(int rt, int ln, int rn){
    //从根节点rt往子节点上分发update,update一般是指一部分范围的更新
    if(update[rt]){ //如果当前节点有更新
      update[rt<<1] = true; //（1）子节点更新update状态
      update[rt<<1 | 1] = true;
      change[rt << 1] = change[rt];//（2）子节点更新chage值
      change[rt << 1 | 1] = change[rt];
      //如果是更新，把这个点的两个子节点的lazy也置空，新的要来了，因为已经往下push了update和change所以这个lazy要变0
      //update和lazy是相对的，只要是update子节点就不可能是lazy;前面lazy的值因为更新全清零
      lazy[rt << 1] = 0; //（3）左右两个孩子的揽信息清除
      lazy[rt << 1 | 1] = 0; //（3）左右孩子两个揽信息清除
      sum[rt << 1] = change[rt] * ln;//因为是ln范围的更新，这个范围的值全变为change[rt]的值
      sum[rt << 1 | 1] = change[rt] * rn;
      update[rt] = false;
    }
    //add揽更新把揽信息下发一层（只发一层只发一层只发一层！）的代码，ln表示rt左侧节点的个数，rn表示rt右侧节点的个数
    if(lazy[rt] != 0){
      lazy[rt << 1] += lazy[rt];//（1）lazy信息下发一层，给左孩子
      sum[rt << 1] += lazy[rt] * ln; // （2）把rt的揽信息往左侧sum增加
      lazy[rt << 1 | 1] += lazy[rt];//（1）lazy信息下发一层，给右孩子
      sum[rt << 1 | 1] += lazy[rt] * rn; //（2）把rt的揽信息往右侧孩子上的sum上增加
      lazy[rt] = 0;//（3）当前rt清零好让后续的lazy进来
    }
  }

  //2、build出原始数组，利用arr数组建立出sum数组
  // 初始化阶段把sum数组填好，在arr[l~r]范围上，build[1~n]
  //rt:这个范围在sum中的下标
  public void build(int l,int r,int rt){
    if ( l == r ){ //递归的base case，肯定是叶子节点，必然没有子节点
      sum[rt] = arr[l]; //直接网上塞
      return;
    }
    //l 不等于 r
    int mid = (l+r) >> 1;
    build(l,mid,rt << 1); //l到r范围的左孩子 给我build好
    build(mid+1,r,rt << 1|1); //l到r范围的右孩子 给我build好
    pushUp(rt); //左右孩子更新rt的sum
  }

  //5、update 任务L到R所有值变成C，
  //对某个范围进行值的更新,LR是任务的范围，开始的时候就是rt=1，l=1，r=n，rt可以把所有范围的点都扎到
  public void update(int L,int R,int C,int l,int r,int rt){
    //任务范围LR可以覆盖lr
    if(L<=l && r<=R){
      update[rt] = true; //有更新
      change[rt] = C; //设置值
      sum[rt] = C*(r-l+1); //sum更新
      lazy[rt] = 0; //之前揽的值清空！！！
      return;
    }
    //l到r有一部分无法揽住LRC这个揽任务，只有部分可以更新，就要往下一层发揽任务
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

  //3、增加值：情况1：能够揽住更新，情况2：不能够揽住更新，找到当前rt对应范围l到r的终点，然后把揽信息往下压一层，然后把任务分发给左右孩子！！
  //LRC三个参数表示当前的任务信息，L..R任务范围，所有值加上C
  //l,r->表达的范围，rt表示l到r范围对应的下标
  // rt去哪找l，r范围上的信息，开始的时候就是rt=1，l=1，r=n，rt可以把所有范围的点都扎到
  public void add(int L,int R, int C, int l, int r, int rt){
    //任务被覆盖到最好
    if(L<=l && r <= R){
      sum[rt] += C*(r-l+1);//根节点的累加和变大这么多，更新值C，一共r-l+1，这个范围可以揽住任务的一部分！！！
      lazy[rt] += C; //lazy数组也要更新信息！！！把之前揽住的信息和当前的信息一起揽住（开课时解释的是可以先清0，也可以这么一直揽）
      return;
    }
    //l到r有一部分揽不住这个任务，任务往下发
    int mid = (l+r) >> 1;
    pushDown(rt,mid - l + 1,r - mid);//完不成的往下推，rt表示l到r范围的下标，计算出左右两侧的节点个数mid-l + 1 ，r-mid+1
    //左边孩子需要接到任务
    if(L <= mid){
      add(L, R, C, l, mid, rt << 1);//左边从l到mid，下标为rt*2
    }
    //右边孩子需要接到任务
    if(R > mid){
      add(L, R, C, mid+1, r, rt <<1 | 1);//右边从mid到r，下标为rt * 2 + 1
    }
    //左右孩子完成任务汇总当前节点的信息
    pushUp(rt);
  }

  //查询L到R的累加和是多少
  public long query(int L, int R, int l, int r, int rt){
    if( L <= l && r <= R) { //全揽住了
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

  public static void main(String[] args) {
    int[] origin = new int[]{1,2,3,4,5,6,7,8,9,10};
    //Segment固定调用方法！！！直接这么生成就行
    SegmentTree st = new SegmentTree(origin);
    int S = 1;
    int N = origin.length;
    int root = 1;
    st.build(S,N, root); //调用的时候虽然是看着origin调用的，但是内部是在执行SegmentTree构建得到的st内部的arr数组和sum数组
    int L = 2;
    int R = 5;
    int C = 4;
    st.add(L,R, C, S, N, root);
    st.update(L, R, C, S, N, root);
    long sum = st.query(L, R, S, N, root);
  }
}


