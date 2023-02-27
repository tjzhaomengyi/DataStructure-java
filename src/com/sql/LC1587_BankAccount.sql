Users
+--------------+---------+
| Column Name  | Type    |
+--------------+---------+
| account      | int     |
| name         | varchar |
+--------------+---------+
account 是该表的主键.
表中的每一行包含银行里中每一个用户的账号.

Transactions
+---------------+---------+
| Column Name   | Type    |
+---------------+---------+
| trans_id      | int     |
| account       | int     |
| amount        | int     |
| transacted_on | date    |
+---------------+---------+
trans_id 是该表主键.
该表的每一行包含了所有账户的交易改变情况.
如果用户收到了钱, 那么金额是正的; 如果用户转了钱, 那么金额是负的.
所有账户的起始余额为 0.
写一个 SQL,  报告余额高于 10000 的所有用户的名字和余额. 账户的余额等于包含该账户的所有交易的总和.

返回结果表单没有顺序要求.

查询结果格式如下例所示.
Users table:
+------------+--------------+
| account    | name         |
+------------+--------------+
| 900001     | Alice        |
| 900002     | Bob          |
| 900003     | Charlie      |
+------------+--------------+

Transactions table:
+------------+------------+------------+---------------+
| trans_id   | account    | amount     | transacted_on |
+------------+------------+------------+---------------+
| 1          | 900001     | 7000       |  2020-08-01   |
| 2          | 900001     | 7000       |  2020-09-01   |
| 3          | 900001     | -3000      |  2020-09-02   |
| 4          | 900002     | 1000       |  2020-09-12   |
| 5          | 900003     | 6000       |  2020-08-07   |
| 6          | 900003     | 6000       |  2020-09-07   |
| 7          | 900003     | -4000      |  2020-09-11   |
+------------+------------+------------+---------------+



--先处理小表的效率更高,太难了~直接记住例子吧先join再select
select name, balance from
    (
        (select account, sum(amount) as balance from Transactions group by account) c
        join Users
        on c.account = Users.account
    ) where balance > 10000




--注意having可以不像where那样过滤必须先选，having可以直接过滤
select name, sum(account) as balance
from Users left join Trasactions on Users.account = Trasactions.account
group by (Users.account)
having balance > 10000