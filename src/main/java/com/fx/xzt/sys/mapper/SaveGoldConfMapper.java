package com.fx.xzt.sys.mapper;


import com.fx.xzt.sys.entity.SaveGoldConf;

import java.util.List;

public interface SaveGoldConfMapper extends BaseMapper<SaveGoldConf>{
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:33
     * @Description：获取所有的存金宝产品
     * @return
     */
    List<SaveGoldConf> selectAllProduct();

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:34
     * @Description：根据id删除存金宝产品
     * @param id
     * @return
     */
    int deleteOneById(Long id);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:35
     * @Description：修改选中的存金宝产品
     * @param saveGoldConf
     * @return
     */
    int updateProductById(SaveGoldConf saveGoldConf);
}