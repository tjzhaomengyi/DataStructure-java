+----------------+---------+
| Column Name    | Type    |
+----------------+---------+
| user_id        | int     |
| name           | varchar |
+----------------+---------+
user_id 是该表的主键。
该表包含用户的 ID 和名字。名字仅由小写和大写字符组成。
编写一个 SQL 查询来修复名字，使得只有第一个字符是大写的，其余都是小写的。

返回按 user_id 排序的结果表。

查询结果格式示例如下。

SELECT user_id, CONCAT(UPPER(LEFT(name, 1)),LCASE(MID(name, 2, LENGTH(name)-1))) as name from users order by user_id