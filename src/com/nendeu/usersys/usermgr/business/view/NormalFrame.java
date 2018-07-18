package com.nendeu.usersys.usermgr.business.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.nendeu.usersys.common.dao.Translator;
import com.nendeu.usersys.usermgr.business.domain.UserDto;

public class NormalFrame implements BaseFrame {
	//用以视图显示用户信息的用户dto
	UserDto dto = null;
	
	/**
	 * 构造方法
	 * 获取用户dto
	 * @param dto
	 */
	public NormalFrame(UserDto dto) {
		this.dto = dto;
	}
	
	@Override
	public void show() {
		//声明缓冲处理流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//转义工具类对象，用以翻译实际涵义
		Translator ts = new Translator();
		//用以表示登录用户实际权限
		String supervalue = ts.UnitedTranslate(dto.getSupervalue());
		while(true) {
			System.out.println("用户："+dto.getSname()+"            权限:"+supervalue);
			System.out.println("︻︻︻︻︻︻︻︻请选择︻︻︻︻︻︻︻︻︻");
			System.out.println("1-查询用户信息");
			System.out.println("2-修改用户信息");
			System.out.println("3-退出系统");
			System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
			int i = -1;
			//循环-读取用户控制台输入，判断输入是否为整型
			while(true) {
				try {
					//读取用户输入，尝试转换为整型
					i = Integer.parseInt(br.readLine());
					//转换成功，退出循环
					break;
				}catch(Exception e) {
					System.out.println("-----输入信息有误，请输入数字数字1~3---");
					System.out.println("用户："+dto.getSname()+"            权限:"+supervalue);
					System.out.println("︻︻︻︻︻︻︻︻︻请选择︻︻︻︻︻︻︻︻");
					System.out.println("1-查询用户信息");
					System.out.println("2-修改用户信息");
					System.out.println("3-退出系统");
					System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
				}
			}
			switch (i) {
			case 1:
				System.out.println("-------------功能未实现！-------------");
				break;
			case 2:
				System.out.println("-------------功能未实现！-------------");
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("-----输入信息有误，请输入数字数字1~3---");
			}
		}
	}

	@Override
	public void searchShow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addShow(String type) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateShow(String type, UserDto dto) {
		// TODO Auto-generated method stub

	}

}
