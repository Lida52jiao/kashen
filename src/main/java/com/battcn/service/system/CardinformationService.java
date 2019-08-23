package com.battcn.service.system;

import com.battcn.entity.Cardinformation;

public interface CardinformationService {

    Cardinformation queryCardInfoByCardNo(String cardNo, String appId);
}
