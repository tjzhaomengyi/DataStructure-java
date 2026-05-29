"""
实现一个简单的循环神经网络,只求隐藏层的前向传播
函数`rnn_forward`接收五个参数：
1. input_sequence：输入序列，每个元素是一个输入向量
2. initial_hidden_state：初始隐藏状态
3. Wx：输入到隐藏层的权重矩阵
4. Wh：隐藏层到隐藏层的权重矩阵
5. b：偏置向量
"""
import numpy as np
def rnn_forward(input_sequence, initial_hidden_state, Wx, Wh, b):
    h = np.array(initial_hidden_state)
    Wx = np.array(Wx)
    Wh = np.array(Wh)
    b = np.array(b)
    for x in input_sequence:
        x = np.array(x)
        h = np.tanh(np.dot(Wx, x) + np.dot(Wh, h) + b)
    final_hidden_state = np.round(h, 4)
    return final_hidden_state.tolist()

if __name__ == "__main__":
    input_sequence = eval(input())
    initial_hidden_state = eval(input())
    Wx = eval(input())
    Wh = eval(input())
    b = eval(input())
    print(rnn_forward(input_sequence, initial_hidden_state, Wx, Wh, b))