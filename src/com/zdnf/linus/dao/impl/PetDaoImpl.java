package com.zdnf.linus.dao.impl;

import java.security.acl.Owner;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.zdnf.linus.dao.PetDao;
import com.zdnf.linus.entity.Pet;
import com.zdnf.linus.entity.PetOwner;
import com.zdnf.linus.entity.PetStore;
import com.zdnf.linus.util.DBUtil;

public class PetDaoImpl extends DBUtil<Pet> implements PetDao {

	/**
	 * 获取宠物列表
	 */
	@Override
	public List<Pet> listPet() {
		return query("select * from pet");
	}
	
	/**
	 * 根据宠物id获取宠物对象
	 */
	@Override
	public Pet findByID(int id) {
		List<Pet> list = new ArrayList<Pet>();
		list = query("select * from pet where id=?", id);		
		return list.get(0);
	}

	/**
	 * 根据主人id获取宠物列表
	 */
	@Override
	public List<Pet> findByOid(int owner_id) {		
		return query("select * from pet where owner_id=?", owner_id);
	}
	
	/**
	 * 根据商店id获取宠物列表
	 */
	@Override
	public List<Pet> findBySid(int store_id) {
		return query("select * from pet where store_id=?", store_id);
	}

	/**
	 * 获取库存宠物
	 */
	public List<Pet> inventoryListPet(){
		return query("select * from pet where store_id is not null and (health!=100 or love!=0)");		
	}
		
	/**
	 * 获取新培育的宠物
	 */
	@Override
	public List<Pet> newListPet() {
		return query("select * from pet where store_id is not null and (health=100 and love=0)");
	}	
	
	/**
	 * 商店卖出宠物给主人
	 */
	@Override
	public boolean storeSeller(Pet pet, PetOwner petOwner) {
		if(update("update pet set owner_id=?,store_id=null,price=null where id=?", petOwner.getId(), pet.getId())){
			return true;
		}
		return false;
	}
		
	/**
	 * 主人卖出宠物给商店
	 */
	@Override
	public boolean ownerSeller(Pet pet, PetStore petStore, int price) {
		if(update("update pet set owner_id=null,store_id=?,price=? where id=?", petStore.getId(), price, pet.getId())){
			return true;
		}		
		return false; 
	}
	
	/**
	 * 增加亲密值
	 */
	@Override
	public boolean addLove(Pet pet) {
		if(update("update pet set love=? where id=?", (pet.getLove()+10), pet.getId())){
			return true;
		}		
		return false;
	}
	
	/**
	 * 培育新宠物
	 */
	@Override
	public boolean addPet(Pet pet) {
		Date day = new Date();
		SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		if(update("insert into pet values(null,?,?,100,0,?,null,?,?)", pet.getName(), pet.getType(), dft.format(day), pet.getStore_id(), pet.getPrice())){
			return true;
		}
		return false;
	}

	@Override
	public Pet getEntity(ResultSet resultSet) throws SQLException {
		
		Pet pet = new Pet();
		pet.setId(resultSet.getInt(1));
		pet.setName(resultSet.getString(2));
		pet.setType(resultSet.getString(3));
		pet.setHealth(resultSet.getInt(4));
		pet.setLove(resultSet.getInt(5));
		pet.setBirthday(resultSet.getDate(6));
		pet.setOwner_id(resultSet.getInt(7));
		pet.setStore_id(resultSet.getInt(8));
		pet.setPrice(resultSet.getInt(9));
		return pet;
	}
}
