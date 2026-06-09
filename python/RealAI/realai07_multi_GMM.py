"""
描述
某公司在进行用户行为分析时，需要计算用户行为数据在给定的多元高斯分布下的概率密度。请根据输入描述和输出描述中的要求，编程实现多元高斯分布的概率密度函数的计算。
输入描述：
输入的数据为一个字典，该字典包含三个键值对，"x" 对应的值是一个一维 list，表示待计算的数据点；
"mu" 对应的值是一个一维 list，表示多元高斯分布的均值向量；"sigma" 对应的值是一个二维 list，表示多元高斯分布的协方差矩阵。
输出描述：
要求给出数据点在给定的多元高斯分布下的概率密度，数据类型为 float 类型。

"""

import numpy as np
from scipy.stats import multivariate_normal


def calculate_pdf(data):
    # 将输入参数转换为numpy数组
    x = np.array(data["x"])  # 数据点向量
    mu = np.array(data["mu"])  # 均值向量
    sigma = np.array(data["sigma"])  # 协方差矩阵

    # 计算多元高斯分布的概率密度
    pdf = multivariate_normal.pdf(x, mean=mu, cov=sigma)
    # 四舍五入保留两位小数
    return round(pdf, 2)


if __name__ == '__main__':
    data = eval(input())
    print(calculate_pdf(data))
