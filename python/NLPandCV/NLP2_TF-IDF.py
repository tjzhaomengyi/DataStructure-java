import numpy as np
"""
TF = 词频 / 文本长度，（这个是单词在自己这篇文章中出现的次数）
DF = 包含该词的文档数 / 文档总数 （这个是单词出现的个数 比上 在所有文档中出现的次数）
"""

def compute_tf_idf(corpus, query):
    vocab = sorted(set(word for document in corpus for word in document).union(query))  # 展开所有文档，构建词汇表再和query求并集，保证query的词在表中
    word_to_index = {word: idx for idx, word in enumerate(vocab)}  # 建立词到下标的映射
    tf = np.zeros((len(corpus), len(vocab)))  # 初始化TF矩陣 ，（文档数，词汇数）
    for doc_idx, document in enumerate(corpus):  # 遍历每篇文档
        for word in document:  # 遍历单词，统计每篇文档中每个单词出现的次数
            word_idx = word_to_index[word]
            tf[doc_idx, word_idx] += 1
        tf[doc_idx, :] /= len(document)  # 做归一化整理
    df = np.count_nonzero(tf > 0, axis=0)  # 表示有几个文档出现过这个词，按照列统计，这里统计的是个数
    num_docs = len(corpus)
    idf = np.log((num_docs + 1) / (df + 1)) + 1
    tf_idf = tf * idf
    query_indices = [word_to_index[word] for word in query]
    tf_idf_scores = tf_idf[:, query_indices]
    tf_idf_scores = np.round(tf_idf_scores, 5)
    return tf_idf_scores.tolist()

if __name__ == "__main__":
    corpus = eval(input())
    query = eval(input())
    print(compute_tf_idf(corpus, query))