package com.battcn.service.system;
import com.battcn.entity.BlackListEntity;
import com.battcn.util.YJResult;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * Created by Dada on 2018/11/26.
 */
public interface limitcashService {
    PageInfo<BlackListEntity> find(Map<String, Object> map);

    String add(Map<String, Object> map);

    String delete(Map<String, Object> map);

}
