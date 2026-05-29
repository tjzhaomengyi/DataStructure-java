"""
SGDM 带冲量的随机梯度下降是SGD的一种改进，在SGD的基础上增加了冲量，可以加速收敛，简单说，就是每次更新参数时，
不仅考虑当前的梯度，还考虑之前的梯度。使用动量代替梯度。
- 第一行包含两个整数 m，n，表示训练样本的数量和特征的数量。
- 第二行包含一个整数，表示迭代次数。
- 接下来的 m 行，每行包含 n 个浮点数，表示特征矩阵 X 的一行。
- 接下来一行包含 m 个浮点数，表示目标值 y。
- 最后一行包含两个浮点数，表示学习率和动量衰减率（momentum_decay）。
"""

import numpy as np
def sgdm_linear_regression(X, y, learning_rate, momentum_decay, epochs):
    m, n = X.shape
    theta = np.zeros((n, 1)) # 第t次迭代时的参数，位置更新，v会影响这个值
    v = np.zeros((n, 1)) # 这个是和SGD的明显区别，v累积了历史梯度方向，theta值记录当前位置，有"惯性"的性质
    for _ in range(epochs):
        y_pred = np.dot(X, theta)
        error = y_pred - y
        gradient = (1/m) * np.dot(X.T, error) #计算误差
        v = momentum_decay * v + learning_rate * gradient  # 更新动量，注意前面部分是动量衰减*历史梯度的速度，这就是SGDM中的惯性
        theta -= v
    return np.round(theta.flatten(), 2).tolist()

if __name__ == "__main__":
    m, n = map(int, input().split())
    epochs = int(input())
    X = np.array([list(map(float, input().split())) for _ in range(m)])
    y = np.array(list(map(float, input().split()))).reshape(-1,1)
    learning_rate, momentum_decay = map(float, input().split())
    theta = sgdm_linear_regression(X, y, learning_rate, momentum_decay, epochs)
    print(" ".join(map(str, np.round(theta, 2))))

