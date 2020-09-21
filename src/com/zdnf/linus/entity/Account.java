package com.zdnf.linus.entity;

import java.sql.Date;

/**
 * 账目信息实体类
 */
public class Account {

	private int id; //账目编号
	private int store_id; //商店编号(外键)
	private int deal_type; //交易类型
	private int pet_id; //宠物编号(外键)
	private int buyer_id; //买家编号(外键)	
	private int seller_id; //卖家编号(外键)
	private int price; //交易价格
	private Date time; //交易时间
	
	public Account() {
		super();
	}

	public Account(int id, int store_id, int deal_type, int pet_id,
			int buyer_id, int seller_id, int price, Date time) {
		super();
		this.id = id;
		this.store_id = store_id;
		this.deal_type = deal_type;
		this.pet_id = pet_id;
		this.buyer_id = buyer_id;
		this.seller_id = seller_id;
		this.price = price;
		this.time = time;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getStore_id() {
		return store_id;
	}

	public void setStore_id(int store_id) {
		this.store_id = store_id;
	}

	public int getDeal_type() {
		return deal_type;
	}

	public void setDeal_type(int deal_type) {
		this.deal_type = deal_type;
	}

	public int getPet_id() {
		return pet_id;
	}

	public void setPet_id(int pet_id) {
		this.pet_id = pet_id;
	}

	public int getBuyer_id() {
		return buyer_id;
	}

	public void setBuyer_id(int buyer_id) {
		this.buyer_id = buyer_id;
	}

	public int getSeller_id() {
		return seller_id;
	}

	public void setSeller_id(int seller_id) {
		this.seller_id = seller_id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "account [id=" + id + ", store_id=" + store_id + ", deal_type="
				+ deal_type + ", pet_id=" + pet_id + ", buyer_id=" + buyer_id
				+ ", seller_id=" + seller_id + ", price=" + price + ", time="
				+ time + "]";
	}
}