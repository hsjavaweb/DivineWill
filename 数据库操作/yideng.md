- 创建数据库
CREATE DATABASE yideng;
USE yideng;
- 创建表格
CREATE TABLE Student(
  id INT PRIMARY KEY,
  NAME VARCHAR(20),
  age INT,
  sex VARCHAR(10)
);
- 增加数据
INSERT INTO Student(id,NAME,age,sex) VALUES('1','jack','10','男');
INSERT INTO Student VALUES('2','rose','11','女');
INSERT INTO Student VALUES('3','tom','22','男');
INSERT INTO Student VALUES('4','jerry','100','男');
INSERT INTO Student VALUES('5','yideng','11','男');
INSERT INTO Student VALUES('6','gaohong','22','女');
INSERT INTO Student VALUES('7','qunzhu','11','女');
INSERT INTO Student VALUES('8','test','44','男');
INSERT INTO Student(id,age) VALUES('9','33');


## 改数据
将名字为jerry的同学年龄改为25
UPDATE Student SET age='25' WHERE NAME='jerry';
将年龄为22岁的性别为女的同学名字改为nice
UPDATE Student SET NAME='nice' WHERE age='22' AND sex='女';
将学号为7的同学性别改为女
UPDATE Student SET sex='男' WHERE id='7';
在上面员工表的基本上增加一个class列
ALTER TABLE Student ADD class VARCHAR(20);

## 删除操作
* 删除表中名为tom的记录
DELETE FROM Student WHERE NAME='tom';
* 删除表中所以的数据
DELETE FROM Student;
* 使用truncate删除表中记录
TRUNCATE TABLE Student;
* 删除clss列,一次只能删一列。
ALTER TABLE Student DROP class;

* DELETE 删除表中的数据，表结构还在;删除后的数据可以找回
TRUNCATE 删除是把表直接DROP掉，然后再创建一个同样的新表。
删除的数据不能找回。执行速度比DELETE快

## 查询
* 查询所有
SELECT * FROM Student;
* 查询学号为3，或者姓名为rose的记录
SELECT * FROM Student WHERE id=3 OR NAME='rose';
* 查询学号为1,2,3,4的记录
SELECT * FROM Student WHERE id IN (1,2,3,4);
-- 查询学号不是1,2的记录
SELECT * FROM Student WHERE id NOT IN (1,2);
* 查询姓名为null的记录
SELECT * FROM Student WHERE NOT NAME IS NULL;
SELECT * FROM Student WHERE NAME IS NOT NULL;
* 查询年龄在10到20之间的学生记录
SELECT * FROM Student WHERE age>=10 AND age<=30
SELECT * FROM Student WHERE age BETWEEN 10 AND 20;
* 查询性别非男的学生记录
SELECT * FROM Student WHERE sex!='男';
SELECT * FROM Student WHERE sex<>'男';
SELECT * FROM Student WHERE NOT sex='男';
* 查询学号为1的同学的姓名 
SELECT NAME FROM Student WHERE id=1;

* 模糊查询
SELECT * FROM Student
* 查询姓名由5个字母构成的学生记录
SELECT * FROM Student WHERE NAME LIKE '_____';
* 查询姓名由4个字母构成，并且第5个字母为“e”的学生记录
SELECT * FROM Student WHERE NAME LIKE '___e';
* 查询姓名以“t”开头的学生记录
SELECT * FROM Student WHERE NAME LIKE 't%';
* 查询姓名中第2个字母为“i”的学生记录
SELECT * FROM Student WHERE NAME LIKE '_i%';
* 查询姓名中包含“a”字母的学生记录
SELECT * FROM Student WHERE NAME LIKE '%a%';

## 排序
* 查询所有学生记录，按年龄升序排序
SELECT * FROM Student ORDER BY age ASC;
SELECT * FROM Student ORDER BY age; -- 默认升序
* 查询所有学生记录，按年龄降序排序
SELECT * FROM Student ORDER BY age DESC;
* 查询年龄大于10小于30学生记录，按年龄降序排序
SELECT * FROM Student WHERE age>=10 AND age<=30 ORDER BY age;

## count 
* 查询emp表中记录数：
SELECT COUNT(*) c FROM Student;
