package com.zdnf.linus.entity;

/**
 * 宠物主人实体类
 */
public class PetOwner {

	private int id; //主人编号
	private String name; //登录名
	private String password; //登录密码
	private int money; //余额
	
	public PetOwner() {
		super();
	}

	public PetOwner(int id, String name, String password, int money) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.money = money;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	@Override
	public String toString() {
		return "petowner [id=" + id + ", name=" + name + ", password="
				+ password + ", money=" + money + "]";
	}
}
