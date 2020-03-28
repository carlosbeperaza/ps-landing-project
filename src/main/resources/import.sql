
/* Creamos algunos usuarios con sus roles */
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email, create_date) VALUES ('andres','$2a$10$C3Uln5uqnzx/GswADURJGOIdBqYrly9731fnwKDaUdBkt/M3qvtLq',1, 'Andres', 'Guzman','profesor@bolsadeideas.com', NOW());
INSERT INTO `usuarios` (username, password, enabled, nombre, apellido, email, create_date) VALUES ('admin','$2a$10$RmdEsvEfhI7Rcm9f/uZXPebZVCcPC7ZXZwV51efAvMAp1rIaRAfPK',1, 'John', 'Doe','jhon.doe@bolsadeideas.com', NOW());

INSERT INTO `roles` (nombre, status, create_date) VALUES ('User', 1, NOW());
INSERT INTO `roles` (nombre, status, create_date) VALUES ('Admin', 1, NOW());

INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (2, 2);
INSERT INTO `usuarios_roles` (usuario_id, role_id) VALUES (1, 1);

INSERT INTO `modulos` (nombre, uiref , order_, icon, status, create_date) VALUES ('Inicio', 'app.inicio', 1, 'home', 1, NOW());
INSERT INTO `modulos` (nombre, uiref , order_, icon, status, create_date) VALUES ('Perfil', 'app.profile', 2, 'user', 1, NOW());

INSERT INTO `roles_modulos` (modulo_id, role_id) VALUES (1, 2);
INSERT INTO `roles_modulos` (modulo_id, role_id) VALUES (2, 2);