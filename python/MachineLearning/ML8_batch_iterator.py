"""
数据集的批量迭代器（Batch Iterator）是一种常用的数据处理方法，用于将数据集分为多个批次，每个批次包含固定数量的数据。
 本题也是mini-batch梯度下降法的关键步骤，关键思想可以参考梯度下降部分。

"""

import numpy as np


def batch_iterator(X, y=None, batch_size=64):
    n_samples = X.shape[0]
    batches = []
    for i in np.arange(0, n_samples, batch_size):
        begin, end = i, min(i + batch_size, n_samples)
        if y is not None:
            batches.append([X[begin:end], y[begin:end]])
        else:
            batches.append(X[begin:end])
    return batches


if __name__ == "__main__":
    X = np.array(eval(input()))
    y = np.array(eval(input()))
    batch_size = int(input())
    print(batch_iterator(X, y, batch_size))
