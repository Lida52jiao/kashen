package com.battcn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.TransactionalEntity;
import com.battcn.entity.TransactionalStatistics;


public interface TransactionalMapper extends Mapper<TransactionalEntity> {
    List<TransactionalStatistics> getListByDay(@Param("dayNum")Integer dayNum, @Param("merchantId")String merchantId);
    List<TransactionalStatistics> getListByMonth(@Param("merchantId")String merchantId);
    List<TransactionalStatistics> getListByYear(@Param("merchantId")String merchantId);
    Double cash(Map<String, Object> map);
    Double query(Map<String, Object> map);
    Double get(Map<String, Object> map);
	Double gets(Map<String, Object> map);
	Double alter(Map<String, Object> map);
	Double amend(Map<String, Object> map);
}
