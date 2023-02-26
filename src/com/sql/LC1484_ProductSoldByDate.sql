Activities
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| sell_date   | date    |
| product     | varchar |
+-------------+---------+
此表没有主键，它可能包含重复项。
此表的每一行都包含产品名称和在市场上销售的日期。

编写一个 SQL 查询来查找每个日期、销售的不同产品的数量及其名称。
每个日期的销售产品名称应按词典序排列。
返回按 sell_date 排序的结果表。
查询结果格式如下例所示。

--连接结果集，并按照字典序排序，按照日期排序
SELECT sell_date, count(distinct product) as num_sold,
       group_concat(distinct proceduct order by product seperator ',') products
from Activities
group by sell_date order by sell_date
