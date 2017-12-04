package com.fx.xzt.sys.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.fx.xzt.sys.entity.LogRecord;
import com.fx.xzt.sys.entity.UserInfo;
import com.fx.xzt.sys.service.LogRecordService;
import com.fx.xzt.sys.util.*;
import com.fx.xzt.sys.util.log.AuditLog;
import com.fx.xzt.util.MD5Utils;
import com.fx.xzt.util.POIUtils;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fx.xzt.sys.entity.Users;
import com.fx.xzt.sys.model.UsersModel;
import com.fx.xzt.sys.service.UsersService;
import com.github.pagehelper.PageInfo;

import static com.fx.xzt.sys.util.GenerateQRCodeUtil.toBufferedImage;

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
	@Resource
	LogRecordService logRecordService;
	
	/**
	 * 插入新用户
	 * @param userInfo 用户信息类
	 * @return  成功:保存入库
	 *          失败:提示用户保存失败
	 */
	@ResponseBody
	@RequestMapping(value="/insert", method=RequestMethod.POST)
	public String insert(@RequestBody Users userInfo,HttpServletRequest request) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("添加用户");
		log.setContent("添加失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		if(userInfo.getStatus()==null){

			userInfo.setStatus("1");
		}
		Integer count = userService.save(userInfo);
		if(count>0){
			log.setUserId(users.getId());
			log.setContent("获取成功");
			logRecordService.add(log);
			AuditLog.info(log.toString());
			return "保存用户信息成功";
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
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
	public String select(@RequestParam Long uid,HttpServletRequest request) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("查询用户信息");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		Users userInfo = userService.selectByKey(uid);
		HttpSession httpSession = request.getSession();
		Users users = (Users) httpSession.getAttribute("currentUser");
		if(userInfo!=null){
			logRecordService.add(log);
			AuditLog.info(log.toString());
			return "您要查找的用户名是:"+userInfo.getUserName();
		}
		log.setUserId(users.getId());
		log.setContent("获取成功");
		logRecordService.add(log);
		AuditLog.info(log.toString());
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
	public String delete(@RequestParam Long uid,HttpServletRequest request) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("优惠券查询查询");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		Integer count = userService.delete(uid);
		if(count>0){
			logRecordService.add(log);
			AuditLog.info(log.toString());
			return "删除用户信息成功";
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return "删除用户信息失败";
	}
	/**
	 * 添加用户 手机号 和 密码必须传递
	 */
	@RequestMapping(value="/insertUser")
	@ResponseBody
	public Map<String,Object> insertUser(Users users,@RequestParam(value="rids", required=false)List<Integer> rids){
		Map<String,Object> map = new HashMap<String,Object>();
		users.setPid(new Long(1));
		users.setStatus("1");
		int msg = userService.insertUsers(users, rids);
		map.put("msg", msg);
		return map;
	}
	/**
	 * 删除
	 */
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public Map<String,Object> deleteUser(Long id,HttpServletRequest request) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("删除用户");
		log.setContent("删除失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		Map<String,Object> map = new HashMap<String,Object>();
		int msg = userService.deleteById(id);
		map.put("msg", msg);
		logRecordService.add(log);
		AuditLog.info(log.toString());
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
			Integer pageSize,HttpServletRequest request){
		return userService.selectByUsersModel(phone, startTime, endTime, pageNum, pageSize);
	}

	/**
	 * 获取代理商列表
	 * @return
	 */
	@RequestMapping(value="/selectByAgentMessage")
	@ResponseBody
	public Object selectByAgentMessage(){
		CommonResponse cr = new CommonResponse();
        try {
        	List<Map<String, Object>> list = userService.selectByAgentMessage();
        	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
            cr.setData(list);
            cr.setMsg("操作成功！");
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }
        return cr;
	}

	/**
	 * 根据代理商获取经纪人列表,若pid为空则取当前登录人下的经纪人
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/selectByBrokerMessage")
	@ResponseBody
	public Object selectByBrokerMessage(HttpServletRequest request, @RequestParam String pid){
		CommonResponse cr = new CommonResponse();
        try {
        	Long pidl = null;
        	HttpSession httpSession = request.getSession();
            Users users = (Users) httpSession.getAttribute("currentUser");
            if (users != null) {
            	if (StringUtil.isNotEmpty(pid)) {
            		pidl = Long.parseLong(pid);
            	} else {
            		pidl = users.getId();
            	}
            	List<Map<String, Object>> list = userService.selectByBrokerMessage(pidl);
            	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
                cr.setData(list);
                cr.setMsg("操作成功！");

            } else {
            	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_NOAUTH);
                cr.setData("{}");
                cr.setMsg("操作失败！");
            }
        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }
        return cr;
	}

	/**
	 * 运营商视角 - 查询  tianliya
	 * @param request
	 * @param pid
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/sightOfCarrieroperator")
	@ResponseBody
	public Object sightOfCarrieroperator(HttpServletRequest request, Long pid, String startTime, String endTime, Integer pageNum, Integer pageSize) throws ParseException {
		CommonResponse cr = new CommonResponse();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("代理商视角");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null){
				pid = users.getId();
				PageInfo<Map<String, Object>> list = userService.sightOfOperator(pid,startTime,endTime,pageNum,pageSize);
				cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				cr.setData(list);
				cr.setMsg("操作成功！");

				log.setUserId(users.getId());
				log.setContent("查询成功");
			}

		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return cr;
	}

	/**
	 * @param request
	 * @param usersInfo
	 * @return
	 * @Author:  tianliya
	 * @Description:新建代理商
	 * @Date:11:03 2017/10/21
	 */
	@RequestMapping(value="/insertAgent",method = RequestMethod.POST)
	@ResponseBody
	public Object insertAgent(HttpServletRequest request,Users usersInfo) throws ParseException {
		CommonResponse cr = new CommonResponse();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("新建代理商");
		log.setContent("新建失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null){
				users = userService.getUserInfo(users.getUserName(),users.getPassword());
				Long pid = users.getId();
				usersInfo.setPassword(MD5Utils.encrypt(usersInfo.getPassword()));
				usersInfo.setPid(pid);
				int i = userService.insertAgent(usersInfo);
				if (i != 0){
					cr.setData(i);
					cr.setMsg("操作成功！");
					log.setUserId(users.getId());
					log.setContent("新建成功");
				}
				else{
					cr.setData(i);
					cr.setMsg("操作失败！");
				}
				cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
			}
		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return cr;
	}

	
	/**
	 * 
	* @Title: selectByChannelMessage 
	* @Description: TODO
	* @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	* @author htt
	 */
	@RequestMapping(value="/selectByChannelMessage")
	@ResponseBody
	public Object selectByChannelMessage(){
		CommonResponse cr = new CommonResponse();
        try {
        	List<Map<String, Object>> list = userService.selectByChannelMessage();
        	cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
            cr.setData(list);
            cr.setMsg("操作成功！");

        } catch (Exception e) {
            cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
            cr.setData("{}");
            cr.setMsg("操作失败！");
            throw e;
            // e.printStackTrace();
        }

        return cr;
	}

	/**
	 * 小象视角 - 查询  tianliya
	 * @param request
	 * @param startTime
	 * @param endTime
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value="/sightOfElephant")
	@ResponseBody
	public Object sightOfsightOfElephant(HttpServletRequest request, Long agentName ,Long brokerName, String startTime, String endTime, Integer pageNum, Integer pageSize) throws ParseException {
		CommonResponse cr = new CommonResponse();
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("小象管理視角");
		log.setContent("查询失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null){
				PageInfo<Map<String, Object>> list = userService.sightOfElephant(brokerName,agentName,startTime,endTime,pageNum,pageSize);
				cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_SUCCESS_DATA);
				cr.setData(list);
				cr.setMsg("操作成功！");
				log.setUserId(users.getId());
				log.setContent("查询成功");
			}

		} catch (Exception e) {
			cr.setCode(ConstantUtil.COMMON_RESPONSE_CODE_EXCEPTION);
			cr.setData("{}");
			cr.setMsg("操作失败！");
			throw e;
			// e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
		return cr;
	}

	/**
	 * 导出 小象视角查询的结果
	 * @param request
	 * @param response
	 * @param agentName
	 * @param brokerName
	 * @param startTime
	 * @param endTime
	 * @throws Exception
	 */
	@RequestMapping(value="/excelSightOfElephant")
	@ResponseBody
	public void excelSightOfElephant(HttpServletRequest request,HttpServletResponse response,
									 Long agentName , Long brokerName, String startTime,
									 String endTime) throws Exception{
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("导出小想管理视角查询结果");
		log.setContent("导出失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));

		try {
			String tieleName = "小象管理视角";
			String excelName = "小象管理视角";
			HttpSession httpSession = request.getSession();
			Users users = (Users) httpSession.getAttribute("currentUser");
			if (users != null) {
				List<Map<String, Object>> list = userService.execelSightOfElephant(brokerName,agentName,startTime,endTime);
					POIUtils poi = new POIUtils();
					String[] heads = {"商户名", "姓名", "类型", "代理商", "创建时间"};
					String[] colums = {"userName", "userName",  "type", "agentName", "createTime"};
					poi.doExport(request, response, list, tieleName, excelName, heads, colums);
				log.setUserId(users.getId());
				log.setContent("导出成功");
				}
			}catch (Exception e){
				e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
	}
	/**
	 * 生成二维码
	 */
	@RequestMapping(value = "/generateQRCode")
	@ResponseBody
	public void createCode(HttpServletRequest request,HttpServletResponse response,String userName,Long id) throws ParseException {
		//操作日志
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		LogRecord log = new LogRecord();
		log.setTitle("生成二维码");
		log.setContent("生成失败");
		log.setModuleName(ConstantUtil.logRecordModule.YHQ.getName());
		log.setType(ConstantUtil.logRecordType.CX.getIndex());
		log.setIp(IPUtil.getHost(request));
		log.setCreateTime(sdf.parse(sdf.format(new Date())));
		try {
			Users users = userService.getUser(id);
			if (users.getId() != null && users.getPid()!= null)
				if (users.getPid() == 1){
					GenerateQRCodeUtil.generateQRCode(response,userName,users.getId(),users.getPid());
				}else{
					GenerateQRCodeUtil.generateQRCode(response,userName,users.getId(),users.getPid());
				}

			log.setContent("生成成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
		logRecordService.add(log);
		AuditLog.info(log.toString());
	}
	/**
	 * 生成二维码  并返回到浏览器
	 */
	@RequestMapping(value = "/createQRCode")
	@ResponseBody
	public void createCode(String userName,Long id,HttpServletResponse response){
		int width = 300;
		int height = 300;
		String format = "png";
		Hashtable<EncodeHintType, Object> hints = new Hashtable<>();
		hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
		try {
			Users users = userService.getUser(id);
			BitMatrix bitMatrix = null;
			if (users.getPid()==1){
				 bitMatrix = new MultiFormatWriter().encode("http://192.168.0.37:8081/act1/register.html?brokerId=&agentId="+users.getId(),BarcodeFormat.QR_CODE, width, height, hints);
				 //bitMatrix = new MultiFormatWriter().encode("http://116.255.188.180:10080/act1/register.html?brokerId=&agentId="+users.getId(),BarcodeFormat.QR_CODE, width, height, hints);
			}else{
				 bitMatrix = new MultiFormatWriter().encode("http://192.168.0.37:8081/act1/register.html?brokerId="+users.getId()+"&agentId="+users.getPid(),BarcodeFormat.QR_CODE, width, height, hints);
				 //bitMatrix = new MultiFormatWriter().encode("http://116.255.188.180:10080/act1/register.html?brokerId="+users.getId()+"&agentId="+users.getPid(),BarcodeFormat.QR_CODE, width, height, hints);
			}

			GenerateQRCodeUtil.writeToStream(response,bitMatrix,format);
		} catch (WriterException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
