INSERT INTO testreloadly.public.role (id, name) VALUES (1, 'ROLE_ADMIN') ON CONFLICT (id) DO NOTHING;
INSERT INTO testreloadly.public.role (id, name) VALUES (2, 'ROLE_MODERATOR') ON CONFLICT (id) DO NOTHING;
INSERT INTO testreloadly.public.role (id, name) VALUES (3, 'ROLE_USER') ON CONFLICT (id) DO NOTHING;