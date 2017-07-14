INSERT INTO languages (id_language, name) VALUES (1, 'en');
INSERT INTO languages (id_language, name) VALUES (2, 'pl');

INSERT INTO users (id_user, language_id, email, password, username, is_enabled, login_attempts, name, surname, is_deleted) VALUES (1, 1, 'test@test.pl', '$2a$10$T4tyiP3ifWavQjM.7K7dpOAAkZgbcJmj2A2cca0CGGKI7mZysd0DS', 'admin', true, NULL, 'admin', 'admin', false);

INSERT INTO roles (id_role, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);

 INSERT INTO rights (id_right, name) VALUES (1, 'ADMIN_TEST_LIST');
 INSERT INTO role_rights (role_id, right_id) VALUES (2, 1);
