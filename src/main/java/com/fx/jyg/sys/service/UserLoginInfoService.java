package com.fx.jyg.sys.service;

import java.util.Date;
import java.util.List;

import com.fx.jyg.sys.entity.UserLoginInfo;
import com.github.pagehelper.PageInfo;

public interface UserLoginInfoService extends IService<UserLoginInfo>{
	PageInfo<UserLoginInfo> getfindAll(String userName,String loginFrom,String startTime,String endTime,Integer pageNum,Integer pageSize);
}
