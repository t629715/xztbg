package com.fx.xzt.sys.mapper;

import java.util.List;
import java.util.Map;

import com.fx.xzt.sys.entity.InfoInformation;
import org.springframework.stereotype.Repository;

/**
 * @Author:  tianliya
 * @Description:
 * @Date:10:21 2017/10/17
*/
@Repository
public interface InfoInforMationMapper extends BaseMapper<InfoInformation>{
	
	List<Map<String, Object>> getByAll(Map<String,Object> map);
	
	int posted(InfoInformation i);
	
	/**
	 * 查看
	 */
	Map<String, Object> getById(Long serialNo);
	/**
	 * 修改
	 */
	int edit(InfoInformation i);
	/**
	 * 删除
	 */
	int deleteById(Long infoId);
	/**
	 * 取消置顶
	 */
	int editTopState(Map<String,Object> map);

	/**
	 * 获取资讯中的各种状态的集合
	 * @return
	 */
	List<Integer> selecctStates();

	/**
	 * 获取所有的发布人 tianliya
	 * @return
	 */
	List<Map<String, Object>> selectOperators();
}
