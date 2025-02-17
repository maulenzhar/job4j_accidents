INSERT INTO accident_types (name) VALUES ('Дорожная авария'), ('Пожар'), ('Угон автомобиля');

INSERT INTO rules (name) VALUES ('Нарушение скорости'), ('Проезд на красный'), ('Отсутствие страховки');

INSERT INTO accidents (name, text, address, type_id)
VALUES ('Авария 1', 'Описание аварии 1', 'Улица 1', 1);

INSERT INTO accident_rule (accident_id, rule_id) VALUES (1, 1), (1, 2);