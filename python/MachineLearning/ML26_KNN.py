"""
K近邻算法，通过计算测试样本与训练数据之间的距离，选择距离最小的K个样本作为测试样本的邻居
并根据这些邻居的类别进行投票，子ui中确定测试样本的类别.
1、计算测试样本与训练数据之间的距离
2、选择距离最小的k个样本作为测试样本的邻居
3、根据这些邻居的类别进行投票，最终确定测试样本的类别
"""
from collections import Counter

import numpy as np


def k_nearest_neighbors(X, y, test_sample, k):
    test_sample = np.array(test_sample)
    distances = np.linalg.norm(X - test_sample,  axis=1)
    nearest_indices = np.argsort(distances)[:k]
    nearest_labels = y[nearest_indices]
    return Counter(nearest_labels).most_common(1)[0][0] # Counter统计出每个类别出现的次数，most_comon(1)取出次数最多的一个元素，这个元素是一个数组，再选出数组中第一个，tuple[0]就是它的label

from sklearn.neighbors import KNeighborsClassifier
def k_nearest_neighbors_standard(X, y, test_sample, k):
    x_ = np.array(X)
    y_ = np.array(y)
    model = KNeighborsClassifier(n_neighbors = k)
    model.fit(x_, y_)
    test_sample = np.array(test_sample).reshape(1, -1)
    species = int(model.predict(test_sample))
    return species

"""
- 第一行：m, n，以空格分隔的整数。
- 接下来m行：X: 特征矩阵，形状为 (m, n)，其中m是样本数量，n是特征数量。以空格分隔的浮点数。
- 接下来一行：y: 目标值，形状为 (m, 1)，其中m是样本数量。以空格分隔的整数。
- 接下来一行：test_sample: 测试样本，形状为 (n, 1)，其中n是特征数量。以空格分隔的浮点数。
- 接下来一行：k: 近邻数量，整数。
输出描述：
- 输出一个整数，表示测试样本的预测分类。
"""
if __name__ == "__main__":
    m, n = map(int, input().split())
    X = np.array([list(map(float, input().split())) for _ in range(m)])
    y = np.array(list(map(int, input().split())))
    test_sample = np.array(list(map(float, input().split())))
    k = int(input())
    print(k_nearest_neighbors(X, y, test_sample, k))