INSERT INTO users (id, email, first_name, last_name, password, logo_url)
VALUES (1, 'admin@mail.com', 'admin', 'admin', '$2a$10$i5a2We9cZIP6D6EXRkCihuRjtkTvtLvghNY0wkAfvLM6lk/.WMyb2', 'admin.jpg');


INSERT INTO roles(id, name)
VALUES (1, 'user'),
       (2, 'admin');
-- SELECT setval('roles_id_seq', 2);

INSERT INTO user_has_roles (user_id, role_id)
VALUES (1, 2);

ALTER SEQUENCE SEQ_USER RESTART WITH 2;