package com.fx.xzt.shiro;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;

import com.fx.xzt.exception.AuthenticateException;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.entity.UsersPermission;
import com.fx.xzt.sys.entity.UsersRole;
import com.fx.xzt.sys.entity.UsersUserRole;
import com.fx.xzt.sys.mapper.UsersUserRoleMapper;
import com.fx.xzt.sys.service.UsersRoleService;
import com.fx.xzt.sys.service.UsersService;
import com.fx.xzt.sys.service.UsersUserRoleService;

/**
 * 
* @ClassName: MyRealm 
* @Description: 重写shiro的realm实现类
* @author jcwang
* @date 2017年7月31日 下午3:56:08 
*
 */
public class MyRealm extends AuthorizingRealm{
	
	@Resource
	private UsersService userService;
	@Resource
	UsersUserRoleService usersUserRoleService;
	@Resource
	UsersRoleService usersRoleService;
	@Resource
	private UsersUserRoleMapper usersUserRoleMapper;
	
	public MyRealm() {
		setAuthenticationTokenClass(AuthenticationToken.class);
	}

	/**
	 * 重写认证方法
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		Users userInfo = null;
		
		if(token instanceof MyAuthenticationToken){
			MyAuthenticationToken myToken = (MyAuthenticationToken) token;
			String phone = myToken.getPhone();
			String password = new String(myToken.getPassword());
			try {
				userInfo = userService.getUserInfoNyPhone(phone,password);
			} catch (AuthenticateException e){
				throw new IncorrectCredentialsException(e);
			}
			
			if (null == userInfo) {
				throw new IncorrectCredentialsException("账号或密码错误！");
			}
			
			if ("0".equals(userInfo.getStatus())) {
				throw new DisabledAccountException("帐号"+myToken.getPrincipal()+"已失效！");
			}
			
			//比对成功则返回authcInfo
    	    AuthenticationInfo authcInfo = new SimpleAuthenticationInfo(userInfo, userInfo.getPassword().toCharArray(),this.getName());  
    	    this.setSession("currentUser", userInfo);
    	    
    	    Map<String, Object> map = new HashMap<String, Object>();
    	    String id = "";
    	    String roleIsView = "0";
			
    	    List<Integer> rids = usersUserRoleService.selectByUserId(userInfo.getId().intValue());
    	    if (rids != null && rids.size() == 1) {
    	    	Integer rid = rids.get(0);
    	    	Map<String, Object> map1 = usersRoleService.getById(rid.toString());
    	    	if (map1 != null) {
    	    		id = map1.get("id").toString();
    	    		roleIsView = map1.get("isView").toString();
    	    	}
    	    } 
    	    map.put("id",id);
			map.put("roleIsView",roleIsView);
    	    this.setSession("currentUserRole", map);
    	    return authcInfo; 
		}
		return null;
	}
	
    /**
     * 为认证成功的用户授权,然后就可以调用subject.hasRole等方法进行逻辑判断
     */
    @Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
    	//认证通过后会将需要的身份信息放到SimpleAuthenticationInfo中返回，这是是获取到其中的principal对象
    	Iterator<?> iterator = principals.fromRealm(getName()).iterator();
    	if (iterator.hasNext()) {
    		Users userInfo = (Users) iterator.next();
    		if (null != userInfo) {
    			List<String> roleList = new ArrayList<String>();
    			List<String> permissionList = new ArrayList<String>();
    			//查询用户角色
    			List<UsersPermission> usersPermissions = usersUserRoleService.getByUserId(userInfo.getId());
    			if(null !=usersPermissions){
    				if(null !=usersPermissions){
    					for(UsersPermission menu:usersPermissions){
    						permissionList.add(menu.getText());
    					}
    				}
    			}
    			
    			//为用户添加权限，角色
    			SimpleAuthorizationInfo simpleAuthorInfo = new SimpleAuthorizationInfo();
    			simpleAuthorInfo.addRoles(permissionList);
    			simpleAuthorInfo.addStringPermissions(permissionList);
    			return simpleAuthorInfo;
    		}
    	}
    	
		return null;
	}

    /** 
     * 将一些数据放到ShiroSession中,以便于其它地方使用 
     *  比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     * 也可以直接从subject的身份对象中取
     */  
    private void setSession(Object key, Object value){  
        Subject currentUser = SecurityUtils.getSubject();  
        if(null != currentUser){
            Session session = currentUser.getSession();  
            if(null != session){  
                session.setAttribute(key, value);  
            }  
        }  
    }

}
