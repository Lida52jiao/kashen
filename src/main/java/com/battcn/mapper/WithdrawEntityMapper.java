package com.battcn.mapper;

import java.util.List;
import java.util.Map;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.Withdraw;
import com.battcn.entity.WithdrawEntity;

public interface WithdrawEntityMapper extends Mapper<WithdrawEntity> {

	List<Withdraw> gain(Map<String, Object> map);

	List<Withdraw> get(Map<String, Object> map);

	List<Withdraw> finds(Map<String, Object> map);

}
