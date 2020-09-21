package com.zdnf.linus.dao;

import java.util.List;

import com.zdnf.linus.entity.Account;

public interface AccountDao {

	//生产新的卖出账目信息
	boolean addSellAccount(Account account);
	
	//生产新的买入账目信息
	boolean addBuyAccount(Account account);	
	
	//通过宠物商店查找账目信息
	List<Account> findbysid(int store_id);
}
