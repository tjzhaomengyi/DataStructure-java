"""
编写一个 Python 函数来计算给定值、平均值和标准差的正态分布的概率密度函数 (PDF)。该函数应使用正态分布的数学公式返回四舍五入到小数点后 5 位的 PDF 值。
"""


def normal_pdf(x, mean, std_dev):
    # 输出x的概率密度
    from scipy.stats import norm
    pdf = norm.pdf(x, mean, std_dev)
    return round(pdf, 5)


if __name__ == '__main__':
    x, mean, std_dev = map(float, input().split())
    print(normal_pdf(x, mean, std_dev))
