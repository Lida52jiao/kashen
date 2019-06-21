package com.battcn.service.system.impl;

import com.battcn.entity.IndexPush;
import com.battcn.mapper.IndexPushMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.IndexPushService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 11:02
 */
@Service
public class IndexPushServiceImpl extends BaseServiceImpl<IndexPush> implements IndexPushService{
    @Autowired
    private IndexPushMapper indexPushMapper;
}
