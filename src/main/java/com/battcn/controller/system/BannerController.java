package com.battcn.controller.system;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.battcn.annotation.SystemLog;
import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.Banner;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.BannerService;
import com.battcn.util.MD5Util;
import com.battcn.util.OSSClientUtil;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Banner")
public class BannerController extends BaseController {
	
	@Autowired
	private BannerService bannerService;
	@Autowired
	private AppNameService appNameService;
	
	@RequestMapping("edit")
	public String add(Model model) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		if(k.getMerId().startsWith("T")){
			model.addAttribute("t", appNameService.queryObjectForList());
			return "/system/banner/edit";
		}
		AppName name = new AppName();
		name.setAgentId(k.getMerId());
		model.addAttribute("t", appNameService.findByObject(name));
		return "/system/banner/edits";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String addBanner(String appId, @RequestParam("imgURL") MultipartFile file, String name, String forwardURL, String status, HttpServletRequest request){
		if(null == appId || "".equals(appId) || null == status || "".equals(status)){
			return "s"; 
		}
		String filePath = "";
		// 判断文件是否为空
		if (!file.isEmpty()) {
			try {
				// 文件保存路径
				filePath = request.getSession().getServletContext()
						.getRealPath("/")
						+ file.getOriginalFilename();
				// 转存文件
				file.transferTo(new File(filePath));
			} catch (Exception e) {
				e.printStackTrace();
				return "f";
			}
		}else{
			return "fail";
		}
		String s = MD5Util.getMD5String(filePath);
		String ossKey = "banner/" + s;
		String result = "";
		OSSClientUtil clientUtil = new OSSClientUtil();
		Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
				* 365 * 10);
		// 上传
		InputStream instream = null;
		try {
			instream = new FileInputStream(filePath);
			clientUtil.uploadFile2OSS(instream, ossKey);
			// 获取url
			URL url = clientUtil.createUrl(ossKey, expiration);
			clientUtil.destory();
			result = url.toString();
			Banner n=new Banner(result, name, forwardURL, System.currentTimeMillis()+"", status, "");
			n.setAppId(appId);
			bannerService.save(n);
			return "success";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "f";
		}
	}
	
	@RequestMapping("list")
	@SystemLog(module = "系统管理", methods = "轮播图查询")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/banner/list";
	}
	
	@RequestMapping("getBannerList")
	@ResponseBody
	public PageInfo<Banner> getList(String merId) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		if(k.getMerId().startsWith("T")){
			return bannerService.queryPageForList();
		}
		AppName name = new AppName();
		name.setAgentId(k.getMerId());
		AppName t = appNameService.findByObject(name);
		Banner n=new Banner();
		n.setAppId(t.getAppId());
		return bannerService.queryPageForList(n);
	}
	
	@RequestMapping("editbanner")
	public String editbanner(Model model, Long id) {
		if (id != null) {
			model.addAttribute("banner", bannerService.findByPrimaryKey(id));
		}
		return "/system/banner/editbanner";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String id,String name,String forwardURL) {
		Banner n=new Banner();
		n.setId(Long.parseLong(id));
		Banner k=bannerService.findByObject(n);
		k.setName(name);
		k.setForwardURL(forwardURL);
		return bannerService.update(k);
	}
	
	@RequestMapping("delbanners")
	@ResponseBody
	public String deleteMer(Long id) {
		Banner n=bannerService.findByPrimaryKey(id);
		return bannerService.delete(n);
	}
}
