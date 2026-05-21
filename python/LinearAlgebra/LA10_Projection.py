"""
向量到直线的正交投影
"""
import numpy as np


def orthogonal_projection(v, L):
    """
    v 在 L 上的正交投影
    projL(v) = v·L / ||L||^2 · L
    :param v:
    :param L:
    :return:
    """
    vec = np.array(v)
    line = np.array(L)
    v1 = vec @ line  # dot product
    l2 = line @ line  # dot product
    scaler = v1 / l2
    projL = scaler * line
    projL = projL.round(3)
    return projL.tolist()


if __name__ == "__main__":
    v = eval(input())
    L = eval(input())
    print(orthogonal_projection(v, L))