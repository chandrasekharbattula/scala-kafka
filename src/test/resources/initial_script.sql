DROP TABLE test5 IF EXISTS;
CREATE TABLE test5 AS
SELECT * FROM CSVREAD('./src/test/resources/test.csv')