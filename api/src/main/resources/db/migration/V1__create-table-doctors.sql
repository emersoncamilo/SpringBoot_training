create table doctors (
    id bigint not null auto_increment,
    full_name varchar(100) not null,
    email varchar(100) not null unique,
    id_doctor varchar(6) not null unique,
    specialty varchar(100) not null,
    public_place varchar(100) not null,
    district varchar(100) not null,
    zip_code varchar(9) not null,
    complement varchar(100),
    number_address varchar(20),
    city varchar(100) not null,
    primary key(id)
);