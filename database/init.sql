begin;

INSERT INTO customers (customer_name, email, passwd_hash, bonuses) VALUES
    ('John', 'john@mail.com', 123456, 0),
    ('Sam', 'sam@mail.com', 123456, 0),
    ('Nick', 'nick@mail.com', 123456, 0),
    ('Max', 'max@mail.com', 123456, 0),
    ('Mai', 'mai@mail.com', 123456, 0),
    ('Ann', 'al@cash.sp.irt', 123456, 0);

INSERT INTO aircrafts (aircraft_name, n_rows, n_cols) VALUES
    ('Boeing-747', 2, 2),
    ('Boeing-748', 2, 2),
    ('Boeing-749', 2, 2);

INSERT INTO seats (aircraft_id, "row", "column", fare_condition) VALUES
    (1, 0, 0, 'business'),
    (1, 0, 1, 'business'),
    (1, 1, 0, 'econom'),
    (1, 1, 1, 'econom'),
    (2, 0, 0, 'business'),
    (2, 0, 1, 'business'),
    (2, 1, 0, 'econom'),
    (2, 1, 1, 'econom'),
    (3, 0, 0, 'business'),
    (3, 0, 1, 'business'),
    (3, 1, 0, 'econom'),
    (3, 1, 1, 'econom');

INSERT INTO cities (city_name) VALUES
    ('Moscow'),
    ('Oslo'),
    ('Stockholm');

INSERT INTO flights (company, scheduled_departure, scheduled_arrival, departure_city_id, arrival_city_id, aircraft_id, base_price) VALUES
    ('FFlights', to_timestamp(1700208451), to_timestamp(1700217384), 1, 3, 3, 893),
    ('Teirlines', to_timestamp(1700137111), to_timestamp(1700143388), 1, 3, 3, 627),
    ('Teirlines', to_timestamp(1700504716), to_timestamp(1700510217), 1, 3, 1, 550),
    ('FFlights', to_timestamp(1700433598), to_timestamp(1700448653), 1, 3, 2, 1505),
    ('Teirlines', to_timestamp(1700587834), to_timestamp(1700592888), 1, 2, 1, 505),
    ('FFlights', to_timestamp(1700514799), to_timestamp(1700525843), 1, 3, 3, 1104),
    ('FFlights', to_timestamp(1700291103), to_timestamp(1700297379), 1, 2, 3, 627),
    ('TinyAirlines', to_timestamp(1700121146), to_timestamp(1700126167), 2, 3, 3, 502),
    ('FFlights', to_timestamp(1700261370), to_timestamp(1700275259), 1, 3, 1, 1388),
    ('Teirlines', to_timestamp(1700318684), to_timestamp(1700335236), 1, 2, 3, 1655),
    ('Teirlines', to_timestamp(1700115083), to_timestamp(1700123851), 1, 3, 3, 876),
    ('FFlights', to_timestamp(1700364210), to_timestamp(1700369641), 1, 2, 1, 543),
    ('Teirlines', to_timestamp(1700106748), to_timestamp(1700113328), 1, 2, 2, 658),
    ('TinyAirlines', to_timestamp(1700384239), to_timestamp(1700387849), 1, 3, 2, 361),
    ('Teirlines', to_timestamp(1700328539), to_timestamp(1700335645), 1, 2, 1, 710);

INSERT INTO bookings (customer_id, flight_id, seat_id, price) VALUES
    (1, 1, 4, 400),
    (2, 2, 5, 900),
    (4, 3, 11, 1200);

commit;
