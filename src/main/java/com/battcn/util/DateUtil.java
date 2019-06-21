package com.battcn.util;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by bin on 2017/11/7.
 */
public class DateUtil {
    public static void main(String[] args) {
        String s=longToString(1512121200000L,"yyyyMMddHHmmss");
        System.out.println(s);

    }
    public static String fmtTime(Long time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return sdf.format(time);
    }
    public static String longToString(long time,String fmt){
        SimpleDateFormat sdf = new SimpleDateFormat(fmt);
        return sdf.format(new Date(time));
    }
    public static Long zero(Long timestamp){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(timestamp));
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date start = calendar.getTime();
        return start.getTime();
    }
    // 时间戳计算到每天0点    判断有没有当前时间之前的时间戳
    public static boolean testTimestampStr(String[] timeStampStrs){
        Long today9Time=zero(new Date().getTime())+3600*9*1000L;
        for(int i=0;i<timeStampStrs.length;i++){
            if(Long.parseLong(timeStampStrs[i])<today9Time)return false;
        }
        return true;
    }
    public static String[]  fmtTimestampStr(String timeStampStr){
        String [] timeStampStrs=timeStampStr.split(",");
        Arrays.sort(timeStampStrs);
        for(int i=0;i<timeStampStrs.length;i++){
            timeStampStrs[i]=""+zero(Long.parseLong(timeStampStrs[i]));
        }
        return timeStampStrs;
    }
    //计算今天离还款日还有几天
    public static int date(String startDay,String endDay){
        int value;
        int start=Integer.parseInt(startDay);
        int end=Integer.parseInt(endDay);
        if(end>start){
            int v=end-getNowDay();
            //end>getNowDay()
            if(v>0){
                value=v;
            }else {
                value=getCurrentMonthDay()-getNowDay()+end;
            }
        }else {
            int c=end-getNowDay();
            if(c>0){
                value=c;
            }else {
                value=getCurrentMonthDay()-getNowDay()+end;
            }
        }
        return value;
    }
    //上月天数
    public static int lastDay() {
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String lastDay = format.format(calendar2.getTime());
        int day = Integer.parseInt(lastDay);
        return day;
    }
    //当月天数
    public static int getCurrentMonthDay() {
        Calendar a = Calendar.getInstance();
        a.set(Calendar.DATE, 1);
        a.roll(Calendar.DATE, -1);
        int maxDate = a.get(Calendar.DATE);
        return maxDate;
    }
    public static int getNowDay() {
        Calendar now = Calendar.getInstance();
        return now.get(Calendar.DAY_OF_MONTH);
    }

}
