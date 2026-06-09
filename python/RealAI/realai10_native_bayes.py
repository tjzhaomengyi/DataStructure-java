"""
描述
请你在仅使用 numpy / pandas的前提下，手写实现高斯朴素贝叶斯（Gaussian Naive Bayes，GNB），并对给定测试样本输出类别预测。具体流程：

1. 读取数据

    •    train 字段：二维列表，每行最后一列为类别标签 y∈{0,1}，其余为数值特征

    •    test 字段：二维列表，仅包含与训练集同维度的特征

2. 参数估计

    •    对每个类别 c 计算先验

​


    •    对每个特征计算类条件独立假设下的 均值 与 方差




3. 预测
    •    使用对数后验：
    •    取 argmax clogP(c∣x) 作为预测标签


4. 结果输出
    •    预测值保留整数 0/1，以 JSON 数组形式一次性输出，顺序与输入 test 保持一致

输入描述：
标准输入为 一行 JSON：
    •    n 行训练样本，m 维特征，最后一列为标签

    •    所有值均为浮点数 / 整数，无额外空行

输出描述：
标准输出仅含一行：即测试集中每个样本的预测标签（整数），使用单行 JSON 数组表示。
"""

import json
import numpy as np

data = json.loads(input().strip())
train = np.array(data['train'])
test = np.array(data['test'], dtype=float)
n, m = train.shape[0], test.shape[1]  # n 是测试样本个数，m是特征数量

# 获取索引类别
label = train[:, -1].astype(int)
X = train[:, :-1].astype(float)

# 计算先验概率
n0 = np.sum(label == 0)
n1 = np.sum(label == 1)
prior = np.array([n0, n1]) / len(label)

# 按照类别计算均值和方差
mu = np.zeros((2, m))
sigma = np.zeros((2, m))
X0 = X[label == 0]
X1 = X[label == 1]
mu[0] = X0.mean(axis=0)
mu[1] = X1.mean(axis=0)
sigma[0] = X0.var(axis = 0, ddof = 0)
sigma[1] = X1.var(axis = 0, ddof = 0)
sigma[sigma == 0] = 1e-9 # 避免log0

#预测
results = []
for x in test:
    log_lik = -0.5 * np.log(2 * np.pi * sigma * 2) - (x - mu) ** 2 / (2 * sigma ** 2)
    log_post = np.log(prior) + np.sum(log_lik, axis=1)  # 把公式中∑是按照列来计算的，就是横着算
    results.append(int(np.argmax(log_post)))
print(results)


