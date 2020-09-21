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


public class OwnerMenu {

	Scanner input = new Scanner(System.in);
	PetOwnerDaoImpl petOwnerDaoImpl = new PetOwnerDaoImpl();
	PetStoreDaoImpl petStoreDaoImpl = new PetStoreDaoImpl();
	AccountDaoImpl accountDaoImpl = new AccountDaoImpl();
	
			
	/**
	 * 用户登录
	 */
	public void login(){
		LoginMenu loginMenu = new LoginMenu();
		int i = 3;
		while(i>0){
			System.out.print("请输入主人的名字:");
			String nameInput = input.next();
			System.out.print("请输入主人的密码:");
			String passwordInput = input.next();
			List<PetOwner> list = petOwnerDaoImpl.login(nameInput, passwordInput);
			if(list.size() > 0){
				//登录成功
				System.out.println("\n-------恭喜您成功登录-------");
				showOwner(list.get(0));				
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
	 * 宠物主人基本信息
	 */
	public void showOwner(PetOwner petOwner){
		//更新数据
		PetOwner newPetOwner = petOwnerDaoImpl.findByID(petOwner.getId());
		System.out.println("\n-------您的基本信息：-------");
		System.out.println("名字:"+newPetOwner.getName());
		System.out.println("元宝数:"+newPetOwner.getMoney());
		operation(newPetOwner);
	}	
	
	/**
	 * 宠物主人买卖操作
	 */
	public void operation(PetOwner petOwner){
		LoginMenu loginMenu = new LoginMenu();
		System.out.println("\n请选择您的操作...");
		while (true) {		
			System.out.println("(1.购买宠物 2.卖出宠物 3.查看宠物 4.充值账户 5.修改密码 6.退出账号)");
			System.out.print("请输入:");			
			String num = input.next();
			if(num.equals("1")){
				buyPet(petOwner);
				break;
			}				
			if(num.equals("2")){
				sellPet(petOwner);
				break;
			}
			if(num.equals("3")){
				listPet(petOwner);
				break;
			}
			if(num.equals("4")){
				System.out.print("请输入需要充值的元宝数:");						
				int moneys = 0;
				try {
					moneys = input.nextInt();	
				} catch (Exception e) {
					System.out.println("输入错误...");
					input.next();
				}
	
				petOwnerDaoImpl.changeMoney(petOwner, moneys);
				showOwner(petOwner);
				break;
			}
			if(num.equals("5")){
				changePassword(petOwner);
				break;
			}
			if(num.equals("6")){
				loginMenu.select();
				break;
			}
			System.out.println("输入错误!");			
		}
	}
	
	/**
	 * 修改密码
	 */
	public void changePassword(PetOwner petOwner){
		System.out.print("请输入原密码:");
		String pwString = input.next();
		if(pwString.equals(petOwner.getPassword())){
			System.out.print("请输入新密码:");
			String newPwString = input.next();
			if(petOwnerDaoImpl.changePw(petOwner, newPwString)){
				System.out.println("密码修改成功...");
				petOwner.setPassword(newPwString);
				operation(petOwner);
			}else {
				System.out.println("密码错误!返回上一层...");
				operation(petOwner);
			}			
		}else {
			System.out.println("密码错误!返回上一层...");
			operation(petOwner);
		}		
	}

	/**
	 * 查看宠物
	 */
	public void listPet(PetOwner petOwner){
		PetDaoImpl petDaoImpl = new PetDaoImpl();
		List<Pet> list = new ArrayList<Pet>();
		list = petDaoImpl.findByOid(petOwner.getId());
		
		System.out.println("\n-------我的宠物列表-------");	
		System.out.println("序号\t宠物名称\t类型\t健康值\t亲密度");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId()+"\t"+list.get(i).getName()+"\t"+list.get(i).getType()+"\t"+list.get(i).getHealth()+"\t"+list.get(i).getLove());
		}
		operation(petOwner);
	}
		
	/**
	 * 购买宠物
	 */
	public void buyPet(PetOwner petOwner){
		System.out.println("\n-------请输入选择要购买范围:只输入选择项的序号-------");		
		while (true) {		
			System.out.println("1.购买库存宠物");
			System.out.println("2.购买新培育宠物");
			String num = input.next();
			if(num.equals("1")){								
				buyInventoryPet(petOwner);				
				break;
			}				
			if(num.equals("2")){
				buyNewPet(petOwner);
				break;
			}
			System.out.println("输入错误!");			
		}

	}
	
	/**
	 * 购买库存宠物
	 */
	public void buyInventoryPet(PetOwner petOwner){
		
		PetDaoImpl petDaoImpl = new PetDaoImpl();
		List<Pet> list = new ArrayList<Pet>();
		list = petDaoImpl.inventoryListPet();

		System.out.println("\n-------以下是库存宠物-------");
		System.out.println("序号\t宠物名称\t类型\t元宝数");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId()+"\t"+list.get(i).getName()+"\t"+list.get(i).getType()+"\t"+list.get(i).getPrice());
		}
		
