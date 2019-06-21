package com.battcn.service.system.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.battcn.entity.MerChantsRate;
import com.battcn.entity.MerChantsRateExample;
import com.battcn.entity.MerRateRecord;
import com.battcn.entity.UserEntity;
import com.battcn.mapper.MerChantsRateMapper;
import com.battcn.service.system.MerRateRecordService;
import com.battcn.service.system.MerchantsRateService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtil;
import com.battcn.util.HttpClientUtils;
import com.battcn.util.SignUtil;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
@Service
public class MerchantsRateServiceImpl implements MerchantsRateService {
	
	@Autowired
	private MerChantsRateMapper merChantsRateMapper;
	@Autowired
	private MerRateRecordService merRateRecordService;
 
	@Override
	public PageInfo<MerChantsRate> getList(String appId, String aislecode,String isrepayment) {
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
		MerChantsRateExample example = new MerChantsRateExample();
		MerChantsRateExample.Criteria cri = new MerChantsRateExample().createCriteria();
		if(!StringUtils.isBlank(appId)){
			cri.andAppidEqualTo(appId);
		}
		if(!StringUtils.isBlank(aislecode)){
			cri.andAislecodeEqualTo(aislecode);
		}
		if(!StringUtils.isBlank(isrepayment)){
			cri.andIsrepaymentEqualTo(isrepayment);
		}
		example.or(cri);
		List<MerChantsRate> list = merChantsRateMapper.selectByExample(example);
		return new PageInfo<MerChantsRate>(list);
	}
	@Override
	public Integer updateRate(MerChantsRate merchantsrate) {
		UserEntity k=UserEntityUtil.getUserFromSession();
		MerChantsRate merc =  merChantsRateMapper.selectByPrimaryKey(merchantsrate.getId());
		//添加修改记录
		MerRateRecord record = new MerRateRecord();
		record.setMertype(merc.getMertype());
		record.setRate(merc.getRate());
		record.setD0fee(merc.getD0fee());
		record.setAislecode(merc.getAislecode());
		record.setAppid(merc.getAppid());
		record.setUpdatedate(new Date());
		record.setUpdatename(k.getUserName());
		merRateRecordService.insertChangeRecord(record);
		//修改通道费率
		BigDecimal bigDecimal = new BigDecimal("100");
		BigDecimal big = new BigDecimal(merchantsrate.getRate().toString());
		merc.setMertype(merc.getMertype());
		merc.setRate(big.divide(bigDecimal));
		merc.setD0fee(merchantsrate.getD0fee()*100);
		merc.setAislecode(merc.getAislecode());
		merc.setAppid(merc.getAppid());
		Integer message = merChantsRateMapper.updateByPrimaryKey(merc);
		return message;
	}

	@Override
	public MerChantsRate getById(Long id) {
		MerChantsRate merc = merChantsRateMapper.selectByPrimaryKey(id);
		return merc;
	}

	public void find(String merChantId, String appId, String institutionId, List<MerChantsRate> list) {
	HashMap<String,Object> param = new HashMap<String,Object>();
	param.put("merchantId", merChantId);
	param.put("appId", appId);
	param.put("institutionId", institutionId);
	param.put("timestamp", System.currentTimeMillis());
	param.put("jsonStr", JSON.toJSONString(list));
	String aisleSign= SignUtil.createYJSign(param);
	param.put("sign",aisleSign);
	String resultJsonStr = HttpClientUtil.doPost("http://47.104.25.147/yj-epos/bind/updateRate", param); 
	System.out.println(resultJsonStr);
}

	@Override
	public List<MerChantsRate> getMerchantsRateList(String appId, String merType) {
		MerChantsRateExample example = new MerChantsRateExample();
		example.or().andAppidEqualTo(appId).andMertypeEqualTo(merType);
		return merChantsRateMapper.selectByExample(example);
	}
	@Override
	public List<MerChantsRate> getList() {
		List<MerChantsRate> list = merChantsRateMapper.selectByAisle();
		return list;
	}
}
