create table patients(
    id bigint not null auto_increment,
    fullName varchar(100) not null,
    email varchar(100) not null unique,
    doc varchar(14) not null unique,
    public_place varchar(100) not null,
    district varchar(100) not null,
    zip_code varchar(9) not null,
    complement varchar(100),
    number_address varchar(20),
    city varchar(100) not null,
    phone varchar(20) not null,
    active tinyint not null,
    primary key(id)
);