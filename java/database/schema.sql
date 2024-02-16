BEGIN TRANSACTION;

DROP TABLE IF EXISTS users;

CREATE TABLE users (
	user_id SERIAL,
	username varchar(50) NOT NULL UNIQUE,
	password_hash varchar(200) NOT NULL,
	role varchar(50) NOT NULL,
	CONSTRAINT PK_user PRIMARY KEY (user_id)
);

CREATE TABLE saved_wines (
    wine_id SERIAL,
    user_id INT NOT NULL,
    title varchar(100) NOT NULL,

	CONSTRAINT PK_wine PRIMARY KEY (wine_id)
)


COMMIT TRANSACTION;
