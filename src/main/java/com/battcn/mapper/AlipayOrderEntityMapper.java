package com.battcn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.AlipayOrderEntity;
import com.battcn.entity.AlipayOrderVo;

public interface AlipayOrderEntityMapper extends Mapper<AlipayOrderEntity> {

	List<AlipayOrderEntity> get(AlipayOrderEntity t);
	
	List<AlipayOrderEntity> getByApp(AlipayOrderEntity t);
	
	List<AlipayOrderEntity> getByAgent(AlipayOrderVo t);

	Double gain(Map<String, Object> map);

	Double gets(Map<String, Object> map);

	Double alter(Map<String, Object> map);

	Double gains(Map<String, Object> map);

	Double getAmount(Map<String, Object> map);

	Double selectAmounts(Map<String, Object> map);

}
