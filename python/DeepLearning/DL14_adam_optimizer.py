"""
实现adam优化算法，adam算法是一种结合了动量和RMSprop的优化算法，为每个参数计算自适应的学习率

函数`adam_optimizer`接收以下参数：
1. f：目标函数
2. grad：梯度函数
3. x0：初始参数值
4. learning_rate：学习率（默认0.001）
5. beta1：一阶矩估计的衰减率（默认0.9）
6. beta2：二阶矩估计的衰减率（默认0.999）
7. epsilon：数值稳定性常数（默认1e-8）
8. num_iterations：迭代次数（默认10）

步骤：
1、更新有偏一阶矩估计
2、更新有偏二阶矩估计
3、偏差校正
4、更新参数
"""
import numpy as np


def adam_optimizer(f, grad, x0, learning_rate=0.001, beta1=0.9, beta2=0.999, epsilon=1e-8, num_iterations=10):
    x = x0
    m = np.zeros_like(x)
    v = np.zeros_like(x)
    for t in range(1, num_iterations + 1):
        g = grad(x)  # 计算当前梯度
        # 更新有偏一阶矩估计
        m = beta1 * m + (1 - beta1) * g
        # 更新有偏二阶矩估计
        v = beta2 * v + (1 - beta2) * g ** 2
        # 偏差矫正
        m_hat = m / (1 - beta1 ** t)
        v_hat = v / (1 - beta2 ** t)
        x = x - learning_rate * m_hat / (np.sqrt(v_hat) + epsilon)
    return x
