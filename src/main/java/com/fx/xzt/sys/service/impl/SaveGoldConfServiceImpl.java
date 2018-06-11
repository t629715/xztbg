package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.RealGoldConf;
import com.fx.xzt.sys.entity.SaveGoldConf;
import com.fx.xzt.sys.mapper.RealGoldBuyConfMapper;
import com.fx.xzt.sys.mapper.RealGoldConfMapper;
import com.fx.xzt.sys.mapper.SaveGoldConfMapper;
import com.fx.xzt.sys.service.RealGoldConfService;
import com.fx.xzt.sys.service.SaveGoldConfService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

/**
 * @author tianliya
 * @Description: 实金买卖设定实现类
 * @date 13:19 2017/10/16
 */
@Service
public class SaveGoldConfServiceImpl extends BaseService<SaveGoldConf> implements SaveGoldConfService {
    @Resource
    private SaveGoldConfMapper saveGoldConfMapper;

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:42
     * @Description：获取所有的存金宝产品
     */
    @Override
    public CommonResponse getAllProduct(Integer pageNum, Integer pageSize) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            List<SaveGoldConf> saveGoldConfs = saveGoldConfMapper.selectAllProduct();
            commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
            commonResponse.setMsg("获取成功");
            commonResponse.setData(saveGoldConfs);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("删除选中的存金宝产品", "删除选中的存金宝产品异常");
        }
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:42
     * @Description：删除选中的存金宝产品
     */
    @Override
    @Transactional
    public CommonResponse removeProduct(Long id) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            int i = saveGoldConfMapper.deleteOneById(id);
            if (i > 0) {
                commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
                commonResponse.setMsg("删除成功");
            } else {
                commonResponse.setCode(Constant.RESCODE_EXCEPTION);
                commonResponse.setMsg("删除失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("删除选中的存金宝产品", "删除选中的存金宝产品异常");
        }
        return commonResponse;
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/6/9 10:42
     * @Description：修改选中的保存金宝产品
     */
    @Override
    @Transactional
    public CommonResponse modifyById(SaveGoldConf saveGoldConf) {
        CommonResponse commonResponse = new CommonResponse();
        try {
            int i = saveGoldConfMapper.updateProductById(saveGoldConf);
            if (i > 0) {
                commonResponse.setCode(Constant.RESCODE_SUCCESS_MSG);
                commonResponse.setMsg("修改成功");
            } else {
                commonResponse.setCode(Constant.RESCODE_EXCEPTION);
                commonResponse.setMsg("修改失败");
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new GlobalException("修改选中的存金宝产品", "修改选中的存金宝产品异常");
        }
        return commonResponse;
    }
}
