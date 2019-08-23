package com.battcn.mapper;

import com.battcn.entity.Cardinformation;
import org.apache.ibatis.annotations.Param;

public interface CardinformationMapper {
    int deleteByPrimaryKey(Long cardid);

    int insert(Cardinformation record);

    int insertSelective(Cardinformation record);

    Cardinformation selectByPrimaryKey(Long cardid);

    int updateByPrimaryKeySelective(Cardinformation record);

    int updateByPrimaryKey(Cardinformation record);

    Cardinformation queryCardInfoByCardNo(@Param("cardNo") String cardNo, @Param("appId") String appId);
}