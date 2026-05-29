"""
生成器是生成对抗网络GAN中的关键组件，负责生成假数据。实现一个简单的生成器，能够根据输入的随机噪声生成假数据样本。
数据从正态分布中随机生成，生成的真数据加上随机噪声，就变成假数据。

输入描述：
- 第一行包含两个整数N和D，表示生成的样本数量和特征数量。
- 接下来的N行，每行包含D个随机噪声值，用空格分隔的浮点数表示，表示生成数据要加的特征值。
- 接下来N行，每行包含D个浮点数，表示真实数据。
输出描述：
- 输出生成器生成的假数据样本，每个样本以空格分隔。
-返回结果保留两位小数

输出描述：
- 输出生成器生成的假数据样本，每个样本以空格分隔。
-返回结果保留两位小数

生成器是生成式对抗网络的一个关键组件，主要任务是从随机噪声中生成逼真的数据样本。生成器通过学习真实数据的分布，逐步调整其参数，
以便生成的样本能够欺骗判别器，使其无法区分生成的数据和真实数据。生成器通常使用反向传播算法进行训练，并通过优化损失函数来提高生成样本的质量
"""

import numpy as np

def generate_data(noise, real):
    return np.add(noise, real)

if __name__ == '__main__':
    np.random.seed(42)
    n,d = map(int, input().split())
    noise = [list(map(float, input().split())) for _ in range(n)]
    real_data = [list(map(float,input().split())) for _ in range(n)]
    data = generate_data(noise,real_data)
    for row in data:
        print(' '.join(str(round(row[i],2))for i in range(d)))
