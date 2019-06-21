package com.battcn.service.system.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.CountT1Entity;
import com.battcn.entity.PlanEntity;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.StatisticsService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Service
public class StatisticsServiceImpl extends BaseServiceImpl<CountT1Entity> implements StatisticsService {

    @Override
    public PageInfo<CountT1Entity> findT1LIST(Map<String, Object> map) {
        HttpServletRequest request = CommonUtil.getHttpRequest();
        Integer pageNum = CommonUtil
                .valueOf(request.getParameter("pageNum"), 1);
        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
                10);
        String orderField = request.getParameter("sort");
        String orderDirection = request.getParameter("order");
        map.put("pageNum", pageNum.toString());
        map.put("pageSize", pageSize.toString());
        map.put("sort", orderField);
        map.put("order", orderDirection);
        String url = "http://172.31.109.39/yj-account/count/t1ByDate";
        String result = new HttpClientUtils().doPost(url, map);
        PageInfo<CountT1Entity> page = JSON.parseObject(result, PageInfo.class);
        return page;
    }

    @Override
    public String amountCount(Map<String, Object> map) {
        String url = "http://47.104.25.147/yj-epos/order/amountCount";
        String result = new HttpClientUtils().doPost(url, map);
        JSONObject job = JSONObject.parseObject(result);
        if (job == null) {
            return 0 + "";
        }
        String d0Fee = job.getString("d0Fee");
        Long a = Long.parseLong(d0Fee);
        System.out.println(d0Fee + "----------------------------------------------------");
        String number = job.getString("number");
        Long b = Long.parseLong(number);
        System.out.println(number + "----------------------------------------------------");
        String data = a - b * 100 + "";

        return data;
    }

    @Override
    public PlanEntity merchantCount(Map<String, Object> map) {
        String url = "http://47.104.22.226/yj-account/transactional/countProfit";
        String result = new HttpClientUtils().doPost(url, map);
        System.out.println(map);
        System.out.println(result);
        JSONObject job = JSONObject.parseObject(result);
        String data = job.getString("data");
        JSONObject datas = JSONObject.parseObject(data);
        PlanEntity temp = new PlanEntity();
        if (data != null && !"".equals(data)) {
            temp.setTotalAmount(datas.getLong("totalAmount"));
            temp.setTotalFee(datas.getLong("totalFee"));
        }
        return temp;
    }
}