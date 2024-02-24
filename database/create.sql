begin;

CREATE TABLE customers (
    customer_id serial PRIMARY KEY,
    customer_name text NOT NULL,
    email text NOT NULL UNIQUE,
    passwd_hash bigint NOT NULL,
    bonuses bigint NOT NULL
);

CREATE TABLE aircrafts (
    aircraft_id serial PRIMARY KEY,
    aircraft_name text NOT NULL UNIQUE,
    n_rows int NOT NULL CONSTRAINT positive_row CHECK(n_rows > 0),
    n_cols int NOT NULL CONSTRAINT positive_col CHECK(n_cols > 0)
);

CREATE TABLE seats (
    seat_id serial PRIMARY KEY,
    aircraft_id int NOT NULL REFERENCES aircrafts,
    "row" int NOT NULL,
    "column" int NOT NULL,
    fare_condition text NOT NULL
);

CREATE TABLE cities (
    city_id serial PRIMARY KEY,
    city_name text NOT NULL UNIQUE
);

CREATE TABLE flights (
    flight_id serial PRIMARY KEY,
    company text NOT NULL,
    scheduled_departure timestamp NOT NULL,
    scheduled_arrival timestamp NOT NULL,
    departure_city_id int NOT NULL REFERENCES cities,
    arrival_city_id int NOT NULL REFERENCES cities,
    aircraft_id int NOT NULL,
    base_price int NOT NULL CONSTRAINT positive CHECK (base_price > 0)
);

CREATE TABLE bookings (
    booking_id serial PRIMARY KEY,
    customer_id int NOT NULL REFERENCES customers,
    flight_id int NOT NULL REFERENCES flights,
    seat_id int NOT NULL REFERENCES seats,
    price int NOT NULL CONSTRAINT positive CHECK (price > 0)
);

commit;
