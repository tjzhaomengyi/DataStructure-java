package com.mikemyzhao.TreeClassicSytle.TreeChain;

/**
 * @Author: zhaomengyi
 * @Date: 2022-04-04 15:46
 * @Description:经典树链刨分问题：给定树的父亲节点数组，大小为N，表示N个节点。father[i] = j(i表示数组下标，j表示父节点对应下标),father表示一棵树，不是森林。
 * 给定数组values，大小同样为n，values[i]表示树中每个节点的值，实现下面四个方法保证每个方法的速度：
 *  (1)让某个子树所有节点值加上V，addAll(head,v)
 *  (2)查询某子树所有节点值的累加和是多少,query(head)
 *  (3)在树上从a到b的整条链上，所有节点都加上V addRange(a,b,v)
 *  (4)查询在树上从a到b的整条链所有节点值的累加和,query(a,b)
 *
 *  思路：整体的更新和查询肯定是线段树，然后需要把原来的树构建成带有重链的树，并且这个树要以序号1开头，构建重链树后，同样构建对应的重建树对应的权重
 *
 */
public class TreeChain_21 {
  //技巧：(1)重链和轻链，一个根节点有左右两个子树，子节点多的部分被划分为重链，少的是轻链。划分轻重链的目标是让节点在找父节点的时候减少跳跃次数，直接到达
  // (2)重链上的标号是连续的
  public static class TreeChain{
    //每个节点时间戳
    private int tim;
    //节点个数n，编号1~n
    private int n;
    //头结点
    private int h;
    //技巧：从下面开始数组编号都加1，因为要满足SegmentTree的一些特性要求
    //朴素树结构，技巧：这里先要把原来的树平移1[4,1,1,0,1] -> [5,2,2,1,2]。再对应出朴素树结构，二维数组表示每个节点的孩子有哪些
    private int[][] tree;
    //权重数组
    private int[] val;
    private int[] fa;
    //深度数组
    private int[] dep;
    //son[i] = 0 表示没有儿子，如果不等于0表示重儿子是谁
    private int[] son;
    //i为头结点的子树，有多少个节点
    private int[] siz;

    //技巧：重构的带有重链的树对应的数组都在这
    //top[i] = j i所在的重链头结点是j
    private int[] top;//头结点
    //dfn[i] = j i这个节点，在dfs序中是第j个
    private int[] dfn;//以重链为准的头
    //如果原来节点a的value=10，那么该节点在dfs序中是第5个节点，tnw[5] = 10;
    private int[] tnw; //把原来val值映射到dfs序中
    private SegmentTree seg;//经典的线段树结构，在线段树上进行连续区间的更新和查询

    public TreeChain(int[] father, int[] values){
      //对原始的结构进行treechain初始化
      //技巧：使用initTree初始化了这些变量：tree,val
      initTree(father, values);
      //技巧:通过DFS，初始化了fa、dep、siz和son数组
      dfs1(h, 0);
      //技巧：使用步骤1中的tree、son数组构建dfs深度遍历的树dfn和dfn数组对应tnw数组
      dfs2(h, h);
      //技巧:已经把原有的树放在了dfn上了,权重都在tnw数组上，构建线段树随时更新即可
      seg = new SegmentTree(tnw);
      seg.build(1,n,1);//根节点是1

    }

    private void initTree(int[] father, int[] values){
      //技巧：完成迁移的数组有tree，val
      tim = 0;
      //技巧：上面初始化的所有数组都把0位置刨除掉，这样好满足线段树SegmentTree的一些特征要求
      n = father.length + 1;//新的
      tree = new int[n][];
      val = new int[n];
      fa = new int[n];
      dep = new int[n];
      son = new int[n];
      siz = new int[n];
      top = new int[n];
      dfn = new int[n];
      tnw = new int[n--];//todo:这个特殊
      int[] cnum = new int[n];//技巧：记录每个父节点的子节点数量
      for(int i = 0; i < n; i++){
        val[i + 1] = values[i];
      }
      for (int i = 0; i < n; i++){
        if(father[i] == i){
          //这个是头部
          h = i + 1;//初始化头部变脸
        }else{
          //这都是子节点,统计其父节点的个数
          cnum[father[i]]++;
        }
      }
      tree[0] = new int[0];//技巧：新的树节点的0位置不存东西，放空
      for (int i = 0; i < n; i++){
        tree[i + 1] = new int[cnum[i]];//建立孩子节点数量大小的数组
      }
      for (int i = 0; i < n; i++){
        if(i + 1 != h){//当前下标位置不是头结点
          //技巧：在自定义数组中孩子节点从后往前填入，cnum中存个数，所以对应tree的数组中的下标就是cnum[father[i]]-1
          cnum[father[i]] = cnum[father[i]] - 1;//
          tree[father[i] + 1][cnum[father[i]]] = i + 1;
        }
      }
    }

