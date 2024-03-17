create table if not exists category(
	id int primary key,
	name varchar(20) unique not null
);

create table if not exists product(
	id int primary key,
	category_id int not null,
	name varchar(20) unique not null,
	price int not null,
	foreign key (category_id) references category(id)
);