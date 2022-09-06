DROP DATABASE IF EXISTS exampledb;
CREATE DATABASE exampledb;

DROP TABLE IF EXISTS schema1.book;
CREATE TABLE schema1.book (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000), price INT, title VARCHAR(45));

INSERT INTO schema1.book (price, title) VALUES (15, 'Un livre à 15€');
INSERT INTO schema1.book (price, title) VALUES (18, 'Un livre à 18€');
INSERT INTO schema1.book (price, title) VALUES (20, 'Un livre à 20€');
INSERT INTO schema1.book (price, title) VALUES (14, 'Un livre à 14€');

DROP TABLE IF EXISTS schema1.page;
CREATE TABLE schema1.page (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000), book_id INT REFERENCES schema1.book(id), number INT, firstword VARCHAR(45));

INSERT INTO schema1.page (book_id, number, firstword) VALUES (1000, 1, 'Premier mot de la page 1 du livre 1');
INSERT INTO schema1.page (book_id, number, firstword) VALUES (1001, 2, 'Premier mot de la page 2 du livre 2');
INSERT INTO schema1.page (book_id, number, firstword) VALUES (1002, 3, 'Premier mot de la page 3 du livre 3');
INSERT INTO schema1.page (book_id, number, firstword) VALUES (1003, 4, 'Premier mot de la page 4 du livre 4');

DROP TABLE IF EXISTS schema1.customer;
CREATE TABLE schema1.customer (id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000), name VARCHAR(45));

INSERT INTO schema1.customer (name) VALUES ('Customer 1');
INSERT INTO schema1.customer (name) VALUES ('Customer 2');
INSERT INTO schema1.customer (name) VALUES ('Customer 3');
INSERT INTO schema1.customer (name) VALUES ('Customer 4');

DROP TABLE IF EXISTS schema1.buy;
CREATE TABLE schema1.buy (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000),
    customer_id INT REFERENCES schema1.customer(id),
    buyingnumber INT,
    buyingdate DATE
 );

INSERT INTO schema1.buy (book_id, customer_id, buyingnumber, buyingdate) VALUES (1000, 1000, '2020-07-01');
INSERT INTO schema1.buy (book_id, customer_id, buyingnumber, buyingdate) VALUES (1000, 1000, '2020-07-01');
INSERT INTO schema1.buy (book_id, customer_id, buyingnumber, buyingdate) VALUES (1000, 1000, '2020-07-01');
INSERT INTO schema1.buy (book_id, customer_id, buyingnumber, buyingdate) VALUES (1001, 1001, '2022-08-23');
INSERT INTO schema1.buy (book_id, customer_id, buyingnumber, buyingdate) VALUES (1002, 1003, '2021-10-02');
INSERT INTO schema1.buy (book_id, customer_id, buyingnumber, buyingdate) VALUES (1003, 1002, '2022-06-15');

DROP TABLE IF EXISTS schema1.buy_book;
CREATE TABLE schema1.buy_book (book_id INT REFERENCES schema1.book(id), buy_id INT REFERENCES schema1.buy(id));

INSERT INTO schema1.buy_book (book_id, buy_id) VALUES (1000, 1000);
INSERT INTO schema1.buy_book (book_id, buy_id) VALUES (1001, 1000);
INSERT INTO schema1.buy_book (book_id, buy_id) VALUES (1002, 1001);
INSERT INTO schema1.buy_book (book_id, buy_id) VALUES (1002, 1001);
INSERT INTO schema1.buy_book (book_id, buy_id) VALUES (1003, 1003);
INSERT INTO schema1.buy_book (book_id, buy_id) VALUES (1003, 1002);