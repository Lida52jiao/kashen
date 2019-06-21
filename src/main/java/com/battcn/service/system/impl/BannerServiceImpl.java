package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Banner;
import com.battcn.mapper.BannerMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.BannerService;

@Service
public class BannerServiceImpl extends BaseServiceImpl<Banner> implements BannerService {
	
	@Autowired
	private BannerMapper bannerMapper;

}
