package com.nendeu.usersys.usermgr.business.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Date;

import com.nendeu.usersys.common.util.Translator;
import com.nendeu.usersys.common.util.TypeUtil;
import com.nendeu.usersys.usermgr.business.controller.UserController;
import com.nendeu.usersys.usermgr.business.domain.UserDto;

/**
 * 视图普通用户主页类
 * @author Heyouth
 *
 */
public class NormalFrame implements BaseFrame {
	/**
	 * 当前使用的用户dto对象
	 */
	UserDto dto = null;
	
	/**
	 * 构造方法
	 * 获取用户dto
	 * @param dto 当前用户信息
	 */
	public NormalFrame(UserDto dto) {
		this.dto = dto;
	}
	
	/**
	 * 普通用户首页展示方法
	 */
	public void show() {
		//声明缓冲处理流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//用以表示登录用户实际权限
		String supervalue = Translator.translatorSupervalue(dto.getSupervalue());
		boolean flag = true;
		while(flag) {
			System.out.println("用户："+dto.getSname()+"            权限:"+supervalue);
			System.out.println("︻︻︻︻︻︻︻︻请选择︻︻︻︻︻︻︻︻︻");
			System.out.println("1-查询用户信息");
			System.out.println("2-修改用户信息");
			System.out.println("3-修改用户密码");
			if(dto.getSupervalue()!=1) {
				System.out.println("4-返回管理员主页");
			}
			else {
				System.out.println("4-注销");
			}
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
					System.out.println("3-修改用户密码");
					if(dto.getSupervalue()!=1) {
						System.out.println("4-返回管理员主页");
					}
					else {
						System.out.println("4-注销");
					}
					System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
				}
			}
			switch (i) {
			case 1:
				this.msgShow();
				break;
			case 2:
				this.updateShow("0", this.dto);
				break;
			case 3:
				this.updatePwdShow();
				break;
			case 4:
				flag = false;
				if(dto.getSupervalue()==2)
					break;
				dto = null;
				System.out.println("----------------注销成功---------------");
				break;
			default:
				System.out.println("-----输入信息有误，请输入数字数字1~4---");
			}
		}
	}

	/**
	 * 用户信息展示方法
	 */
	public void msgShow() {
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		while(true) {
			System.out.println("︻︻︻︻︻︻︻︻个人信息︻︻︻︻︻︻︻︻");
			System.out.println("1.用户编号:"+dto.getId());
			System.out.println("2. 用户名 :"+dto.getSid());
			System.out.println("3.真实姓名:"+dto.getSname());
			System.out.println("4.账户邮箱:"+dto.getEmail());
			Date date = dto.getBrithday();
			String strBtith = TypeUtil.DateTostr(date);
			System.out.println("5.出生日期:"+strBtith);
			System.out.println("6.用户权限："+Translator.translatorStatus(dto.getSupervalue()));
			System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
			System.out.println("按Enter键结束浏览。");
			try {
				br.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			break;
		}
	}

	/**
	 * 用户信息更新展示方法
	 */
	public void updateShow(String type, UserDto dto) {
		//更新用户信息对象，用以存储新的用户信息
		UserDto newdto = new UserDto();
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("︻︻︻︻︻︻︻︻修改信息︻︻︻︻︻︻︻︻");
			//获取可修改信息：真实姓名、用户邮箱、出生日期
			System.out.println("1.请输入姓名：（"+dto.getSname()+")");
			String sname = br.readLine();		
			String email = "";
			while(true) {
				System.out.println("2.请输入用户邮箱：（"+dto.getEmail()+")");
				email = br.readLine();
				if(TypeUtil.checkMail(email)) {
					break;
				}
				System.out.println("邮箱格式不正确，请重新输入");
			}
			Date brithday = null;
			while(true) {
				System.out.println("3.请输入出生日期（yyyy-mm-dd）：（"+TypeUtil.DateTostr(dto.getBrithday())+")");
				String str_brithday = br.readLine();
				if(TypeUtil.checkDate(str_brithday)) {
					brithday = TypeUtil.strToDate(str_brithday);
					break;
				}
				System.out.println("日期格式不正确，请重新输入");
			}
			System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
			
			//设置用户信息
			newdto.setSid(dto.getSid());
			newdto.setSname(sname);
			newdto.setEmail(email);
			newdto.setBrithday(brithday);
			
			//用户Controller类实例,用以添加用户信息
			UserController userctrl = new UserController();
			//调用用户Controller对象方法执行更新操作，返回更新条目数
			newdto = userctrl.updateNormal(newdto);
			if(newdto!=null) {
				System.err.println("更新成功！按Enter键结束注册。");
				this.dto = newdto;
			}
			else {
				System.out.println("更新失败！按Enter键结束注册。");
			}
			br.read();
		}catch(Exception e) {
			System.err.println("--------------视图层异常-------------\n");
			e.printStackTrace();
		}
	}
	
	/**
	 * 修改密码选项展示方法
	 */
	public void updatePwdShow() {
		//字符串，用以存储新的用户密码
		String newPwd = null;
		//字符串，用以媒介
		String pwd = null;
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("︻︻︻︻︻︻︻︻修改密码︻︻︻︻︻︻︻︻");
			while(true) {
				System.out.println("1.请输入旧密码：");
				pwd = br.readLine();
				if(pwd.equals(dto.getSpwd())) {
					break;
				}
				else {
					System.out.println("密码错误，请重新输入。");
				}
			}
			while(true) {
				System.out.println("2.请输入新的用户密码：");
				pwd = br.readLine();
				if(!pwd.equals("")) {
					break;
				}
				else {
					System.out.println("密码不能为空，请重新输入。");
				}
			}
			//用户Controller类实例,用以添加用户信息
			UserController userctrl = new UserController();
			//调用用户Controller对象方法执行更新操作，返回更新条目数
			newPwd = userctrl.updatePwd(dto.getSid(),pwd);
			System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
			if(newPwd==null||newPwd.equals("")) {
				System.err.println("更新失败！按Enter键结束修改。");
			}
			else {
				this.dto.setSpwd(newPwd);
				System.out.println("更新成功！按Enter键结束修改。");
			}
			br.read();
		}catch(Exception e) {
			System.err.println("--------------视图层异常-------------\n");
			e.printStackTrace();
		}
	}
	
	@Override
	public void searchShow() {
		// TODO Auto-generated method stub

	}

	@Override
	public void addShow() {
		// TODO Auto-generated method stub
		
	}
	
	public static void main(String[] args) {
		
	}
}
