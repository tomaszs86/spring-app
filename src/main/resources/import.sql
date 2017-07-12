INSERT INTO languages (id_language, name) VALUES (1, 'en');
INSERT INTO languages (id_language, name) VALUES (2, 'pl');

INSERT INTO users (id_user, language_id, email, password, username, is_enabled, login_attempts, name, surname, is_deleted) VALUES (1, 1, 'test@test.pl', 'admin', 'admin', true, NULL, 'admin', 'admin', false);

INSERT INTO roles (id_role, name) VALUES (1, 'ADMIN');
INSERT INTO roles (id_role, name) VALUES (2, 'USER');

INSERT INTO user_roles (user_id, role_id) VALUES (1, 2);

-- INSERT INTO rights (id_right, name) VALUES (1, 'ADMIN_TEST_LIST');
-- INSERT INTO role_rights (id_role_right, role_id, right_id) VALUES (1, 1, 1);
