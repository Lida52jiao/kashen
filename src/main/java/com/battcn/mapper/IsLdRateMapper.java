package com.battcn.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.battcn.entity.IsLdRate;
import com.battcn.entity.IsLdRateExample;

public interface IsLdRateMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int countByExample(IsLdRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int deleteByExample(IsLdRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int insert(IsLdRate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int insertSelective(IsLdRate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    List<IsLdRate> selectByExample(IsLdRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    IsLdRate selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") IsLdRate record, @Param("example") IsLdRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") IsLdRate record, @Param("example") IsLdRateExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(IsLdRate record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_isldrate
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(IsLdRate record);
    
    List<IsLdRate> selectasile();
    List<IsLdRate> asileGroupByAppId(@Param("appId")String appId);
    List<IsLdRate> asileGroup();
}