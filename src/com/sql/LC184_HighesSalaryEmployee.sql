Employee
+--------------+---------+
| 列名          | 类型    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
id是此表的主键列。
departmentId是Department表中ID的外键。
此表的每一行都表示员工的ID、姓名和工资。它还包含他们所在部门的ID。

Department
+-------------+---------+
| 列名         | 类型    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
id是此表的主键列。
此表的每一行都表示一个部门的ID及其名称。

编写SQL查询以查找每个部门中薪资最高的员工。
按 任意顺序 返回结果表。
查询结果格式如下例所示。
+------------+----------+--------+
| Department | Employee | Salary |
+------------+----------+--------+
| IT         | Jim      | 90000  |
| Sales      | Henry    | 80000  |
| IT         | Max      | 90000  |
+------------+----------+--------+

SELECT Department,Employee,Salary FROM
(SELECT Department.name as Department,Employee.name as Employee,Salary as Salary,
       DENSE_RANK() OVER(PARTITION BY departmentId Order BY salary desc) as rnk
FROM Employee join Department on Employee.departmentId = Department.id) a
WHERE rnk = 1;