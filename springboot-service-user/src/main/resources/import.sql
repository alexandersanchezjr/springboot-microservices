INSERT INTO users (username, password, enabled, name, lastname, email) VALUES ('admin', '$2a$10$3', true, 'Admin', 'Admin', 'admin@admin.com');
INSERT INTO users (username, password, enabled, name, lastname, email) VALUES ('user', '$2a$10$3', true, 'User', 'User', 'user@user.com');
INSERT INTO users (username, password, enabled, name, lastname, email) VALUES ('manager', '$2a$10$3', true, 'Manager', 'Manager', 'manager@manager.com');

INSERT INTO roles (name) VALUES ('ROLE_ADMIN');
INSERT INTO roles (name) VALUES ('ROLE_USER');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (3, 2);