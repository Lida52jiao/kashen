package com.battcn.service.system;

import com.battcn.entity.CountT1Entity;
import com.battcn.entity.CountT1O;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @Author: Create By DaDa
 * @Date: 2019/4/2 10:37
 */
public interface CountT1OService extends BaseService<CountT1O> {
    public PageInfo<CountT1O> findT1OLIST(Map<String, Object> map);
}
