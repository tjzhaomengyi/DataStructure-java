"""
某银行希望优化其信用卡申请者的信用评分模型，以更准确地预测申请者的信用风险。为此，银行决定使用机器学习方法对申请者的特征数据进行分析。在这个任务中，你需要使用决策树算法中的信息增益比来选择最佳的特征，以进行信用风险分类。
输入描述：
输入数据为一个二维列表，每个子列表代表一个申请者的记录，其中包含申请者的特征和信用评分结果（良好或不良）。最后一个元素为信用评分结果，其中 'G' 表示信用良好，'B' 表示信用不良。其余元素代表申请者的不同特征值，例如年龄、年收入、信用卡余额等。
输出描述：
输出信息增益比最高的特征的索引（从0开始计数），如果信息增益比最高的特征是第一个，则输出0，如果是第二个，则输出1，以此类推。
输入
[[25, 50000, 2000, 'G'],[30, 55000, 3000, 'G'],[35, 60000, 0, 'B'],[40, 65000, 4000, 'B'],[28, 48000, 1000, 'G']]

思路：
给定一组申请者的特征数据和信用评分结果（'G' 表示良好，'B' 表示不良），需要计算每个特征的信息增益比（Information Gain Ratio），输出信息增益比最高的特征索引。
"""

import math
from collections import Counter

# 熵计算公式:H(D) = -Σpklogpk
def entropy(labels):
    n = len(labels)
    if n == 0:
        return 0.0
    counts = Counter(labels)
    return -sum((c / n) * math.log2(c / n) for c in counts.values())  # 这里的c对应图片算法解释中的D集合，那个D就表示每组标签的个数

def info_gain_ratio(data, feat_idx):
    n = len(data)
    labels = [row[-1] for row in data]
    h_y = entropy(labels)  # 给出测试数据的熵信息

    # 按照特征分组
    groups = {}
    for row in data:
        groups.setdefault(row[feat_idx], []).append(row[-1])

    # 重要： 条件熵 Σ|Dv| / |D| * entropy(Dv)
    h_cond = sum(len(g) / n * entropy(g) for g in groups.values())
    # 信息增益 = 整体的熵 - 条件熵
    gain = h_y - h_cond

    # 重要 固有值（分裂信息）,衡量特征A本身的“分散程度” = -Σ|Dv|/|D|* log2|Dv|/|D|
    split_info = -sum((len(g) / n) * math.log2(len(g) / n) for g in groups.values())
    # 最终求信息增益比 gain / split_info
    return gain / split_info if split_info > 0 else 0.0


data = eval(input())
num_features = len(data[0]) - 1
# range(num_features)代表所有特征的索引，lambda i，表示对每个特征索引i，计算该特征的信息增益比，max返回这个信息增益比最大的那个
best_idx = max(range(num_features), key=lambda i: info_gain_ratio(data, i))
print(best_idx)
