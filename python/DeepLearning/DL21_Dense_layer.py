"""
实现一个自定义的Dense全连接层，该类继承基础Layer类，并实现神经网络中全连接层的所有基本功能
输入描述：
Dense类需要实现以下方法：
1. `__init__(self, n_units, input_shape=None)`：
- n_units：输出神经元数量
- input_shape：输入形状（可选）

2. `initialize(self, optimizer)`：
- 初始化权重W（使用1/sqrt(input_shape[0])作为范围进行正负区间的均匀分布采样）
- 初始化偏置w0为零
- 设置优化器

3. `parameters(self)`：
- 返回层中可训练参数总数

4. `forward_pass(self, X, training=True)`：
- 执行前向传播计算
- 返回
X⋅W+w0

5. `backward_pass(self, accum_grad)`：
- 计算并返回梯度
- 使用优化器更新参数

6. `output_shape(self)`：
- 返回输出形状(n_units,)
输出描述：
每个方法都需要返回特定的值：
- initialize：无返回值
- parameters：返回整数
- forward_pass：返回numpy数组
- backward_pass：返回numpy数组
- output_shape：返回元组



"""

import numpy as np
import copy
import math

# DO NOT CHANGE SEED
np.random.seed(42)


# DO NOT CHANGE LAYER CLASS
class Layer(object):

    def set_input_shape(self, shape):
        self.input_shape = shape

    def layer_name(self):
        return self.__class__.__name__

    def parameters(self):
        return 0

    def forward_pass(self, X, training):
        raise NotImplementedError()

    def backward_pass(self, accum_grad):
        raise NotImplementedError()

    def output_shape(self):
        raise NotImplementedError()


# Your task is to implement the Dense class based on the above structure
class Dense(Layer):
    def __init__(self, n_units, input_shape=None):
        self.layer_input = None
        self.input_shape = input_shape
        self.n_units = n_units  # 输入神经元数
        self.trainable = True
        self.W = None  # 初始化权重
        self.w0 = None  # 初始化偏置

    def initialize(self, optimizer):
        limit = 1 / math.sqrt(self.input_shape[0])
        self.W = np.random.uniform(-limit, limit, (self.input_shape[0], self.n_units))
        self.w0 = np.zeros((1, self.n_units))
        self.W_opt = copy.copy(optimizer)
        self.w0_opt = copy.copy(optimizer)

    def parameters(self):
        return np.prod(self.W.shape) + np.prod(self.w0.shape)

    def set_input_shape(self, shape):
        self.input_shape = shape

    def layer_name(self):
        return self.__class__.__name__

    def forward_pass(self, X, training):
        self.layer_input = X
        return X.dot(self.W) + self.w0

    def backward_pass(self, accum_grad):
        W = self.W
        if self.trainable:
            grad_w = self.layer_input.T.dot(accum_grad)
            grad_w0 = np.sum(accum_grad, axis=0, keepdims=True)
            self.W = self.W_opt.update(self.W, grad_w)
            self.w0 = self.w0_opt.update(self.w0, grad_w0)
        accum_grad = accum_grad.dot(W.T) # ∂L / ∂X = ·∂L/∂Y·W^T，因为Y=XW
        return accum_grad

    def output_shape(self):
        return (self.n_units,)

    def number_of_parameters(self):
        return X.dot(self.W) + self.w0


if __name__ == "__main__":
    # Initialize a Dense layer with 3 neurons and input shape (2,)
    dense_layer = Dense(n_units=3, input_shape=(2,))


    # Define a mock optimizer with a simple update rule
    class MockOptimizer:
        def update(self, weights, grad):
            return weights - 0.01 * grad


    optimizer = MockOptimizer()

    # Initialize the Dense layer with the mock optimizer
    dense_layer.initialize(optimizer)

    # Perform a forward pass with sample input data
    X = np.array(eval(input()))
    output = dense_layer.forward_pass(X,training=True)
    print("Forward pass output:", output)

    accum_grad = np.array([[0.1, 0.2, 0.3]])
    back_output = dense_layer.backward_pass(accum_grad)
    print("Backward pass output:", back_output)
