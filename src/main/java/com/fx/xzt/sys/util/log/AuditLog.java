package com.fx.xzt.sys.util.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AuditLog {

private static final Logger logger = LoggerFactory.getLogger(AuditLog.class);
    
    /**
     * 内容格式（模块名::操作类型::客户端IP::客户端设备::操作人::标题::内容）
     * 操作类型：查看、增加、修改、逻辑删除、物理删除、导入、导出、授权、审核、登录、重置密码
     * 模块名：功能模块
     * @param msg
     */
    public static void info(String msg) {
      //  logger.info(msg);
    	System.out.println("==================msg:" + msg);
        logger.info(msg);
    }
    
    /**
     * 内容格式（模块名::操作类型::客户端IP::客户端设备::操作人::标题::内容）
     * 操作类型：查看、增加、修改、逻辑删除、物理删除、导入、导出、授权、审核、登录、重置密码
     * 模块名：功能模块
     * @param msg
     */
    public static void info(String format, Object... arguments) {
        logger.info(format, arguments);
    }

}
