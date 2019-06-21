package com.battcn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.PlanDetail;
import com.battcn.entity.PlanDetailEntity;
import com.battcn.entity.Transactional;

/**
 * Created by bin on 2017/11/6.
 */
public interface PlanDetailMapper extends Mapper<PlanDetailEntity> {
    List<PlanDetailEntity> getExpireList(@Param("newDate")Long newDate);
    /*List<PlanDetailEntity> findPlanDetailByAll(
            PlanDetailEntity entity,
           @Param("startExecuteTime")Long startExecuteTime,
           @Param("endExecuteTime")Long endExecuteTime,
           @Param("startFinishTime")Long startFinishTime,
           @Param("endFinishTime")Long endFinishTime);*/

	List<PlanDetail> gain(PlanDetail t);

	List<Transactional> get(Transactional transactional);
	
	List<Transactional> gets(Transactional transactional);

	List<Transactional> query(Transactional transactional);

	List<PlanDetailEntity> getPlanDetaillist(PlanDetailEntity planDetail);

	//List<PlanDetail> query(PlanDetail t);
}
