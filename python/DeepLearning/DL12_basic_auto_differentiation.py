"""
实现基本自动微分操作（Basic Automatic Differentiation）是一种常用的自动微分方法，用于计算函数的导数。
 所谓的自动计算微分，就是通过计算图，从输入到输出，反向传播，计算每个节点的导数。而这个计算图，
通俗点来说就是高中所学到的链式法则需要画的函数关系图，也可以理解为深度学习中神经网络的结构图。

1、构建计算图神经网络，在main函数中给出
2、前向传播，计算每个节点的值 从输入开始，根据计算图的结构，计算每个节点的值
3、反向传播，计算每个节点的导数，从输入开始。根据计算图的结构，计算每个节点的导数，这里用到的关键技术就是链式求导法则

"""


class Value:
    def __init__(self, data, _children=(), _op=''):
        self.data = data  # 节点存储的数值
        self.grad = 0  # 梯度，初始为0
        self._backward = lambda: None  # 反向传播函数，初始为空操作
        self._prev = set(_children)  # 前驱节点，用于构建图计算
        self._op = _op  # 操作符号，用于调试可视化

    def __repr__(self):
        return f"Value(data={self.data}, grad={self.grad})"

    # 注意内部的梯度分配计算，把上游的梯度分配给下游两个梯度，一个是other 一个是本身self
    # a = Value(2) , b = Value(3) c = a+b, 这里，a是self，b是other ，c是out
    # self = a 对应图中的左孩子，other=b对应图中的右孩子，out=c代表当前节点。
    # 在反向传播时，c.grad从上游来，要把c.grad传递给左孩子和右孩子，所以就是self.grad += out.grad, other.grad += out.grad
    """
    前向：    a (self) ──┐
                        ├──→ c (out)
            b (other) ──┘

    反向：    a.grad ←───┤
                        │ c.grad
             b.grad ←───┘
    """

    def __add__(self, other):
        # Implement addition here
        # 如果other 不是 Value 对象, 转换为Value对象
        other = other if isinstance(other, Value) else Value(other)
        # 创建新的Value 对象存储加法的结果
        out = Value(self.data + other.data, (self, other), "+")

        # 定义反向传播梯度函数，不能说是计算梯度函数，而是分发梯度，把上游传来的梯度原样分配给所有输入节点！
        def _backward():
            # out.grad 是从上游传下来的梯度，要把out.grad分配给self.grad 和 other.grad
            self.grad += out.grad  # out.grad是从上游传下来的梯度
            other.grad += out.grad

        out._backward = _backward  # 将反向函数挂载到输出节点
        return out

    # 乘法：self * other = out,其中
    #   从out = self * other中出去的梯度要知道对a和对b的梯度都是多少，所以有如下公式
    #   针对self的导数(self是未知数，other是常a数) ∂L/∂self = ∂L/∂out * ∂out/∂self = out.grad * other.data
    #   针对other的导数(other是未知数，self是常数) ∂L/∂other = ∂L/∂out * ∂out/∂other = out.grad * self.data
    #
    def __mul__(self, other):
        # Implement multiplication here
        other = other if isinstance(other, Value) else Value(other)
        out = Value(self.data * other.data, (self, other), "*")

        def _backward():
            # self.grad 就是 ∂L/∂self
            self.grad += out.grad * other.data
            other.grad += out.grad * self.data

        out._backward = _backward
        return out

    def relu(self):
        # Implement ReLU here
        out = Value(0 if self.data < 0 else self.data, (self,), "Relu")

        def _backward():
            self.grad += (out.data > 0) * out.grad

        out._backward = _backward
        return out

    def backward(self):
        # Implement backward pass here
        topo = []
        visited = set()

        def build_topo(v):
            if v not in visited:
                visited.add(v)
                for child in v._prev:
                    build_topo(child) # 递归构建子图
                topo.append(v)
        build_topo(self)
        self.grad = 1 # 设置自己的梯度为1
        for v in reversed(topo): #进行反向传播
            v._backward()



if __name__ == "__main__":
    a = Value(int(input()))
    b = Value(int(input()))
    c = Value(int(input()))
    d = a + b * c
    e = Value(7) * Value(2)
    f = e + d
    g = f.relu()
    g.backward()
    print(a, b, c, d, e, f, g)