    //技巧：处理个啥问题呢？起始还是初始化上面定义的数组：fa,dep,siz和son
    //u表示当前节点，f表示u的父节点
    private void dfs1(int u, int f){
      fa[u] = f;//把u的爹放
      dep[u] = dep[f] + 1; //所在层比他爹多1
      siz[u] = 1;//就自己
      int maxSize = -1;//技巧：两个子节点哪个更大，分配成重儿子
      //技巧：u这个逼开始在tree里面折腾子节点了
      for(int v : tree[u]){
        //dfs
        dfs1(v, u);
        //如何分配重儿子
        siz[u] += siz[v];
        if(siz[v] > maxSize){
          maxSize = siz[v];
          son[u] = v;//这个逼的size多，分配成重儿子
        }
      }
    }

    //u是当前节点
    //t是u所在重链的头部
    //技巧：(1)这里存储 dfn节点的编号，只要在同一个重链上，让他们的编号连续。
    // (2)同时把权重值转移到dfn对应的tnw数组上，
    // (3)设置top数组，对应重链的头部
    private void dfs2(int u, int t){
      dfn[u] = ++tim;//从1开始
      top[u] = t;
      tnw[tim] = val[u];//把权重值转移到dfs树上
      if(son[u] != 0){
        //如果有重儿子
        dfs2(son[u], t);
        for(int v : tree[u]){
          if(v != son[u]){//如果v不是u的重儿子，自己为重链的头，重新算
            dfs2(v,v);
          }
        }
      }
    }
  }

  //技巧：辅助的线段树，主要是负责批量查询和修改操作
  public static class SegmentTree{
    private int MAXN;//线段树的大小
    private int[] arr;//在这个数组中维护线段树数组，技巧：下标从1开始,注意这里已经在上游把数组下标处理好了，直接传即可
    private int[] sum;//维护线段树的区间和，技巧：原树长度的4倍
    private int[] lazy;//技巧：维护懒逼的标记，看看当前这个节点是不是懒着之前更新任务了

    public SegmentTree(int[] origin){
      MAXN = origin.length;
      arr = origin;
      sum = new int[MAXN << 2];
      lazy = new int[MAXN << 2];
    }

    //技巧：1.当前节点求和：求当前节点rt的累加值
    private void pushUp(int rt){
      sum[rt] = sum[rt << 1] + sum[rt << 1 | 1];
    }
    //技巧：2.当前节点犯懒逼了【什么时候会犯懒逼就是当下发的任务不能囊括住当前节点，当前节点太牛逼了，往下下发吧！】
    // 比如任务要求在范围 50~100 范围的值都增加10，当前节点的囊括范围是 40 ~ 120，我草任务盖不住啊，懒逼吧~！
    // 往下压任务，当前节点给范围内的节点压任务.注意：这里只用了增加没有用update，所以没有update标志
    // 左侧范围大小为ln，右侧大小为rn
    private void pushDown(int rt, int ln, int rn){
      //rt懒逼了，懒逼往下压任务,因为只有相加没有update更新所以只有lazy，把update删了
      if (lazy[rt] != 0){
       lazy[rt << 1] += lazy[rt];
       lazy[rt << 1 | 1] += lazy[rt];
       sum[rt << 1] += lazy[rt] * ln;
       sum[rt << 1 | 1] += lazy[rt] * rn;
       lazy[rt] = 0;//技巧：已经往下压了，当前节点lazy为空，好让新任务下来
      }
    }

    //技巧：【其实挺简单】3、使用左右侧递归构建线段树，L，R表示构建树的范围，rt表示这个区间上的和是多少
    //        rt
    //   rt*2    rt*2+1
    // ...  ... ...  ...
    // l    ... ...    r
    public void build(int l, int r, int rt){
      if(l == r){
        sum[rt] = arr[l];
        return;
      }
      int mid = (l + r) >> 1;
      build(l, mid,rt << 1);
      build(mid + 1, r,rt << 1 | 1);
      pushUp(rt);//技巧：自己的左右节点搞定，把自己和算出来
    }

    //技巧：LR表示任务下发的范围，即L到R范围上每个数增加C，rt表示当前任务来到的格子，囊括的范围l到r
    public void add(int L, int R, int C, int l, int r, int rt){
      if(L <= l && r <= R){
        //技巧：下发的任务可以囊括住该范围
        sum[rt] += C * (l - r + 1);
        lazy[rt] += C;
        return;
      }
      //技巧：囊括不住分左右两边分发，就是犯懒逼往下pushdow()
      int mid = (l + r) >> 1;
      pushDown(rt, mid - l + 1, r - mid);//左侧节点的长度和右侧节点的长度
      //技巧：二分往下递归去
      if(L <= mid){
        add(L, R, C, l, mid, rt << 1);
      }
      if(R > mid){
        add(L, R, C, mid + 1, r, rt << 1 | 1);
      }
      pushUp(rt);//技巧：更新当前节点
    }

    public int query(int L, int R, int l, int r, int rt){
      if(L <= l && r <= R){
        return sum[rt];
      }
      int mid = (l + r) >> 1;
      pushDown(rt, mid - l + 1, r - mid);
      int ans = 0;
      if(L <= mid){
        ans += query(L, R, l, mid, rt << 1);
      }
      if(R > mid){
        ans += query(L, R, mid + 1, r, rt << 1 | 1);
      }
      return ans;
    }



  }
}
