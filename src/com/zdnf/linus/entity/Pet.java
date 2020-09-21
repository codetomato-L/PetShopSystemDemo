package com.zdnf.linus.entity;

import java.sql.Date;

/**
 * 宠物实体类
 */
public class Pet {

	private int id; //宠物编号
	private String name; //宠物名
	private String type; //类型
	private int health; //健康值
	private int love; //亲密值
	private Date birthday; //出生日期
	private int owner_id; //主人编号(外键)
	private int store_id; //商店编号(外键)
	private int price; //宠物价值
	
	public Pet() {
		super();
	}

	public Pet(int id, String name, String type, int health, int love,
			Date birthday, int owner_id, int store_id, int price) {
		super();
		this.id = id;
		this.name = name;
		this.type = type;
		this.health = health;
		this.love = love;
		this.birthday = birthday;
		this.owner_id = owner_id;
		this.store_id = store_id;
		this.price = price;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public int getLove() {
		return love;
	}

	public void setLove(int love) {
		this.love = love;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public int getOwner_id() {
		return owner_id;
	}

	public void setOwner_id(int owner_id) {
		this.owner_id = owner_id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "pet [id=" + id + ", name=" + name + ", type=" + type
				+ ", health=" + health + ", love=" + love + ", birthday="
				+ birthday + ", owner_id=" + owner_id + ", store_id="
				+ store_id + ", price=" + price + "]";
	}
}
