package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.UserRoleEntity;
import com.battcn.mapper.UserRoleMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.UserRoleService;
@Service
public class UserRoleServiceImpl extends BaseServiceImpl<UserRoleEntity> implements UserRoleService  {
	
	@Autowired
	private UserRoleMapper userRoleMapper;

}
