"""
计算泊松分布
用泊松分布公式计算在固定时间间隔内恰好观察到 k 个事件的概率。该函数应将
k（事件数）和 λ(平均发生率）作为输入，并返回四舍五入到小数点后 5 位的概率。
"""


def poisson_probality(k, lam):
    from scipy.stats import poisson
    val = poisson.pmf(k, lam)
    pass
    return round(val, 5)


if __name__ == '__main__':
    k, lam = map(input, input().split())
    print(poisson_probality(k, lam))
