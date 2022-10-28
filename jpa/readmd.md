//https://incheol-jung.gitbook.io/docs/study/jpa/16
//https://4whomtbts.tistory.com/118
//https://medium.com/javarevisited/spring-beans-in-depth-a6d8b31db8a1
//https://www.eginnovations.com/blog/tomcat-monitoring-metrics/
//https://codedragon.tistory.com/3529


INSERT INTO PUBLIC.PRODUCT (CONTENT, CURRENT_AMOUNT, TITLE, TOTAL_AMOUNT, STATE, version)
VALUES ('test', 0, 'test', 100000, 'PROGRESS', 0)


SELECT COUNT(*)
FROM PRODUCT_USER
-- order by USER_ID ASC ;


SELECT SUM(AMOUNT)
FROM PRODUCT_USER
where PRODUCT_ID = 1;

SELECT count(*)
FROM PRODUCT_USER
where PRODUCT_ID = 3;

SELECT p.id,
p.CURRENT_AMOUNT,
(
SELECT count(*)
FROM PRODUCT_USER
where PRODUCT_ID = p.ID
) as userCount
,
p.TOTAL_AMOUNT,
STATE
FROM PRODUCT p;

SELECT *
FROM PRODUCT;

select 3500 / 100;

select 500 / 100
;

select 1000 * 100
;


//https://ljtaek2.tistory.com/142
