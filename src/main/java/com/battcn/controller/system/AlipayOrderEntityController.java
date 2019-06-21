package com.battcn.controller.system;

import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.battcn.entity.*;
import com.battcn.util.DateUtil;
import com.battcn.util.excelUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.service.system.AlipayOrderEntityService;
import com.battcn.service.system.AppNameService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("AlipayOrderEntity")
public class AlipayOrderEntityController extends BaseController {

	@Autowired
	private AlipayOrderEntityService alipayOrderEntityService;
	@Autowired
	private AppNameService appNameService;

	@RequestMapping("list")
	@SystemLog(module = "交易及结算管理", methods = "年月费和升级查询")
	public String list(Model model) {
		model.addAttribute("res", findResByUser());
		model.addAttribute("app", appNameService.getList());
		return "/system/alipayOrder/list";
	}

	@RequestMapping("getAlipayOrderList")
	@ResponseBody
	public PageInfo<AlipayOrderEntity> list(String merchantId, String agentId,
											String phone, String orderNo, String state, String type, String appId, String starttime, String finishtime) throws ParseException {
		UserEntity k = UserEntityUtil.getUserFromSession();
		Map<String, Object> map = new HashMap<String, Object>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		long startSeconds = 0;
		long finishSeconds = 0;
		if (!"".endsWith(starttime)) {
			startSeconds = sdf.parse(starttime).getTime();//毫秒
		}
		if (!"".endsWith(finishtime)) {
			finishSeconds = sdf.parse(finishtime).getTime();//毫秒
		}
		//总平台查询所有
		if (k.getMerId().startsWith("T")) {
			if (!StringUtils.isBlank(orderNo)) {
				map.put("orderNo", orderNo);
			}
			if (!StringUtils.isBlank(state)) {
				map.put("state", state);
			}
			if (!StringUtils.isBlank(type)) {
				map.put("type", type);
			}
			if (!StringUtils.isBlank(merchantId)) {
				map.put("merchantId", merchantId);
			}
			if (!StringUtils.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (!StringUtils.isBlank(appId)) {
				map.put("appId", appId);
			}
			if (!StringUtils.isBlank(agentId)) {
				map.put("agentId", agentId);
			}
			if (startSeconds != 0) {
				map.put("startTime", String.valueOf(startSeconds));
			}
			if (finishSeconds != 0) {
				map.put("finishTime", String.valueOf(finishSeconds));
			}
			map.put("institutionId", InstitutionIdNumber.AGENT);
			PageInfo<AlipayOrderEntity> page = alipayOrderEntityService.recieve(map);
			return page;
		}
		AppName appName = new AppName();
		appName.setAgentId(k.getMerId());
		AppName appNames = appNameService.findByObject(appName);
		//O单
		if (null != appNames) {
			if (!StringUtils.isBlank(orderNo)) {
				map.put("orderNo", orderNo);
			}
			if (!StringUtils.isBlank(state)) {
				map.put("state", state);
			}
			if (!StringUtils.isBlank(type)) {
				map.put("type", type);
			}
			if (!StringUtils.isBlank(merchantId)) {
				map.put("merchantId", merchantId);
			}
			if (!StringUtils.isBlank(phone)) {
				map.put("phone", phone);
			}
			if (!StringUtils.isBlank(appId)) {
				map.put("appId", appId);
			}
			if (!StringUtils.isBlank(agentId)) {
				map.put("agentId", agentId);
			}
			if (startSeconds != 0) {
				map.put("startTime", String.valueOf(startSeconds));
			}
			if (finishSeconds != 0) {
				map.put("finishTime", String.valueOf(finishSeconds));
			}
			map.put("institutionId", InstitutionIdNumber.AGENT);
			map.put("appId", appNames.getAppId());
			return alipayOrderEntityService.recieve(map);
		}
		//代理商自己查自己的
		if (!StringUtils.isBlank(orderNo)) {
			map.put("orderNo", orderNo);
		}
		if (!StringUtils.isBlank(state)) {
			map.put("state", state);
		}
		if (!StringUtils.isBlank(type)) {
			map.put("type", type);
		}
		if (!StringUtils.isBlank(merchantId)) {
			map.put("merchantId", merchantId);
		}
		if (!StringUtils.isBlank(phone)) {
			map.put("phone", phone);
		}
		if (!StringUtils.isBlank(appId)) {
			map.put("appId", appId);
		}
		if (!StringUtils.isBlank(agentId)) {
			map.put("agentId", agentId);
		}
		if (startSeconds != 0) {
			map.put("startTime", String.valueOf(startSeconds));
		}
		if (finishSeconds != 0) {
			map.put("finishTime", String.valueOf(finishSeconds));
		}
		map.put("institutionId", InstitutionIdNumber.AGENT);
		map.put("agentId", k.getMerId());
		return alipayOrderEntityService.recieve(map);
	}

}

