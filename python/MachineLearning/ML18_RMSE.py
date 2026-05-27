"""计算均方根误差"""
import numpy as np


def rmse(y_true, y_pred):
    rmse = np.sqrt(np.mean((y_true - y_pred) ** 2))
    return round(rmse, 3)