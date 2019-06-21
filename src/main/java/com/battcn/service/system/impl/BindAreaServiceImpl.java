package com.battcn.service.system.impl;

import com.battcn.entity.AgentBindArea;
import com.battcn.mapper.BindAreaMapper;

import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.BindAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dada on 2018/11/20.
 */
@Service
public class BindAreaServiceImpl extends BaseServiceImpl<AgentBindArea>  implements BindAreaService {
    @Autowired
    private BindAreaMapper bindAreaMapper;
}
