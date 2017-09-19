package com.fx.jyg.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.fx.jyg.sys.entity.Users;
import com.fx.jyg.sys.model.UsersModel;
import com.fx.jyg.sys.service.UsersService;
import com.github.pagehelper.PageInfo;

/**
 * 
* @ClassName: UserController 
* @Description: 用户操作类 
* @author jcwang
* @date 2017年7月28日 下午3:56:08 
*
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Resource
	private UsersService userService;
	
	/**
	 * 插入新用户
	 * @param userInfo 用户信息类
	 * @return  成功:保存入库
	 *          失败:提示用户保存失败
	 */
	@ResponseBody
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestBody Users userInfo){
		if(userInfo.getStatus()==null){
			userInfo.setStatus("0");
		}
		Integer count = userService.save(userInfo);
		if(count>0){
			return "保存用户信息成功";
		}
		return "保存用户信息失败";
	}
	
	/**
	 * 查询用户信息根据用户id
	 * @param uid  用户ID
	 * @return   成功:提示找到的用户名
	 *           失败:查找失败
	 */
	@ResponseBody
	@RequestMapping(value="/select", method=RequestMethod.GET)
	public String select(@RequestParam Long uid){
		Users userInfo = userService.selectByKey(uid);
		if(userInfo!=null){
			return "您要查找的用户名是:"+userInfo.getUserName();
		}
		return "查找用户失败";
	}
	
	/**
	 * 根据用户ID删除用户
	 * @param uid  用户ID
	 * @return   成功:成功删除用户
	 *           失败:删除失败
	 */
	@ResponseBody
	@RequestMapping(value="/delete", method=RequestMethod.DELETE)
	public String delete(@RequestParam Long uid){
		Integer count = userService.delete(uid);
		if(count>0){
			return "删除用户信息成功";
		}
		return "删除用户信息失败";
	}
	/**
	 * 添加用户 手机号 和 密码必须传递
	 */
	@RequestMapping(value="/insertUser")
	@ResponseBody
	public Map<String,Object> insertUser(Users users,@RequestParam(value="rids", required=false)List<Integer> rids){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userService.insertUsers(users, rids);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public Map<String,Object> deleteUser(Long id){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userService.deleteById(id);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 更新用户 id 手机号 和 密码必须传递
	 */
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public Map<String,Object> updateUser(Users users,@RequestParam(value="rids", required=false)List<Integer> rids){
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userService.updateByIdSelective(users, rids);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 用户信息列表
	 */
	@RequestMapping(value="/selectByUsers")
	@ResponseBody
	public PageInfo<UsersModel> selectByUsers(String phone, String startTime, String endTime, Integer pageNum,
			Integer pageSize){
		return userService.selectByUsersModel(phone, startTime, endTime, pageNum, pageSize);
	}
}