package com.fx.xzt.sys.service;

import com.fx.xzt.sys.entity.SaveGoldConf;
import com.fx.xzt.sys.util.CommonResponse;


/**
 * @author tianliya
 * @Description:
 * @date 13:16 2017/10/16
 */
public interface SaveGoldConfService extends IService<SaveGoldConf>{
    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:37
     * @Description：获取所有的存金宝产品
     * @param pageNum
     * @param pageSize
     * @return
     */
    CommonResponse getAllProduct(Integer pageNum,Integer pageSize);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:39
     * @Description：删除选中的存金宝产品
     * @param id
     * @return
     */
    CommonResponse removeProduct(Long id);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:39
     * @Description：修改选中的存金宝产品
     * @param saveGoldConf
     * @return
     */
    CommonResponse modifyById(SaveGoldConf saveGoldConf);
}
