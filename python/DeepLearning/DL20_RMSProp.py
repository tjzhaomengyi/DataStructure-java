"""
给定一组训练数据，使用RMSProp算法实现线性回归模型。
你的任务是编写一个函数，接受特征矩阵和目标值、学习率和衰减率，并返回训练好的模型参数。
损失函数为均方误差：MSE = 1/2*m * sum((y_pred - y) ** 2)
训练方式是批量梯度下降，即每次迭代使用所有样本。
参数更新时，使用1e-8防止分母为0。
输入描述：
- 第一行包含两个整数 m，n，表示训练样本的数量和特征的数量。
- 第二行包含一个整数，表示迭代次数。
- 接下来的 m 行，每行包含 n 个浮点数，表示特征矩阵 X 的一行。
- 接下来一行包含 m 个浮点数，表示目标值 y。
- 最后一行包含两个浮点数，表示学习率和衰减率。
输出描述：
- 输出一行，包含 n 个浮点数，表示训练好的模型参数，保留两位小数。

RMSProp 是一种自适应学习率的优化算法，主要思想是通过调整每个参数的学习率来加速收敛，特备实在处理非平稳目标时

步骤：
1、初始化参数：
    初始化参数θ和均方根平方和s
    初始化学习率α和衰减率γ
2、计算梯度
    计算损失函数对参数θ的梯度g
3、更新均方根平方和：
    计算均方根平方和 s = γ * s + (1 - γ) * g^2。
4、更新参数
    更新参数 θ = θ - α * g / sqrt(s + ε)，其中 ε 是一个很小的正数（如 1e-8），以防止分母为零。

RMSProp 通过动态调整学习率，使得在梯度较大的方向上学习率较小，而在梯度较小的方向上学习率较大。

"""
import numpy as np


def rmsprop_linear_regression(X, y, learning_rate, decay_rate, epochs):
    m, n = X.shape
    theta = np.zeros((n, 1))
    s = np.zeros((n, 1))
    for _ in range(epochs):
        y_pred = np.dot(X, theta)
        error = y_pred - y
        gradient = (1 / m) * np.dot(X.T, error)  # 计算梯度
        # 和SGDM进行对比
        # v = momentum_decay * v + learning_rate * gradient  # 更新动量，注意前面部分是动量衰减*历史梯度的速度，这就是SGDM中的惯性
        # theta -= v
        s = decay_rate * s + (1 - decay_rate) * gradient ** 2  # 更新平方和
        theta -= learning_rate * gradient / np.sqrt(s + 1e-8)

    return np.round(theta.flatten(), 2).tolist()


