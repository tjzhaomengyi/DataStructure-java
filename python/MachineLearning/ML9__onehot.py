import numpy as np

def to_categorical(x, n_col=None):
    if not n_col:
        n_col = np.amax(x) + 1 #取得x矩阵中的最大值

    one_hot = np.zeros((x.shape[0], n_col))
    one_hot[np.arange(x.shape[0]), x] = 1 # 高级索引，前一个数组和后一个数组x，两两组成位置，然后填1
    return one_hot


if __name__ == "__main__":
    x = np.array(eval(input()))
    print(to_categorical(x))


