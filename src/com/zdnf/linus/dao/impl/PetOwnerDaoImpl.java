package com.zdnf.linus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.zdnf.linus.dao.PetOwnerDao;
import com.zdnf.linus.entity.Pet;
import com.zdnf.linus.entity.PetOwner;
import com.zdnf.linus.util.DBUtil;

public class PetOwnerDaoImpl extends DBUtil<PetOwner> implements PetOwnerDao {

	/**
	 * 宠物主人登录
	 */
	@Override
	public List<PetOwner> login(String name, String password) {
		List<PetOwner> list = query("select * from petowner where name=? and password=?", name, password);
		return list;
	}

	/**
	 * 宠物主人注册
	 */
	@Override
	public boolean register(PetOwner petOwner) {		
		return update("insert into petowner values(null,?,?,10)", petOwner.getName(), petOwner.getPassword());
	}

	/**zuan'xie
	 * 获取宠物主人列表
	 */
	@Override
	public List<PetOwner> listPetOwners() {		
		return query("select * from petowner");
	}
	
	/**
	 * 修改余额
	 */
	@Override
	public boolean changeMoney(PetOwner petOwner, int money) {
		if(update("update petowner set money=? where id=?", (petOwner.getMoney()+money), petOwner.getId())){
			return true;
		}
		return false;
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean changePw(PetOwner petOwner, String password) {
		if(update("update petowner set password=? where id=?", password, petOwner.getId())){
			return true;
		}
		return false;
	}
	
	
	/**
	 * 根据主人id获取主人对象
	 */
	@Override
	public PetOwner findByID(int id) {
		List<PetOwner> list = new ArrayList<PetOwner>();
		list = query("select * from petowner where id=?", id);		
		return list.get(0);
	}

	@Override
	public PetOwner getEntity(ResultSet resultSet) throws SQLException {
		PetOwner petOwner = new PetOwner();
		
		petOwner.setId(resultSet.getInt(1));
		petOwner.setName(resultSet.getString(2));
		petOwner.setPassword(resultSet.getString(3));
		petOwner.setMoney(resultSet.getInt(4));		
		return petOwner;
	}
}
