Employee
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| id           | int     |
| name         | varchar |
| salary       | int     |
| departmentId | int     |
+--------------+---------+
Id是该表的主键列。
departmentId是Department表中ID的外键。
该表的每一行都表示员工的ID、姓名和工资。它还包含了他们部门的ID。

Department
+-------------+---------+
| Column Name | Type    |
+-------------+---------+
| id          | int     |
| name        | varchar |
+-------------+---------+
Id是该表的主键列。
该表的每一行表示部门ID和部门名。

公司的主管们感兴趣的是公司每个部门中谁赚的钱最多。一个部门的 高收入者 是指一个员工的工资在该部门的 不同 工资中 排名前三 。

编写一个SQL查询，找出每个部门中 收入高的员工 。

以 任意顺序 返回结果表。
 Department | Employee | Salary |
+------------+----------+--------+
| IT         | Max      | 90000  |
| IT         | Joe      | 85000  |
| IT         | Randy    | 85000  |
| IT         | Will     | 70000  |
| Sales      | Henry    | 80000  |
| Sales      | Sam      | 60000  |
+------------+----------+--------

SELECT dname AS Department, ename AS Employee, salary AS Salary FROM
(SELECT departmentId, Employee.name AS ename, Department.name AS dname,
        DENSE_RANK() OVER(PARTITION BY departmentId ORDER BY salary DESC) AS rnk, salary FROM
        Employee join Department on Employee.departmentId = Department.id
) a WHERE rnk <= 3