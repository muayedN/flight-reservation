INSERT role VALUE (4, 'ROLE_SYSTEM');

INSERT INTO user (id, date_of_birth, email, first_name, last_name, password, public_id) values (200, '2020-06-17', 'mailingservice@flightreservation.com', 'Flight Reservation - Emailing Service', 'Flight Reservation - Emailing Service', '$2a$10$BfIgxGBsNXkZ.QClyv4g5O1jym4QOf98vRqJGU95JwxYL51sJmev.', '3b0344df-c306-4f9a-80a2-f1183c0d7c46');

INSERT INTO user_roles (id, user_id, role_id) values (220, 200, 4);


