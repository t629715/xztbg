package com.fx.xzt.sys.mapper;

import com.fx.xzt.sys.entity.InfoXioude;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
@Repository
public interface InfoXioudeMapper extends BaseMapper<InfoXioude>{
    /**
     * @Author:  tianliya
     * @Description:  查询
     * @Date:
     * @param map
     * @return
     */
    List<Map<String, Object>> selectByInfoXioude(Map<String, Object> map);
    /**
     * @Author:  tianliya
     * @Description: 增加
     * @Date:
     */
    int insert(Map<String, Object> map);
    /**
     * @Author:  tianliya
     * @Description: 编辑
     * @Date:
     */
    int edit(Map<String, Object> map);
    /**
     * @Author:  tianliya
     * @Description: 删除
     * @Date:
     */
    int deleteXioude(Long infoId);

    /**
     * @param infoId
     * @return
     * @Author:  tianliya
     * @Description: 获取一条数据
     * @Date:14:27 2017/10/16
    */

    Map<String, Object> selectOneByInfoId(Long infoId);

    /**
     * 发布
     * @param
     * @return
     */
    int releaseXioude(Map<String, Object> map);
}