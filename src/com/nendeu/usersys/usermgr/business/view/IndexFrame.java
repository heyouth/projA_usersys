package com.nendeu.usersys.usermgr.business.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.nendeu.usersys.usermgr.business.controller.UserController;
import com.nendeu.usersys.usermgr.business.domain.UserDto;
/**
 * 系统首页-用户登录与注册
 * @author Administrator
 *
 */
public class IndexFrame implements BaseFrame {
	//测试用指示常量
	private static final String Ca = Thread.currentThread().getStackTrace()[1].getClassName()+":";

	/**
	 * 
	 */
	@Override
	public void show() {
		//声明缓冲处理流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		while(true) {
			System.out.println("欢迎使用Heyouth的用户信息管理系统，请选择:");
			System.out.println("︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻");
			System.out.println("1-用户登录");
			System.out.println("2-用户注册");
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
					System.out.println("--------输入信息有误，请输入1~3--------");
					System.out.println("︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻︻");
					System.out.println("1-用户登录");
					System.out.println("2-用户注册");
					System.out.println("3-退出系统");
					System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
				}
			}
			switch (i) {
			case 1:
				this.loginShow();
				break;
			case 2:
				System.out.println("-------------功能未实现！-------------");
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.out.println("--------输入信息有误，请输入1~3--------");
				break;
			}
		}
	}

	public void loginShow() {
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//读取登录用户名
//			System.out.println("1.请输入用户名(纯数字)：");
//			int sid = Integer.parseInt(br.readLine());
			//读取登录密码
//			System.out.println("2.请输入密码：");
//			String password = br.readLine();
			//用户Controller类实例,用以获取用户信息
			UserController userctrl = new UserController();
			//调用用户Controller对象方法执行登录操作，结果给予用户dto对象
			UserDto dto = userctrl.doLogin(2016874240, "123456");
			if(dto!=null) {
				if(dto.getSupervalue()==2) {
					System.out.println("--------------管理员登录成功-------------");
				}
				else {
					NormalFrame nmf = new NormalFrame(dto);
					nmf.show();
				}
			}
			else {
				System.out.println("----------------登录失败-----------------");
			}
		}catch(Exception e) {
			e.printStackTrace();
//			System.out.println("----输入信息有误，用户名必须为纯数字:----\n");
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
	public static void main(String[] args) {
		IndexFrame frame = new IndexFrame();
		frame.show();
	}

}
