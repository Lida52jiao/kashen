package com.battcn.service.system;

import com.battcn.entity.Num;
import com.battcn.entity.NumExample;
import com.github.pagehelper.PageInfo;


public interface NumService {

	
	PageInfo<Num> getList(NumExample example);
	
	Num getNum(Long id);
	
	Integer editNum(Num num);
	
	Integer addNum(Num num);
	
}
