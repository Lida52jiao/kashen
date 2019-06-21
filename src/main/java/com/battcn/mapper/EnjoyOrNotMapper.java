package com.battcn.mapper;

import com.battcn.entity.EnjoyOrNot;
import com.battcn.entity.EnjoyOrNotExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface EnjoyOrNotMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int countByExample(EnjoyOrNotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int deleteByExample(EnjoyOrNotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int insert(EnjoyOrNot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int insertSelective(EnjoyOrNot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    List<EnjoyOrNot> selectByExample(EnjoyOrNotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    EnjoyOrNot selectByPrimaryKey(Long id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") EnjoyOrNot record, @Param("example") EnjoyOrNotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") EnjoyOrNot record, @Param("example") EnjoyOrNotExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(EnjoyOrNot record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table num_equity_enjoy
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(EnjoyOrNot record);
}