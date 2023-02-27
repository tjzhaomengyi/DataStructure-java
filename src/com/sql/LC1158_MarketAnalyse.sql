Users
+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| join_date      | date    |
| favorite_brand | varchar |
+----------------+---------+
此表主键是 user_id。
表中描述了购物网站的用户信息，用户可以在此网站上进行商品买卖。

Orders
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| order_id      | int     |
| order_date    | date    |
| item_id       | int     |
| buyer_id      | int     |
| seller_id     | int     |
+---------------+---------+
此表主键是 order_id。
外键是 item_id 和（buyer_id，seller_id）。

Table: Items

+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| item_id       | int     |
| item_brand    | varchar |
+---------------+---------+
此表主键是 item_id。

请写出一条SQL语句以查询每个用户的注册日期和在 2019 年作为买家的订单总数。

以 任意顺序 返回结果表。

查询结果格式如下。
+-----------+------------+----------------+
| buyer_id  | join_date  | orders_in_2019 |
+-----------+------------+----------------+
| 1         | 2018-01-01 | 1              |
| 2         | 2018-02-09 | 2              |
| 3         | 2018-01-19 | 0              |
| 4         | 2018-05-21 | 0              |
+-----------+------------+----------------+

--Mysql又一个傻逼地方，where回过滤掉不符合条件的，但是ON就回保留，我操你妈的
SELECT buyer_id, join_date, COUNT(1) orders_in_2019 FROM
(SELECT buyer_id, join_date FROM
Users LEFT JOIN Orders ON user_id = buyer_id and order_date <= '2019-12-31' AND order_date >= '2019-01-01') a
GROUP BY buye_id

SELECT user_id buyer_id, join_date, COUNT(order_id) orders_in_2019 FROM
    (SELECT user_id,join_date,order_id FROM
        Users LEFT JOIN Orders ON user_id = buyer_id and order_date <= '2019-12-31' AND order_date >= '2019-01-01') a
GROUP BY user_id