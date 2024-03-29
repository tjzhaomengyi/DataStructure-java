Courses
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| student     | varchar |
| class       | varchar |
+-------------+---------+
(student, class)是该表的主键列。
该表的每一行表示学生的名字和他们注册的班级。

编写一个SQL查询来报告 至少有5个学生 的所有班级。

以 任意顺序 返回结果表。

查询结果格式如下所示。

SELECT class FROM Courses GROUP BY class HAVING COUNT(student) >= 5

