"""
某电商平台计划使用决策树为首页活动根据用户的兴趣偏好进行排序，将用户最可能感兴趣的活动优先进行展示。对于特征选择部分，为了改进决策树信息增益中的诸多缺陷，如泛化性较差以及对某一类别样本数量过于敏感等，计划使用信息增益比作为特征重要性的判别标准。
计算某一特征的信息增益主要分为两步，第一步是计算数据集的信息熵，表示为：
[计算熵的公式]
第二步是计算每个特征的信息增益，特征A对于数据集D的经验条件熵
【还是real02的那个计算公式】
信息增益比表示为当前特征的信息增益与当前特征属性熵的比值

思路：这道题的整体思路和realai02的思路大致一致，都是筛选特征
"""

import math
import sys
from collections import Counter


def calc_entropy(data):
    """计算数据集的信息熵， 整体数据集只有一个信息熵"""
    # 统计每种label标签的数量
    label_cnt = Counter(row[-1] for row in data)
    # 计算标签熵
    total_count = len(data)
    h = 0.0
    for c in label_cnt.values():
        # 题目中给的信息熵就是从k=1开始，没有k=0的情况
        if c == 0:
            continue
        p = c / total_count
        h -= p * math.log2(p)
    return h


def calculate_information_gain(data):
    rows = len(data)
    cols = len(data[0])
    num_features = cols - 1

    best_ratio = float('-inf')
    best_idx = 0

    # 计算标签熵
    HD = calc_entropy(data)
    # 为计算某个特征的信息增益做准备, 以列为外围进行遍历，然后遍历每行
    for f in range(num_features):  # 考察每个特征
        # 按照特征值分组
        groups = {}
        for row in data:
            fv = row[f]  # fv表示某个特征的一种取值
            if fv not in groups:
                groups[fv] = Counter()
            groups[fv][row[-1]] += 1  # 某个特征的某个值对应的标签统计数量+1
        # 条件熵
        HDA = 0.0
        group_sizes = {}
        for fv, cnt in groups.items():
            subset = [row for row in data if row[f] == fv]
            sz = len(subset)  # 某个特征的某个值的所有数量
            group_sizes[fv] = sz
            HDA += sz / rows * calc_entropy(subset)

        gain = HD - HDA

        # 计算属性熵H_A(D)
        HA = 0.0
        for sz in group_sizes.values():
            p = sz / rows
            HA -= p * math.log2(p)


        if HA == 0:
            continue
        ratio = gain / HA
        if ratio > best_ratio:
            best_ratio = ratio
            best_idx = f

    return best_idx


if __name__ == '__main__':
    data = eval(sys.stdin.readline().strip())
    # 拿到数据的基本信息
    rows = len(data)
    cols = len(data[0])
    num_features = cols - 1
    res = calculate_information_gain(data)
    print(res)