		Account account = new Account();
		System.out.println("\n-------请选择要购买哪一个宠物，并输入选择项的序号-------");
		System.out.println("-------输入错误将返回菜单");
		int buynum = 0;
		try {
			buynum = input.nextInt();
		} catch (Exception e) {
			input.next();
		}
		for (int i = 0; i < list.size(); i++) {
			if(buynum == list.get(i).getId()){
				account.setStore_id(list.get(i).getStore_id());//商店编号
				account.setPet_id(list.get(i).getId());//宠物编号
				account.setBuyer_id(petOwner.getId());//买家编号
				account.setPrice(list.get(i).getPrice());//价格				
				
				//扣除宠物主人的余额
				if(petOwner.getMoney()>list.get(i).getPrice()){
					if(petOwnerDaoImpl.changeMoney(petOwner, -list.get(i).getPrice())){
						System.out.println("\n您的账户已扣除"+list.get(i).getPrice()+"元宝");					
					}
				}else {					
					System.out.println("您的元宝数不足,请返回上一层进行充值");
					showOwner(petOwner);
				}
				
				
				
				//增加宠物商城的余额
				PetStore petStore = petStoreDaoImpl.findById(list.get(i).getStore_id());
				if(petStoreDaoImpl.changeMoney(petStore, list.get(i).getPrice())){
					System.out.println(petStore.getName()+"账户已存入"+list.get(i).getPrice()+"元宝");
				}
				//将宠物从商店转移给宠物主人
				if(petDaoImpl.storeSeller(list.get(i), petOwner)){
					System.out.println("您已购买宠物"+list.get(i).getName());
				}
				
				//生产新的账目信息
				if(accountDaoImpl.addSellAccount(account)){
					System.out.println("正确生成一条账目信息");
				}
				
				//增加宠物亲密值
				if(petDaoImpl.addLove(list.get(i))){
					System.out.println(list.get(i).getName()+"与您的亲密值+10");
				}
				System.out.println("正在返回菜单...");
				showOwner(petOwner);
			}
		}
		System.out.println("输入错误...");
		showOwner(petOwner);
	}
		
	/**
	 * 购买新培育的宠物
	 */
	public void buyNewPet(PetOwner petOwner){
		
		PetDaoImpl petDaoImpl = new PetDaoImpl();
		List<Pet> list = new ArrayList<Pet>();
		list = petDaoImpl.newListPet();

		System.out.println("\n-------以下是新培育宠物-------");
		System.out.println("序号\t宠物名称\t类型\t元宝数");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId()+"\t"+list.get(i).getName()+"\t"+list.get(i).getType()+"\t"+list.get(i).getPrice());
		}
		
		Account account = new Account();
		AccountDaoImpl accountDaoImpl = new AccountDaoImpl();		

		System.out.println("\n-------请选择要购买哪一个宠物，并输入选择项的序号-------");
		int buynum = 0;
		try {
			buynum = input.nextInt();
		} catch (Exception e) {
			input.next();
		}
		
		
		for (int i = 0; i < list.size(); i++) {
			if(buynum == list.get(i).getId()){
				account.setStore_id(list.get(i).getStore_id());//商店编号
				account.setPet_id(list.get(i).getId());//宠物编号
				account.setBuyer_id(petOwner.getId());//买家编号
				account.setPrice(list.get(i).getPrice());//价格
								
				//扣除宠物主人的余额
				if(petOwner.getMoney()>list.get(i).getPrice()){
					if(petOwnerDaoImpl.changeMoney(petOwner, -list.get(i).getPrice())){
						System.out.println("\n您的账户已扣除"+list.get(i).getPrice()+"元宝");					
					}
				}else {					
					System.out.println("您的元宝数不足,请返回上一层进行充值");
					showOwner(petOwner);
				}				
				//增加宠物商城的余额
				PetStore petStore = petStoreDaoImpl.findById(list.get(i).getStore_id());
				if(petStoreDaoImpl.changeMoney(petStore, list.get(i).getPrice())){
					System.out.println(petStore.getName()+"账户已存入"+list.get(i).getPrice()+"元宝");
				}
				//将宠物从商店转移给宠物主人
				if(petDaoImpl.storeSeller(list.get(i), petOwner)){
					System.out.println("您已购买宠物"+list.get(i).getName());
				}			
				//生产新的账目信息
				if(accountDaoImpl.addSellAccount(account)){
					System.out.println("正确生成一条账目信息");
				}
				
				//增加宠物亲密值
				if(petDaoImpl.addLove(list.get(i))){
					System.out.println(list.get(i).getName()+"与您的亲密值+10");
				}
				System.out.println("正在返回菜单...");
				showOwner(petOwner);
			}
		}
		System.out.println("输入错误...");
		showOwner(petOwner);
	}
		
	/**
	 * 卖出宠物
	 */
	public void sellPet(PetOwner petOwner){
		
		PetDaoImpl petDaoImpl = new PetDaoImpl();
		List<Pet> list = new ArrayList<Pet>();
		list = petDaoImpl.findByOid(petOwner.getId());
		Account account = new Account();
		
		System.out.println("\n-------我的宠物列表-------");	
		System.out.println("序号\t宠物名称\t类型");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getId()+"\t"+list.get(i).getName()+"\t"+list.get(i).getType());
		}
		
		System.out.println("\n-------请选择要出售的宠物序号-------");
		int sellnum = 0;
		try {
			sellnum = input.nextInt();
		} catch (Exception e) {
			System.out.println("输入错误,返回上一层...");
			input.next();
			sellPet(petOwner);
		}
		
		
		
		System.out.println("\n-------您要卖出的宠物信息如下-------");
		Pet pet = new Pet();
		for (int i = 0; i < list.size(); i++) {
			if(list.get(i).getId() == sellnum){
				pet = list.get(i);
			}
		}
		if(pet.getName() == null){
			System.out.println("宠物不存在,返回上一层...");
			showOwner(petOwner);
		}else {
			System.out.println("宠物名字叫:"+pet.getName()+"\t宠物类别是:"+pet.getType());
			
			System.out.println("请确认是否卖出,y代表卖出,n代表不卖");
			String s = input.next();
			while(true){
				if(s.equals("y")){
					System.out.println("\n-------下面是现有宠物商店,请选择您要卖给买家序号-------");
					List<PetStore> list2 = new ArrayList<PetStore>();
					list2 = petStoreDaoImpl.listPetStore();
					System.out.println("序号\t宠物商店名字");
					for (int i = 0; i < list2.size(); i++) {					
						System.out.println(list2.get(i).getId()+"\t"+list2.get(i).getName());
					}
					int i = input.nextInt();
					PetStore petStore = new PetStore();
					petStore = petStoreDaoImpl.findById(i);
					
					System.out.print("\n请输入需要卖出的价格:");
					int prices = input.nextInt();
					
					account.setStore_id(petStore.getId());//商店编号
					account.setPet_id(pet.getId());//宠物编号
					account.setSeller_id(petOwner.getId());//卖家编号
					account.setPrice(prices);//价格	
					
					
					//扣除宠物商店的余额
					if(petStoreDaoImpl.changeMoney(petStore, -prices)){
						System.out.println("\n"+petStore.getName()+"账户已扣除"+prices+"元宝");
					}
														
					//增加宠物主人的余额				
					if(petOwnerDaoImpl.changeMoney(petOwner, prices)){
						System.out.println("您的账户已存入"+prices+"元宝");
					}
		
					//将宠物从宠物主人转移给宠物商店	
					if(petDaoImpl.ownerSeller(pet, petStore, prices)){
						System.out.println("您已卖出宠物"+pet.getName());
					}
					
					//生产新的账目信息
					if(accountDaoImpl.addBuyAccount(account)){
						System.out.println("正确生成一条账目信息");
					}
					System.out.println("正在返回菜单...");
					showOwner(petOwner);
					break;
				}else if(s.equals("n")) {
					System.out.println("正在返回菜单...");
					showOwner(petOwner);
					break;
				}else {
					System.out.println("输入错误!");
				}
			}
		}
	}
}
