create table t_order(
order_id varchar(50),
chan_id varchar(50),
order_type varchar(20),
product_id varchar(50),
amount decimal(18,2),
outer_order_id varchar(20),
chan_user_id varchar(20),
order_status varchar(20),
memo varchar(100),
create_at date,
update_at date
);

create table product(
id varchar(50),
name varchar(50),
status varchar(50),
threshold_amount decimal(18,2),
step_amount decimal(18,2),
lock_term int,
reward_rate decimal(5,2),
memo varchar(50),
create_at date,
update_at date,
create_user varchar(50),
update_user varchar(50)
);

create table verification_order(
order_id varchar(50),
chan_id varchar(50),
order_type varchar(20),
product_id varchar(50),
amount decimal(18,2),
outer_order_id varchar(50),
chan_user_id varchar(50),
create_at date
);

