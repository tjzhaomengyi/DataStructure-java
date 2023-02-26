Visits
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| visit_id    | int     |
| customer_id | int     |
+-------------+---------+
visit_id 是该表的主键。
该表包含有关光临过购物中心的顾客的信息。

Transactions
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| transaction_id | int     |
| visit_id       | int     |
| amount         | int     |
+----------------+---------+
transaction_id 是此表的主键。
此表包含 visit_id 期间进行的交易的信息。

有一些顾客可能光顾了购物中心但没有进行交易。请你编写一个 SQL 查询，来查找这些顾客的 ID ，以及他们只光顾不交易的次数。

返回以 任何顺序 排序的结果表。

查询结果格式如下例所示。


--太难了~直接记住例子吧 一列在某个子查询范围中的过滤
SELECT customer_id, count(visit_id) count_no_trans FROM Visits
WHERE visit_id not in
      (select Visits.visit_id FROM Visits JOIN Transactions on Visits.visit_id = Transactions.visit_id)
GROUP BY customer_id