--DROP DATABASE IF EXISTS exampledb;
--CREATE DATABASE exampledb;

DROP TABLE IF EXISTS schema1.page;
DROP TABLE IF EXISTS schema1.book;
DROP TABLE IF EXISTS schema1.game;
DROP TABLE IF EXISTS schema1.offer_article;
DROP TABLE IF EXISTS schema1.offer;
DROP TABLE IF EXISTS schema1.article;
DROP TABLE IF EXISTS schema1.purchase;
DROP TABLE IF EXISTS schema1.customer;
DROP TABLE IF EXISTS schema1.rnumber;


DROP TABLE IF EXISTS schema1.rnumber;
CREATE TABLE schema1.rnumber (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 7000),
    isUsed BIT);--

--books ref
INSERT INTO schema1.rnumber (isUsed) VALUES ('1');-- (id) VALUES (1000);
INSERT INTO schema1.rnumber (isUsed) VALUES ('1');-- (id) VALUES (1001);
INSERT INTO schema1.rnumber (isUsed) VALUES ('1');-- (id) VALUES (1002);
INSERT INTO schema1.rnumber (isUsed) VALUES ('1');-- (id) VALUES (1003);

--games ref
INSERT INTO schema1.rnumber (isUsed) VALUES ('1');-- (id) VALUES (1004);
INSERT INTO schema1.rnumber (isUsed) VALUES ('1');-- (id) VALUES (1005);
INSERT INTO schema1.rnumber (isUsed) VALUES ('1');-- (id) VALUES (1006);
INSERT INTO schema1.rnumber (isUsed) VALUES ('1');-- (id) VALUES (1007);


CREATE TABLE schema1.book (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1000),
    price INT,
    title VARCHAR(100),
    idRnumber INT REFERENCES schema1.rnumber(id) );

INSERT INTO schema1.book (idRnumber, price, title) VALUES (7000, 15, 'Un livre à 15€ avec 1 page');
UPDATE schema1.rnumber SET isUsed = '0' WHERE id = 7000;

INSERT INTO schema1.book (idRnumber, price, title) VALUES (7001, 18, 'Un livre à 18€ avec 0 page');
UPDATE schema1.rnumber SET isUsed = '0' WHERE id = 7001;

INSERT INTO schema1.book (idRnumber, price, title) VALUES (7002, 20, 'Un livre à 20€ avec 5 page');
UPDATE schema1.rnumber SET isUsed = '0' WHERE id = 7002;

INSERT INTO schema1.book (idRnumber, price, title) VALUES (7003, 14, 'Un livre à 14€');
UPDATE schema1.rnumber SET isUsed = '0' WHERE id = 7003;


CREATE TABLE schema1.page (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 2000),
    idBook INT REFERENCES schema1.book(id),
    number INT,
    firstword VARCHAR(150) );

INSERT INTO schema1.page (idBook, number, firstword) VALUES (1001, 1, 'Première page du livre 1');
INSERT INTO schema1.page (idBook, number, firstword) VALUES (1002, 2, 'Première page du livre 3');
INSERT INTO schema1.page (idBook, number, firstword) VALUES (1002, 3, 'Deuxième page du livre 3');
INSERT INTO schema1.page (idBook, number, firstword) VALUES (1002, 3, 'Troixième page du livre 3');
INSERT INTO schema1.page (idBook, number, firstword) VALUES (1002, 3, 'Quatrième page du livre 3');
INSERT INTO schema1.page (idBook, number, firstword) VALUES (1002, 3, 'Cinquième page du livre 3');
INSERT INTO schema1.page (idBook, number, firstword) VALUES (1003, 4, 'Première page du livre 4');


DROP TABLE IF EXISTS schema1.game;
CREATE TABLE schema1.game (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 3000),
    price INT,
    title VARCHAR(100),
    idRnumber INT REFERENCES schema1.rnumber(id) );

INSERT INTO schema1.game (idRnumber, price, title) VALUES (7004, 55, 'Un jeu à 55€');
UPDATE schema1.rnumber SET isUsed = '0' WHERE id = 7004;

INSERT INTO schema1.game (idRnumber, price, title) VALUES (7005, 58, 'Un jeu à 58€');
UPDATE schema1.rnumber SET isUsed = '0' WHERE id = 7005;

INSERT INTO schema1.game (idRnumber, price, title) VALUES (7006, 70, 'Un jeu à 70€');
UPDATE schema1.rnumber SET isUsed = '0' WHERE id = 7006;

