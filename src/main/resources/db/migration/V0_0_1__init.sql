CREATE TABLE restaurants (
  id TEXT PRIMARY KEY,
  name TEXT NOT NULL,
  password TEXT NOT NULL
);

CREATE TABLE waiters (
  id TEXT PRIMARY KEY,
  name TEXT NOT NULL,
  code TEXT NOT NULL,
  account_bill TEXT NOT NULL,
  restaurant_id TEXT NOT NULL REFERENCES restaurants(id)
);

CREATE TABLE tips (
  time BIGINT NOT NULL PRIMARY KEY,
  sum NUMERIC NOT NULL,
  waiter_id TEXT NOT NULL REFERENCES waiters(id)
);

