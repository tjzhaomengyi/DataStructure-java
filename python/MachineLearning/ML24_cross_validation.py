import numpy as np

"""
K折交叉验证数据拆分
"""


def cross_validation_split(data, k, seed=42):
    np.random.seed(seed)
    np.random.shuffle(data)

    n, m = data.shape
    sub_size = int(np.ceil(n / k))  # 每折大小
    id_s = np.arange(0, n, sub_size)  # start，stop ，step ，返回每组的起始位置
    id_e = id_s + sub_size  # 每折结束索引
    if id_e[-1] > n:
        id_e[-1] = n

    # [训练集， 验证集]
    # 验证集部分data[id_s[i]: id_e[i]]
    # data = [[0],[1],[2],[3],[4],[5]] , k = 3, 第一折[0,2] 第二折[2,4] 第三折[4,6] ,id_s = [0, 2, 4], id_e=[2,4,6] (注意区间是左闭右开)
    #  i = 0
    # 先看验证集 data[id_s[0]: id_e[0]] = data[0: 2] ,验证集是[[0],[1]]
    # 再看训练集 data[:id_s[0]] + data[id_e[0]:]  =  data[:0] + data[2:] 就是从 [2][3][4][5]
    # 主要思路就是，先抽取k折的第i折作为验证集合（就只有一部分），剩下的折都去做训练集，挺简单的，代码写的也很好
    return [[ np.concatenate([data[: id_s[i]], data[id_e[i]:]], axis=0).tolist(), data[id_s[i]: id_e[i]].tolist() ] for i
            in range(k)]
