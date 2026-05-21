import numpy as np

"""
计算协方差矩阵
cov(X,Y) = (1/(n-1)) * ∑(xi - μx)^T（x - μ）
协方差矩阵本质是对数据做中心化后，计算不同特征之间的内积，用来衡量特征间的线性相关性。
"""
def calculate_covariance_matrix(vectors):
# 补全代码
    X = np.array(vectors)
    n = X.shape[0]  #列的大小
    means = np.mean(X, axis=0, keepdims=True) #对每一列求均值
    center = X - means
    cov = np.dot(center.T, center) / (n -1)
    return cov.tolist()


# 主程序
if __name__ == "__main__":
    # 输入
    ndarrayA = input()

    # 处理输入
    import ast
    A = ast.literal_eval(ndarrayA)

    # 调用函数计算
    output = calculate_covariance_matrix(A)

    # 输出结果
    print(output)
