package com.battcn.service.system.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.entity.BlackListEntity;
import com.battcn.service.system.limitcashService;
import com.battcn.util.CommonUtil;
import com.battcn.util.HttpClientUtils;
import com.battcn.util.YJResult;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Dada on 2018/11/26.
 */
@Service
public class limitcashServiceImpl  implements limitcashService {

    @Override
    public PageInfo<BlackListEntity> find(Map<String, Object> map) {
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
            String url = "http://47.104.4.155/yj-account/blackList/find";
            String result=new HttpClientUtils().doPost(url,map);
            JSONObject t = JSONObject.parseObject(result);
            List<BlackListEntity> list = JSON.parseArray(t.getString("list"),BlackListEntity.class);
            PageInfo<BlackListEntity> page = new PageInfo<BlackListEntity>();
            page.setList(list);
            page.setPageSize(Integer.parseInt(t.getString("pageSize")));
            page.setPages(Integer.parseInt(t.getString("pages")));
            page.setTotal(Integer.parseInt(t.getString("total")));
            return page;

}

        @Override
        public String add(Map<String, Object> map) {
                String url = "http://47.104.4.155/yj-account/blackList/add";
                String result=new HttpClientUtils().doPost(url,map);
                YJResult yjResult = JSONObject.parseObject(result,YJResult.class);
                return yjResult.getRespCode();
        }

        @Override
        public String delete(Map<String, Object> map) {
                String url = "http://47.104.4.155/yj-account/blackList/delete";
                String result=new HttpClientUtils().doPost(url,map);
                YJResult yjResult = JSONObject.parseObject(result,YJResult.class);
                return yjResult.getRespCode();
        }
}
