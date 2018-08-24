CREATE DATABASE store;

CREATE TABLE type(
    id serial PRIMARY KEY,
    name VARCHAR(1000)
);
--
CREATE TABLE product(
    id serial PRIMARY KEY,
    name VARCHAR(1000),
    type_id INTEGER REFERENCES type(id),
    expiried_data DATE,
    price INTEGER
);

--
INSERT INTO type(name) VALUES('Сыр');
INSERT INTO type(name) VALUES('Молоко');
INSERT INTO type(name) VALUES('Мороженное');
INSERT INTO type(name) VALUES('Колбаса');

--
INSERT INTO product(name, type_id, expiried_data, price) VALUES('Сыр Росийский', 1, '2018-06-08', 100);
INSERT INTO product(name, type_id, expiried_data, price) VALUES('Сыр Дорблю', 1, '2018-05-09', 300);
INSERT INTO product(name, type_id, expiried_data, price) VALUES('Молоко Останконское', 2, '2018-05-10', 100);
INSERT INTO product(name, type_id, expiried_data, price) VALUES('Молоко Старожиловское', 2, '2018-06-10', 120);
INSERT INTO product(name, type_id, expiried_data, price) VALUES('Мороженное Ленинградское', 3, '2018-07-12', 50);
INSERT INTO product(name, type_id, expiried_data, price) VALUES('Мороженное Семейное', 3, '2018-08-01', 70);
INSERT INTO product(name, type_id, expiried_data, price) VALUES('Колбаса Докторская', 4, '2018-10-10', 400);

--1. Написать запрос получение всех продуктов с типом "СЫР"
SELECT * FROM product WHERE product.type_id = 1;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "Мороженное"
SELECT product.name FROM product WHERE product.name LIKE '%Мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT * FROM product WHERE product.expiried_data BETWEEN '2018-10-01' AND '2018-10-30';

--4. Написать запрос, который вывод самый дорогой продукт.
SELECT MAX(price) FROM product;

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, (SELECT COUNT(p.id) FROM product AS p WHERE p.type_id = t.id) FROM type AS t;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
SELECT p.name FROM product AS p WHERE p.type_id IN(1,2);

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT * FROM type AS t WHERE ((SELECT COUNT(p.id) FROM product AS p WHERE p.type_id = t.id) < 10);

--8. Вывести все продукты и их тип.
SELECT p.name, t.name FROM product AS p INNER JOIN type AS t ON p.type_id = t.id;