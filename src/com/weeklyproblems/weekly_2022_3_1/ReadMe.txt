强连通分量专题：
Tarjan算法：
一、概念
1、使用dfn数组表示图，dfs把图中的节点搞出dfn描述，就是给出节点编号，父节点的dfn肯定比子节点的dfn要小
2、low数组，表示后续所有孩子中，谁能插到最顶的位置，
    例子：2->3->4->5->6->7->>2 ,dfn[0] = 2,2节点能插到比自己小的节点上，就是自己low[2] = 2,3节点的后续节点能插到最小的dfn节点，low[3]=2
    伪代：如何找到自己的low
    public static int cnt = 0;
    public void f(int from,int[] dfn int[] low, int[][] nexts, boolean[] visited){ //nexts表示后代
        if(visted[from]{ //注意在结算过程中不要处理前面处理过的节点！
            return;
        }
        dfn[from] = ++cnt; //给from点一个dfn编号
        low[from] = dfn[from];//把low先设置为自己
        for(int next : nexts[from]){
            f(next, dfn, low, nexts, visited);//递归看子节点
            low[from] = Math.min(low[from], low[next]);//找到from的low，是low自己还是next找到的
        }
    }
二、结论
1、如果在完成dfn和low数组生成后，某个点的dfn和low值相等，那么这个点就是该强连通分量的口袋！！！
2、一个节点只有三种状态：未遍历、遍历未结算low、遍历已结算low
3、用栈保存遍历过的点，如果找到口袋点，将栈中遍历过的节点标记为遍历已结算

三、用处
使用Tarjan生成DAG图

四、代码
注意scc变量的使用，直到这个值大于了0，那么这个点被处理过了，并且这个点属于某个大集团，大集团号从1开始，sccn记录集团编号