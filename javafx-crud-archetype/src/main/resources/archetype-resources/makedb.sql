CREATE TABLE Users
(
    username TEXT PRIMARY KEY NOT NULL,
    password TEXT NOT NULL,
    name TEXT,
    gender TEXT,
    birth DATETIME
);