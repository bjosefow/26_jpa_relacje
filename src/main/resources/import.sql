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
