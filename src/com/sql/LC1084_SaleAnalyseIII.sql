Product
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| product_id   | int     |
| product_name | varchar |
| unit_price   | int     |
+--------------+---------+
Product_id是该表的主键。
该表的每一行显示每个产品的名称和价格。

Sales
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| seller_id   | int     |
| product_id  | int     |
| buyer_id    | int     |
| sale_date   | date    |
| quantity    | int     |
| price       | int     |
+------ ------+---------+
这个表没有主键，它可以有重复的行。
product_id 是 Product 表的外键。
该表的每一行包含关于一个销售的一些信息。

编写一个SQL查询，报告2019年春季才售出的产品。即仅在2019-01-01至2019-03-31（含）之间出售的商品。

以 任意顺序 返回结果表。

查询结果格式如下所示。

SELECT distinct Product.product_id product_id, product_name FROM
Product JOIN Sales ON Product.product_id = Sales.product_id
WHERE sale_date >= '2019-01-01' and sale_date <= '2019-03-31'
AND Product.product_id NOT IN (
    SELECT product_id FROM Sales WHERE sale_date < '2019-01-01' OR sale_date > '2019-03-31'
)