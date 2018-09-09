CREATE DATABASE car_storage;

CREATE TABLE engine(
    id SERIAL PRIMARY KEY,
    type VARCHAR(500)
);

CREATE TABLE transmission(
    id SERIAL PRIMARY KEY,
    type VARCHAR(500)
);

CREATE TABLE car_body(
    id SERIAL PRIMARY KEY,
    type VARCHAR(500)
);

CREATE TABLE car(
    id SERIAL PRIMARY KEY,
    model VARCHAR(500),
    engine_id INTEGER REFERENCES engine(id),
    transmission_id INTEGER REFERENCES transmission(id),
    car_body_id INTEGER REFERENCES car_body(id)
);

--engine
INSERT INTO engine(type) VALUES('engine 1.6');
INSERT INTO engine(type) VALUES('engine 1.8');
INSERT INTO engine(type) VALUES('engine 2.0');
INSERT INTO engine(type) VALUES('engine 2.4');
INSERT INTO engine(type) VALUES('engine 3.2');

--transmission
INSERT INTO transmission(type) VALUES('automatic transmission');
INSERT INTO transmission(type) VALUES('manual transmission');

--car body
INSERT INTO car_body(type) VALUES('sedan');
INSERT INTO car_body(type) VALUES('hatchback');
INSERT INTO car_body(type) VALUES('wagon');

--car
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Toyota Camry', 4, 2, 1);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Toyota Camry', 4, 1, 1);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Toyota Camry', 5, 1, 1);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Mazda 3', 3, 2, 1);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Mazda 3', 3, 2, 2);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Mazda 3', 3, 1, 1);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Mazda 3', 3, 1, 2);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Lada Priora', 1, 2, 1);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Lada Priora', 1, 2, 2);
INSERT INTO car(model, engine_id, transmission_id, car_body_id) VALUES('Lada Priora', 1, 1, 3);

--1. Вывести список всех машин и все привязанные к ним детали.
SELECT c.model, e.type, t.type, cb.type FROM car AS c
INNER JOIN engine AS e ON c.engine_id = e.id
INNER JOIN transmission AS t ON c.transmission_id = t.id
INNER JOIN car_body AS cb ON c.car_body_id = cb.id;

--2. Вывести отдельно детали, которые не используются в машине, кузова, двигатели, коробки передач.

--Выводит тип неиспользуемого двигателя.
SELECT e.type FROM engine AS e
LEFT OUTER JOIN car ON car.engine_id = e.id
WHERE car.engine_id IS NULL;

--Выводит тип неиспользуемой трансмиссии.
SELECT t.type FROM transmission AS t
LEFT OUTER JOIN car ON car.transmission_id = t.id
WHERE car.engine_id IS NULL;

--Выводит тип неиспользуемого кузова.
SELECT cb.type FROM car_body AS cb
LEFT OUTER JOIN car ON car.car_body_id = cb.id
WHERE car.car_body_id IS NULL;