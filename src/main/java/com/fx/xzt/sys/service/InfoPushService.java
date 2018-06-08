package com.fx.xzt.sys.service;


import com.fx.xzt.sys.entity.InfoPush;
import com.fx.xzt.sys.util.CommonResponse;

/**
 * @CreateBy：tianliya
 * @CreateTime：2018/6/5 14:18
 * @Description：首页资讯推送业务接口
 */
public interface InfoPushService extends IService<InfoPush>{
	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/5 14:19
	 * @Description：获取所有的推送的资讯
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	CommonResponse getAllPushes(Integer pageNum, Integer pageSize);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/5 14:19
	 * @Description：增加一条资讯推送
	 * @param infoPush
	 * @return
	 */
	CommonResponse addOnePush(InfoPush infoPush);

	/**
	 * @CreateBy：tianliya
	 * @CreateTime：2018/6/5 14:20
	 * @Description：删除一条推送资讯
	 * @param id
	 * @return
	 */
	CommonResponse removeById(String id);

}
