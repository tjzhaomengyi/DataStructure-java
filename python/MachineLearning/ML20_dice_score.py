import numpy as np

"""
 Dice得分是一种衡量分类模型性能的指标，具有良好鲁棒性
 Dice = 2 * TP / (2*TP + FP + FN)
"""


def dice_score(y_true, y_pred):
    tp = np.sum((y_true == 1) & (y_pred == 1))
    fp = np.sum((y_true == 0) & (y_pred == 1))
    fn = np.sum((y_true == 1) & (y_pred == 0))
    dice = (2 * tp) / (2 * tp + fp + fn)
    return round(dice, 3)
