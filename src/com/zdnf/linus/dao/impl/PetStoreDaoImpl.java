package com.zdnf.linus.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.zdnf.linus.dao.PetStoreDao;
import com.zdnf.linus.entity.PetStore;
import com.zdnf.linus.util.DBUtil;

public class PetStoreDaoImpl extends DBUtil<PetStore> implements PetStoreDao {

	/**
	 * 宠物商店登录
	 */
	@Override
	public List<PetStore> login(int id, String password) {
		List<PetStore> list = query("select * from petstore where id=? and password=?", id,password);		
		return list;
	}
	
	/**
	 * 宠物商店注册
	 */
	@Override
	public boolean register(PetStore petStore) {
		return update("insert into petstore values(?,?,?,10)", petStore.getId(), petStore.getName(), petStore.getPassword());
	}

	/**
	 * 获取宠物商店列表
	 */
	public List<PetStore> listPetStore(){	
		return query("select * from petstore");
		
	}
	
	/**
	 * 修改余额
	 */
	@Override
	public boolean changeMoney(PetStore petStore, int money) {
		if(update("update petstore set balance=? where id=?", (petStore.getBalance()+money), petStore.getId())){
			return true;
		}
		return false;
	}
	
	/**
	 * 修改密码
	 */
	@Override
	public boolean changePw(PetStore petStore, String password) {
		if(update("update petstore set password=? where id=?", password, petStore.getId())){
			return true;
		}
		return false;
	}
	
	/**
	 * 通过id查找宠物商店对象
	 */
	@Override
	public PetStore findById(int id) {
		List<PetStore> list = query("select * from petstore where id=?", id);
		if(list.size()>0)
			return list.get(0);
		return null;
	}
	
	@Override
	public PetStore getEntity(ResultSet resultSet) throws SQLException {
		PetStore petStore = new PetStore();
		petStore.setId(resultSet.getInt(1));
		petStore.setName(resultSet.getString(2));
		petStore.setPassword(resultSet.getString(3));
		petStore.setBalance(resultSet.getInt(4));
		return petStore;
	}
}
