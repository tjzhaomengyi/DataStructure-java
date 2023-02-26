Person
+----+---------+
| Id | Email   |
+----+---------+
| 1  | a@b.com |
| 2  | c@d.com |
| 3  | a@b.com |
+----+---------+

输出重复邮箱

SELECT Email FROM(
    SELECT Email, Count(1) cnt FROM Person GROUP BY Email
) where cnt > 1

---使用having
SELECT Email, Count(1) cnt FROM Person GROUP BY Email HAVING cnt > 1