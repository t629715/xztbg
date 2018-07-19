package com.fx.xzt.sys.service.impl;

import com.fx.xzt.exception.GlobalException;
import com.fx.xzt.sys.entity.FinanceRegulargoldProduct;
import com.fx.xzt.sys.mapper.FinanceRegulargoldProductMapper;
import com.fx.xzt.sys.service.FinanceConfRegulargoldService;
import com.fx.xzt.sys.util.CommonResponse;
import com.fx.xzt.sys.util.Constant;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.annotation.Resource;

/**
 * @author:tianliya
 * @CreateTime:2018-04-17 11:24
 * @Description:定期金产品设定相关业务接口
 **/
@Service
public class FinanceConfRegulargoldServiceImpl implements FinanceConfRegulargoldService {
    @Resource
    private FinanceRegulargoldProductMapper financeRegulargoldProductMapper;

    public static final Logger logger = LoggerFactory.getLogger(FinanceConfRegulargoldServiceImpl.class);

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:03
     * @Description：添加定期产品
     */
    @Override
    @Transactional
    public CommonResponse addOne(FinanceRegulargoldProduct confRegulargold) throws GlobalException {
        CommonResponse commonResponse = new CommonResponse();
        logger.info("添加定期产品");
        try {
            int i = financeRegulargoldProductMapper.insertOne(confRegulargold);
            if (i > 0) {
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("添加成功");
            } else {
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("添加失败");
            }
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            logger.error("添加定期产品异常", e);
            throw new GlobalException("理财产品-定期产品管理", "添加定期产品异常");
        }
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:03
     * @Description：根据id删除定期产品
     */
    @Override
    @Transactional
    public CommonResponse deleteOneById(String id) throws GlobalException {
        CommonResponse commonResponse = new CommonResponse();
        logger.info("删除定期产品");
        try {
            int i = financeRegulargoldProductMapper.deleteOne(Long.valueOf(id));
            if (i > 0) {
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("删除成功");
            } else {
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("删除失败");
            }
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            logger.error("删除定期产品异常", e);
            throw new GlobalException("理财产品-定期产品管理", "删除定期产品异常");
        }
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:04
     * @Description：修改选中的定期产品
     */
    @Override
    @Transactional
    public CommonResponse modifyOneSelective(FinanceRegulargoldProduct financeConfRegulargold) throws GlobalException {
        CommonResponse commonResponse = new CommonResponse();
        logger.info("修改定期产品");
        try {
            int i = financeRegulargoldProductMapper.modifyOneSelective(financeConfRegulargold);
            if (i > 0) {
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("修改成功");
            } else {
                commonResponse.setCode(Constant.RESCODE_SUCCESS);
                commonResponse.setMsg("修改失败");
            }
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            logger.error("修改定期产品异常", e);
            throw new GlobalException("理财产品-定期产品管理", "修改定期产品异常");
        }
    }

    /**
     * @CreateBy：tianliya
     * @CreateTime：2018/4/17 13:04
     * @Description：根据条件分页查询定期产品
     */
    @Override
    public CommonResponse getByConditions(Integer pageNum, Integer pageSize) throws GlobalException {
        CommonResponse commonResponse = new CommonResponse();
        logger.info("查询金生金产品-业务层入口");
        try {
            PageHelper.startPage(pageNum, pageSize);
            List<FinanceRegulargoldProduct> financeConfRegulargoldList = financeRegulargoldProductMapper.getByAll();
            PageInfo<FinanceRegulargoldProduct> financeConfRegulargoldPageInfo = new PageInfo<>(financeConfRegulargoldList);
            commonResponse.setData(financeConfRegulargoldPageInfo);
            commonResponse.setCode(Constant.RESCODE_SUCCESS);
            commonResponse.setMsg("查询成功");
            return commonResponse;
        } catch (Exception e) {
            e.printStackTrace();
            commonResponse.setCode(Constant.RESCODE_EXCEPTION);
            logger.error("查询定期产品异常", e);
            throw new GlobalException("理财产品-定期产品管理", "查询定期产品异常");
        }
    }
}
