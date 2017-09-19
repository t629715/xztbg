package com.fx.xzt.sys.service;

import java.util.Date;
import java.util.List;

import com.fx.xzt.sys.entity.UserLoginInfo;
import com.github.pagehelper.PageInfo;

public interface UserLoginInfoService extends IService<UserLoginInfo>{
	PageInfo<UserLoginInfo> getfindAll(String userName,String loginFrom,String startTime,String endTime,Integer pageNum,Integer pageSize);
}
