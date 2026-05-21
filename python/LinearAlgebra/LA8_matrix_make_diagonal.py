import numpy as np

'''
    将向量转换为对角矩阵
'''
def make_diagonal(x):
    arr = np.array(x, dtype=np.float32)
    diag = np.diag(arr)
    return diag
    pass


if __name__ == "__main__":
    x = np.array(eval(input()))
    print(make_diagonal(x))

