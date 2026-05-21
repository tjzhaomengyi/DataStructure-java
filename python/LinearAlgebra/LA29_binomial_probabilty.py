"""
编写一个函数，使用二项式分布公式计算在
n 次独立的伯努利试验中精确实现 k 次成功的概率，每次试验的成功概率为 p。
"""
import math


def binomial_probability(n, k, p):
    """
    公式:P(X = k) = C(n,k)p^k(1-p)^(n-k)
    :param n:
    :param k:
    :param p:
    :return:
    """
    binomial_coeff = math.comb(n, k) # 这里统一一下，总量n在下，k选取数在上
    probability = binomial_coeff * (p ** k) * ((1-p) ** (n-k))
    return round(probability, 5)

if __name__ == "__main__":
    n, k, p = map(float, input().split())
    print(binomial_probability(int(n), int(k), p))