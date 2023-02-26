Orders
+-----------------+----------+
| Column Name     | Type     |
+-----------------+----------+
| order_number    | int      |
| customer_number | int      |
+-----------------+----------+
Order_number是该表的主键。
此表包含关于订单ID和客户ID的信息。

编写一个SQL查询，为下了 最多订单 的客户查找 customer_number 。

测试用例生成后， 恰好有一个客户 比任何其他客户下了更多的订单。

查询结果格式如下所示。

SELECT customer_number
FROM orders
GROUP BY customer_number
ORDER BY COUNT(*) DESC
    LIMIT 1;

SELECT customer_number FROM Orders GROUP BY customer_number ORDER BY Count(*) desc limit 1