Customers
+----+-------+
| Id | Name  |
+----+-------+
| 1  | Joe   |
| 2  | Henry |
| 3  | Sam   |
| 4  | Max   |
+----+-------+

Orders
+----+------------+
| Id | CustomerId |
+----+------------+
| 1  | 3          |
| 2  | 1          |
+----+------------+

# Write your MySQL query statement below
SELECT Name as Customers FROM Customers
WHERE Id NOT IN
      (
          SELECT CustomerId FROM Orders
      )

SELECT Name as Customers FROM
    (Customers c
    left join Orders o
    on c.id = o.CustomerId)
where o.id is null