CREATE DATABASE tracker;

CREATE TABLE IF NOT EXISTS items (
  item_id serial PRIMARY KEY,
  name VARCHAR(255),
  description VARCHAR(2000),
  create_date TIMESTAMP
);

CREATE TABLE IF NOT EXISTS comments (
  comment_id serial PRIMARY KEY,
  comment TEXT,
  item_id INTEGER REFERENCES items(item_id)
);