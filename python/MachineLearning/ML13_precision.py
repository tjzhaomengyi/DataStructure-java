import numpy as np
"""
精确率 precision 是衡量二分类模型在正样本上的表现指标
"""
def precision(y_true, y_pred):
    true_positive = np.sum((y_true == 1) & (y_pred == 1))
    false_positive = np.sum((y_true == 0) & (y_pred == 1))
    return true_positive / (true_positive + false_positive)