package com.battcn.service.system.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.Agent;
import com.battcn.entity.AgentBindArea;
import com.battcn.entity.AgentBindAreaExample;
import com.battcn.entity.MerChants;
import com.battcn.mapper.AgentBindAreaMapper;
import com.battcn.service.system.AgentAreaBindService;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.MerChantsService;
import com.battcn.util.HttpClientUtils;
@Service
public class AgentAreaBindServiceImpl implements AgentAreaBindService{
	
	@Autowired
	private AgentBindAreaMapper agentBindAreaMapper;
	@Autowired
	private AgentService agentService;
	@Autowired
	private MerChantsService merChantsService;

	@Override
	public String bindArea(String merId, String merChantId, String province,
			String city, String region) {
		Map<String, Object> map = new HashMap<String, Object>();
		AgentBindAreaExample example = new AgentBindAreaExample();
//		example.or().andAgentidEqualTo(merId);
//		List<AgentBindArea> list = agentBindAreaMapper.selectByExample(example);
//		if(list.size()>0){
//			return "该代理商已绑定过区域代理！！！";
//		}
		Agent n=new Agent();
		n.setMerId(merId);
		Agent h=agentService.findByObject(n);
		if(h == null){
			return "代理不存在！！！";
		}
		MerChants m = new MerChants();
		m.setMerChantId(merChantId);
		MerChants mh = merChantsService.findByObject(m);
		if(mh == null){
			return "商户不存在！！！";
		}
		if(!StringUtils.isBlank(region) && !"-1".equals(region)){
			example.or().andAreaidEqualTo(Long.parseLong(region));
			List<AgentBindArea> rlist = agentBindAreaMapper.selectByExample(example);
			if(rlist.size()>0){
				return "该县已绑定了代理商！！！";
			}
			map.put("id", region);
			String url = "http://47.104.198.186:1178/community/getCountryById";
			String result=new HttpClientUtils().doPost(url,map);
			JSONObject json = JSONObject.parseObject(result);
			String newResult = json.getString("data");
			JSONObject newJson = JSONObject.parseObject(newResult);
			AgentBindArea area = new AgentBindArea();
			area.setAgentId(merId);
			area.setMerChantId(merChantId);
			area.setAreaCode(newJson.getString("code"));
			area.setAreaId(Integer.parseInt(region));
			area.setAreaLevel(3);
			area.setAreaName(newJson.getString("areaName"));
			area.setAreaParentId(Integer.parseInt(newJson.getString("parentId")));
			area.setCreatedTime(new Date());
			Integer count= agentBindAreaMapper.insert(area);
			if(count !=0){
				return "绑定成功！！";
			}
			return "绑定失败！！";
		}
		if(!StringUtils.isBlank(city) && !"-1".equals(city)){
			example.or().andAreaidEqualTo(Long.parseLong(city));
			List<AgentBindArea> clist = agentBindAreaMapper.selectByExample(example);
			if(clist.size()>0){
				return "该市已绑定了代理商！！！";
			}
			map.put("id", city);
			String url = "http://47.104.198.186:1178/community/getCountryById";
			String result=new HttpClientUtils().doPost(url,map);
			JSONObject json = JSONObject.parseObject(result);
			String newResult = json.getString("data");
			JSONObject newJson = JSONObject.parseObject(newResult);
			AgentBindArea area = new AgentBindArea();
			area.setAgentId(merId);
			area.setMerChantId(merChantId);
			area.setAreaCode(newJson.getString("code"));
			area.setAreaId(Integer.parseInt(region));
			area.setAreaLevel(2);
			area.setAreaName(newJson.getString("areaName"));
			area.setAreaParentId(Integer.parseInt(newJson.getString("parentId")));
			area.setCreatedTime(new Date());
			Integer count= agentBindAreaMapper.insert(area);
			if(count !=0){
				return "绑定成功！！";
			}
			return "绑定失败！！";
		}
		if(!StringUtils.isBlank(province) && !"-1".equals(province)){
			example.or().andAreaidEqualTo(Long.parseLong(province));
			List<AgentBindArea> plist = agentBindAreaMapper.selectByExample(example);
			if(plist.size()>0){
				return "该省已绑定了代理商！！！";
			}
			map.put("id", province);
			String url = "http://47.104.198.186:1178/community/getCountryById";
			String result=new HttpClientUtils().doPost(url,map);
			JSONObject json = JSONObject.parseObject(result);
			String newResult = json.getString("data");
			JSONObject newJson = JSONObject.parseObject(newResult);
			AgentBindArea area = new AgentBindArea();
			area.setAgentId(merId);
			area.setMerChantId(merChantId);
			area.setAreaCode(newJson.getString("code"));
			area.setAreaId(Integer.parseInt(region));
			area.setAreaLevel(1);
			area.setAreaName(newJson.getString("areaName"));
			area.setAreaParentId(Integer.parseInt(newJson.getString("parentId")));
			area.setCreatedTime(new Date());
			Integer count= agentBindAreaMapper.insert(area);
			if(count !=0){
				return "绑定成功！！";
			}
			return "绑定失败！！";
		}
		return "未知错误，请稍后再试！！";
	}


}
