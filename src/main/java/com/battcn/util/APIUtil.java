package com.battcn.util;

/**
 * @Author: Create By DaDa
 * @Date: 2019/2/27 10:49
 */
public class APIUtil {

    //计划详情查询/计划查询
//    public static final String Find_Plan = "http://172.31.109.46:1003/";
    public static final String Find_Plan = "http://47.104.161.254:1003/";
    public static final String Find_Plan_Detial_by_Time = Find_Plan + "common/plan/findPlanDetailByTime";
    public static final String Find_Plan_by_Time = Find_Plan + "common/plan/findPlanByTime";
    public static final String Find_Group_History = Find_Plan + "ld17/planMultiCard/findGroupHistory";
    public static final String clear = Find_Plan + "ld17/planMultiCard/clear";
    public static final String Find_Date_List = Find_Plan + "ld17/order/findDateList";

}
