import numpy as np
def ridge_loss(X, w, y_true, alpha):
    loss = np.mean((y_true - X @ w) ** 2) * alpha * np.sum(w**2)
    return loss