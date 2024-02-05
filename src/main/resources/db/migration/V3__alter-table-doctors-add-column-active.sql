ALTER TABLE doctors ADD COLUMN active TINYINT(1);
UPDATE doctors SET active = 1;
ALTER TABLE doctors MODIFY  active TINYINT(1) NOT NULL;