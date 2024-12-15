INSERT INTO roles (description, is_active, name, tenant_id) VALUES
('Admin role', true, 'ADMIN', 'startalent'),
('User role', true, 'USER', 'startalent');

INSERT INTO accounts (name, domain, branding_config, is_active, created_at, updated_at, account_type, tenant_id) VALUES
('Account1', 'domain1.com', '{"theme":"dark"}', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'type1', 'startalent'),
('Account2', 'domain2.com', '{"theme":"light"}', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'type2', 'startalent');

INSERT INTO roles (name, description, is_active, tenant_id) VALUES
('ADMIN', 'Admin role', true, 'startalent'),
('USER', 'User role', true, 'startalent');

INSERT INTO users (email, password_hash, first_name, last_name, is_active, created_at, updated_at, tenant_id) VALUES
('lgzarturo@gmail.com', 'hashedpassword', 'Admin', 'User', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'startalent'),
('Alberto@gmail.com', 'hashedpassword', 'Regular', 'User', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 'startalent');

INSERT INTO user_roles (user_id, role_id, assigned_at, updated_at, is_active, tenant_id) VALUES
(1, 1, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'startalent'),
(2, 2, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, true, 'startalent');

INSERT INTO jobs (name, description, is_active, created_at, updated_at, account_id, tenant_id) VALUES
('Job1', 'Description for Job1', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 1, 'startalent'),
('Job2', 'Description for Job2', true, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP, 2, 'startalent');
