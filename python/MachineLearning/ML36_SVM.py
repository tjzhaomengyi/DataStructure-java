import numpy as np

"""
输入描述：
函数接收6个参数：
1. data：二维numpy数组，每行是一个样本的特征向量
2. labels：一维numpy数组，包含对应的二分类标签（-1或1）
3. kernel：字符串，指定核函数类型（'linear'或'rbf'）
4. lambda_val：浮点数，正则化参数
5. iterations：整数，算法迭代次数
6. sigma：浮点数，RBF核函数的带宽参数
输出描述：
返回一个元组，包含两个元素：
1. alpha：列表，每个样本对应的alpha系数（保留4位小数）
2. bias：浮点数，模型的偏置项（保留4位小数）
"""


def linear_kernel(x, y):
    return np.dot(x, y)


def rbf_kernel(x, y, sigma=1.0):
    return np.exp(-np.linalg.norm(x - y) ** 2 / (2 * (sigma ** 2)))


def pegasos_kernel_svm(data, labels, kernel='linear', lambda_val=0.01, iterations=100, sigma=1.0):
    n_samples = len(data)
    alphas = np.zeros(n_samples)
    b = 0

    for t in range(1, iterations + 1):
        for i in range(n_samples):
            eta = 1.0 / (lambda_val * t)
            if kernel == 'linear':
                kernel_func = linear_kernel
            elif kernel == 'rbf':
                kernel_func = lambda x, y: rbf_kernel(x, y, sigma)
            decision = sum(alphas[j] * labels[j] * kernel_func(data[j], data[i]) for j in range(n_samples)) + b
            if labels[i] * decision < 1:
                alphas[i] += eta * (labels[i] - lambda_val * alphas[i])
                b += eta * labels[i]

    return np.round(alphas, 4).tolist(), np.round(b, 4)




if __name__ == "__main__":
    data = np.array(eval(input()))
    labels = np.array(eval(input()))
    kernel = eval(input())
    lambda_val = float(input())
    iterations = int(input())
    sigma = float(input())
    print(pegasos_kernel_svm(data, labels, kernel, lambda_val, iterations, sigma))
