+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| id            | int     |
| revenue       | int     |
| month         | varchar |
+---------------+---------+
(id, month) 是表的联合主键。
这个表格有关于每个部门每月收入的信息。
月份（month）可以取下列值 ["Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"]。

编写一个 SQL 查询来重新格式化表，使得新的表中有一个部门 id 列和一些对应 每个月 的收入（revenue）列。
Department 表：
+------+---------+-------+
| id   | revenue | month |
+------+---------+-------+
| 1    | 8000    | Jan   |
| 2    | 9000    | Jan   |
| 3    | 10000   | Feb   |
| 1    | 7000    | Feb   |
| 1    | 6000    | Mar   |
+------+---------+-------+

查询得到的结果表：
+------+-------------+-------------+-------------+-----+-------------+
| id   | Jan_Revenue | Feb_Revenue | Mar_Revenue | ... | Dec_Revenue |
+------+-------------+-------------+-------------+-----+-------------+
| 1    | 8000        | 7000        | 6000        | ... | null        |
| 2    | 9000        | null        | null        | ... | null        |
| 3    | null        | 10000       | null        | ... | null        |
+------+-------------+-------------+-------------+-----+-------------+

SELECT id ,
SUM(IF(month = 'Jan', revenue, null)) Jan_Revenue,
SUM(IF(month = 'Feb', revenue, null)) Feb_Revenue,
SUM(IF(month = 'Mar', revenue, null)) Mar_Revenue,
SUM(IF(month = 'Apr', revenue, null)) Apr_Revenue,
SUM(IF(month = 'May', revenue, null)) May_Revenue,
SUM(IF(month = 'Jun', revenue, null)) Jun_Revenue,
SUM(IF(month = 'Jul', revenue, null)) Jul_Revenue,
SUM(IF(month = 'Aug', revenue, null)) Aug_Revenue,
SUM(IF(month = 'Sep', revenue, null)) Sep_Revenue,
SUM(IF(month = 'Oct', revenue, null)) Oct_Revenue,
SUM(IF(month = 'Nov', revenue, null)) Nov_Revenue,
SUM(IF(month = 'Dec', revenue, null)) Dec_Revenue
From Department group by id