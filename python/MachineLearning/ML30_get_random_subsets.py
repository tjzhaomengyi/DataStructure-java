import numpy as np

"""
实现一个函数来生成数据集的随机子集，需要支持有放回和无放回两种采样方式
输入描述：
函数`get_random_subsets`接收四个参数：
1. X：特征矩阵，二维numpy数组，形状为(n_samples, n_features)
2. y：标签向量，一维numpy数组，形状为(n_samples,)
3. n_subsets：需要生成的子集数量，整数
4. replacements：是否允许重复采样，布尔值，默认为True

输出描述：
返回一个列表，包含n_subsets个元组：
- 每个元组包含(X_subset, y_subset)
- X_subset是特征子集
- y_subset是对应的标签子集
- 所有数组都转换为Python列表
"""


def get_random_subsets(X, y, n_subsets, replacements=True, seed=42):
    np.random.seed(seed)
    n, m = X.shape

    subset_size = n if replacements else n // 2
    idx = np.array([np.random.choice(n, subset_size, replace=replacements) for _ in range(n_subsets)])
    return [(X[idx[i]].tolist(), y[idx[i]].tolist()) for i in range(n_subsets)]


if __name__ == "__main__":
    X = np.array(eval(input()))
    y = np.array(eval(input()))
    n_subsets = int(input())
    print(get_random_subsets(X, y, n_subsets))
