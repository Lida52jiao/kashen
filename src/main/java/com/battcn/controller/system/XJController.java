package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.controller.BaseController;
import com.battcn.entity.XJ;
import com.battcn.service.system.XJService;

@Controller
@RequestMapping("XJ")
public class XJController extends BaseController {
	
	@Autowired
	private XJService xjService;
	
	@RequestMapping(value = "get")
	public @ResponseBody
	XJ get() {
			XJ t = new XJ();
			t.setId((long)1);
			XJ xj = xjService.findByObject(t);
			return xj;
		}

}
