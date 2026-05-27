import numpy as np
"""
Jaccard指数是一种衡量分类性能的指标
Jaccard = TP /(TP + FP + FN)
"""

def jaccard_index(y_true, y_pred):
    tp = np.sum((y_true==1) & (y_pred==1))
    fp = np.sum((y_true==0) & (y_pred==1))
    fn = np.sum((y_true==1) & (y_pred==0))
    jaccard = tp / (tp + fp + fn)
    return round(jaccard, 3)
