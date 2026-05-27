import numpy as np
def f_score(y_true, y_pred, beta):
    tp = np.sum((y_true == 1) & (y_pred == 1))
    fn = np.sum((y_true == 1) & (y_pred == 0))
    fp = np.sum((y_true == 0) & (y_pred == 1))

    recall = tp / (tp + fn)
    precision = tp / (tp + fp)

    op = precision * recall
    div = (beta**2 * precision) + recall
    if op == 0 or div == 0:
        return 0
    div = (1 + beta**2) * op / div

    #使用库方法
    from sklearn.metrics import fbeta_score
    f_score = fbeta_score(y_true, y_pred, beta=beta)
    return round(div, 3)