import numpy as np

"""
1、计算遗忘门
2、计算输入门
3、计算细胞状态更新
4、计算输出门
"""
class LSTM:
    def __init__(self, input_size, hidden_size):
        self.input_size = input_size
        self.hidden_size = hidden_size

        # Initialize weights and biases
        self.Wf = np.random.randn(hidden_size, input_size + hidden_size)
        self.Wi = np.random.randn(hidden_size, input_size + hidden_size)
        self.Wc = np.random.randn(hidden_size, input_size + hidden_size)
        self.Wo = np.random.randn(hidden_size, input_size + hidden_size)

        self.bf = np.zeros((hidden_size, 1))
        self.bi = np.zeros((hidden_size, 1))
        self.bc = np.zeros((hidden_size, 1))
        self.bo = np.zeros((hidden_size, 1))

    def forward(self, x, initial_hidden_state, initial_cell_state):
        """
        Processes a sequence of inputs and returns the hidden states, final hidden state, and final cell state.
        """
        h = initial_hidden_state
        c = initial_cell_state #细胞装她爱
        outputs = []

        for t in range(len(x)):
            xt = x[t].reshape(-1, 1)
            concat = np.vstack((h, xt))
            # 遗忘门
            ft = self.sigmoid(np.dot(self.Wf, concat) + self.bf)

            # 输入门
            it = self.sigmoid(np.dot(self.Wi, concat) + self.bi)
            c_tilde = np.tanh(np.dot(self.Wc, concat) + self.bc)  # 当前时刻的新信息候选值
            # 细胞状态
            c = ft * c + it * c_tilde
            # 输出门
            ot = self.sigmoid(np.dot(self.Wo, concat) + self.bo)
            # 隐藏层更新
            h = ot * np.tanh(c)

            outputs.append(h)
        return np.array(outputs), h, c

    def sigmoid(self, x):
        return 1 / (1 + np.exp(-x))

if __name__ == "__main__":
    input_sequence = np.array([[0.1, 0.2], [0.3, 0.4]])
    initial_hidden_state = np.zeros((2, 1))
    initial_cell_state = np.zeros((2, 1))
    lstm = LSTM(input_size=2, hidden_size=2) # Set weights and biases for reproducibility
    lstm.Wf = np.array([[0.1, 0.2, 0.3, 0.4], [0.5, 0.6, 0.7, 0.8]])
    lstm.Wi = np.array([[0.1, 0.2, 0.3, 0.4], [0.5, 0.6, 0.7, 0.8]])
    lstm.Wc = np.array([[0.1, 0.2, 0.3, 0.4], [0.5, 0.6, 0.7, 0.8]])
    lstm.Wo = np.array([[0.1, 0.2, 0.3, 0.4], [0.5, 0.6, 0.7, 0.8]])
    lstm.bf = np.array([[0.1], [0.2]])
    lstm.bi = np.array([[0.1], [0.2]])
    lstm.bc = np.array([[0.1], [0.2]])
    lstm.bo = np.array([[0.1], [0.2]])
    outputs, final_h, final_c = lstm.forward(input_sequence, initial_hidden_state, initial_cell_state)
    print(final_h)