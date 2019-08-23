package com.battcn.service.system.impl;

import com.battcn.entity.Cardinformation;
import com.battcn.mapper.CardinformationMapper;
import com.battcn.service.system.CardinformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardinformationServiceImpl implements CardinformationService {
    @Autowired
    private CardinformationMapper cardinformationMapper;

    @Override
    public Cardinformation queryCardInfoByCardNo(String cardNo, String appId) {
        return cardinformationMapper.queryCardInfoByCardNo(cardNo, appId);
    }
}
