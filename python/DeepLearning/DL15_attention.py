import numpy as np


def compute_qkv(X, W_q, W_k, W_v):
    Q = np.dot(X, W_q)
    K = np.dot(X, W_k)
    V = np.dot(X, W_v)
    return Q, K, V


def self_attention(Q, K, V):
    d_k = Q.shape[1]
    scores = np.matmul(Q, K.T) / np.sqrt(d_k)  # 矩阵乘法 ，高维度里面尽量用matmul
    attention_weights = np.exp(scores) / np.sum(np.exp(scores), axis=1, keepdims=True) #计算每个结果分数
    attention_output = np.matmul(attention_weights, V)
    return attention_output


if __name__ == "__main__":
    X = np.array(eval(input()))
    W_q = np.array(eval(input()))
    W_k = np.array(eval(input()))
    W_v = np.array(eval(input()))
    Q, K, V = compute_qkv(X, W_q, W_k, W_v)
    print(self_attention(Q, K, V))
