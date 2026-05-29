"""
GAN判别器是生成式对抗网络中的另一个关键组件，其主要任务是判断输入数据是真实的还是生成的
该判别器函数接受一个数据样本，并返回一个介于0和1之间的值，表示该样本为真实数据的概率。
判别器使用逻辑回归，激活函数使用sigmoid函数

输入描述：
第一行输入一个整数n，表示样本数量。
接下来n行，每行输入一个浮点数，表示生成器生成的数据。
输出描述：
输出n行，每行一个浮点数，表示该数据为真实数据的概率，保留两位小数
"""

import numpy as np
def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def discriminator(sample):
    return sigmoid(sample)

if __name__ == '__main__':
    n = int(input())
    real_data = [float(input()) for _ in range(n)]
    generated_data = real_data
    for sample in generated_data:
        print(f"{discriminator(sample):.2f}")
