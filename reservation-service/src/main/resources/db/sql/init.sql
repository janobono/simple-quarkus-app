create table sqa_reservation
(
    id               bigint identity,
    customer_id      bigint not null,
    room_id          bigint not null,
    reservation_date date   not null,
    primary key (id)
)
