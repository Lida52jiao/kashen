package com.battcn.controller.system;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.entity.*;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.CountT1OService;
import com.battcn.service.system.StatisticsService;
import com.battcn.service.system.TransactionService;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/Statistics/")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private AppNameService appNameService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private CountT1OService countT1OService;

    @RequestMapping("count")
    @SystemLog(module = "分润管理", methods = "分润详情")
    public String countby(Model model) {
        return "/system/statistics/list";
    }

    @RequestMapping("amountCount")
    @ResponseBody
    public PageInfo<CountT1Entity> findbytime(String startTime , String finishTime , String module , String aisleCode) throws ParseException {
        Map<String,Object> map = new HashedMap();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("institutionId", InstitutionIdNumber.AGENT);
        if(!"".equals(startTime)) {
            System.out.println(startTime+"--------------------------------------------");
            map.put("startDate", startTime);
            map.put("finishDate", finishTime);

            if(!"".equals(module)) {
                map.put("module", module);
            }
            if(!"".equals(aisleCode)) {
                map.put("aisleCode", aisleCode);
            }
        }else{
            //时间条件为空时默认前一天时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
            startTime = sdf.format(calendar.getTime());
            finishTime = sdf.format(today.getTime());
            map.put("startDate", startTime);
            map.put("finishDate", finishTime);
            if(!"".equals(module)) {
                map.put("module", module);
            }
            if(!"".equals((aisleCode))) {
                map.put("aisleCode", aisleCode);
            }
        }
        return statisticsService.findT1LIST(map);
    }

    @RequestMapping("total")
    @ResponseBody
    public String count(String startTime , String finishTime) throws ParseException {
        Map<String,Object> map = new HashedMap();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        map.put("institutionId", InstitutionIdNumber.AGENT);
        if(!"".equals(startTime)) {
            map.put("startTime", sdf.parse(startTime + " 00:00:00").getTime());
            map.put("finishTime", sdf.parse(finishTime + " 23:59:59").getTime());
        }else{
            //时间条件为空时默认前一天时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            map.put("startTime", calendar.getTimeInMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            map.put("finishTime", calendar.getTimeInMillis());
        }
        return statisticsService.amountCount(map);
    }
    //代理商利润查询(总交易金额、总分润)
    @RequestMapping("mercount")
    @SystemLog(module = "分润管理", methods = "分润详情")
    public String mercount(Model model) {
        return "/system/statistics/merList";
    }

    @RequestMapping("merCount")
    @ResponseBody
    public PageInfo<PlanEntity> merCount(String module, String aisleCode , String agentId , String startTime , String finishTime) throws ParseException {
        if(agentId.equals("")||agentId==null){
            return null;
        }
        Map<String,Object> map = new HashedMap();
        Transaction t = new Transaction();
        t.setMerId(agentId);
        t = transactionService.findByObject(t);
        Date today = new Date();
        map.put("merchantId",t.getMerChantId());
        map.put("aisleCode", aisleCode);
        if(!"".equals(module)){
            map.put("module", module);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if(!"".equals(startTime)) {
            map.put("startTime", sdf.parse(startTime + " 00:00:00").getTime());
            map.put("finishTime", sdf.parse(finishTime + " 23:59:59").getTime());
        }else{
            //时间条件为空时默认前一天时间
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(today);
            calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
            calendar.set(Calendar.HOUR_OF_DAY, 0);
            calendar.set(Calendar.MINUTE, 0);
            calendar.set(Calendar.SECOND, 0);
            map.put("startTime", calendar.getTimeInMillis());
            calendar.set(Calendar.HOUR_OF_DAY, 23);
            calendar.set(Calendar.MINUTE, 59);
            calendar.set(Calendar.SECOND, 59);
            map.put("finishTime", calendar.getTimeInMillis());
        }
        PlanEntity p = statisticsService.merchantCount(map);
        List<PlanEntity> list = new ArrayList<PlanEntity>();
        list.add(p);
        return new PageInfo<PlanEntity>(list);
    }


    @RequestMapping("countO")
    @SystemLog(module = "分润管理", methods = "O单商分润统计")
    public String countbyO(Model model) {
        UserEntity k = UserEntityUtil.getUserFromSession();
        if(k.getMerId().startsWith("T")) {
            model.addAttribute("app", appNameService.getList());
        }else{
            AppName an = new AppName();
            an.setAgentId(k.getMerId());
            an = appNameService.findByObject(an);
            if (an != null & !an.equals("")) {
                List<AppName> list = new ArrayList<>();
                list.add(an);
                model.addAttribute("app",list);
            }
        }
        return "/system/statistics/listO";
    }

    @RequestMapping("totalO")
    @ResponseBody
    public PageInfo<CountT1O> countO(String appId, String startTime, String finishTime,String module , String aisleCode){
        UserEntity k = UserEntityUtil.getUserFromSession();
        Map<String, Object> map = new HashedMap();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("institutionId", InstitutionIdNumber.AGENT);
        if (!"".equals(appId)) {
            map.put("appId", appId);
        }
        if(!"".equals(startTime)) {
            System.out.println(startTime + "--------------------------------------------");
            map.put("startDate", startTime);
            map.put("finishDate", finishTime);
            if (!"".equals(module)) {
                map.put("module", module);
            }
            if (!"".equals(aisleCode)) {
                map.put("aisleCode", aisleCode);
            }
        }else{
                //时间条件为空时默认前一天时间
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(today);
                calendar.set(Calendar.DATE, calendar.get(Calendar.DATE) - 1);
                startTime = sdf.format(calendar.getTime());
                finishTime = sdf.format(today.getTime());
                map.put("startDate", startTime);
                map.put("finishDate", finishTime);
                if(!"".equals(module)) {
                    map.put("module", module);
                }
                if(!"".equals(aisleCode)) {
                    map.put("aisleCode", aisleCode);
                }
            }
        System.out.println("---------------"+map);
        return countT1OService.findT1OLIST(map);
    }

}
