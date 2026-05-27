"""
根据特定特征的阈值将数据划分成两个子集，
对于数值型阈值：将特征值大于等于阈值的样本划分到一个子集，小于阈值的样本划分到第二个子集
对于非数值型阈值：将特征等于阈值的样本划分到第一个子集，不等于的划分到第二个子集
"""
import numpy as np


def divide_on_feature(X, feature_i, threshold):
    if isinstance(threshold, (int, float)):  # 如果是数值类型
        mask = X[:, feature_i] >= threshold
    else:  # 如果是非数值类型
        mask = X[:, feature_i] == threshold
    X1 = X[mask]
    X2 = X[~mask]
    return [X1, X2]


if __name__ == "__main__":
    X = np.array(eval(input()))
    feature_i = int(input())
    threshold = eval(input())
    print(divide_on_feature(X, feature_i, threshold))
