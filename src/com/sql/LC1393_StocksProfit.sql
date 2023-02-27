Stocks
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| stock_name    | varchar |
| operation     | enum    |
| operation_day | int     |
| price         | int     |
+---------------+---------+
(stock_name, day) 是这张表的主键
operation 列使用的是一种枚举类型，包括：('Sell','Buy')
此表的每一行代表了名为 stock_name 的某支股票在 operation_day 这一天的操作价格。
保证股票的每次'Sell'操作前，都有相应的'Buy'操作。

编写一个SQL查询来报告每支股票的资本损益。

股票的资本损益是一次或多次买卖股票后的全部收益或损失。

以任意顺序返回结果即可。

SELECT stock_name, SUM(IF(operation = 'Sell', price, 0)) - SUM(IF(operation = 'Buy', price, 0)) AS capital_gain_loss
FROM Stocks GROUP BY stock_name,operation;