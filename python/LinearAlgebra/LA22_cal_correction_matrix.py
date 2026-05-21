"""
不错的题：计算两个矩阵的相关性 = 先计算两个矩阵的协方差，再计算两个矩阵的标准差
"""

import numpy as np

"""公式：R = cov(X,Y) / σXσY"""

# 计算 X 和 Y的协方差 cov
def cal_cov_matrix(X, Y):
    X_centered = X - X.mean(axis=0)
    Y_centered = Y - Y.mean(axis=0)
    return (X_centered.T @ Y_centered) / np.shape(X_centered)[0]


def get_sd(X):
    '''Sqrt(1/n∑(x-x拔)²）'''
    return np.sqrt( np.mean(( X - X.mean(axis=0)) ** 2, axis=0) )


def calculate_correlation_matrix(X, Y=None):
    # Your code here
    if Y is None:
        Y = X
    # 将标准差转换为列向量
    std_X = get_sd(X).reshape(-1, 1)
    std_Y = get_sd(Y).reshape(-1, 1)
    return cal_cov_matrix(X, Y) / (std_X @ std_Y.T)


if __name__ == "__main__":
    X = np.array(eval(input()))
    print(calculate_correlation_matrix(X))
