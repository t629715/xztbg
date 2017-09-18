package com.fx.jyg.sys.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fx.jyg.sys.service.IService;

import tk.mybatis.mapper.common.Mapper;

/**
 * 
* @ClassName: BaseService 
* @Description: 基础实现类
* @author jcwang
* @date 2017年8月2日 下午4:00:05 
* 
* @param <T>
 */
public abstract class BaseService<T> implements IService<T> {

    @Autowired
    protected Mapper<T> mapper;

    public Mapper<T> getMapper() {
        return mapper;
    }

    public T selectByKey(Object key) {
        return mapper.selectByPrimaryKey(key);
    }

    public int save(T entity) {
        return mapper.insert(entity);
    }

    public int delete(Object key) {
        return mapper.deleteByPrimaryKey(key);
    }

    public int updateAll(T entity) {
        return mapper.updateByPrimaryKey(entity);
    }

    public int updateNotNull(T entity) {
        return mapper.updateByPrimaryKeySelective(entity);
    }

    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

}
