package com.nendeu.usersys.usermgr.business.view;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Date;

import com.nendeu.usersys.common.util.TypeUtil;
import com.nendeu.usersys.usermgr.business.controller.UserController;
import com.nendeu.usersys.usermgr.business.domain.UserDto;
/**
 * 系统首页视图类
 * @author Heyouth
 *
 */
public class IndexFrame implements BaseFrame {
	//测试用指示常量
	private static final String Ca = Thread.currentThread().getStackTrace()[1].getClassName()+":";

	/**
	 * 首页展示方法
	 */
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
				this.addShow();
				break;
			case 3:
				System.exit(0);
				break;
			default:
				System.err.println("--------输入信息有误，请输入1~3--------");
				break;
			}
		}
	}

	/**
	 * 登录选项展示方法
	 */
	public void loginShow() {
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			//读取登录用户名
			System.out.println("1.请输入用户名(纯数字)：");
			int sid = Integer.parseInt(br.readLine());
			//读取登录密码
			System.out.println("2.请输入密码：");
			String password = br.readLine();
			//用户Controller类实例,用以获取用户信息
			UserController userctrl = new UserController();
			//调用用户Controller对象方法执行登录操作，结果给予用户dto对象
			UserDto dto = userctrl.doLogin(sid,password);
			if(dto!=null) {
				if(dto.getSupervalue()==2) {
					AdminFrame amf = new AdminFrame(dto);
					amf.show();
				}
				else {
					NormalFrame nmf = new NormalFrame(dto);
					nmf.show();
				}
			}
			else {
				System.err.println("-----------登录失败，用户名或密码错误------------");
			}
		}catch(Exception e) {
			System.err.println("---------输入信息有误，用户名必须为纯数字:-------\n");
			e.printStackTrace();
		}
		
	}

	/**
	 * 注册选项展示方法
	 */
	public void addShow() {
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("1.请输入用户名(纯数字)：");
			int name = Integer.parseInt(br.readLine());
			System.out.println("2.请输入密码：");
			String password = br.readLine();
			System.out.println("3.请输入真实姓名：");
			String sname = br.readLine();
			String email = "";
			while(true) {
				System.out.println("4.请输入用户邮箱：");
				email = br.readLine();
				if(TypeUtil.checkMail(email)) {
					break;
				}
				System.err.println("邮箱格式不正确，请重新输入：");
			}
			Date brithday = null;
			while(true) {
				System.out.println("5.请输入出生日期（yyyy-mm-dd）：");
				String str_brithday = br.readLine();
				if(TypeUtil.checkDate(str_brithday)) {
					brithday = TypeUtil.strToDate(str_brithday);
					break;
				}
				System.err.println("日期格式不正确，请重新输入：");
			}
			
			//设置用户信息
			UserDto dto = new UserDto();
			dto.setSid(name);
			dto.setSpwd(password);
			dto.setSname(sname);
			dto.setEmail(email);
			dto.setBrithday(brithday);
			
			//用户Controller类实例,用以添加用户信息
			UserController userctrl = new UserController();
			//调用用户Controller对象方法执行注册操作，返回更新条目数
			int rs = userctrl.register(dto);
			if(rs<=0) {
				System.err.println("注册失败！按Enter键结束注册。");
			}
			else {
				System.out.println("注册成功！按Enter键结束注册。");
			}
			br.read();
		}catch(Exception e) {
			System.err.println("----输入信息有误，用户名必须为纯数字:----\n");
			e.printStackTrace();
		}
	}
	
	@Override
	public void msgShow() {
	}
	
	@Override
	public void searchShow() {
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
