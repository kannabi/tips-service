ALTER TABLE restaurants ADD COLUMN email TEXT;
ALTER TABLE restaurants ADD COLUMN login TEXT;
ALTER TABLE restaurants ADD COLUMN account_bill TEXT;

ALTER TABLE waiters RENAME COLUMN name TO first_name;
ALTER TABLE waiters ADD COLUMN second_name TEXT;
ALTER TABLE waiters ADD COLUMN third_name TEXT;
ALTER TABLE waiters ADD COLUMN email TEXT;