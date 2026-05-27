"""
生成频繁项集的递归过程：
1、从频繁k项集中组合出候选k+1项集
2、统计候选k+1项集在交易数据中出现的次数
3、过滤出频繁k+1项集（出现次数大于等于min_support）
4、重复上述过程，直到无法生成更高阶的频繁项集。
在实现上，组合出候选集合的方式有多种，本体可以通过直接枚举合并出所有可能的k+1项集来简化代码实现，
然后统计出现次数，最后过滤出频繁项集k+1项集，这种方式在数据量较大时，效率较低
"""

from itertools import combinations
from collections import defaultdict


def generate_frequent_itemsets(transactions, min_support):
    item_count = defaultdict(int)  # 带默认值的字典
    # 统计每个物品出现的次数
    for transaction in transactions:
        items = transaction.split(',')
        for item in items:
            item_count[item.strip()] += 1;
    # 过滤出大于min_supportde 频繁项集
    frequent_itemsets = {
        item: count
        for item, count in item_count.items() if count >= min_support
    }

    # 生成更高阶的频繁项集
    k = 2
    while True:
        # 组合k个项
        new_combinations = combinations(frequent_itemsets.keys(), k)
        # 统计k个项在交易数据中出现的次数
        new_item_count = defaultdict(int)
        # 遍历交易数据，统计k个项在交易数据中出现的次数
        for transaction in transactions:
            items = set(transaction.split(','))
            for combo in new_combinations:
                if all(item in items for item in combo):  # 生成器表示每个物品是否在combo中，如果最后全都在，那么就全加上
                    new_item_count[combo] += 1

        # 过滤出频繁项集
        frequent_itemsets_k = {combo: count
                               for combo, count in new_item_count.items() if count >= min_support}
        # 如果k个项在交易数据中出现的次数小于min_suppoert 则停止生成更高阶的频繁项集
        if not frequent_itemsets_k:
            break
        frequent_itemsets.update(frequent_itemsets_k)
        k += 1
    return frequent_itemsets


# 主程序
T = int(input())
transactions = [input().strip() for _ in range(T)]

min_support = int(input())

frequent_itemsets = generate_frequent_itemsets(transactions, min_support)

# 输出结果
for itemset, count in sorted(frequent_itemsets.items(), key=lambda x: -x[1]):
    if isinstance(itemset, str):
        print(f'{{{itemset}}}')
    else:
        print(f'{{{", ".join(itemset)}}}')
