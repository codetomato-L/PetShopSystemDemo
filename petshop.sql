-- 主键PRIMARY KEY
-- 自增长auto_increment
-- 默认值DEFAULT

-- 宠物主人表
create table petowner(
	id int auto_increment PRIMARY KEY,
	name varchar(10) not null,
	password varchar(10) not null,
	money int DEFAULT 1000
)

-- 宠物商城表
create table petstore(
	id int auto_increment PRIMARY KEY,
	name varchar(10) not null,
	password varchar(10) not null,
	money int DEFAULT 1000
)

-- 宠物表
create table pet(
	id int auto_increment PRIMARY KEY,
	name varchar(50) not null,
	type varchar(50) not null,
	health int(4) DEFAULT 100,
	love int(4) DEFAULT 0,
	birthday date,
	owner_id int,
	store_id int,
	price int,
	CONSTRAINT fk_pet_oid FOREIGN KEY(owner_id) REFERENCES petowner(id),
	CONSTRAINT fk_pet_sid FOREIGN KEY(store_id) REFERENCES petstore(id)
)

-- 账目信息表
create table account(
	id int auto_increment PRIMARY KEY,
	store_id int,
	deal_type int(1),
	pet_id int,
	buyer_id int,
	seller_id int,
	price int,
	time date,
	CONSTRAINT fk_account_sid FOREIGN KEY(store_id) REFERENCES petstore(id),
	CONSTRAINT fk_account_pid FOREIGN KEY(pet_id) REFERENCES pet(id),
	CONSTRAINT fk_account_buyerid FOREIGN KEY(buyer_id) REFERENCES petowner(id),
	CONSTRAINT fk_account_sellerid FOREIGN KEY(seller_id) REFERENCES petowner(id)
)

