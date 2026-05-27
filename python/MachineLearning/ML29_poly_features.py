"""
生成多項式特征
"""
from itertools import combinations_with_replacement

import numpy as np


def poly_features(X, degree):
    n_samples, n_features = np.shape(X)

    # 生成所有特征组合
    def index_combinations():
        # range是左闭右开的区间
        # 1、先看第二个range(0, 2+1) 得到[0，1，2],表示生成0次项、1次项和2次项
        # n_feature=3
        # 2、combinations_with_replacement([0,1,2], i)表示从中选出i个特征进行组合，返回一个lazy迭代器，（0，[]）(1,[[0],[1],)
        combs = [combinations_with_replacement(range(n_features), i) for i in range(0, degree + 1)]
        flat_combs = [item for sublist in combs for item in sublist]
        return flat_combs

    combinations = index_combinations()
    n_output_features = len(combinations)
    X_new = np.empty((n_samples, n_output_features))

    for i, index_combs in enumerate(combinations):
        X_new[:, i] = np.prod(X[:, index_combs], axis=1)
    return X_new


def poly_features_standard(X, degreee):
    from sklearn.preprocessing import PolynomialFeatures
    poly = PolynomialFeatures(degreee=degreee)
    return poly.fit_transform(X)



if __name__ == "__main__":
    X = np.array(eval(input()))
    degree = int(input())
    print(poly_features(X, degree))