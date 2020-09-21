package com.zdnf.linus.menu;

import java.util.List;

import com.zdnf.linus.dao.impl.PetDaoImpl;
import com.zdnf.linus.dao.impl.PetOwnerDaoImpl;
import com.zdnf.linus.dao.impl.PetStoreDaoImpl;
import com.zdnf.linus.entity.Pet;
import com.zdnf.linus.entity.PetOwner;
import com.zdnf.linus.entity.PetStore;

public class Menu {

	public static void main(String[] args) {
		LoginMenu loginMenu = new LoginMenu();
		
		System.out.println("宠物商店启动");
		printPet(); //打印宠物列表
		printOwner(); //打印宠物主人列表
		printStore(); //打印宠物商店列表
		loginMenu.select(); //登录选择
	}

	/**
	 * 打印宠物列表
	 */
	public static void printPet(){
		PetDaoImpl petDaoImpl = new PetDaoImpl();
		System.out.println("Wonderland醒来,所有宠物从MySQL中醒来");
		System.out.println("****************************************");
		List<Pet> petList = petDaoImpl.listPet();
		System.out.println("序号\t宠物名称");
		for (int i = 0; i < petList.size(); i++) {
			System.out.println(petList.get(i).getId()+"\t"+petList.get(i).getName());
		}
		System.out.println("****************************************\n\n");	
	}
	
	/**
	 * 打印宠物主人列表
	 */
	public static void printOwner(){
		PetOwnerDaoImpl petOwnerDaoImpl = new PetOwnerDaoImpl();
		System.out.println("所有宠物主人从MySQL中醒来");
		System.out.println("****************************************");
		List<PetOwner> ownerList = petOwnerDaoImpl.listPetOwners();	
		System.out.println("序号\t主人姓名");
		for (int i = 0; i < ownerList.size(); i++) {
			System.out.println(ownerList.get(i).getId()+"\t"+ownerList.get(i).getName());
		}
		System.out.println("****************************************\n\n");	
	}

	/**
	 * 打印宠物商店列表
	 */
	public static void printStore(){
		PetStoreDaoImpl petStoreDaoImpl = new PetStoreDaoImpl();
		System.out.println("所有宠物商店从MySQL中醒来");
		System.out.println("****************************************");
		List<PetStore> storeList = petStoreDaoImpl.listPetStore();
		System.out.println("序号\t宠物商店名称");
		for (int i = 0; i < storeList.size(); i++) {
			System.out.println(storeList.get(i).getId()+"\t"+storeList.get(i).getName());
		}
		System.out.println("****************************************\n\n");	
	}
}
