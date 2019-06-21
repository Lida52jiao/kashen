package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.battcn.service.system.UserRoleService;

@Controller
@RequestMapping("UserRoleEntity")
public class UserRoleController {
	
	@Autowired
	private UserRoleService userRoleService;

}
