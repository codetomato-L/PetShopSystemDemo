package com.zdnf.linus.dao;

import java.util.List;
import com.zdnf.linus.entity.PetOwner;

public interface PetOwnerDao {

	//登录
	List<PetOwner> login(String name,String password);
	
	//注册
	boolean register(PetOwner petOwner);

	//获取列表
	List<PetOwner> listPetOwners();
	
	//修改余额
	boolean changeMoney(PetOwner petOwner,int money);
	
	//修改密码
	boolean changePw(PetOwner petOwner, String password);
	
	//根据主人id获取主人对象	 
	PetOwner findByID(int id);
}
