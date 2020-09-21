package com.zdnf.linus.menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.zdnf.linus.dao.impl.AccountDaoImpl;
import com.zdnf.linus.dao.impl.PetDaoImpl;
import com.zdnf.linus.dao.impl.PetOwnerDaoImpl;
import com.zdnf.linus.dao.impl.PetStoreDaoImpl;
import com.zdnf.linus.entity.Account;
import com.zdnf.linus.entity.Pet;
import com.zdnf.linus.entity.PetOwner;
import com.zdnf.linus.entity.PetStore;

public class StoreMenu {

	Scanner input = new Scanner(System.in);
	PetOwnerDaoImpl petOwnerDaoImpl = new PetOwnerDaoImpl();
	PetStoreDaoImpl petStoreDaoImpl = new PetStoreDaoImpl();
	AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
	PetDaoImpl petDaoImpl = new PetDaoImpl();
	
	/**
	 * 宠物商店登录
	 */
	public void login(){
		LoginMenu loginMenu = new LoginMenu();
		int i = 3;
		while(i>0){
			System.out.print("请输入宠物商店的ID:");
			int idInput = input.nextInt();
			System.out.print("请输入宠物商店的密码:");
			String passwordInput = input.next();
			List<PetStore> list = petStoreDaoImpl.login(idInput, passwordInput);
			if(list.size() > 0){
				//登录成功
				System.out.println("\n-------恭喜您成功登录-------");
				showStore(list.get(0));				
				break;
			}
			i--;
			if(i == 0){
				System.out.println("登录失败! 机会已用完，系统退出!");
				System.exit(0);
			}
			System.out.println("登录失败!(还剩下"+i+"次机会)");
			System.out.print("是否需要重新登录？y代表重新登录,n代表不登录:");
			String orString = input.next();
			
			if(orString.equals("n")){
				System.out.println("程序返回上一层");
				loginMenu.select();
				break;
			}else if(!orString.equals("y")){
				System.out.println("输入错误!请重新登录...");
			}
		}	
	}

	/**
	 * 宠物商店基本信息
	 */
	public void showStore(PetStore petStore) {
		System.out.println("\n-------宠物商店的基本信息：-------");
		System.out.println("宠物商店的名字:"+petStore.getName());
		System.out.println("账目余额:"+petStore.getBalance());
		operation(petStore);	
	}
	
	/**
	 * 宠物商店操作
	 */
	public void operation(PetStore petStore){
		LoginMenu loginMenu = new LoginMenu();
		System.out.println("\n请选择您的操作...");
		while (true) {		
			System.out.println("(1.查看宠物 2.培育新宠物 3.查看账目 4.修改密码 5.退出账号)");
			System.out.print("请输入:");
			String num = input.next();
			if(num.equals("1")){
				lookPet(petStore);
				break;
			}				
			if(num.equals("2")){
				newPet(petStore);
				break;
			}
			if(num.equals("3")){
				accountList(petStore);
				break;
			}
			if(num.equals("4")){
				changePassword(petStore);
				break;
			}
			if(num.equals("5")){
				loginMenu.select();
				break;
			}
			System.out.println("输入错误!");			
		}
	}
	
	/**
	 * 修改密码
	 */
	public void changePassword(PetStore petStore){
		System.out.print("请输入原密码:");
		String pwString = input.next();
		if(pwString.equals(petStore.getPassword())){
			System.out.print("请输入新密码:");
			String newPwString = input.next();
			if(petStoreDaoImpl.changePw(petStore, newPwString)){
				System.out.println("密码修改成功...");
				petStore.setPassword(newPwString);
				operation(petStore);
			}else {
				System.out.println("密码错误!返回上一层...");
				operation(petStore);
			}			
		}else {
			System.out.println("密码错误!返回上一层...");
			operation(petStore);
		}		
	}
	
	/**
	 * 查看宠物
	 */
	public void lookPet(PetStore petStore){
		List<Pet> list = new ArrayList<Pet>();	
		list = petDaoImpl.findBySid(petStore.getId());
		System.out.println("\n-------"+petStore.getName()+"的宠物列表-------");
		System.out.println("序号\t宠物名称\t类型\t价格");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId()+"\t"+list.get(i).getName()+"\t"+list.get(i).getType()+"\t"+list.get(i).getPrice());
		}		
		showStore(petStore);		
	}
	
	/**
	 * 培育新宠物
	 */
	public void newPet(PetStore petStore){
		Pet pet = new Pet();
		System.out.println("\n-------开始培育新宠物-------");		
		System.out.print("请输入新宠物的昵称:");
		pet.setName(input.next());		
		System.out.print("请输入新宠物的类型:");
		pet.setType(input.next());		
		System.out.print("请输入新宠物的价值:");
		pet.setPrice(input.nextInt());
		pet.setStore_id(petStore.getId());
		
		if(petDaoImpl.addPet(pet)){
			System.out.println("新宠物培育成功...");
			showStore(petStore);
		}
	}
	
	/**
	 * 查看账目
	 */
	public void accountList(PetStore petStore){
		
		List<Account> list = new ArrayList<Account>();
		List<Account> buyList = new ArrayList<Account>();
		List<Account> sellList = new ArrayList<Account>();
		
		System.out.println("\n-------"+petStore.getName()+"的账目信息-------");

		//获取本商店所有账目信息		
		list = accountDaoImpl.findbysid(petStore.getId());
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getDeal_type() == 1){
				sellList.add(list.get(i));
			}
			if(list.get(i).getDeal_type() == 2){
				buyList.add(list.get(i));
			}
		}
		
		System.out.println("\n商店买入宠物的账目信息:");
		System.out.println("编号\t宠物\t卖家\t交易价格\t交易时间");
		for (int i = 0; i < buyList.size(); i++) {
			//获取宠物昵称
			Pet pet = petDaoImpl.findByID(buyList.get(i).getPet_id());			
			//获取卖家名称
			PetOwner petOwner = petOwnerDaoImpl.findByID(buyList.get(i).getSeller_id());			
			System.out.println(buyList.get(i).getId()+"\t"+pet.getName()+"\t"+petOwner.getName()+"\t"+buyList.get(i).getPrice()+"元宝\t"+buyList.get(i).getTime());
		}

		System.out.println("\n商店卖出宠物的账目信息:");
		System.out.println("编号\t宠物\t买家\t交易价格\t交易时间");
		for (int i = 0; i < sellList.size(); i++) {
			Pet pet = petDaoImpl.findByID(sellList.get(i).getPet_id());
			PetOwner petOwner = petOwnerDaoImpl.findByID(sellList.get(i).getBuyer_id());
			System.out.println(sellList.get(i).getId()+"\t"+pet.getName()+"\t"+petOwner.getName()+"\t"+sellList.get(i).getPrice()+"元宝\t"+sellList.get(i).getTime());
		}
		showStore(petStore);
	}
}
