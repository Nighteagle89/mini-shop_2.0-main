create table carts
(
    id      int8 not null,
    user_id int8,
    primary key (id)
);
create table carts_products
(
    product_id int8 not null,
    cart_id    int8 not null
);
create table categories
(
    id              int8   not null,
    title           varchar(255),
    amount_products numeric(19, 2),
    summa           float8 not null,
    order_id        int8,
    product_id      int8,
    primary key (id)
);
create table orders
(
    id          int8   not null,
    address     varchar(255),
    create_time timestamp,
    status      varchar(255),
    summa       float8 not null,
    update_time timestamp,
    user_id     int8,
    primary key (id)
);
create table orders_details
(
    order_id   int8 not null,
    details_id int8 not null
);
create table products
(
    id     int8   not null,
    amount int4,
    price  float8 not null,
    title  varchar(255),
    primary key (id)
);
create table products_categories
(
    product_id  int8 not null,
    category_id int8 not null
);
create table users
(
    id       int8    not null,
    archive  boolean not null,
    email    varchar(255),
    number   varchar(255),
    password varchar(255),
    role     varchar(255),
    username varchar(255),
    cart_id  int8,
    primary key (id)
);
alter table if exists orders_details drop constraint if exists UK_kk6y3pyhjt6kajomtjbhsoajo;
alter table if exists orders_details add constraint UK_kk6y3pyhjt6kajomtjbhsoajo unique (details_id);
create sequence cart_seq start 1 increment 1;
create sequence category_seq start 1 increment 1;
create sequence order_details_seq start 1 increment 1;
create sequence order_seq start 1 increment 1;
create sequence product_seq start 1 increment 1;
create sequence user_seq start 1 increment 1;
alter table if exists carts add constraint FKb5o626f86h46m4s7ms6ginnop foreign key (user_id) references users;
alter table if exists carts_products add constraint FK3nvguygrfbn661omvvu2uafu5 foreign key (cart_id) references carts on delete cascade;
alter table if exists carts_products add constraint FKt3mepi19unnkcmw4683q5wr39 foreign key (product_id) references products;
alter table if exists categories add constraint FKbvxj7yyy7wyrp1gwoksfrd5ji foreign key (order_id) references orders;
alter table if exists categories add constraint FKb4c3eeiugg1svyrcheyd4yo63 foreign key (product_id) references products;
alter table if exists orders add constraint FK32ql8ubntj5uh44ph9659tiih foreign key (user_id) references users;
alter table if exists orders_details add constraint FKipixxqtl6ooh776qk4wit5kui foreign key (details_id) references categories;
alter table if exists orders_details add constraint FK5o977kj2vptwo70fu7w7so9fe foreign key (order_id) references orders;
alter table if exists products_categories add constraint FKqt6m2o5dly3luqcm00f5t4h2p foreign key (category_id) references categories;
alter table if exists products_categories add constraint FKtj1vdea8qwerbjqie4xldl1el foreign key (product_id) references products;
alter table if exists users add constraint FKdv26y3bb4vdmsr89c9ppnx85w foreign key (cart_id) references carts;