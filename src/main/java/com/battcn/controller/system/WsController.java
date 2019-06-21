package com.battcn.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.constant.Constaint;
import com.battcn.controller.BaseController;
import com.battcn.entity.WsConfigEntity;
import com.battcn.util.HttpClientUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Create By DaDa
 * @Date: 2019/4/29 11:21
 */
@Controller
@RequestMapping("WsConfig")
public class WsController extends BaseController {

    @RequestMapping("getlist")
    public String  getList(Model model) {
        model.addAttribute("res", findResByUser());
        return "/system/WsConfig/list";
    }


    @RequestMapping("list")
    @ResponseBody
    public PageInfo<WsConfigEntity> List(String pageNum) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("institutionId", Constaint.AGENT);
        map.put("pageNum",pageNum);
        String result = HttpClientUtil.doPost(Constaint.WsList,map);
        return JSONObject.parseObject(result,PageInfo.class);
    }

    @RequestMapping("getEdit")
    public String getEdit(Model model,
                          String id,
                          String wsName,
                          String self,
                          String oneMer,
                          String twoMer,
                          String oneAgent,
                          String towAgent) {
        model.addAttribute("id",id);
        model.addAttribute("wsName",wsName);
        model.addAttribute("self",self);
        model.addAttribute("oneMer",oneMer);
        model.addAttribute("twoMer",twoMer);
        model.addAttribute("oneAgent",oneAgent);
        model.addAttribute("towAgent",towAgent);
        return "/system/WsConfig/edit";
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(String id,
                       Double self,
                       Double oneMer,
                       Double twoMer,
                       Double oneAgent,
                       Double towAgent) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id",id);
        map.put("self",self.intValue()*100+"");
        map.put("oneMer",oneMer.intValue()*100+"");
        map.put("twoMer",twoMer.intValue()*100+"");
        map.put("oneAgent",oneAgent.intValue()*100+"");
        map.put("towAgent",towAgent.intValue()*100+"");
        String result = HttpClientUtil.doPost(Constaint.WsSet,map);
        JSONObject j = JSON.parseObject(result);
        String msg = j.getString("msg");
        System.out.println("---修改网申结果---"+result);
        return msg;
    }
}
