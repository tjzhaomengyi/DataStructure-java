"""
决策树是一个用于分类和回归的模型，它通过将数据集分割成更小的子集来构建树形结构。
每个内部节点代表一个特征的测试，每个分支代表测试结果，而每个叶子节点则表示最终的输出或者值。

函数接收3个参数：
1. examples：列表，包含多个字典，每个字典表示一个训练样本
2. attributes：列表，包含可用于划分的属性名
3. target_attr：字符串，表示目标属性（类别标签）的名称
[{"outlook": "sunny", "temp": "hot", "humidity": "high", "windy": "false", "play": "no"},
{"outlook": "sunny", "temp": "hot", "humidity": "high", "windy": "true", "play": "no"},
{"outlook": "overcast", "temp": "hot", "humidity": "high", "windy": "false", "play": "yes"},
{"outlook": "rain", "temp": "mild", "humidity": "high", "windy": "false", "play": "yes"}]
["outlook", "temp", "humidity", "windy"]
"play"
输出描述：
返回一个嵌套字典，表示学习到的决策树结构：
- 内部节点：`{属性名: {属性值: 子树, ...}}`
- 叶节点：直接返回类别值
{outlook:{overcast:yes,rain:yes,sunny:no}}
"""
import math
from collections import Counter


# 熵
def calculate_entropy(labels):
    label_counts = Counter(labels)
    total_count = len(labels)
    entropy = -sum((count / total_count) * math.log2(count / total_count) for count in label_counts.values())
    return entropy


# 信息增益
def calculate_information_gain(examples, attr, target_attr):
    total_entropy = calculate_entropy([example[target_attr] for example in examples])
    values = set(example[attr] for example in examples)
    attr_entropy = 0
    for value in values:
        value_subset = [
            example[target_attr] for example in examples if example[attr] == value
        ]
        value_entropy = calculate_entropy(value_subset)
        attr_entropy += (len(value_subset) / len(examples)) * value_entropy # 这个是条件熵
    return total_entropy - attr_entropy


def majority_class(examples, target_attr):
    return Counter([example[target_attr] for example in examples]).most_common(1)[0][0]


def learn_decision_tree(examples, attributes, target_attr):
    if not examples:
        return "No examples"
    if all(example[target_attr] == examples[0][target_attr] for example in examples):
        return examples[0][target_attr]
    if not attributes:
        return majority_class(examples, target_attr)
    gains = {
        attr: calculate_information_gain(examples, attr, target_attr)
        for attr in attributes
    }
    best_attr = max(gains, key=gains.get)
    tree = {best_attr: {}}

    for value in set(example[best_attr] for example in examples):
        subset = [example for example in examples if example[best_attr] == value]
        new_attributes = attributes.copy()
        new_attributes.remove(best_attr)
        subtree = learn_decision_tree(subset, new_attributes, target_attr) # 递归
        tree[best_attr][value] = subtree

    return tree


def print_tree(tree):
    outs = []
    for key, value in sorted(tree.items()):
        outs.append(f"{key}:{print_tree(value) if isinstance(value, dict) else value}")
    return "{" + ",".join(outs) + "}"


if __name__ == "__main__":
    examples = eval(input())
    attributes = eval(input())
    target_attr = eval(input())
    print(print_tree(learn_decision_tree(examples, attributes, target_attr)))
