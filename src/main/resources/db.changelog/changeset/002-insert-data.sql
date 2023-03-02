
--changeset eyakauleva:insert_initial_data

insert into events(name, description, category, event_time, price)
	values('Imagine Dragons 2023', 'Imagine Dragons concert in Minsk', 'CONCERT',
                    '2023-03-10 19:00', 100.00);
insert into events(name, description, category, event_time, price)
	values('Annual market', 'Fruits and vegetables from all over the country', 'MARKET',
                    '2023-05-01 10:00', 23.78);
insert into events(name, description, category, event_time, price)
	values('Football', 'Final game of the season', 'SPORT',
                    '2023-08-15 18:00', 150.34);
insert into events(name, description, category, event_time, price)
	values('Ballet', 'Swan Lake', 'THEATRE',
                    '2023-04-07 19:00', 155);
insert into events(name, description, category, event_time, price)
	values('Quest', 'For the smartest ones', 'OTHER',
                    '2023-03-21 15:30', 50.00);


insert into tickets(user_id, event_id, quantity, price) values(1, 1, 1, 100.00);
insert into tickets(user_id, event_id, quantity, price) values(2, 1, 2, 200.00);
insert into tickets(user_id, event_id, quantity, price) values(2, 2, 2, 45.00);
insert into tickets(user_id, event_id, quantity, price) values(3, 3, 1, 150.34);
insert into tickets(user_id, event_id, quantity, price) values(3, 4, 3, 465.00);


--rollback delete from tickets where id>0;
--  delete from events where id>0;