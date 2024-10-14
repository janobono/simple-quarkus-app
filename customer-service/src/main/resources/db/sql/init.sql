create table sqa_customer
(
    id         bigint identity,
    first_name varchar(255) not null,
    last_name  varchar(255) not null,
    primary key (id)
)
