package com.zdnf.linus.entity;

/**
 * 宠物商店实体类
 */
public class PetStore {

	private int id; //商店编号
	private String name; //登录名
	private String password; //登录密码
	private int balance; //账目余额
	
	public PetStore() {
		super();
	}

	public PetStore(int id, String name, String password, int balance) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.balance = balance;
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

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "petstore [id=" + id + ", name=" + name + ", password="
				+ password + ", balance=" + balance + "]";
	}
}
