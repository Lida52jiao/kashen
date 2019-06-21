package com.battcn.service.system.impl;


import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.Agent;
import com.battcn.entity.AgentBindArea;
import com.battcn.entity.MerChants;
import com.battcn.mapper.AgentBindAreaMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.AgentBindService;
import com.battcn.service.system.AgentService;
import com.battcn.service.system.MerChantsService;
import com.battcn.util.HttpClientUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class AgentBindServiceImpl extends BaseServiceImpl<AgentBindArea> implements AgentBindService {
    @Override
    public String bindArea(String merId, String merChantId, String province, String city, String region) {
        return null;
    }
//    @Autowired
//    private AgentService agentService;
//    @Autowired
//    private MerChantsService merChantsService;
//    @Autowired
//    private AgentBindAreaMapper agentBindAreaMapper;
//    public String bindArea(String merId, String merChantId, String province,
//                           String city, String region) {
//        Map<String, Object> map = new HashMap<String, Object>();
//        AgentBindArea example = new AgentBindArea();
//        Agent n=new Agent();
//        n.setMerId(merId);
//        Agent h=agentService.findByObject(n);
//
//        if(h == null){
//
//            return "代理不存在！！！";
//        }
//        MerChants m = new MerChants();
//        m.setMerChantId(merChantId);
//        MerChants mh = merChantsService.findByObject(m);
//        if(mh == null){
//
//            return "商户不存在！！！";
//        }
//        if(!StringUtils.isBlank(region) && !"-1".equals(region)){
//            //example.or().andAreaidEqualTo(Long.parseLong(region));
//            example.setAreaId(Integer.parseInt(region));
//            List<AgentBindArea> rlist = agentBindAreaMapper.select(example);
//            if(rlist.size()>0){
//                return "该县已绑定了代理商！！！";
//            }
//            map.put("id", region);
//            String url = "http://47.104.198.186:1178/community/getCountryById";
//            String result=new HttpClientUtils().doPost(url,map);
//            JSONObject json = JSONObject.parseObject(result);
//            String newResult = json.getString("data");
//            JSONObject newJson = JSONObject.parseObject(newResult);
//            AgentBindArea area = new AgentBindArea();
//            area.setAgentId(merId);
//            area.setMerChantId(merChantId);
//            area.setAreaCode(newJson.getString("code"));
//            area.setAreaId(Integer.parseInt(region));
//            area.setAreaLevel(3);
//            area.setAreaName(newJson.getString("areaName"));
//            area.setAreaParentId(Integer.parseInt(newJson.getString("parentId")));
//            area.setCreatedTime(new Date());
//            Integer count= agentBindAreaMapper.insert(area);
//            if(count>0){
//                return "绑定成功！！";
//            }
//            return "绑定失败！！";
//        }
//        if(!StringUtils.isBlank(city) && !"-1".equals(city)){
//            //example.or().andAreaidEqualTo(Long.parseLong(city));
//            example.setAreaId(Integer.parseInt(city));
//            List<AgentBindArea> clist = agentBindAreaMapper.select(example);
//            if(clist.size()>0){
//                return "该市已绑定了代理商！！！";
//            }
//            map.put("id", city);
//            String url = "http://47.104.198.186:1178/community/getCountryById";
//            String result=new HttpClientUtils().doPost(url,map);
//            JSONObject json = JSONObject.parseObject(result);
//            String newResult = json.getString("data");
//            JSONObject newJson = JSONObject.parseObject(newResult);
//            AgentBindArea area = new AgentBindArea();
//            area.setAgentId(merId);
//            area.setMerChantId(merChantId);
//            area.setAreaCode(newJson.getString("code"));
//            area.setAreaId(Integer.parseInt(region));
//            area.setAreaLevel(2);
//            area.setAreaName(newJson.getString("areaName"));
//            area.setAreaParentId(Integer.parseInt(newJson.getString("parentId")));
//            area.setCreatedTime(new Date());
//            Integer count= agentBindAreaMapper.insert(area);
//            if(count>0){
//                return "绑定成功！！";
//            }
//            return "绑定失败！！";
//        }
//        if(!StringUtils.isBlank(province) && !"-1".equals(province)){
//            //example.or().andAreaidEqualTo(Long.parseLong(province));
//            example.setAreaId(Integer.parseInt(province));
//            List<AgentBindArea> plist = agentBindAreaMapper.select(example);
//            if(plist.size()>0){
//                return "该省已绑定了代理商！！！";
//            }
//            map.put("id", province);
//            String url = "http://47.104.198.186:1178/community/getCountryById";
//            String result=new HttpClientUtils().doPost(url,map);
//            JSONObject json = JSONObject.parseObject(result);
//            String newResult = json.getString("data");
//            JSONObject newJson = JSONObject.parseObject(newResult);
//            AgentBindArea area = new AgentBindArea();
//            area.setAgentId(merId);
//            area.setMerChantId(merChantId);
//            area.setAreaCode(newJson.getString("code"));
//            area.setAreaId(Integer.parseInt(region));
//            area.setAreaLevel(1);
//            area.setAreaName(newJson.getString("areaName"));
//            area.setAreaParentId(Integer.parseInt(newJson.getString("parentId")));
//            area.setCreatedTime(new Date());
//            Integer count= agentBindAreaMapper.insert(area);
//            if(count>0){
//                return "绑定成功！！";
//            }
//            return "绑定失败！！";
//        }
//        return "未知错误，请稍后再试！！";
//    }
}
