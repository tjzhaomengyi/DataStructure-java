import numpy as np


def svd_2x2(A: np.ndarray) -> tuple:
    U, s, V = np.linalg.svd(A)
    return U, s, V
