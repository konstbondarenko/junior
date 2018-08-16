CREATE DATABASE java_from_a_to_z;

--create role
create table role (
    id serial primary key,
    role_name varchar (1000)
);

--create rules
create table rules (
    id serial primary key,
    rule_name varchar (1000),
    description varchar (2000)
);

--create role_rules
create table role_rules (
    role_id integer references role(id),
    rule_id integer references rules(id)
);

--create user
create table users (
    id serial primary key,
    login varchar (1000),
    password varchar (1000),
    create_data timestamp,
    role_id integer references role(id)
);

--create category
create table category (
    id serial primary key,
    category_name varchar (100)
);

--create item
create table item (
    id serial primary key,
    item_name varchar (2000),
    category_id integer references category(id)
);

--create attaches
create table attaches (
    id serial primary key,
    attaches_name varchar (1000),
    item_id integer references item(id)
);

--create comments
create table comments (
    id serial primary key,
    comment varchar (2000),
    item_id integer references item(id)
);

--create state
create table state (
    id serial primary key,
    state_name varchar (100)
);

--create orders
create table orders (
    id serial primary key,
    user_id integer references users(id),
    state_id integer references state(id),
    order_date timestamp,
    amount numeric
);

--create items_orders
create table items_orders (
    order_id integer references orders(id),
    item_id integer references item(id)
);

--добавление ролей
insert into role(role_name) values('admin');
insert into role(role_name) values('user');

--добавление правил
insert into rules(rule_name) values('Добавить предмет в заявку');
insert into rules(rule_name) values('Удалить предмет из заявки');
insert into rules(rule_name) values('Удалить заявку');
insert into rules(rule_name) values('Добавить комментарий');
insert into rules(rule_name) values('Добавить файл к предмету');
insert into rules(rule_name) values('Создать категорию');
insert into rules(rule_name) values('Изменить категорию');
insert into rules(rule_name) values('Удалить категорию');
insert into rules(rule_name) values('Добавить предмет в категорию');

--правила для ролей
insert into role_rules(role_id, rule_id) values(1, 1);
insert into role_rules(role_id, rule_id) values(1, 2);
insert into role_rules(role_id, rule_id) values(1, 3);
insert into role_rules(role_id, rule_id) values(1, 4);
insert into role_rules(role_id, rule_id) values(1, 5);
insert into role_rules(role_id, rule_id) values(1, 6);
insert into role_rules(role_id, rule_id) values(1, 7);
insert into role_rules(role_id, rule_id) values(1, 8);
insert into role_rules(role_id, rule_id) values(2, 1);
insert into role_rules(role_id, rule_id) values(2, 2);
insert into role_rules(role_id, rule_id) values(2, 3);
insert into role_rules(role_id, rule_id) values(2, 4);

--пользователи
insert into users(login, password, role_id) values('Fedor', '123', 1);
insert into users(login, password, role_id) values('Alexandr', '123asd', 2);

--категории
insert into category(category_name) values('Автомобили');
insert into category(category_name) values('IT');
insert into category(category_name) values('Одежда');
insert into category(category_name) values('Книги');

--предметы
insert into item(item_name, category_id) values('Ауди ТТ', 1);
insert into item(item_name, category_id) values('Ноутбук', 2);
insert into item(item_name, category_id) values('Свитер', 3);
insert into item(item_name, category_id) values('Изучаем Java', 4);

--вложения
--отстутствуют

--состояние заявки
insert into state(state_name) values('Новая');
insert into state(state_name) values('Ожидает подтверждения');
insert into state(state_name) values('Исполнена');

--заказ
insert into orders(user_id, state_id, order_date, amount) values(1, 1, '2018-01-08 04:05:06', 500);
insert into orders(user_id, state_id, order_date, amount) values(1, 3, '2018-07-12 13:15:21', 100);
insert into orders(user_id, state_id, order_date, amount) values(2, 2, '2017-05-07 11:28:14', 1500);

--товары в заказах
insert into items_orders(order_id, item_id) values(1, 2);
insert into items_orders(order_id, item_id) values(1, 3);
insert into items_orders(order_id, item_id) values(1, 4);
insert into items_orders(order_id, item_id) values(2, 1);
insert into items_orders(order_id, item_id) values(2, 4);