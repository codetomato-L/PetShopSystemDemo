package com.zdnf.linus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.zdnf.linus.dao.AccountDao;
import com.zdnf.linus.entity.Account;
import com.zdnf.linus.util.DBUtil;

public class AccountDaoImpl extends DBUtil<Account> implements AccountDao {

	/**
	 * 生产新的卖出账目信息
	 */
	public boolean addSellAccount(Account account) {		
		Date day = new Date();
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
		return update("insert into account values(null,?,1,?,?,null,?,?)",
				account.getStore_id(), account.getPet_id(),
				account.getBuyer_id(), account.getPrice(), dft.format(day));
	}
		
	/**
	 * 生产新的买入账目信息
	 */
	@Override
	public boolean addBuyAccount(Account account) {
		Date day = new Date();
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				
		return update("insert into account values(null,?,2,?,null,?,?,?)",
				account.getStore_id(), account.getPet_id(),
				account.getSeller_id(), account.getPrice(), dft.format(day));
	}

	/**
	 * 通过宠物商店查找账目信息
	 */
	@Override
	public List<Account> findbysid(int store_id) {	
		return query("select * from account where store_id=?", store_id);
	}

	@Override
	public Account getEntity(ResultSet resultSet) throws SQLException {
		Account account = new Account();
		account.setId(resultSet.getInt(1));
		account.setStore_id(resultSet.getInt(2));
		account.setDeal_type(resultSet.getInt(3));
		account.setPet_id(resultSet.getInt(4));
		account.setBuyer_id(resultSet.getInt(5));
		account.setSeller_id(resultSet.getInt(6));
		account.setPrice(resultSet.getInt(7));
		account.setTime(resultSet.getDate(8));
		return account;
	}
}
