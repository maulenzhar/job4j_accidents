
CREATE TABLE accident_types (
                               id SERIAL PRIMARY KEY,
                               name VARCHAR(255) NOT NULL
);

CREATE TABLE rules (
                      id SERIAL PRIMARY KEY,
                      name VARCHAR(255) NOT NULL
);

CREATE TABLE accidents (
                          id SERIAL PRIMARY KEY,
                          name VARCHAR(50) NOT NULL,
                          text TEXT NOT NULL,
                          address VARCHAR(255) NOT NULL,
                          type_id INT NOT NULL,
                          FOREIGN KEY (type_id) REFERENCES accident_types (id)
);

CREATE TABLE accident_rule (
                               accident_id INT NOT NULL,
                               rule_id INT NOT NULL,
                               PRIMARY KEY (accident_id, rule_id),
                               FOREIGN KEY (accident_id) REFERENCES accidents (id),
                               FOREIGN KEY (rule_id) REFERENCES rules (id)
);