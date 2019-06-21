package com.battcn.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.battcn.constant.Constaint;
import com.battcn.controller.BaseController;
import com.battcn.entity.AisleEntity;
import com.battcn.entity.AppName;
import com.battcn.entity.MerChantsRate;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.util.HttpClientUtil;
import com.battcn.util.UserEntityUtil;
import com.battcn.util.YJResult;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import org.apache.shiro.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: Create By DaDa
 * @Date: 2019/4/24 16:55
 */
@Controller
@RequestMapping("AisleSet")
public class AisleSetController extends BaseController {

    @Autowired
    private AppNameService appNameService;

    @RequestMapping("showList")
    public String merchantModel(Model model) {
        model.addAttribute("res", findResByUser());
        model.addAttribute("app", appNameService.getList());
        return "/system/aisleSet/aisleList";
    }

    @RequestMapping("getList")
    @ResponseBody
    public PageInfo<AisleEntity> getList(String appId){
        Map<String,Object> map = new HashMap<>();
        map.put("institutionId", Constaint.AGENT);
        if(!StringUtil.isEmpty(appId)) {
            map.put("appId", appId);
        }
        String result = HttpClientUtil.doPost(Constaint.AisleSet,map);
        JSONObject t = JSON.parseObject(result);
        return JSON.parseObject(t.toJSONString(),PageInfo.class);
    }
    @RequestMapping("edit")
    public String edit(Model model,String id) {
        Map<String,Object> map = new HashMap<>();
        map.put("id", id);
        String result = HttpClientUtil.doPost(Constaint.AisleSet,map);
        JSONObject t = JSON.parseObject(result);
        String list = t.getString("list");
        JSONArray tt = JSON.parseArray(list);
        AisleEntity a = JSONArray.parseObject(tt.get(0).toString(),AisleEntity.class);
        model.addAttribute("orderBy", a.getOrderBy());
        model.addAttribute("aisleName", a.getAisleName());
        model.addAttribute("description", a.getDescription());
        model.addAttribute("remarks", a.getRemarks());
        model.addAttribute("id", a.getId());
//        model.addAttribute("Aisle", JSON.parseObject(tt.toJSONString(),AisleEntity.class));

        return "/system/aisleSet/edit";
    }

    @RequestMapping("editList")
    @ResponseBody
    public String editList(Long id,String orderBy,String aisleName,String description,String remarks){
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("orderBy", orderBy);
        map.put("aisleName", aisleName);
        map.put("description", description);
        map.put("remarks", remarks);
        String result = HttpClientUtil.doPost(Constaint.AisleSetEdit,map);
        System.out.println(result);
        return "success";
    }


}
