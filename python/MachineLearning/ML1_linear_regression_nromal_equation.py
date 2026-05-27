"""使用正规方程的线性回归"""

import numpy as np

def linear_regression_normal_equation(X: list[list[float]], y: list[float]) -> list[float]:
    """
    1、初始化矩阵，创建一个与输入矩阵X和输出矩阵y相关的矩阵A
    A = X^T x X , A^(-1) = X^(-1) x (X^T)^(-1)
    2、求解回归系数
        通过求解矩阵A的逆来得到回归系数
    :param X:
    :param y:
    :return:
    """
    X = np.array(X)
    y = np.array(y).reshape(-1, 1) # 变成1列
    X_tran = X.T
    # 计算系数 ,w=X^(-1)y,但是X通常是不可逆矩阵，所以要分解为格拉姆矩阵，(X^TX)^(-1)·X^T
    theata =np.linalg.inv(X_tran @ X) @ X.transpose @y
    #将系数扁平为一维列表
    theta = np.round(theata, 4).flatten().tolist()
    return theta