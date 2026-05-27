import numpy as np

"""Lasso regression 即使用了L1正则的回归"""


# 注意这个参数里面的tol是针对 grad_w 梯度来卡的
def l1_regularization_gradient_descent(X: np.array, y: np.array, alpha: float = 0.1, learning_rate: float = 0.01,
                                       max_iter: int = 1000, tol: float = 1e-4) -> tuple:
    n_samples, n_features = X.shape
    weights = np.zeros(n_features)
    bias = 0

    for iteration in range(max_iter):
        y_pred = X @ weights + bias
        error = y_pred - y
        # 这个dot乘法已经包含了求和的效果
        grad_w = (1 / n_samples) * X.T @ error + alpha * np.sign(weights)
        grad_b = (1 / n_samples) * np.sum(error)

        # 更新权重和截距
        weights -= learning_rate * grad_w
        bias -= learning_rate * grad_b
        # 如果梯度向量的L1范数小于tol，结束
        if np.linalg.norm(grad_w, ord=1) < tol:
            break

    return [round(w, 3) for w in weights], round(bias, 3)
