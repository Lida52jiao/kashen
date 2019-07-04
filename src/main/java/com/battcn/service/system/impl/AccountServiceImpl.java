package com.battcn.service.system.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.Record;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.AccountService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Dada on 2018/12/7.
 */
@Service
public class AccountServiceImpl extends BaseServiceImpl<Record> implements AccountService {
    @Override
    public String count(Map<String, Object> map) {
        String url = "http://47.104.4.155:1172/record/count";
        String result=new HttpClientUtils().doPost(url,map);
//        JSONObject job = JSONObject.parseObject(result);
//        String data = job.getString("data");
//        JSONObject jobs = JSONObject.parseObject(data);
//        String totalFee = jobs.getString("totalFee");
        return result;
    }

    @Override
    public String acount(Map<String, Object> map) {
        String url = "http://47.104.4.155:1172/account/getAccount";
        String result=new HttpClientUtils().doPost(url,map);
        JSONObject job = JSONObject.parseObject(result);
        String data = job.getString("data");
        JSONObject jobs = JSONObject.parseObject(data);
        String balance = jobs.getString("balance");
        return balance;
    }

    @Override
    public PageInfo<Record> find(Map<String, Object> map) {
        HttpServletRequest request = CommonUtil.getHttpRequest();
        Integer pageNum = CommonUtil
                .valueOf(request.getParameter("pageNum"), 1);
        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"), 10);
        String orderField = request.getParameter("sort");
        String orderDirection = request.getParameter("order");
        map.put("pageNum", pageNum.toString());
        map.put("pageSize", pageSize.toString());
        map.put("sort", orderField);
        map.put("order",orderDirection);
        String url = "http://47.104.4.155:1172/record/select";
        String result=new HttpClientUtils().doPost(url,map);
        JSONObject t = JSONObject.parseObject(result);
        List<Record> list = JSON.parseArray(t.getString("list"),Record.class);
        PageInfo<Record> page = new PageInfo<Record>();
        page.setList(list);
        page.setPageSize(Integer.parseInt(t.getString("pageSize")));
        page.setPages(Integer.parseInt(t.getString("pages")));
        page.setTotal(Integer.parseInt(t.getString("total")));
        return page;
    }
}
