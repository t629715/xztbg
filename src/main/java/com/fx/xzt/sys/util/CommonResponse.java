package com.fx.xzt.sys.util;

/**
 * @author htt
 * @ClassName: CommonResponse.java
 * @Description: 接口返回格式化
 * @date 2017-09-21 18:04
 */
public class CommonResponse {

    private Integer code;	//返回码
    private String msg;		//返回提示信息
    private Object data;	//返回数据

    public CommonResponse() {
        super();
    }

    public CommonResponse(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
