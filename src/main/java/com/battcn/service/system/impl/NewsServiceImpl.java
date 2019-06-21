package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.News;
import com.battcn.mapper.NewsMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.NewsService;
@Service
public class NewsServiceImpl extends BaseServiceImpl<News> implements NewsService {
	
	@Autowired
	private NewsMapper newsMapper;

}
