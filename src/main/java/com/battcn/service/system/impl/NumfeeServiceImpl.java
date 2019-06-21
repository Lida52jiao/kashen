package com.battcn.service.system.impl;

import com.battcn.entity.Numfee;
import com.battcn.mapper.NumfeeMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.NumfeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Dada on 2018/11/13.
 */
@Service
public class NumfeeServiceImpl extends BaseServiceImpl<Numfee> implements NumfeeService {
    @Autowired
    private NumfeeMapper numfeeMapper;

}
