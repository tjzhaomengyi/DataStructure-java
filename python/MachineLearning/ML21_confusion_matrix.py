"""生成混淆矩阵"""
from collections import Counter


def confusion_matrix(data):
    counts = Counter(tuple(pair) for pair in data) # 这个非常骚啊，统计数组中四种元素，每种元素出现的次数
    TP, FN, FP, TN = counts[(1, 1)], counts[(1, 0)], counts[(0, 1)], counts[(0, 0)]
    matrix = [[TP, FN], [FP, TN]]
    return matrix
