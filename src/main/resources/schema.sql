CREATE TABLE IF NOT EXISTS users
(id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
email varchar(320),
 first_name varchar(100),
 last_name varchar(100),
 registration_date timestamp,
 state varchar(50));

CREATE TABLE IF NOT EXISTS items
(id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
 user_id BIGINT,
 normal_url VARCHAR(100),
 resolved_url VARCHAR(100),
 title VARCHAR(100),
 mime_type VARCHAR(100),
 has_image BOOLEAN,
 has_video BOOLEAN,
 date_resolved TIMESTAMP,
 unread BOOLEAN,
 CONSTRAINT fk_items_to_users FOREIGN KEY(user_id) REFERENCES users(id), UNIQUE(id, resolved_url));

CREATE TABLE IF NOT EXISTS tags
(id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
 item_id BIGINT,
 name VARCHAR(50),
 CONSTRAINT fk_tags_to_items FOREIGN KEY(item_id) REFERENCES items(id));

CREATE TABLE IF NOT EXISTS item_notes
(id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
 note_text varchar(2000),
 item_id BIGINT,
 creation_date timestamp,
 CONSTRAINT fk_item_notes_to_items FOREIGN KEY(item_id) REFERENCES items(id));
