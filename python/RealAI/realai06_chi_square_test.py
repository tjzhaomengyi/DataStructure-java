"""
假设你团队正在开发一个文本分类模型，用于将客户评论分类为正面或负面。由于文本数据具有高维度的特性，模型训练和预测的效率受到影响。你提议使用卡方检验进行特征选择，挑选出与分类任务最相关的词汇，降低数据的维度，从而提高模型的性能。
请你编写一个程序，使用卡方检验对给定的文本数据集进行特征选择。具体要求如下：



    1.    读取输入数据集，包含多篇标注了类别的文本文档。

    2.    提取特征，采用词频（Bag-of-Words）模型，将文本转换为特征向量。（不能忽视单词字母大小写）

    3.    计算每个特征（词）的卡方统计量，衡量其与类别标签的相关性。

    4.    根据卡方统计量选择前 ( k ) 个最重要的特征。

    5.    输出选定的特征列表。



输入描述：
    •    第一行包含一个整数 ( N )，表示文档的数量。

    •    接下来的 ( N ) 行，每行包含一个文档，格式为：
<label>\t<text>

        •    <label>：文档的类别，取值为 'positive' 或 'negative'。

        •    \t：一个制表符，分隔类别标签和文档内容。

        •    <text>：文档的内容，由若干单词组成，单词之间用空格分隔。

    •    最后一行包含一个整数 ( k )，表示需要选择的特征数量。

输出描述：
    •    输出 ( k ) 行，每行包含一个单词（特征），按照卡方统计量从大到小排序。如果多个特征的卡方值相同，按字母顺序升序排列。

"""
import sys
from collections import defaultdict

"""
1、数据读取
    读取文档数量N
    解析每行文档的标签和文本内容
    读取需要选择的特征数K
2、特征统计
    统计每个词汇在正/负面文档中出现的次数
    统计正/负面文档总数
3、卡方计算
    对每个词汇构建 2 * 2 列联表
    根据公式计算卡方统计量
4、特征选择
    按照卡方值降序排序
    卡方值相同时按照字母升序
    选择前K个特特征
"""


def read_data(N):
    """读取文档数据和标签"""
    documents = []  # 文档单词列表
    labels = []  # 文档标签列表
    for _ in range(N):
        line = sys.stdin.readline().strip()
        label, text = line.split('\t', 1)  # 分割标签和文本
        words = text.split()  # 分割单词保留大小写
        documents.append(words)
        labels.append(label)
    return documents, labels


def compute_chi_square(documents, labels):
    """计算每个词汇的卡方统计量"""
    # 初始化统计结构
    word_set = set()  # 所有词汇集合
    word_counts = defaultdict(lambda: defaultdict(int))  # 词汇-标签计数
    label_counts = defaultdict(int)  # 标签计数

    # 统计标签分布
    for label in labels:
        label_counts[label] += 1

    # 统计词汇在各个类别中的文档频率
    for words, label in zip(documents, labels):
        unique_words = set(words)  # 文档去重
        for word in unique_words:
            word_set.add(word)
            word_counts[word][label] += 1

    # 计算卡方值
    N = len(documents)
    chi_square_scores = {}
    for word in word_set:
        A = word_counts[word]['positive']  # 正面出现
        B = word_counts[word]['negative']  # 负面出现
        C = label_counts['positive'] - A  # 正面未出现
        D = label_counts['negative'] - B  # 负面未出现

        # 计算卡方分子和分母
        numerator = N * (A * D - B * C) ** 2
        denominator = (A + B) * (C + D) * (A + C) * (B + D)

        # 处理分母为0的情况
        chi_square = numerator / denominator if denominator != 0 else 0
        chi_square_scores[word] = chi_square
    return chi_square_scores


def select_top_k_features(chi_square_score, k):
    """选择前k个特征"""
    # 按照卡方值降序，字母升序排序
    sorted_features = sorted(chi_square_score.items(), key=lambda x: (-x[1], x[0]))
    return [feature for feature, _ in sorted_features[:k]]


if __name__ == '__main__':
    # 读取文档数量
    N = int(sys.stdin.readline().strip())
    # 读取文档数据
    documents, labels = read_data(N)

    # 读取特征数量K
    k = int(sys.stdin.readline().strip())

    # 计算卡方统计量
    chi_scores = compute_chi_square(documents, labels)

    # 选择并输出特征
    top_features = select_top_k_features(chi_scores, k)
    for feature in top_features:
        print(feature)
