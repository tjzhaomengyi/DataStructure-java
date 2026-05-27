"""
第一行输入一个整数n，表示事务的数量。
接下来n行，每行输入一个事务，格式为“item1, item2, ..., itemk”，
其中item1, item2, ..., item_k是事务中的项，用空格分隔。
最后一行输入两个浮点数min_sup和min_conf，分别表示最小支持度和最小置信度。
输出描述：
输出关联规则及其置信度，按照置信度从高到低排序，置信度相同则按照规则前件大小升序排列，如若还是相同则按照规则后件大小升序排列
格式为“规则前件 -> 规则后件: 置信度”。置信度保留两位小数。

频繁项集：在数据集中频繁出现的项集。
关联规则：在数据集中，若A则B的规则形式。
支持度：项集A在数据集中出现的次数除以数据集的总数。就是项集A在数据集中出现的概率。
置信度：项集A和项集B同时出现的次数除以项集A出现的次数，就是项集A出现时，项集B出现的概率

频繁项集的生成：
1、扫描数据集，生成1-频繁项集
2、对当前频繁项集，生成候选频繁项集，并计算其支持度
3、根据支持度阈值，筛选出频繁项集，并更新当前频繁项集
4、重复2-3，直到无法生成新的频繁项集

关联规则的生成
1、对一个频繁项集，枚举其所有非空子集作为规则前件，剩余部分作为规则后件
2、对每个规则，计算其置信度
3、根据置信度阈值，筛选出关联规则
4、重复2-3，直到无法生成新的关联规则
"""

from collections import defaultdict
def support(itemset, transactions):
    return len([t for t in transactions if itemset.issubset(t)]) / len(transactions)

def confidence(antecedent, consequent, transactions):
    return support(antecedent.union(consequent), transactions) / support(antecedent, transactions)

def combinations(iterable, r):
    # 生成组合的辅助函数，使用位运算
    n = len(iterable)
    if r > n:
        return []
    indices = list(range(n))
    result = []
    # 生成所有可能的组合
    for i in range(1 << n):
        if bin(i).count('1') == r:
            result.append(frozenset(iterable[j] for j in indices if (i & (1 << j)) > 0))
    return result

def apriori(transactions, min_sup, min_conf):
    item_count = defaultdict(int)
    # 计算每个单项的支持度
    for transaction in transactions:
        for item in transaction:
            item_count[item] += 1

    # 筛选出最低的频繁项集
    frequent_itemsets = {frozenset([item]) for item in item_count if item_count[item] / len(transactions) >= min_sup}
    k=2
    all_frequent_itemsets = frequent_itemsets.copy() # 存储所有频繁项集
    #频繁项集越筛越少 每次 k+1
    while frequent_itemsets:
        # 生成候选k项集
        itemset_count = defaultdict(int)
        candiddate_itemsets = [frozenset(x) for x in combinations(list(set().union(*frequent_itemsets)), k)]
        # 计算候选项集的支持度
        for transaction in transactions:
            for candidate in candiddate_itemsets:
                if candidate.issubset(transaction):
                    itemset_count[candidate] += 1
        #筛选频繁k项集
        frequent_itemsets = {itemset for itemset, count in itemset_count.items() if count / len(transactions) >= min_sup}
        # 更新所有频繁项集
        all_frequent_itemsets.update(frequent_itemsets)
        k += 1
    #生成关联规则
    rules = []
    #遍历所有频繁项集
    for itemset in all_frequent_itemsets:
        itemlist = list(itemset)
        # 遍历频繁项集的子集大小
        for i in range(1, len(itemlist)):
            # 枚举子集作为规则前件
            for subset in combinations(itemlist, i):
                antecendent = subset
                consequent = itemset - antecendent
                conf = confidence(antecendent, consequent, transactions)
                if conf >= min_conf:
                    rules.append((antecendent, consequent, conf))
    return rules

if __name__ == "__main__":
    n = int(input())
    transactions = []
    for i in range(n):
        transactions.append(input().split())
    min_sup, min_conf = map(float, input().split())
    rules = apriori(transactions, min_sup, min_conf)
    for rule in sorted(rules, key=lambda x: (-x[2],len(x[0]),len(x[1]))):
        print(f"{set(rule[0])} -> {set(rule[1])}: {rule[2]:.2f}")
