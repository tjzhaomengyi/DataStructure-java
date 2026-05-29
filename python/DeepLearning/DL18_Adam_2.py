"""
    Adam优化器第二版
    输入描述：
第一行输入五个浮点数，分别代表参数、梯度、一阶矩估计、二阶矩估计、迭代次数。
输出描述：
输出更新后的参数、一阶矩估计、二阶矩估计。结果都保留五位小数。
"""
import numpy as np


def adam_optimizer_v2(parameter, grad, m, v, t, learning_rate=0.001, beta1=0.9, beta2=0.999, epsilon=1e-8):
    for i in range(1, t+1):
        m = beta1 * m + (1 - beta1) * grad
        v = beta2 * v + (1 - beta2) * (grad**2)
        m_hat = m / (1 - beta1 ** i)
        v_hat = v / (1 - beta2 ** i)
        update = learning_rate * m_hat / (np.sqrt(v_hat) + epsilon)
        parameter = parameter - update
    return np.round(parameter, 5), np.round(m, 5), np.round(v, 5)