import numpy as np
"""
本题中采用了三种梯度下降方法，分别是批量梯度下降（batch）、随机梯度下降（stochastic）和mini-batch梯度下降（mini_batch）。区别如下：
批量梯度下降：每次迭代使用所有数据点来计算梯度，更新参数。
随机梯度下降：每次迭代使用一个数据点来计算梯度，更新参数。
mini-batch梯度下降：每次迭代使用一部分数据点来计算梯度，更新参数。
技巧：梯度计算 gt = 2/n ∑(yi - wtxi)xi
"""
def gradient_descent(X, y, weights, learning_rate, n_iterations, batch_size=1, method='batch'):
    m = len(X)
    for _ in range(n_iterations):
        if method == 'batch':
            #使用所有的点计算梯度
            predictions = X @ weights
            errors = predictions - y
            gradient = 2 * X.T @ errors / m
            weights = weights - learning_rate * gradient

        elif method == 'stochastic': #随机
            for i in range(m):
                # weights是一个shape(len(weights),)形状的向量，在计算@时候，numpy会根据上下文把他当成行向量或者列向量
                prediction = X[i] @ weights
                error = prediction - y[i] # 这里是个常量，下面直接用原始的数据乘以这个常量就可以了
                gradient = 2 * X[i] * error
                weights = weights - learning_rate * gradient

        elif method == 'mini_batch':
            for i in range(0, m, batch_size):
                X_batch = X[i: i + batch_size]
                y_batch = y[i: i + batch_size]
                predictions = X_batch @ weights
                errors = predictions - y_batch
                gradient = 2 * X_batch.T @ errors / batch_size
                weights = weights - learning_rate * gradient
        return weights


if __name__ == "__main__":
    X = np.array(eval(input()))
    y = np.array(eval(input()))
    weights = np.array(eval(input()))
    learning_rate = eval(input())
    n_iterations = eval(input())
    batch_size = eval(input())
    method = eval(input())
    print(gradient_descent(X, y, weights, learning_rate, n_iterations, batch_size, method))