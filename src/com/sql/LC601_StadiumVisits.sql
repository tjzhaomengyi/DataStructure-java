Stadium
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| visit_date    | date    |
| people        | int     |
+---------------+---------+
visit_date 是表的主键
每日人流量信息被记录在这三列信息中：序号 (id)、日期 (visit_date)、 人流量 (people)
每天只有一行记录，日期随着 id 的增加而增加

编写一个 SQL 查询以找出每行的人数大于或等于 100 且 id 连续的三行或更多行记录。

返回按 visit_date 升序排列 的结果表。

查询结果格式如下所示。

# Write your MySQL query statement below
SELECT id,  visit_date, people FROM
    (SELECT id,people,
            LAG(people, 1) OVER(ORDER BY id) beforep,
            LEAD(people, 1) OVER(ORDER BY id) afterp,
            visit_date FROM Stadium) tmp
WHERE beforep >= 100 AND afterp >= 100 AND people >= 100
ORDER BY visit_date
