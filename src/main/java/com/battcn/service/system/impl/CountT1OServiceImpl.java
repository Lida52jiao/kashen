package com.battcn.service.system.impl;

import com.alibaba.fastjson.JSON;
import com.battcn.entity.CountT1Entity;
import com.battcn.entity.CountT1O;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.CountT1OService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @Author: Create By DaDa
 * @Date: 2019/4/2 10:38
 */
@Service
public class CountT1OServiceImpl extends BaseServiceImpl<CountT1O> implements CountT1OService {

    @Override
    public PageInfo<CountT1O> findT1OLIST(Map<String,Object> map) {
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
        map.put("order",orderDirection);
        String url = "http://47.104.22.226/yj-account/count/appT1ByDate";
        String result=new HttpClientUtils().doPost(url,map);
        PageInfo<CountT1O> page = JSON.parseObject(result,PageInfo.class);
        return page;
    }
}
