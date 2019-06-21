package com.battcn.controller.system;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.ResourcesEntity;
import com.battcn.service.system.LeftMenuOrderService;
import com.battcn.util.CommonUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

@Controller
@RequestMapping("/MenuOrder/")
public class LeftMenuOrderCtl extends BaseController{
	
	@Autowired
	private LeftMenuOrderService leftMenuOrderService;

	@RequestMapping("getMenuList")
	@SystemLog(module = "系统管理", methods = "查询一级菜单")
	public String oneMenuList(Model model,Long parentId){
		model.addAttribute("res", findResByUser());
		ResourcesEntity r = new ResourcesEntity();
		if(parentId == null){
			Long it = new Long(0);
			r.setParentId(it);
		} else {
			r.setParentId(parentId);
		}
		List<ResourcesEntity> list = leftMenuOrderService.queryObjectForList(r);
		model.addAttribute("menu", list);
		return "/system/leftMenuOrder/menuList";
	}
	@RequestMapping("getTwoMenuList")
	@SystemLog(module = "系统管理", methods = "查询二级菜单")
	public String twoMenuList(Model model,Long parentId){
		ResourcesEntity r = new ResourcesEntity();
		if(parentId == null){
			Long it = new Long(0);
			r.setParentId(it);
		} else {
			r.setParentId(parentId);
		}
		List<ResourcesEntity> list = leftMenuOrderService.queryObjectForList(r);
		model.addAttribute("menu", list);
		return "/system/leftMenuOrder/twoMenuList";
	}
	
	@RequestMapping("MenuOrderList")
	@ResponseBody
	public PageInfo<ResourcesEntity> getMenuList(Long parentId){
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
		ResourcesEntity r = new ResourcesEntity();
		if(parentId == null){
			Long it = new Long(0);
			r.setParentId(it);
		} else {
			r.setParentId(parentId);
		}
		List<ResourcesEntity> list = leftMenuOrderService.queryObjectForList(r);
		return new PageInfo<ResourcesEntity>(list);
	}
	@RequestMapping(value = "updateMenuOrder",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String delete(String menuId,String level) {
		
		String[] newMenuId = menuId.split(",");
		String[] newLevel = level.split(",");
		String message = "";
		//如果两个数组一样长，修改所有，否则修改填写的，其它的不动
		if(newMenuId.length == newLevel.length){
			for(int i=0;i<newMenuId.length;i++){
				ResourcesEntity r = new ResourcesEntity();
				Long it = new Long(newMenuId[i]);
				r.setId(it);
				ResourcesEntity entity = leftMenuOrderService.findByObject(r);
				entity.setLevel(newLevel[i]);
				message = leftMenuOrderService.update(entity);
			}
		} else {
			for(int i=0;i<newLevel.length;i++){
				ResourcesEntity r = new ResourcesEntity();
				Long it = new Long(newMenuId[i]);
				r.setId(it);
				ResourcesEntity entity = leftMenuOrderService.findByObject(r);
				entity.setLevel(newLevel[i]);
				message = leftMenuOrderService.update(entity);
			}
		}
		return message;
	}
}
