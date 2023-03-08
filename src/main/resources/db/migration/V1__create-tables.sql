
create table events
(
    id bigserial,
	name varchar(100) not null,
	description varchar(1000) not null,
	category varchar(45) not null,
	event_time timestamp not null,
	price decimal(10, 2) not null,
	primary key (id)
);

create table tickets(
	id bigserial,
	user_id bigint not null,
	event_id bigint not null,
	quantity int not null,
	price decimal(10, 2) not null,
	primary key (id),
	foreign key (event_id) references events (id) on update cascade on delete set null
);