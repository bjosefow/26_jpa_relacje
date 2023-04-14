INSERT INTO
    application_user (first_name, last_name, email, password)
VALUES
    -- superadmin@example.com / hard
    ('Jan', 'Kowalski', 'superadmin@example.com', '{bcrypt}$2a$10$Ruu5GtmSVkfLeuGfz/wHUuzflCcMbwJHSBo/.Wui0EM0KIM52Gs2S'),
    -- superadmin2@example.com / hard
    ('Jan', 'Kowalski', 'superadmin2@example.com', '{bcrypt}$2a$10$Ruu5GtmSVkfLeuGfz/wHUuzflCcMbwJHSBo/.Wui0EM0KIM52Gs2S'),
    -- superadmin3@example.com / hard
    ('Jan', 'Kowalski', 'superadmin3@example.com', '{bcrypt}$2a$10$Ruu5GtmSVkfLeuGfz/wHUuzflCcMbwJHSBo/.Wui0EM0KIM52Gs2S'),
    -- john@example.com / dog.8
    ('John', 'Abacki', 'john@example.com', '{MD5}{AlZCLSQMMNLBS5mEO0kSem9V3mxplC6cTjWy9Kj/Gxs=}d9007147eb3a5f727b2665d647d36e35'),
    -- java_lover@example.com / javaiscool
    ('Marek', 'Zalewski', 'java_lover@example.com', '{argon2}$argon2id$v=19$m=4096,t=3,p=1$YBBBwx+kfrNgczYDcLlWYA$LEPgdtfskoobyFtUWTMejaE5SBRyieHYbiE5ZmFKE7I');

INSERT INTO
    user_role (name, description)
VALUES
    ('ADMIN', 'Ma dostęp do wszystkiego'),
    ('USER', 'Dostęp tylko do odczytu');

INSERT INTO
    user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 2),
    (3, 2);

INSERT INTO category
    (name, description, img)
VALUES
    ('sniadaniowe', 'Sniadanie to najwazniejszy posilek w ciagu calego dnia.', '/images/breakfast.png'),
    ('obiadowe', 'Obiad - najbardziej tresciwy posilek w ciagu dnia.', '/images/dinner.png'),
    ('kolacja', 'Kolacja to lekki posilek na zakonczenie dnia.', '/images/supper.png');

INSERT INTO recipe
    (name, description, likes, category_id)
VALUES
    ('nalesniki', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mattis ex erat, at sagittis ipsum commodo eget. Donec malesuada turpis at tellus vehicula posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Etiam dui biam.', 15, 1),
    ('parowki', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mattis ex erat, at sagittis ipsum commodo eget. Donec malesuada turpis at tellus vehicula posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Etiam dui biam.', 2, 1),
    ('pierogi ruskie', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mattis ex erat, at sagittis ipsum commodo eget. Donec malesuada turpis at tellus vehicula posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Etiam dui biam.', 22, 2),
    ('mielone', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mattis ex erat, at sagittis ipsum commodo eget. Donec malesuada turpis at tellus vehicula posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Etiam dui biam.', 18, 2),
    ('salatka z kurczakiem', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mattis ex erat, at sagittis ipsum commodo eget. Donec malesuada turpis at tellus vehicula posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Etiam dui biam.', 7, 3),
    ('salatka warzyna', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla mattis ex erat, at sagittis ipsum commodo eget. Donec malesuada turpis at tellus vehicula posuere. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Suspendisse potenti. Etiam dui biam.', 5, 3);
