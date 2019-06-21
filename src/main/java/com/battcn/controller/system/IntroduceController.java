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
import com.battcn.entity.Introduce;
import com.battcn.service.system.IntroduceService;
import com.battcn.util.MD5Util;
import com.battcn.util.OSSClientUtil;
import com.github.pagehelper.PageInfo;

@Controller
@RequestMapping("Introduce")
public class IntroduceController extends BaseController {
	
	@Autowired
	private IntroduceService introduceService;
	
	@RequestMapping("list")
	@SystemLog(module = "系统管理", methods = "查询用卡百科的信息")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		return "/system/introduce/list";
	}
	
	@RequestMapping("getIntroduceList")
	@ResponseBody
	public PageInfo<Introduce> getList() {
		PageInfo<Introduce> hList=introduceService.queryPageForList();
		return hList;
	}
	
	@RequestMapping("addintroduce")
	public String addintroduce() {
		return "/system/introduce/addintroduce";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public String add(@RequestParam("imgURL") MultipartFile file, String titles, String content, String forwardURL, HttpServletRequest request){
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
		String ossKey = "introduce/" + s;
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
			Introduce introduce=new Introduce(result, titles, content, System.currentTimeMillis()+"", forwardURL, "");
			introduceService.save(introduce);
			return "success";
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return "f";
		}
	}
	
	@RequestMapping("editintroduce")
	public String editintroduce(Model model, Long id) {
		if (id != null) {
			model.addAttribute("introduce", introduceService.findByPrimaryKey(id));
		}
		return "/system/introduce/editintroduce";
	}
	
	@RequestMapping("alter")
	@ResponseBody
	public String alter(String id,String titles,String content,String forwardURL) {
		Introduce introduce=new Introduce();
		introduce.setId(Long.parseLong(id));
		Introduce v=introduceService.findByObject(introduce);
		v.setTitles(titles);
		v.setContent(content);
		v.setForwardURL(forwardURL);
		return introduceService.update(v);
	}

	@RequestMapping("delintroduce")
	@ResponseBody
	public String delintroduce(Long id) {
		Introduce introduce=introduceService.findByPrimaryKey(id);
		return introduceService.delete(introduce);
	}
	
}
