package com.fx.xzt.sys.service;

import java.util.List;

/**
 * 
* @ClassName: IService 
* @Description: 通用接口
* @author jcwang
* @date 2017年8月2日 下午3:45:26 
* 
* @param <T>
 */
public interface IService<T> {

    T selectByKey(Object key);

    int save(T entity);

    int delete(Object key);

    int updateAll(T entity);

    int updateNotNull(T entity);

    List<T> selectByExample(Object example);

}
