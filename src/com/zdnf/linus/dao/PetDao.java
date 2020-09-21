package com.zdnf.linus.dao;

import java.util.List;
import com.zdnf.linus.entity.Pet;
import com.zdnf.linus.entity.PetOwner;
import com.zdnf.linus.entity.PetStore;

public interface PetDao {

	//获取宠物列表
	List<Pet> listPet();	
	
	//根据宠物id获取宠物对象	 
	Pet findByID(int id);
	
	//根据主人id获取宠物列表
	List<Pet> findByOid(int owner_id);
	
	//根据商店id获取宠物列表
	List<Pet> findBySid(int store_id);
	
	//获取库存宠物列表
	List<Pet> inventoryListPet();	
	
	//获取新培育的宠物
	List<Pet> newListPet();
	
	//商店卖出宠物给主人
	boolean storeSeller(Pet pet,PetOwner petOwner);
	
	//主人卖出宠物给商店
	boolean ownerSeller(Pet pet, PetStore petStore, int price);
	
	//增加亲密值
	boolean addLove(Pet pet);
	
	//培育新品种
	boolean addPet(Pet pet);
}
