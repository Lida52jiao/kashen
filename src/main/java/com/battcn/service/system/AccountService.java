package com.battcn.service.system;

import com.battcn.entity.Record;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by Dada on 2018/12/7.
 */
public interface AccountService extends BaseService<Record> {

    String count(Map<String, Object> map);

    String acount(Map<String, Object> map);

    PageInfo<Record> find(Map<String, Object> map);
}
