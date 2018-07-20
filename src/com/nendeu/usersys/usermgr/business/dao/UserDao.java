package com.nendeu.usersys.usermgr.business.dao;

import java.util.List;
import java.util.Vector;

import com.nendeu.usersys.common.dao.BaseDao;
import com.nendeu.usersys.usermgr.business.domain.UserDto;
/**
 * 用户Dao接口
 * 所以用户Dao实例都继承自该接口
 * @author Administrator
 *
 */
public interface UserDao extends BaseDao {
	/**
	 * 用户dao登录方法
	 * @param sid 登录用户名
	 * @param password 登录密码
	 * @return 用户信息
	 */
	public UserDto login(int sid,String password);

	/**
	 * 用户dao普通用户注册方法
	 * @param dto 增加的用户信息
	 * @return 更新记录的条目数
	 */
	public int signUp(UserDto dto);

	/**
	 * 用户dao管理员添加方法
	 * @param dto 增加的用户信息
	 * @return 更新记录的条目数
	 */
	public int signUpSuper(UserDto dto);
	
	/**
	 * 用户dao管理员删除方法
	 * @param dto 删除的用户名
	 * @return 更新记录的条目数
	 */
	public int deleteUserById(int sid);
	
	/**
	 * 用户dao普通用户更新信息方法
	 * @param dto 更新信息
	 * @return 更新后信息
	 */
	public UserDto updateNormal(UserDto dto);

	/**
	 * 用户dao更新密码方法
	 * @param sid 用户名
	 * @param password 新密码
	 * @return 更新密码
	 */
	public String updateSpwdById(int sid,String password);
	
	/**
	 * 用户dao根据id查找用户方法
	 * @param sid 用户名
	 * @return 用户信息
	 */
	public UserDto searchBySid(int sid);

	/**
	 * 用户dao管理员更新信息方法
	 * @param dto 更新信息
	 * @return 更新条目数
	 */
	public int updateAdmin(UserDto dto);

	/**
	 * 用户dao管理员获取所有用户信息方法
	 * @return 用户信息集合
	 */
	public List<UserDto> getAllUser();

	/**
	 * 用户dao管理员分页获取所有用户信息方法
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> getAllUser(int pageNo,int pageSize);

	/**
	 * 用户dao管理员分页获取有效用户信息方法
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> getValidUser(int pageNo, int pageSize);

	/**
	 * 用户dao分页模糊方法
	 * @param searchStr 查询字符串
	 * @param pageNo 页码
	 * @param pageSize 分页大小
	 * @return 页用户信息集合
	 */
	public List<UserDto> fuzzSearchByName(String searchStr, int pageNo, int pageSize);

}
