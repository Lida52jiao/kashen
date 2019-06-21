package com.battcn.service.system.impl;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.AgentMerLevel;
import com.battcn.entity.Num;
import com.battcn.entity.NumExample;
import com.battcn.mapper.NumMapper;
import com.battcn.service.system.AgentMerLevelService;
import com.battcn.service.system.NumService;
import com.battcn.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Service
public class NumServiceImpl implements NumService {
	
	@Autowired
	private NumMapper numMapper;
	@Autowired
	private AgentMerLevelService agentMerLevelService;

	@Override
	public PageInfo<Num> getList(NumExample example) {
		HttpServletRequest request = CommonUtil.getHttpRequest();
		Integer pageNum = CommonUtil
				.valueOf(request.getParameter("pageNum"), 1);
		Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
				10);
		PageHelper.startPage(pageNum, pageSize);
		String orderField = request.getParameter("sort");
		String orderDirection = request.getParameter("order");
		if (StringUtil.isNotEmpty(orderField)) {
			PageHelper.orderBy(orderField);
			if (StringUtil.isNotEmpty(orderDirection)) {
				PageHelper.orderBy(orderField + " " + orderDirection);
			}
		}
		List<Num> list = numMapper.selectByExample(example);
		List<Num> newList = new ArrayList<Num>();
		//遍历数据，将等级名称与等级对应起来
		for(Num n:list){
			AgentMerLevel level = agentMerLevelService.getByWeight(Long.parseLong(n.getMertype()));
			n.setMertypeName(level.getLevelname());
			newList.add(n);
		}
		return new PageInfo<Num>(newList);
	}

	@Override
	public Integer editNum(Num num) {
		Num record = numMapper.selectByPrimaryKey(num.getId());
		record.setNum(num.getNum());
		record.setValidtime(num.getValidtime());
		record.setOnemerchant(num.getOnemerchant());
		record.setTwomerchant(num.getTwomerchant());
		record.setThreemerchant(num.getThreemerchant());
		record.setOneagent(num.getOneagent());
		record.setTwoagent(num.getTwoagent());
		record.setCountyagent(num.getCountyagent());
		record.setCityagent(num.getCityagent());
		record.setProvinceagent(num.getProvinceagent());
		record.setInstitution(num.getInstitution());
		record.setTopagent(num.getTopagent());
		record.setAmount(num.getAmount());
		Integer count = numMapper.updateByPrimaryKey(record);
		return count;
	}

	@Override
	public Num getNum(Long id) {
		Num record = numMapper.selectByPrimaryKey(id);
		return record;
	}

	@Override
	public Integer addNum(Num num) {
		Integer count = numMapper.insert(num);
		return count;
	}

}
