import numpy as np
"""
编写一个函数，使用标准化和最小最大标准化对数据集执行特征缩放。
该函数应采用 2维 NumPy 数组作为输入，其中每行代表一个数据样本，每列代表一个特征。返回两个 2维 NumPy 数组 数组：一个通过标准化缩放，一个通过最小-最大标准化缩放。
输出结果均四舍五入保留小数点后后四位。
"""

"""
标准化 和 最大最小化公式
"""
def feature_scaling(data):
    mean = np.mean(data, axis=0)
    std = np.std(data, axis=0)
    standardized_data = (data - mean) / std

    min_val = np.min(data, axis=0)
    max_val = np.max(data, axis=0)
    normalized_data = (data - min_val) / (max_val - min_val)
    return np.round(standardized_data, 4).tolist(), np.round(normalized_data, 4).tolist()
