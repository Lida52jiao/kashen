package com.battcn.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.PlanEntity;

/**
 * Created by bin on 2017/11/6.
 */
public interface PlanMapper extends Mapper<PlanEntity>{
    List<PlanEntity> getListByTime(@Param("merchantsId")String merchantsId,@Param("startTime")Long startTime,@Param("endTime")Long endTime);
    /*List<PlanEntity> getListByAll(@Param("merchantsId")String merchantsId,
                                  @Param("state")String state,
                                  @Param("ratio")String ratio,
                                  @Param("totalDay")String totalDay,
                                  @Param("number")String number,
                                  @Param("bankCode")String bankCode,
                                  @Param("name")String name,
                                  @Param("idCardNo")String idCardNo,
                                  @Param("cardNo")String cardNo,
                                  @Param("phone")String phone,
                                  @Param("startTime")Long startTime,@Param("endTime")Long endTime);*/
	List<PlanEntity> getListByAll(@Param("merchantId")String merchantId,@Param("state")String state,@Param("name")String name,@Param("cardNo")String cardNo,@Param("phone")String phone,@Param("starttime")String starttime,@Param("finishtime")String finishtime);
	List<PlanEntity> query(Map<String, Object> map);
	List<PlanEntity> gets(@Param("merchantId")String merchantId,@Param("merId")String merId,@Param("state")String state,@Param("name")String name,@Param("cardNo")String cardNo,@Param("phone")String phone,@Param("starttime")String starttime,@Param("finishtime")String finishtime);
}
