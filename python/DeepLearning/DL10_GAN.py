"""
请编写一个简单的GAN模型，包括生成器和判别器。
生成器使用正态分布生成噪声，与真实数据相加，得到生成数据。判别器使用sigmoid函数判断数据是真实的还是生成的。

"""

import numpy as np
def sigmoid(x):
    return 1 / (1 + np.exp(-x))

def discriminator(sample):
    return sigmoid(sample)

if __name__ == '__main__':
    n = int(input())
    real_data = [float(input()) for _ in range(n)]
    noise = [float(input()) for _ in range(n)]
    generated_data = np.add(noise,real_data)
    for sample in generated_data:
        print(f"{discriminator(sample):.2f}")