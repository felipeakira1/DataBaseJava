CREATE TABLE Customer (
    Id INT,
    name VARCHAR(255),
    city VARCHAR(64),
    state VARCHAR(64),
    PRIMARY KEY (Id)
);

CREATE TABLE Orders (
    number INT,
    customerId INT NOT NULL,
    description VARCHAR(255),
    price DECIMAL(10,2),
    PRIMARY KEY (number),
    FOREIGN KEY (customerId) REFERENCES Customer(Id)
);