create table sqa_room
(
    id          bigint identity,
    name        varchar(255) not null,
    room_number varchar(255) not null,
    primary key (id)
)
