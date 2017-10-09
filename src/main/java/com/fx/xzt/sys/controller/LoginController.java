package com.fx.xzt.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.shiro.MyAuthenticationToken;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.UsersService;
import com.fx.xzt.util.LoggerUtils;
import com.fx.xzt.util.MD5Utils;
import com.fx.xzt.util.OSCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import tk.mybatis.mapper.entity.Example;

/**
 * 
* @ClassName: LoginController 
* @Description: 登录操作类 
* @author jcwang
* @date 2017年7月28日 下午3:52:32 
*
 */
@Controller
@RequestMapping("/login")
public class LoginController {
	private final Logger logger = LoggerUtils.getLogger("LoginController");

	@Resource
	private UsersService userService;
	
	/**
	 * 基于手机号和密码的身份认证
	 * @param userInfo  用户信息
	 * @param model     
	 * @param request  用户请求
	 * @return 成功:返回该用户可操作的菜单  
	 *         失败:返回登录页,清除token
	 */
	@RequestMapping(value="/login",method=RequestMethod.POST)
	@ResponseBody
	public Map checkLogin( Users userInfo,Model model,HttpServletRequest request){
		Map<String,Object> map = new HashMap<String,Object>();
		logger.debug("userInfo", userInfo);
		String password = MD5Utils.encrypt(userInfo.getPassword());
		MyAuthenticationToken token = new MyAuthenticationToken(userInfo.getPhone(), password, true, null);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);//会到自定义的Realm中进行验证返回
			if(subject.isAuthenticated()){
				    Example example = new Example(Users.class);
			        Example.Criteria criteria = example.createCriteria();
			        PageHelper.startPage(1, 1);
			        List<Users> li = userService.selectByExample(example);
			        PageInfo<Users> pagehelper = new PageInfo<Users>(li);
			        model.addAttribute("pagehelper",pagehelper);
			        System.out.println("COMPANY_OFFICIAL_WEBSITE_ADDRESS::::::::::::::::" + OSCache.getValueByName("COMPANY_OFFICIAL_WEBSITE_ADDRESS"));
			    	map.put("msg", "1");
			        return map;
			}else{
				token.clear();
			}
		}catch(AuthenticationException e){
			e.printStackTrace();
			model.addAttribute("msg",e.getMessage());
		}
		map.put("msg", "0");
		return map;
	}
	
	 /** 
     * 用户登出 
     */  
    @RequestMapping("/logout")  
    public String logout(){  
         SecurityUtils.getSubject().logout();
         return "login";
    } 
}
