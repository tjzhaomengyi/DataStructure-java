"""
1、标准化输入
2、计算协方差矩阵
3、计算特征值和特征向量，PCA核心思想及本质：找协方差矩阵方差最大方向，特征向量=方向，特征值=该方向方差大小
4、选择主成分,选择特征值最大的k个特征向量作为主成分
"""
import numpy as np


def pca(data, k):
    data_standardized = (data - np.mean(data, axis=0)) / np.std(data, axis=0)
    # 计算协方差矩阵
    covariance_matrix = np.cov(data_standardized, rowvar=False) # rowvar 表示每列是一个变量
    # 计算特征值和特征向量, 特征值分解
    eigenvalues, eigenvectors = np.linalg.eig(covariance_matrix)

    # 选择主成分
    idx = np.argsort(eigenvalues)[::-1]  # 从大到小后的排序索引
    eigenvalues_sorted = eigenvalues[idx]
    eigenvectors_sorted = eigenvectors[:, idx]

    principal_components = eigenvectors_sorted[:, :k]
    return np.round(principal_components, 4)
