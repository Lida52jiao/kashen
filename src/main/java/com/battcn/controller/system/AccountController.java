package com.battcn.controller.system;

import com.alibaba.fastjson.JSON;
import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.Record;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AccountService;
import com.battcn.service.system.AppNameService;
import com.battcn.util.HttpClientUtils;
import com.battcn.util.UserEntityUtil;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dada on 2018/12/7.
 */
@Controller
@RequestMapping("Account")
public class AccountController extends BaseController {

    @Autowired
    private AccountService accountService;
    @Autowired
    private AppNameService appNameService;


    @RequestMapping("list")
    @SystemLog(module = "预充值管理", methods = "账户余额")
    public String list(Model model) {
        //20190624zww
        model.addAttribute("res", findResByUser());
        model.addAttribute("app", appNameService.getList());
        return "/system/account/allcash";
    }
    @RequestMapping("payTotalBalance")
    public String payTotalBalance (Model model) {
        UserEntity u= UserEntityUtil.getUserFromSession();
        model.addAttribute("u", u);
        return "/system/account/payTotalBalance";
    }
    @RequestMapping("queryPayRecord")
    public String queryPayRecord (Model model) {
        UserEntity u= UserEntityUtil.getUserFromSession();
        model.addAttribute("u", u);
        return "/system/account/queryPayRecordList";
    }
    @RequestMapping("queryPayRecordList")
    @ResponseBody
    public String queryPayRecordList (String institutionId, String pageSize, String pageNum,String sort, String order) {
        String url = "http://47.104.4.155:1172/rechargeRecord/select";
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("institutionId", institutionId);
        map.put("pageSize", pageSize);
        map.put("pageNum", pageNum);
        map.put("sort", sort);
        map.put("order", order);
        String result = HttpClientUtils.doPost(url, map);
        String string = JSON.parseObject(result).getString("data");
        return string;
    }
    @RequestMapping(value = "findaccount")
    @ResponseBody
    public PageInfo<Record> find(String merchantId ,
                                 String merName,
                                 String agentId,
                                 String orderNo,
                                 String appId,
                                 String type,
                                 String startTime,
                                 String finshTime) throws UnsupportedEncodingException, ParseException {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!"".equals(merchantId)) {
            map.put("merchantId", merchantId);
        }
        if(!"".equals(merName)){
//            merName = new String(merName.getBytes("iso8859-1"), "utf-8");
            map.put("merName", merName);
        }
        if(!"".equals(agentId)){
            map.put("agentId", agentId);
        }
        if(!"".equals(orderNo)){
            map.put("orderNo", orderNo);
        }
        if(!"".equals(appId)){
            map.put("appId", appId);
        }
        if(!"".equals(type)){
            map.put("type", type);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long startSeconds = 0;
        long finishSeconds = 0;
        if(!"".endsWith(startTime)){
            startSeconds = sdf.parse(startTime+" 00:00:00").getTime();//毫秒
            map.put("startTime", startSeconds);
        }
        if(!"".endsWith(finshTime)){
            finishSeconds = sdf.parse(finshTime+" 23:59:59").getTime();//毫秒
            map.put("finshTime", finishSeconds);
        }
        map.put("institutionId", InstitutionIdNumber.AGENT);
        return accountService.find(map);
    }

    @RequestMapping("total")
    @ResponseBody
    public String count(String startTime, String finishTime) throws ParseException {
        Map<String,Object> map = new HashedMap();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long startSeconds = 0;
        long finishSeconds = 0;

            if(!"".endsWith(startTime)){
                startSeconds = sdf.parse(startTime+" 00:00:00").getTime();//毫秒
                map.put("startTime", startSeconds);
            }

            if(!"".endsWith(finishTime)){
                finishSeconds = sdf.parse(finishTime+" 23:59:59").getTime();//毫秒
                map.put("finishTime", finishSeconds);
            }

        return accountService.count(map);
    }

    @RequestMapping("atotal")
    @ResponseBody
    public String acount()
    {
        Map<String,Object> map = new HashedMap();
        map.put("institutionId", InstitutionIdNumber.AGENT);
        return accountService.acount(map);
    }


}
