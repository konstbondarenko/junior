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
SELECT p.name, p.expiried_data, p.price
FROM product AS p INNER JOIN type AS t
ON t.name = 'Сыр' AND t.id = p.type_id
ORDER BY p.name;

--2. Написать запрос получения всех продуктов, у кого в имени есть слово "Мороженное"
SELECT product.name
FROM product
WHERE product.name LIKE '%Мороженное%';

--3. Написать запрос, который выводит все продукты, срок годности которых заканчивается в следующем месяце.
SELECT p.name, p.expiried_data, p.price
FROM product AS p
WHERE p.expiried_data BETWEEN '2018-10-01' AND '2018-10-30'
ORDER BY p.name, p.expiried_data;

--4. Написать запрос, который вывод самый дорогой продукт.
SELECT p.name, p.expiried_data, p.price
FROM product AS p
WHERE p.price = (SELECT MAX(price) FROM product);

--5. Написать запрос, который выводит количество всех продуктов определенного типа.
SELECT t.name, COUNT(p.id) AS number_of_products
FROM type AS t
INNER JOIN product AS p ON t.id = p.type_id
GROUP BY t.name
ORDER BY t.name;

--6. Написать запрос получение всех продуктов с типом "СЫР" и "МОЛОКО".
SELECT p.name FROM product AS p
INNER JOIN type AS t ON t.name = 'Сыр' AND t.id = p.type_id
OR t.name = 'Молоко' AND t.id = p.type_id
ORDER BY p.name;

--7. Написать запрос, который выводит тип продуктов, которых осталось меньше 10 штук.
SELECT t.name, COUNT(p.id) AS number_of_products
FROM product AS p
INNER JOIN type AS t ON t.id = p.type_id
GROUP BY t.name
HAVING COUNT(p.id) < 10;

--8. Вывести все продукты и их тип.
SELECT p.name, t.name FROM product AS p INNER JOIN type AS t ON p.type_id = t.id;