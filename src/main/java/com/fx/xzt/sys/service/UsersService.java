package com.fx.xzt.sys.service;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.UsersModel;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserService 
* @Description: 用户操作接口声明
* @author jcwang
* @date 2017年7月31日 下午4:01:57 
*
 */
public interface UsersService extends IService<Users>{

	/** 从缓存中获取 */
	Users getUserInfo(Long uid);

	/** 获取用户信息集合 */
	List<Users> listUserInfo();

	/** 根据用户名获取用户信息 */
	Users getUserInfoNyName(String currentUserName);

	/** 根据用户名密码校验用户是否存在 */
	Users getUserInfo(String username, String password);

	/** 根据手机号密码校验用户是否存在 */
	Users getUserInfoNyPhone(String phone, String password);
	/**
	 * 添加
	 * @param users
	 * @return
	 */
	int insertUsers(Users users,List<Integer> rids);
	/**
	 * 更新
	 * @param users
	 * @return
	 */
	int updateByIdSelective(Users users,List<Integer> rids);
	/**
	 * 删除
	 * @param
	 * @return
	 */
	int deleteById(Long id);
	
	PageInfo<UsersModel> selectByUsersModel(String phone,String startTime,String endTime,Integer pageNum,Integer pageSize);

	/**
	 *  获取代理商列表
	 * @return
	 */
	List<Map<String,Object>> selectByAgentMessage(Long pid);
	List<Map<String,Object>> selectByAgentMessage1();

	/**
	 * 获取经纪人列表
	 * @param pid
	 * @return
	 */
	List<Map<String,Object>> selectByBrokerMessage(String  pid);
	List<Map<String,Object>> selectByBrokerMessage2(Long pid);
	List<Map<String,Object>> selectByBrokerMessage1(String pid);

	/**
	 * @param usersInfo
	 * @return int
	 * @Author:  tianliya
	 * @Description: 运营商视角-新建经纪人
	 * @Date:11:15 2017/10/21
	 */
	public int insertAgent(Users usersInfo);
	
	/**
	 * 
	* @Title: selectByChannelMessage 
	* @Description: 获取渠道商数据
	* @return    设定文件 
	* @return List<Map<String,Object>>    返回类型 
	* @throws 
	* @author htt
	 */
	List<Map<String,Object>> selectByChannelMessage();

	/**
	 * 小象视角-查询
	 * @param
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	PageInfo<Map<String, Object>> sightOfElephant(String  id,String pid,String startTime, String endTime, Integer pageNum, Integer pageSize,Integer type);

	/**
	 * 商户管理-运营商视角
	 * @param id
	 * @param pid
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	PageInfo<Map<String, Object>> sightOfOperator(Long pid,String startTime, String endTime, Integer pageNum, Integer pageSize);
	/**
	 * 小象视角-导出
	 * @param id
	 * @param pid
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<Map<String, Object>> execelSightOfElephant(String id , String  pid,
														   String startTime, String endTime
	);

	public Users getUser(Long id);

}
