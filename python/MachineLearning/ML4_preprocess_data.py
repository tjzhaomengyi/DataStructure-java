"""
给定一组包含缺失值和异常值的数值型数据，请编写程序对数据进行预处理。要求填补缺失值，并去除异常值，最后输出处理后的数据集。
输入描述：
第一行输入一个整数n，表示数据的个数。
接下来的n行每行输入一个浮点数，表示数据集中的一个数据点。数据点可能包含缺失值（用-1表示）和异常值（大于800或小于200的值）
输出描述：
输出处理后的数据集，每个数据点占一行。缺失值用数据集的均值填补，异常值将被去除。
结果保留四位小数。
"""
import numpy as np


def preprocess_data():
    n = int(input().strip())
    data = np.array([float(input().strip()) for _ in range(n)])
    mean = np.mean(data[data != -1])
    data[data == -1] = mean
    return np.round(data[(data >= 200) & (data <= 800)], 4)


def main():
    ans = preprocess_data()
    for i in range(len(ans)):
        print(f"{ans[i]:.4f}")


if __name__ == '__main__':
    main()