INSERT INTO schema1.game (idRnumber, price, title) VALUES (7007, 54, 'Un jeu à 54€');
UPDATE schema1.rnumber SET isUsed = '0' WHERE id = 7007;


CREATE TABLE schema1.customer (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 4000),
    name VARCHAR(45) );

INSERT INTO schema1.customer (name) VALUES ('Customer 1');
INSERT INTO schema1.customer (name) VALUES ('Customer 2');
INSERT INTO schema1.customer (name) VALUES ('Customer 3');
INSERT INTO schema1.customer (name) VALUES ('Customer 4');


CREATE TABLE schema1.purchase (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 5000),
    idCustomer INT REFERENCES schema1.customer(id),
    creationDate DATE,
    validationDate DATE );

INSERT INTO schema1.purchase (idCustomer, creationDate, validationDate) VALUES (4000, '2020-06-29', '2020-06-30');
INSERT INTO schema1.purchase (idCustomer, creationDate, validationDate) VALUES (4000, '2020-07-01', '2020-07-01');
INSERT INTO schema1.purchase (idCustomer, creationDate) VALUES (4000, '2020-07-01');
INSERT INTO schema1.purchase (idCustomer, creationDate) VALUES (4001, '2022-08-23');
INSERT INTO schema1.purchase (idCustomer, creationDate) VALUES (4002, '2021-10-02');
INSERT INTO schema1.purchase (idCustomer, creationDate, validationDate) VALUES (4003, '2022-06-15', '2022-06-16');


DROP TABLE IF EXISTS schema1.article;
CREATE TABLE schema1.article (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 6000),
    idPurchase INT REFERENCES schema1.purchase(id),
    idRnumber INT REFERENCES schema1.rnumber(id),
    --CONSTRAINT PK_PURCHASES_RNUMBERS Primary Key (idPurchase, idRnumber),
    quantity INT );

ALTER TABLE schema1.article
  ADD CONSTRAINT uq_article UNIQUE(idPurchase, idRnumber);

INSERT INTO schema1.article (idRnumber, idPurchase, quantity) VALUES (7000, 5000, 1);
INSERT INTO schema1.article (idRnumber, idPurchase, quantity) VALUES (7001, 5001, 1);
INSERT INTO schema1.article (idRnumber, idPurchase, quantity) VALUES (7002, 5001, 2);
--INSERT INTO schema1.article (idRnumber, idPurchase, quantity) VALUES (7002, 5001, 1);--triggers correctly an error
INSERT INTO schema1.article (idRnumber, idPurchase, quantity) VALUES (7003, 5002, 3);
INSERT INTO schema1.article (idRnumber, idPurchase, quantity) VALUES (7000, 5003, 2);
INSERT INTO schema1.article (idRnumber, idPurchase, quantity) VALUES (7003, 5004, 1);
INSERT INTO schema1.article (idRnumber, idPurchase, quantity) VALUES (7003, 5005, 1);


DROP TABLE IF EXISTS schema1.offer;
CREATE TABLE schema1.offer (
    id INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 8000),
    label VARCHAR(150) ,
    offerValue INT,
    startValidationDate DATE,
    endValidationDate DATE );

INSERT INTO schema1.offer (label, offerValue, startValidationDate, endValidationDate) VALUES ('Soldes de printemps', 15, '2020-04-15', '2020-06-14');
INSERT INTO schema1.offer (label, offerValue, startValidationDate, endValidationDate) VALUES ('Soldes d automne', 10, '2020-09-20', '2020-12-19');
INSERT INTO schema1.offer (label, offerValue, startValidationDate, endValidationDate) VALUES ('Soldes de printemps 2023', 12, '2023-04-15', '2023-07-14');


DROP TABLE IF EXISTS schema1.offer_article;
CREATE TABLE schema1.offer_article (
    idArticle INT UNIQUE REFERENCES schema1.article(id),
    idOffer INT REFERENCES schema1.offer(id) );

INSERT INTO schema1.offer_article (idArticle, idOffer) VALUES (6000, 8000);
--INSERT INTO schema1.offer_article (idArticle, idOffer) VALUES (6000, 8001);--triggers correctly an error
INSERT INTO schema1.offer_article (idArticle, idOffer) VALUES (6001, 8001);
INSERT INTO schema1.offer_article (idArticle, idOffer) VALUES (6003, 8002);
INSERT INTO schema1.offer_article (idArticle, idOffer) VALUES (6005, 8000);