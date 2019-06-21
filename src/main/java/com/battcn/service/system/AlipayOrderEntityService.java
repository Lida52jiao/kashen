package com.battcn.service.system;

import java.util.List;
import java.util.Map;

import com.battcn.entity.AlipayOrderEntity;
import com.battcn.entity.AlipayOrderVo;
import com.battcn.entity.MerChants;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface AlipayOrderEntityService extends BaseService<AlipayOrderEntity> {
	
	PageInfo<AlipayOrderEntity> recieve(Map<String, Object> map);

}
