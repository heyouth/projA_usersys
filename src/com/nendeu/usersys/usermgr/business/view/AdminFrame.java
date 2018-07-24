package com.nendeu.usersys.usermgr.business.view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.nendeu.usersys.common.util.Translator;
import com.nendeu.usersys.common.util.TypeUtil;
import com.nendeu.usersys.usermgr.business.controller.UserController;
import com.nendeu.usersys.usermgr.business.domain.UserDto;

/**
 * 管理员主页视图类
 * @author Heyouth
 *
 */
public class AdminFrame extends NormalFrame {
	/**
	 * 当前使用的用户dto对象
	 */
	public UserDto dto = null;
	
	/**
	 * 构造方法
	 * 获取管理员dto
	 * @param dto 当前用户信息
	 */
	public AdminFrame(UserDto dto) {
		super(dto);
		this.dto = dto;
	}

	/**
	 * 管理员主页展示方法
	 */
	public void show() {
		//声明缓冲处理流对象,用以接受用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//循环判断，用以退出循环或注销
		boolean flag = true;
		while(flag) {
			System.out.println("您好，管理员："+dto.getSname());
			System.out.println("︻︻︻︻︻︻︻︻管理员主页︻︻︻︻︻︻︻");
			System.out.println("1-查找用户信息");
			System.out.println("2-修改用户信息");
			System.out.println("3-添加用户");
			System.out.println("4-删除用户");
			System.out.println("5-管理员个人信息管理");
			System.out.println("0-注销");
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
					System.out.println("︻︻︻︻︻︻︻︻管理员主页︻︻︻︻︻︻︻");
					System.out.println("1-查找用户信息");
					System.out.println("2-修改用户信息");
					System.out.println("3-添加用户");
					System.out.println("4-删除用户");
					System.out.println("5-管理员个人信息管理");
					System.out.println("0-注销");
					System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
				}
			}
			switch (i) {
			case 1:
				this.searchShow();
				break;
			case 2:
				this.updateShow();
				break;
			case 3:
				this.addShow();
				break;
			case 4:
				this.deleteShow();
				break;
			case 5:
				super.show();
				break;
			case 0:
				this.dto = null;
				flag = false;
				System.out.println("----------------注销成功---------------");
				break;
			default:
				System.err.println("-----输入信息有误，请输入数字数字1~4或0---");
			}
		}
	}
	
	/**
	 * 删除用户选项展示方法
	 */
	private void deleteShow() {
		//用户Controller类实例,用以查找用户信息
		UserController userctrl = new UserController();
		//声明缓冲处理流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//用户模型，用以存储用户信息
		UserDto dto = null;
		//用户名
		int sid = 0;
		while(true) {
			System.out.println("请输入删除用户名：");
			try {
				//读取用户输入，尝试转换为整型
				sid = Integer.parseInt(br.readLine());
				//转换成功，退出循环
				break;
			}catch(Exception e) {
				System.err.println("---输入信息有误，请重新输入数字用户名---");
			}
		}
		dto = userctrl.searchUserBySid(sid);
		if(dto==null) {
			System.err.println("-------------查找的用户不存在------------");
		}
		else {
			System.err.println("---------------请确认信息--------------");
			System.out.println("︻︻︻︻︻︻︻︻个人信息︻︻︻︻︻︻︻︻");
			System.out.println("1.用户编号:"+dto.getId());
			System.out.println("2. 用户名 :"+dto.getSid());
			System.out.println("3.真实姓名:"+dto.getSname());
			System.out.println("4.账户邮箱:"+dto.getEmail());
			Date date = dto.getBrithday();
			String strBtith = TypeUtil.DateTostr(date);
			System.out.println("5.出生日期:"+strBtith);
			System.out.println("6.用户权限："+Translator.translatorSupervalue(dto.getSupervalue()));
			System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
		}
		System.err.println("确认要删除该用户吗？");
		System.out.println("1-确认");
		System.out.println("2-取消 ");
		int i = 0;
		while(true) {
			try {
				//读取用户输入，尝试转换为整型
				i = Integer.parseInt(br.readLine());
				//转换成功，退出循环
				break;
			}catch(Exception e) {
				System.out.println("-----输入信息有误，请输入数字数字1~2---");
				System.out.println("确认要删除该用户吗？");
				System.out.println("1-确认");
				System.out.println("2-取消 ");
			}
		}
		if(i==1) {
			int rs = userctrl.deleteUser(sid);
			if(rs>0) {
				System.out.println("--------------删除成功------------------");
			}
			else {
				System.err.println("--------------删除失败------------------");
			}
		}
		else {
			
		}
		System.out.println("按Enter键结束。");
		try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 查找用户选项方法
	 */
	public void searchShow() {
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//循环判断，用以退出循环或注销
		boolean flag = true;
		while(flag) {
			System.out.println("︻︻︻︻︻︻︻︻查找用户︻︻︻︻︻︻︻︻");
			System.out.println("1-查看所有用户");
			System.out.println("2-查看有效用户");
			System.out.println("3-根据姓名模糊查找");
			System.out.println("4-根据用户名查找用户");
			System.out.println("0-返回管理员主页");
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
					System.err.println("---输入信息有误，请输入数字数字1~4或0---");
					System.out.println("︻︻︻︻︻︻︻︻查找用户︻︻︻︻︻︻︻︻");
					System.out.println("1-查看所有用户");
					System.out.println("2-查看有效用户");
					System.out.println("3-根据姓名模糊查找");
					System.out.println("4-根据用户名查找用户");
					System.out.println("0-返回管理员主页");
					System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
				}
			}
			switch (i) {
			case 1:
				this.getUser(1);
				break;
			case 2:
				this.getUser(2);
				break;
			case 3:
				this.getUser(3);
				break;
			case 4:
				this.searchBySid();
				break;
			case 0:
				flag = false;
				break;
			default:
				System.out.println("-----输入信息有误，请输入数字数字1~3或0---");
			}
		}
	}
	
	/**
	 * 更新用户选择展示方法-重载方法
	 */
	public void updateShow() {
		//更新用户信息对象，用以存储新的用户信息
		UserDto dto = null;
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
			System.out.println("︻︻︻︻︻︻︻︻修改用户︻︻︻︻︻︻︻︻");
			//用户Controller类实例,用以添加用户信息
			UserController userctrl = new UserController();
			//获取可修改信息：真实姓名、用户密码、用户邮箱、出生日期
			while(true) {
				System.out.println("1.请输入需要修改用户名：");
				dto = userctrl.searchUserBySid(Integer.parseInt(br.readLine()));
				if(dto!=null) {
					break;
				}
				else {
					System.out.println("该用户不存在，请重新输入：");
				}
			}
			System.out.println("2.请输入用户姓名：（"+dto.getSname()+")");
			String sname = br.readLine();
			System.out.println("3.请输入登录密码：（"+dto.getSpwd()+")");
			String password = br.readLine();			
			String email = "";
			while(true) {
				System.out.println("4.请输入用户邮箱：（"+dto.getEmail()+")");
				email = br.readLine();
				if(TypeUtil.checkMail(email)) {
					break;
				}
				System.out.println("邮箱格式不正确，请重新输入");
			}
			Date brithday = null;
			while(true) {
				System.out.println("5.请输入出生日期（yyyy-mm-dd）：（"+TypeUtil.DateTostr(dto.getBrithday())+")");
				String str_brithday = br.readLine();
				if(TypeUtil.checkDate(str_brithday)) {
					brithday = TypeUtil.strToDate(str_brithday);
					break;
				}
				System.err.println("----------日期格式不正确，请重新输入----------");
			}
			System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
			
			//设置用户信息
			dto.setSname(sname);
			dto.setSpwd(password);
			dto.setEmail(email);
			dto.setBrithday(brithday);
			
			//调用用户Controller对象方法执行更新操作，返回更新条目数
			int rs = userctrl.updateAdmin(dto);
			if(rs<=0) {
				System.err.println("更新失败！按Enter键结束注册。");
			}
			else {
				System.out.println("更新成功！按Enter键结束注册。");
			}
			br.read();
		}catch(Exception e) {
			System.err.println("-----输入有误，请输入正确的用户名（纯数字）----");
		}
	}
	/**
	 * 更新用户选择展示方法
	 */
	public void updateShow(String type, UserDto dto) {
		super.updateShow(type, dto);
	}
	
	public void userShow(List<UserDto> ldto) {
		String head ="| 编号  |     用户名      |  真实姓名   |        密码           |          邮箱             |    出生日期     | 用户权限 | 账户状态 |※";
		String line = "※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※※";
		System.out.println(line+"\n"+head+"\n"+line);
		for(int i=0;ldto.size()>i;i++) {
			UserDto dto = ldto.get(i);
			System.out.printf("|%2s%-4s|","",Integer.toString(dto.getId()));
			System.out.printf("%2s%-12s|","",Integer.toString(dto.getSid()));
			System.out.printf("%2s%-9s|","",dto.getSname());
			System.out.printf("%4s%-14s|","",dto.getSpwd());
			System.out.printf("%2s%-19s|","",dto.getEmail());
			System.out.printf("%2s%-12s|","",TypeUtil.DateTostr(dto.getBrithday()));
			System.out.printf("%1s%-4s%2s|","",Translator.translatorSupervalue(dto.getSupervalue()),"");
			System.out.printf("%2s%-7s|※\n","",Translator.translatorStatus(dto.getStatus()));
		}
		System.out.println(line);
	}
	
	/**
	 * 查找&输出用户信息集合方法：根据查找的用户类型查找用户信息并输出返回的用户信息集合
	 * @param type  查找类型
	 */
	private void getUser(int type) {
		//用户Controller类实例,用以获取用户信息
		UserController userctrl = new UserController();
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//用户信息集储存容器
		List<UserDto> ldto = null;
		//循环判断，用以退出循环或注销
		boolean flag = true;	
		//分页查找的页号
		int pageNo = 1;
		//分页大小
		int pageSize = 5;
		//用户操作指针
		int i = -1;
		//
		String searchStr = null;
		if(type==3) {
			System.out.println("请输入模糊用户名:");
			try {
				searchStr = br.readLine();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
		while(flag) {
			switch (type) {
			case 1:
				ldto = userctrl.getAllUser(pageNo, pageSize);
				break;
			case 2:
				ldto = userctrl.getValidUser(pageNo, pageSize);
				break;
			case 3:
				ldto = userctrl.fuzzSearchByName(searchStr,pageNo, pageSize);
				break;
			default:
				System.err.println("------------该模式不存在-----------");
				break;
			}
			this.userShow(ldto);
			System.out.println("︻可用操作︻︻︻︻︻︻︻︻︻︻︻︻︻︻");
			System.out.println("7-上一页");
			System.out.println("8-转跳指定页码");
			System.out.println("9-下一页");
			System.out.println("0-退出查询");
			System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼页码："+pageNo);
			try {
				//读取用户输入，尝试转换为整型
				i = Integer.parseInt(br.readLine());
				//转换成功，退出循环
			}catch(Exception e2) {
				System.err.println("-----输入信息有误，请输入数字7~9或0-----");
				System.out.println("当前页码："+pageNo+"︻︻︻︻︻︻︻︻︻︻︻︻︻︻可用操作");
				System.out.println("7-上一页");
				System.out.println("8-转跳指定页码");
				System.out.println("9-下一页");
				System.out.println("0-退出查询");
				System.out.println("︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼︼");
			}
			switch (i) {
				case 7:
					if(pageNo<=1) {
						System.err.println("----------------这是第一页------------------");
					}
					else {
						pageNo--;
					}
					break;
				case 8:
					System.out.println("请输入转跳页码：");
					try {
						pageNo = Integer.parseInt(br.readLine());
					} catch (IOException e) {
						System.err.println("------------您的输入有误，请输入数字-----------");
						e.printStackTrace();
					}
					break;
				case 9:
					if(ldto.size()<pageSize) {
						System.err.println("-------------这已经是最后一页----------------");
					}
					else {
						pageNo++;
					}
					break;
				case 0:
					ldto = null;
					flag = false;
					break;
				default:
					System.err.println("-------输入信息有误，请输入数字数字-------");
			}
		}
	}
	
	/**
	 * 根据用户名查找用户选择展示方法
	 */
	public void searchBySid() {
		//用户Controller类实例,用以查找用户信息
		UserController userctrl = new UserController();
		//声明缓冲处理流对象
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//用户模型，用以存储用户信息
		UserDto dto = null;
		//用户名
		int sid = 0;
		while(true) {
			System.out.println("请输入查找用户名：");
			try {
				//读取用户输入，尝试转换为整型
				sid = Integer.parseInt(br.readLine());
				//转换成功，退出循环
				break;
			}catch(Exception e) {
				System.err.println("---输入信息有误，请重新输入数字用户名---");
			}
		}
		dto = userctrl.searchUserBySid(sid);
		if(dto==null) {
			System.err.println("-------------查找的用户不存在------------");
		}
		else {
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
		}
		System.out.println("按Enter键结束浏览。");
		try {
			br.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加用户展示方法
	 */
	public void addShow() {
		//声明缓冲处理流对象,用以获取用户输入信息
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//用户Controller类实例,用以添加用户信息
		UserController userctrl = new UserController();
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
				System.out.println("邮箱格式不正确，请重新输入：");
			}
			Date brithday = null;
			while(true) {
				System.out.println("5.请输入出生日期（yyyy-mm-dd）：");
				String str_brithday = br.readLine();
				if(TypeUtil.checkDate(str_brithday)) {
					brithday = TypeUtil.strToDate(str_brithday);
					break;
				}
				System.out.println("日期格式不正确，请重新输入：");
			}
			int supervalue;
			while(true) {
				try {
					System.out.println("6.请输入用户权限（1-普通用户/2-管理员）：");
					//读取用户输入，尝试转换为整型
					supervalue = Integer.parseInt(br.readLine());
					//转换成功，退出循环
					break;
				}catch(Exception e) {
					System.err.println("------输入信息有误，请重新输入数字-----");
				}
			}
			//设置用户信息
			UserDto dto = new UserDto();
			dto.setSid(name);
			dto.setSpwd(password);
			dto.setSname(sname);
			dto.setEmail(email);
			dto.setBrithday(brithday);
			dto.setSupervalue(supervalue);
			
			//调用用户Controller对象方法执行注册操作，返回更新条目数
			int rs = userctrl.registerSuper(dto);
			if(rs<=0) {
				System.err.println("用户添加失败！按Enter键结束。");
			}
			else {
				System.out.println("用户添加成功！按Enter键结束。");
			}
			br.read();
		}catch(Exception e) {
			System.out.println("----输入信息有误，用户名必须为纯数字:----\n");
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		//用户Controller类实例,
		UserController userctrl = new UserController();
		
//		String str_date = "1996-07-05";
//		Date date = TypeUtil.strToDate(str_date);
//		UserDto dto = new UserDto();
//		dto.setSid(2016874213);
//		dto.setSname("Natan");
//		dto.setSpwd("123456");
//		dto.setEmail("Natah44@qq.com");
//		dto.setBrithday(date);
//		dto.setSupervalue(2);
//		dto.setStatus(1);
//		dto.setId(100);
//		String str = "1996-07-05";
//		System.out.println(str.length());
		
		List<UserDto> ldto = new ArrayList<UserDto>();
		ldto = userctrl.getAllUser();
		AdminFrame afm = new AdminFrame(null);
		afm.userShow(ldto);
	}
}
