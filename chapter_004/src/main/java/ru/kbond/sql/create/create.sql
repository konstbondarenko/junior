CREATE DATABASE java_from_a_to_z;

--create role
CREATE TABLE role(
    id SERIAL PRIMARY KEY,
    role_name VARCHAR(1000)
);

--create rules
CREATE TABLE rules(
    id SERIAL PRIMARY KEY,
    rule_name VARCHAR(1000),
    description VARCHAR(2000)
);

--create role_rules
CREATE TABLE role_rules(
    role_id INTEGER REFERENCES role(id),
    rule_id INTEGER REFERENCES rules(id)
);

--create user
CREATE TABLE users(
    id SERIAL PRIMARY KEY,
    login VARCHAR(1000),
    password VARCHAR(1000),
    create_data TIMESTAMP,
    role_id INTEGER REFERENCES role(id)
);

--create category
CREATE TABLE category(
    id SERIAL PRIMARY KEY,
    category_name VARCHAR(100)
);

--create item
CREATE TABLE item(
    id SERIAL PRIMARY KEY,
    item_name VARCHAR(2000),
    category_id INTEGER REFERENCES category(id)
);

--create attaches
CREATE TABLE attaches(
    id SERIAL PRIMARY KEY,
    attaches_name VARCHAR(1000),
    item_id INTEGER REFERENCES item(id)
);

--create comments
CREATE TABLE comments(
    id SERIAL PRIMARY KEY,
    comment VARCHAR(2000),
    item_id INTEGER REFERENCES item(id)
);

--create state
CREATE TABLE state(
    id SERIAL PRIMARY KEY,
    state_name VARCHAR(100)
);

--create orders
CREATE TABLE orders(
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    state_id INTEGER REFERENCES state(id),
    order_date TIMESTAMP,
    amount NUMERIC
);

--create items_orders
CREATE TABLE items_orders(
    order_id INTEGER REFERENCES orders(id),
    item_id INTEGER REFERENCES item(id)
);

--добавление ролей
INSERT INTO role(role_name) VALUES('admin');
INSERT INTO role(role_name) VALUES('user');

--добавление правил
INSERT INTO rules(rule_name) VALUES('Добавить предмет в заявку');
INSERT INTO rules(rule_name) VALUES('Удалить предмет из заявки');
INSERT INTO rules(rule_name) VALUES('Удалить заявку');
INSERT INTO rules(rule_name) VALUES('Добавить комментарий');
INSERT INTO rules(rule_name) VALUES('Добавить файл к предмету');
INSERT INTO rules(rule_name) VALUES('Создать категорию');
INSERT INTO rules(rule_name) VALUES('Изменить категорию');
INSERT INTO rules(rule_name) VALUES('Удалить категорию');
INSERT INTO rules(rule_name) VALUES('Добавить предмет в категорию');

--правила для ролей
INSERT INTO role_rules(role_id, rule_id) VALUES(1, 1);
INSERT INTO role_rules(role_id, rule_id) VALUES(1, 2);
INSERT INTO role_rules(role_id, rule_id) VALUES(1, 3);
INSERT INTO role_rules(role_id, rule_id) VALUES(1, 4);
INSERT INTO role_rules(role_id, rule_id) VALUES(1, 5);
INSERT INTO role_rules(role_id, rule_id) VALUES(1, 6);
INSERT INTO role_rules(role_id, rule_id) VALUES(1, 7);
INSERT INTO role_rules(role_id, rule_id) VALUES(1, 8);
INSERT INTO role_rules(role_id, rule_id) VALUES(2, 1);
INSERT INTO role_rules(role_id, rule_id) VALUES(2, 2);
INSERT INTO role_rules(role_id, rule_id) VALUES(2, 3);
INSERT INTO role_rules(role_id, rule_id) VALUES(2, 4);

--пользователи
INSERT INTO users(login, password, role_id) VALUES('Fedor', '123', 1);
INSERT INTO users(login, password, role_id) VALUES('Alexandr', '123asd', 2);

--категории
INSERT INTO category(category_name) VALUES('Автомобили');
INSERT INTO category(category_name) VALUES('IT');
INSERT INTO category(category_name) VALUES('Одежда');
INSERT INTO category(category_name) VALUES('Книги');

--предметы
INSERT INTO item(item_name, category_id) VALUES('Ауди ТТ', 1);
INSERT INTO item(item_name, category_id) VALUES('Ноутбук', 2);
INSERT INTO item(item_name, category_id) VALUES('Свитер', 3);
INSERT INTO item(item_name, category_id) VALUES('Изучаем Java', 4);

--вложения
--отстутствуют

--состояние заявки
INSERT INTO state(state_name) VALUES('Новая');
INSERT INTO state(state_name) VALUES('Ожидает подтверждения');
INSERT INTO state(state_name) VALUES('Исполнена');

--заказ
INSERT INTO orders(user_id, state_id, order_date, amount) VALUES(1, 1, '2018-01-08 04:05:06', 500);
INSERT INTO orders(user_id, state_id, order_date, amount) VALUES(1, 3, '2018-07-12 13:15:21', 100);
INSERT INTO orders(user_id, state_id, order_date, amount) VALUES(2, 2, '2017-05-07 11:28:14', 1500);

--товары в заказах
INSERT INTO items_orders(order_id, item_id) VALUES(1, 2);
INSERT INTO items_orders(order_id, item_id) VALUES(1, 3);
INSERT INTO items_orders(order_id, item_id) VALUES(1, 4);
INSERT INTO items_orders(order_id, item_id) VALUES(2, 1);
INSERT INTO items_orders(order_id, item_id) VALUES(2, 4);