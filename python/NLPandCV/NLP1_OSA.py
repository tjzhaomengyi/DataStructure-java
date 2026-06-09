"""
最优字符串对齐距离
OSA距离是衡量两个字符串相似度的指标，表示将一个字符串转换为另一个字符串所需的最小编辑操作次数
"""


def OSA(source: str, target: str) -> int:
    source_len, target_len = len(source), len(target)

    # 构建动态规划的二维数组
    dp = [[0] * (target_len + 1) for _ in range(source_len + 1)]

    # 如果从i长度的source修改为长度为0的target需要多少操作
    for i in range(0, source_len + 1):
        dp[i][0] = i
    for j in range(0, target_len + 1):
        dp[0][j] = j

    for i in range(1, source_len + 1):
        for j in range(1, target_len + 1):
            # cost = 0 if source[i-1] == target[j-1] else 1
            if source[i - 1] == target[j - 1]:
                dp[i][j] = dp[i - 1][j - 1]
            elif i > 1 and j > 1 and source[i - 1] == target[j - 2] and source[i - 2] == target[j - 1]:
                dp[i][j] = min(dp[i][j], dp[i - 2][j - 2]) + 1
            else:
                dp[i][j] = min(min(dp[i - 1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1

    return dp[-1][-1]


if __name__ == "__main__":
    source = eval(input())
    target = eval(input())
    print(OSA(source, target))
