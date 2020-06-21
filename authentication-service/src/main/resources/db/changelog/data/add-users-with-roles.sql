insert into user (id, date_of_birth, email, first_name, last_name, password, public_id) values (1, '2020-06-15', 'nnegash@miu.edu', 'Natnael', 'Negash', '$2a$10$N.CUg7fsNe3cUEKtfbRj..xKPhx20NSuZ48WIyFymAau/6mgX1YWi', 'da062e23-c27f-4d6d-a450-4008fcf06c92');
insert into user (id, date_of_birth, email, first_name, last_name, password, public_id) values (3, '2020-06-15', 'test1@miu.edu', 'Payman', 'Salek', '$2a$10$qawTuu2sC.Zv1tfi.R.UsuMf1VmP2gIo00birta25xLPMq.xuml7.', '943da0d9-d90f-468e-9936-b82ca5986f64');
insert into user (id, date_of_birth, email, first_name, last_name, password, public_id) values (5, '2020-06-15', 'test2@miu.edu', 'Najeeb', 'Najeen', '$2a$10$mHRGCf23rSn1Vdu5H/TS3eLVfGqUo8pdB.8El/HNVle89tM6L.n/q', '953ec495-4348-4516-99a5-d5bd48b6e0c5');

insert into user_roles (id, user_id, role_id) values (4, 3, 1);
insert into user_roles (id, user_id, role_id) values (6, 5, 3);
insert into user_roles (id, user_id, role_id) values (11, 1, 2);


