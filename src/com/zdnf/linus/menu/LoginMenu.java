package com.zdnf.linus.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.zdnf.linus.dao.impl.PetOwnerDaoImpl;
import com.zdnf.linus.dao.impl.PetStoreDaoImpl;
import com.zdnf.linus.entity.PetOwner;
import com.zdnf.linus.entity.PetStore;

public class LoginMenu {

	Scanner input = new Scanner(System.in);		
	OwnerMenu ownerMenu = new OwnerMenu();
	StoreMenu storeMenu = new StoreMenu();
	PetOwnerDaoImpl petOwnerDaoImpl = new PetOwnerDaoImpl();
	PetStoreDaoImpl petStoreDaoImpl = new PetStoreDaoImpl();
	
	
	public void select(){
		while (true) {
			System.out.println("\n***********欢迎进入宠物商店系统************");
			System.out.print("请输入登录模式(1.宠物主人登录 2.宠物商店登录 3.注册 4.退出系统):");
			String num = input.next();
						
			if(num.equals("1")){
				//宠物主人登录
				ownerMenu.login();
				break;
			}
			if(num.equals("2")){
				//宠物商店登录
				storeMenu.login();
				break;
			}
			if(num.equals("3")){
				//注册
				System.out.println("\n----------注册----------");
				while (true) {
					System.out.print("请输入需要注册的角色(1.注册宠物主人 2.注册宠物商店):");
					String i = input.next();
					if(i.equals("1")){
						ownerRegister();
						break;
					}
					if(i.equals("2")){
						storeRegister();
						break;
					}
					System.out.println("输入错误!");
				}
				break;
			}
			if(num.equals("4")){
				System.out.println("\n系统正在退出...");
				System.exit(0);
				break;
			}
			System.out.println("输入错误!");
		}
	}
	
	/**
	 * 宠物主人注册
	 */
	public void ownerRegister(){
		PetOwner petOwner = new PetOwner();
		List<PetOwner> list = new ArrayList<PetOwner>();
		//获取所有宠物主人列表
		list = petOwnerDaoImpl.listPetOwners();

		System.out.println("\n-------宠物主人注册-------");		
		System.out.print("请输入用户名:");
		String name = input.next();
		for (int i = 0; i < list.size(); i++) {
			if(name.equals(list.get(i).getName())){
				System.out.println("该用户名已存在!注册失败...");
				ownerRegister();
			}
		}
		petOwner.setName(name);
		System.out.print("请输入密码:");
		String password1 = input.next();
		System.out.print("请再次输入密码:");
		String password2 = input.next();
		
		if(password1.equals(password2)){
			petOwner.setPassword(password1);
			petOwnerDaoImpl.register(petOwner);
			System.out.println("注册成功!新用户赠送10元宝...\n");
			select();
		}else {
			System.out.println("两次密码不一致,注册失败...");
			ownerRegister();
		}
	}

	/**
	 * 宠物商店注册
	 */
	public void storeRegister(){
		PetStore petStore = new PetStore();
		List<PetStore> list = new ArrayList<PetStore>();
		//获取所有宠物商店列表
		list = petStoreDaoImpl.listPetStore();

		System.out.println("\n-------宠物商店注册-------");	
		
		System.out.print("请输入商店ID:");
		int id = input.nextInt();
		for (int i = 0; i < list.size(); i++) {
			if(id == list.get(i).getId()){
				System.out.println("该商店ID已存在!注册失败...");
				storeRegister();
			}
		}
		petStore.setId(id);

		System.out.print("请输入商店名:");
		String name = input.next();
		for (int i = 0; i < list.size(); i++) {
			if(name.equals(list.get(i).getName())){
				System.out.println("该商店名已存在!注册失败...");
				storeRegister();
			}
		}		
		petStore.setName(name);
		
		System.out.print("请输入密码:");
		String password1 = input.next();
		System.out.print("请再次输入密码:");
		String password2 = input.next();
		
		if(password1.equals(password2)){
			petStore.setPassword(password1);
			petStoreDaoImpl.register(petStore);
			System.out.println("注册成功!新商户赠送10元宝...\n");
			select();
		}else {
			System.out.println("两次密码不一致,注册失败...");
			storeRegister();
		}
	}
}
