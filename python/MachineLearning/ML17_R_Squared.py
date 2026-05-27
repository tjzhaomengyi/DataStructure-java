"""
R²=1-SSR / SST
SSR是残差平方和,残差
SST是总平方和，偏差
"""
import numpy as np


def r_squared(y_true, y_pred):
    if np.array_equal(y_true, y_pred):
        return 1.0
    y_mean = np.mean(y_true)
    ssr = np.sum((y_true - y_pred) ** 2)
    sst = np.sum((y_true - y_mean) ** 2)
    try:
        r2 = 1 - (ssr / sst)
        if np.isinf(r2):
            return 0.0
        return round(r2, 3)
    except ZeroDivisionError:
        return 0.0