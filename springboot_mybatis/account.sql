CREATE TABLE accounts
(
    account_number VARCHAR(50)    NOT NULL PRIMARY KEY,
    balance        DECIMAL(15, 2) NOT NULL,
    firstname      VARCHAR(50),
    lastname       VARCHAR(50),
    age            INT,
    gender         VARCHAR(10),
    address        VARCHAR(255),
    employer       VARCHAR(100),
    email          VARCHAR(100),
    city           VARCHAR(50),
    state          VARCHAR(50),
    create_time    TIMESTAMP  DEFAULT CURRENT_TIMESTAMP,
    update_time    TIMESTAMP  DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    del_flag       VARCHAR(1) DEFAULT '0'
);
