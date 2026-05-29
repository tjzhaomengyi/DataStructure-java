"""
具有反向传播的单神经元是神经网络中的最常见的基本单元
1、初始化权重和偏置
2、前向传播，计算预测值
3、反向传播，计算梯度
4、更新权重和偏置
5、重复步骤2-4，直到最大迭代次数
输入描述：
函数接收6个参数：
1. features：二维列表，每行是一个样本的特征向量
2. labels：一维列表，包含对应的二分类标签（0或1）
3. initial_weights：一维列表，初始权重
4. initial_bias：浮点数，初始偏置值
5. learning_rate：浮点数，学习率
6. epochs：整数，训练轮数
输出描述：
返回一个元组，包含三个元素：
1. 更新后的权重列表（保留4位小数）
2. 更新后的偏置值（保留4位小数）
3. 每个epoch的MSE值列表（保留4位小数）
"""


import numpy as np

def sigmoid(x):
    return 1 / (1 + np.exp(-x))
def train_neuron(features, labels, initial_weights, initial_bias, learning_rate, epochs):
    weights = np.array(initial_weights)
    bias = initial_bias
    features = np.array(features)
    labels = np.array(labels)
    mse_values = []

    for _ in range(epochs):
        z = features @ weights + bias
        predictions = sigmoid(z)

        errors = predictions - labels
        mse = np.mean(errors ** 2)
        mse_values.append(round(mse, 4))

        #由损失函数L = 1/nΣ(y-y^)²，求导有 L对w的导数=2/n Σ(y-y^)·y^(1-y^)·x
        weight_gradients = (2/len(labels)) * np.dot(features.T, errors * predictions * (1 - predictions))
        bias_gradients = (2/len(labels)) * np.sum(errors * predictions * (1 - predictions))

        #更新权重和偏置
        weights -= learning_rate * weight_gradients
        bias -= learning_rate * bias_gradients

    updated_weight = np.round(weights, 4)
    updated_bias = round(bias, 4)

    return updated_weight.tolist(), updated_bias, mse_values




if __name__ == "__main__":
    features = np.array(eval(input()))
    labels = np.array(eval(input()))
    initial_weights = np.array(eval(input()))
    initial_bias = float(input())
    learning_rate = float(input())
    epochs = int(input())
    print(train_neuron(features, labels, initial_weights, initial_bias, learning_rate, epochs))

