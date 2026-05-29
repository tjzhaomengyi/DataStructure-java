"""
实现一个单神经元的前向传播，使用sigmoid激活函数进行二分预测
1、初始化权重
2、前向传播，计算预测值
3、计算损失函数
"""
import math
import numpy as np
def single_neuron_model(features, labels, weights, bias):
    probabilities = []
    for feature in features:
        z = sum(weight * feature for weight, feature in zip(weights, feature)) + bias
        prob = 1 / (1 + math.exp(-z))
        probabilities.append(round(prob, 4))

    mse = sum((prob - label) ** 2 for prob, label in zip(probabilities, labels)) / len(labels)
    mse = round(mse, 4)
    return probabilities, mse


if __name__ == "__main__":
    features = np.array(eval(input()))
    labels = np.array(eval(input()))
    weights = np.array(eval(input()))
    bias = float(input())
    print(single_neuron_model(features, labels, weights, bias))
