package com.battcn.mapper;

import com.battcn.entity.Num;
import com.battcn.entity.NumExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface NumMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int countByExample(NumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int deleteByExample(NumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int insert(Num record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int insertSelective(Num record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    List<Num> selectByExample(NumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    Num selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") Num record, @Param("example") NumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") Num record, @Param("example") NumExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(Num record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_mp_num
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(Num record);
}