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
    wine_id SERIAL PRIMARY KEY,
    user_id INT NOT NULL,
    title varchar(100) NOT NULL,
    average_rating DOUBLE PRECISION,
    description TEXT,
    image_url varchar(200),
    link varchar(200),
    price MONEY,
    rating_count INT,
    score DOUBLE PRECISION,

	FOREIGN KEY (user_id) REFERENCES users(user_id),
);

COMMIT TRANSACTION;
