"""
题目要求在算法思路中的PCA降维中有写
"""
import sys
import json
import numpy as np

from numpy.random import f


def cal_mse(train, test):
    train_array = np.array(train, dtype=float)
    test_array = np.array(test, dtype=float)

    n_samples = train_array.shape[0]
    # 计算均值
    mu = np.mean(train_array, axis=0)
    # 广播机制
    X_c = train_array - mu

    # 机选协方差矩阵
    sigma = np.matmul(X_c.T, X_c) / n_samples

    # 使用线性代数的库得到特征值和特征向量
    eigenvalues, eigenvectors = np.linalg.eigh(sigma)

    # 返回特征值最大的索引
    eigen_max_idx = np.argmax(eigenvalues)
    # 一列是一个特征向量
    vec_max = eigenvectors[:, eigen_max_idx]

    # 获取所有非零元素的索引
    nonzero_indices = np.nonzero(vec_max)[0]
    # 如果存在非零的元素，可能全是0向量
    if nonzero_indices.size > 0:
        first_nonzero_idx = nonzero_indices[0]
        if vec_max[first_nonzero_idx] < 0:
            # 变换方向
            vec_max = -vec_max

    # 投影-重建
    mse_list = []
    for i in range(len(test_array)):
        x = test_array[i]
        x_center = x - mu
        z = np.dot(x_center, vec_max)
        # 重构的x为
        x_hat = mu + z * vec_max

        # 计算重构损失
        mse = np.mean((x - x_hat) ** 2)
        mse_list.append(f"{mse:.2f}")
    return mse_list


if __name__ == '__main__':
    data = json.load(sys.stdin)
    train = data["train"]
    test = data["test"]
    mse_list = cal_mse(train, test)
    print(json.dumps(mse_list))
