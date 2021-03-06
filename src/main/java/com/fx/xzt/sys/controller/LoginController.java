package com.fx.xzt.sys.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.HttpResponse;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import tk.mybatis.mapper.entity.Example;

import com.fx.xzt.redis.RedisService;
import com.fx.xzt.shiro.MyAuthenticationToken;
import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.service.UsersService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.ConstantUtil;
import com.fx.xzt.sys.util.StringUtil;
import com.fx.xzt.util.CaptchaUtil;
import com.fx.xzt.util.IdUtil;
import com.fx.xzt.util.LoggerUtils;
import com.fx.xzt.util.MD5Utils;
import com.fx.xzt.util.OSCache;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author jcwang
 * @ClassName: LoginController
 * @Description: 登录操作类
 * @date 2017年7月28日 下午3:52:32
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    private final Logger logger = LoggerUtils.getLogger("LoginController");

    @Resource
    private UsersService userService;
    @Resource
    private RedisService redisService;

    /**
     * 基于手机号和密码的身份认证
     *
     * @param request 用户请求
     * @return 成功:返回该用户可操作的菜单
     * 失败:返回登录页,清除token
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Map checkLogin(HttpServletResponse response, String userName, String password, Model model, HttpServletRequest request, String validateCode) {
        Map<String, Object> map = new HashMap<String, Object>();
        Users users = (Users) request.getSession().getAttribute("currentUser");
        if (users != null){
            map.put("msg","您已登录其他账号，请退出或重启浏览器后再登录");
            return map;
        }
        String sessionId = request.getSession().getId();
        HttpSession session = request.getSession(false);
        logger.debug("userInfo", userName);
        password = MD5Utils.encrypt(password);
        MyAuthenticationToken token = new MyAuthenticationToken(userName, password, true, null);
        Subject subject = SecurityUtils.getSubject();
        try {
            if (!StringUtil.isNotEmpty(validateCode)) {
                map.put("msg", "请输入验证码");
                return map;
            }
            char[] strs = validateCode.toCharArray();
            for (int i = 0; i < strs.length; i++) {
                if ('a' <= strs[i] && strs[i] <= 'z') {
                    strs[i] = (char) (strs[i] - 32);
                }
            }
            validateCode = String.valueOf(strs);
            if (redisService.get(sessionId) == null) {
                map.put("msg", "验证码错误");
                return map;
            }
            if (!validateCode.equals(redisService.get(sessionId).toString())) {
                map.put("msg", "验证码错误");
                return map;
            }
            redisService.delete(sessionId);
            subject.login(token);//会到自定义的Realm中进行验证返回
            if (subject.isAuthenticated()) {
                Example example = new Example(Users.class);
                Example.Criteria criteria = example.createCriteria();
                PageHelper.startPage(1, 1);
                List<Users> li = userService.selectByExample(example);
                PageInfo<Users> pagehelper = new PageInfo<Users>(li);
                model.addAttribute("pagehelper", pagehelper);
                System.out.println("COMPANY_OFFICIAL_WEBSITE_ADDRESS::::::::::::::::" + OSCache.getValueByName("COMPANY_OFFICIAL_WEBSITE_ADDRESS"));
                map.put("msg", "1");
                return map;
            } else {
                token.clear();
            }
        } catch (AuthenticationException e) {
            e.printStackTrace();
            model.addAttribute("msg", e.getMessage());
        }

        map.put("msg", "0");
        return map;
    }

    /**
     * 用户登出
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    @ResponseBody
    public CommonResponse logout() {
        CommonResponse response = new CommonResponse();
        try {
            SecurityUtils.getSubject().logout();
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
            response.setData(1);
            response.setMsg("退出成功");
        } catch (Exception e) {
            response.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            response.setData(0);
            response.setMsg("退出失败");
        }
        return response;
    }

    /**
     * 获取验证码
     */
    @RequestMapping(value = "/captcha", method = RequestMethod.GET)
    @ResponseBody
    public void captcha(HttpServletRequest request, HttpServletResponse response, String randomCode)
            throws ServletException, IOException {
        String sessionId = request.getSession().getId();
        Map map = CaptchaUtil.outputCaptcha();
        // 将四位数字的验证码保存到Session中。
        redisService.put(sessionId, map.get("code").toString(), 1);
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        // 将图像输出到Servlet输出流中。
        ServletOutputStream sos = response.getOutputStream();
        ImageIO.write((BufferedImage) map.get("buffImg"), "jpeg", sos);
        sos.close();
    }
}
