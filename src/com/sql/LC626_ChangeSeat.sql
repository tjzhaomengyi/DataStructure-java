SEAT
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
Id是该表的主键列。
该表的每一行都表示学生的姓名和ID。
Id是一个连续的增量。

编写SQL查询来交换每两个连续的学生的座位号。如果学生的数量是奇数，则最后一个学生的id不交换。


SELECT id,IF(id % 2 = 0, beforeseat,IF(afterseat is not null, afterseat, student))
    as student FROM
    (SELECT id, student, LEAD(student, 1) OVER(ORDER BY id) as afterseat, LAG(student, 1) OVER(ORDER BY id) as lastseat FROM Seat)a
ORDER BY id
