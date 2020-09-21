package com.zdnf.linus.dao;

import java.util.List;
import com.zdnf.linus.entity.PetStore;

public interface PetStoreDao {

	//登录
	List<PetStore> login(int id,String password);
	
	//注册
	boolean register(PetStore petStore);
	
	//获取列表
	List<PetStore> listPetStore();
	
	//修改余额
	boolean changeMoney(PetStore petStore,int money);
	
	//修改密码
	boolean changePw(PetStore petStore, String password);
	
	//通过id查找宠物商店对象
	PetStore findById(int id);
}
