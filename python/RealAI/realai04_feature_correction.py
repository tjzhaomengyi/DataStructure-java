"""
特征相关性
你的团队正在研究哪些产品特征最能影响销售，以便优化产品策略。为此，你决定使用相关性分析来评估特征与销售量之间的关系，从而选择最有价值的特征。
请你编写一个程序，使用 Python 和 NumPy 库，对给定的数据集进行相关性分析。具体要求如下：



    1.    读取输入数据集，包含 ( N ) 个样本，每个样本有 ( M ) 个特征和一个目标值（销售量）。

    2.    计算每个特征与目标值之间的皮尔逊相关系数。

    3.    按照相关系数的绝对值从大到小排序，选择前 ( K ) 个特征。

    4.    输出每个特征的相关系数值，保留四位小数，使用{x:.4f}格式化输出。
输入描述：
    •    第一行包含两个整数 ( N ) 和 ( M )，表示样本数量和特征数量。

    •    接下来的 ( N ) 行，每行包含 ( M+1 ) 个浮点数，表示每个样本的特征值和目标值，特征值和目标值之间用空格分隔。

    •    最后一行包含一个整数 ( K )，表示需要选择的特征数量。

输出描述：
    •    输出 ( M ) 行，每行包含一个特征的索引（从 0 开始）和对应的相关系数值，用空格分隔，保留四位小数。

    •    按照相关系数的绝对值从大到小排序输出。
"""
import sys



def main():
    n, m = map(int, input().split())
    data = []
    for _ in range(n):
        data.append(list(map(float, input().split())))
    k = int(input().strip())

    y = [data[i][m] for i in range(n)]
    mean_y = sum(y) / n
    dy = [y[i] - mean_y for i in range(n)]
    den_y = sum(d * d for d in dy) ** 0.5

    results = []
    for j in range(m):
        x = [data[i][j] for i in range(n)]
        mean_x = sum(x) / n
        dx = [x[i] - mean_x for i in range(n)]
        den_x = sum(d * d for d in dx) ** 0.5
        num = sum(dx[i] * dy[i] for i in range(n))
        corr = num / (den_x * den_y) if den_x * den_y > 0 else 0.0
        results.append((j, corr))

    results.sort(key=lambda t: (-t[1], t[0]))  # 按照递减排序
    for idx, corr in results:
        print(f"{idx} {corr:.4f}")


main()
