INSERT INTO address (id, city, state, street, zip) VALUES (1, 'Iowa', 'Iowa', 'John', '52336'), (2, 'SanDiago', 'California', 'Williams', '54643');
INSERT INTO airport (id, code, name, address_id) VALUES (1, 'IDA', 'Iowa International', 1), (2, 'CID', 'Eastern Iowa Airport', 1);
INSERT INTO airline (id, code, name) VALUES (1, 'UA', 'United Airlines'), (2, 'JA', 'Japanese Airline');
INSERT INTO flight (id, arrival_date, arrival_time, capacity, departure_date, departure_time, number, airline_id, arrival_airport_id, departure_airport_id) VALUES (1, '2020-06-14', '13:30:00', 100, '2020-06-14', '10:00:00', 1248, 1, 1, 2);

select * from flight;