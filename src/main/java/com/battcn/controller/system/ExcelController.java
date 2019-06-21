package com.battcn.controller.system;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.*;
import com.battcn.service.system.AlipayOrderEntityService;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.StatisticsService;
import com.battcn.util.*;
import com.github.pagehelper.PageInfo;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author: Create By DaDa
 * @Date: 2019/1/22 18:05
 */
@Controller
@RequestMapping("/excel/")
public class ExcelController  {

    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private AlipayOrderEntityService alipayOrderEntityService;
    @Autowired
    private StatisticsService statisticsService;



    @RequestMapping("getye")
    public String getye(Model model, String merchantId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("service","query_available_balance");
        map.put("version","1.0.0-PRD");
        map.put("charset","utf-8");
        map.put("language","zh_CN");
        map.put("signatureAlgorithm","RSA");
        map.put("signatureInfo","iyRVl02834Bjj0Ovs/RYCvmBQx2O1gVbPQS8nmZAtFZf0sdmoi+w==");
        map.put("callerIp","47.104.110.70");
        map.put("merchantId","2018091702288789");
        map.put("productNo","2GCB0AAA");
        String url = "https://218.17.35.123:6443/gateway/nonbatch";
        String result=new HttpClientUtils().doPost(url,map);
        JSONObject t = JSONObject.parseObject(result);
        String availableBalance = t.getString("availableBalance");
        return availableBalance;
    }


    //#####商户表导出#####
    @RequestMapping("merchantOut")
    @ResponseBody
    public void merchantOut(HttpServletRequest request, HttpServletResponse response, String merChantId,
                        String merName,
                        String merMp,
                        String certNo,
                        String merStat,
                        String agentStatus,
                        String merType,
                        String status,
                        String agentId,
                        String frozen,
                        String appId) throws Exception {
        //获取数据
        UserEntity k= UserEntityUtil.getUserFromSession();
        MerChants m = new MerChants();
        m.setInstitutionId(k.getMerId());
        m.setMerChantId(merChantId);
        m.setMerName(merName);
        m.setMerMp(merMp);
        m.setCertNo(certNo);
        m.setMerStat(merStat);
        m.setStatus(status);
        m.setAgentId(agentId);
        m.setFrozen(frozen);
        m.setMerType(merType);
        m.setAgentStatus(agentStatus);
        m.setAppId(appId);
        List<MerChants> list = merChantsService.getList(m);

        //excel标题
        String[] title = {"商户号","名称","手机号","身份证号","实名状态","绑定关系","代理商等级","代理商编号","商户等级","直推姓名","直推手机号","直推商户号","间推商户号"
                ,"是否可使用","注册时间","实名时间","绑定时间","缴费时间","到期时间","余额","分润","app名称"};

        //excel文件名
        String fileName = "商户管理表"+System.currentTimeMillis()+".xls";

        //sheet名
        String sheetName = "商户管理表";

        String[][] content = new String[list.size()][];
        for (int i = 0; i < list.size(); i++) {
            content[i] = new String[title.length];
            MerChants obj = list.get(i);
            content[i][0] = obj.getMerChantId();
            content[i][1] = obj.getMerName();
            content[i][2] = obj.getMerMp();
            content[i][3] = obj.getCertNo();
            content[i][4] = obj.getMerStat();
            content[i][5] = obj.getStatus();
            content[i][6] = obj.getAgentStatus();
            content[i][7] = obj.getAgentId();
            content[i][8] = obj.getMerType();
            content[i][9] = obj.getOnename();
            content[i][10] = obj.getOnemp();
            content[i][11] = obj.getOneMerId();
            content[i][12] = obj.getTwoMerId();
            content[i][13] = obj.getFrozen();
            if(!StringUtils.isBlank(obj.getRegDate()))
            {
                content[i][14] = DateUtil.fmtTime(Long.parseLong(obj.getRegDate()));
            }
            if(!StringUtils.isBlank(obj.getMerStatTime()))
            {
                content[i][15] = DateUtil.fmtTime(Long.parseLong(obj.getMerStatTime()));
            }
            if(!StringUtils.isBlank(obj.getStatusDate()))
            {
                content[i][16] = DateUtil.fmtTime(Long.parseLong(obj.getStatusDate()));
            }
            if(!StringUtils.isBlank(obj.getStartDate()))
            {
                content[i][17] = DateUtil.fmtTime(Long.parseLong(obj.getStartDate()));
            }
            if(!StringUtils.isBlank(obj.getFinishDate()))
            {
                content[i][18] = DateUtil.fmtTime(Long.parseLong(obj.getFinishDate()));
            }
            content[i][19] = obj.getBalance()+"";
            content[i][20] = obj.getBalanceProfit()+"";
            content[i][21] = obj.getAppName();

        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = excelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //#####年月费和升级查询导出#####

    @RequestMapping("alipayOut")
    @ResponseBody
    public void alipayOut(HttpServletResponse response,
                        String merchantId,
                        String agentId,
                        String phone,
                        String orderNo,
                        String state,
                        String type,
                        String appId
    ) throws Exception {
        //获取数据
        UserEntity k = UserEntityUtil.getUserFromSession();
        Map<String, Object> map = new HashMap<String, Object>();
        //总平台查询所有
        if (k.getMerId().startsWith("T")) {
            if (!StringUtils.isBlank(orderNo)) {
                map.put("orderNo", orderNo);
            }
            if (!StringUtils.isBlank(state)) {
                map.put("state", state);
            }
            if (!StringUtils.isBlank(type)) {
                map.put("type", type);
            }
            if (!StringUtils.isBlank(merchantId)) {
                map.put("merchantId", merchantId);
            }
            if (!StringUtils.isBlank(phone)) {
                map.put("phone", phone);
            }
            if (!StringUtils.isBlank(appId)) {
                map.put("appId", appId);
            }
            if (!StringUtils.isBlank(agentId)) {
                map.put("agentId", agentId);
            }
            map.put("institutionId", InstitutionIdNumber.AGENT);
            List<AlipayOrderEntity> list = getAlipay(map);
            System.out.println(list);
            //excel标题
            String[] title = {"商户号", "名称", "手机号", "订单号", "状态", "交易金额", "随机立减金额", "到账金额", "交易类型", "归属代理商", "机构号", "归属App", "缴费时间"};

            //excel文件名
            String fileName = "年月费和升级查询表" + System.currentTimeMillis() + ".xls";

            //sheet名
            String sheetName = "年月费和升级查询表";

            String[][] content = new String[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                content[i] = new String[title.length];
                AlipayOrderEntity obj = list.get(i);
                content[i][0] = obj.getMerchantId();
                content[i][1] = obj.getName();
                content[i][2] = obj.getPhone();
                content[i][3] = obj.getOrderNo();
                content[i][4] = obj.getState();
                content[i][5] = obj.getTotalAmount() + "";
                content[i][6] = obj.getReducedAmount() + "";
                content[i][7] = obj.getAmount() + "";
                content[i][8] = obj.getType();
                content[i][9] = obj.getAgentId();
                content[i][10] = obj.getInstitutionId();
                content[i][11] = obj.getAppId();
                if (!StringUtils.isBlank(obj.getCreateTime())) {
                    content[i][12] = obj.getCreateTime();
                }
            }

            //创建HSSFWorkbook
            HSSFWorkbook wb = excelUtil.getHSSFWorkbook(sheetName, title, content, null);

            //响应到客户端
            try {
                this.setResponseHeader(response, fileName);
                OutputStream os = response.getOutputStream();
                wb.write(os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //alipay
    public List<AlipayOrderEntity> getAlipay(Map<String, Object> map) {
        HttpServletRequest request = CommonUtil.getHttpRequest();
        Integer pageNum = CommonUtil
                .valueOf(request.getParameter("pageNum"), 100000);
        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
                100000);

        map.put("pageNum", pageNum.toString());
        map.put("pageSize", pageSize.toString());
        map.put("sort", "");
        map.put("order","");
        String url = "http://47.104.4.155/yj-account/pay/findAlipayOrder";
        String result=new HttpClientUtils().doPost(url,map);
        JSONObject t = JSONObject.parseObject(result);
        List<AlipayOrderEntity> list = JSON.parseArray(t.getString("list"),AlipayOrderEntity.class);
        return list;
    }

    //#####消费及还款#####
    @RequestMapping("tradeOut")
    @ResponseBody
    public void tradeOut(HttpServletRequest request,
                         HttpServletResponse response,
                         String orderNo,
                         String merchantId,
                         String name,
                         String phone,
                         String agentId,
                         String state,
                         String isLd,
                         String payState,
                         String repaymentState,
                         String executestartTime,
                         String executefinishTime,
                         String appId,
                         String planId,
                         String aisleCode,
                         String payType) throws UnsupportedEncodingException, ParseException {
        UserEntity k = UserEntityUtil.getUserFromSession();
        name = new String(name.getBytes("iso8859-1"), "utf-8");
        Map<String, Object> map = new HashMap<String, Object>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        if(k.getMerId().startsWith("T")){
            if(!StringUtils.isBlank(orderNo)){
                map.put("orderNo", orderNo);
            }
            if(!StringUtils.isBlank(aisleCode)){
                map.put("aisleCode", aisleCode);
            }
            if(!StringUtils.isBlank(isLd)){
                map.put("isLd", isLd);
            }
            if(!StringUtils.isBlank(payType)){
                map.put("payType", payType);
            }
            if(!StringUtils.isBlank(planId)){
                map.put("planId", planId);
            }
            if(!StringUtils.isBlank(merchantId)){
                map.put("merchantId", merchantId);
            }
            if(!StringUtils.isBlank(phone)){
                map.put("phone", phone);
            }
            if(!StringUtils.isBlank(name)){
                map.put("cardNo", name);
            }
            if(!StringUtils.isBlank(state)){
                map.put("state", state);
            }
            if(!StringUtils.isBlank(payState)){
                map.put("payState", payState);
            }
            if(!StringUtils.isBlank(repaymentState)){
                map.put("repaymentState", repaymentState);
            }
            if(!StringUtils.isBlank(appId)){
                map.put("appId", appId);
            }
            if(!StringUtils.isBlank(agentId)){
                map.put("agentId", agentId);
            }
            if(!StringUtils.isBlank(executestartTime)){
                map.put("executeDateStr", executestartTime);
            }
            map.put("institutionId", InstitutionIdNumber.AGENT);
            List<PlanDetailEntity> list = outtradelist(map);
            //excel标题
            String[] title = {"商户号", "消费类型", "通道标识", "商户姓名", "省份名称",
                    "城市名称", "商户手机号", "发卡行", "卡号", "代理商编号", "类型",
                    "发起状态", "支付状态", "还款状态", "情况描述", "交易金额", "到达金额",
                    "手续费", "订单号", "订单号（补）", "计划编号", "所属app", "创建时间",
                    "只执行开始时间", "只执行结束时间", "备注"};

            //excel文件名
            String fileName = "消费还款" + System.currentTimeMillis() + ".xls";

            //sheet名
            String sheetName = "消费还款";

            String[][] content = new String[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                content[i] = new String[title.length];
                PlanDetailEntity obj = list.get(i);
                content[i][0] = obj.getMerchantId();
                content[i][1] = obj.getIsLd();
                content[i][2] = obj.getAisleCode();
                content[i][3] = obj.getName();
                content[i][4] = obj.getProvince();
                content[i][5] = obj.getCity() + "";
                content[i][6] = obj.getPhone() + "";
                content[i][7] = obj.getBankCode() + "";
                content[i][8] = obj.getCardNo();
                content[i][9] = obj.getAgentId();
                content[i][10] = obj.getPayType();
                content[i][11] = obj.getState();
                content[i][12] = obj.getPayState();
                content[i][13] = obj.getRepaymentState();
                content[i][14] = obj.getCause()+"";
                content[i][15] = obj.getAmount()+"";
                content[i][16] = obj.getArrivalAmount()+"";
                content[i][17] = obj.getFee()+"";
                content[i][18] = obj.getOrderNo();
                content[i][19] = obj.getAnewOrderNo();
                content[i][20] = obj.getPlanId()+"";
                content[i][21] = obj.getAppId()+ "";
                content[i][22] = obj.getCreateTime()+ "";
                content[i][23] = obj.getExecuteTime()+ "";
                content[i][24] = obj.getFinishTime()+ "";
                content[i][25] = obj.getRemarks()+ "";
            }

            //创建HSSFWorkbook
            HSSFWorkbook wb = excelUtil.getHSSFWorkbook(sheetName, title, content, null);

            //响应到客户端
            try {
                this.setResponseHeader(response, fileName);
                OutputStream os = response.getOutputStream();
                wb.write(os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    //getTradeList
    public List<PlanDetailEntity> outtradelist(Map<String, Object> map) {
        HttpServletRequest request = CommonUtil.getHttpRequest();
        Integer pageNum = CommonUtil
                .valueOf(request.getParameter("pageNum"), 1);
        Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
                1000000);
        map.put("pageNum", pageNum.toString());
        map.put("pageSize", pageSize.toString());
        map.put("sort", "executeTime");
        String url = APIUtil.Find_Plan_Detial_by_Time;
        String result=new HttpClientUtils().doPost(url,map);
        JSONObject t = JSONObject.parseObject(result);
        List<PlanDetailEntity> list = JSON.parseArray(t.getString("list"),PlanDetailEntity.class);

        return list;
    }

    //#####T1报表导出#####
    @RequestMapping("T1Out")
    @ResponseBody
    public void T1Out(HttpServletRequest request,
                      HttpServletResponse response,
                      String startTime ,
                      String finishTime ,
                      String module ,
                      String aisleCode) {
        Map<String,Object> map = new HashedMap();
        Date today = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        map.put("institutionId", InstitutionIdNumber.AGENT);
        if(!"".equals(startTime)) {
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
        List<CountT1Entity> list = outT1LIST(map);
            //excel标题
            String[] title = {"交易类型", "通道名称", "交易金额(元)", "平台归属分润", "平台下发分润",
                    "交易时间", "生成时间"};

            //excel文件名
            String fileName = "T1报表导出" + System.currentTimeMillis() + ".xls";

            //sheet名
            String sheetName = "T1报表导出";

            String[][] content = new String[list.size()][];
            for (int i = 0; i < list.size(); i++) {
                content[i] = new String[title.length];
                CountT1Entity obj = list.get(i);
                content[i][0] = obj.getModule();
                content[i][1] = obj.getAisleCode();
                content[i][2] = obj.getTotalAmount()+"";
                content[i][3] = obj.getInstitutionFee()+"";
                content[i][4] = obj.getNotInstitutionFee()+"";
                content[i][5] = obj.getCountDate();
                content[i][6] = obj.getCreateTime();
            }

            //创建HSSFWorkbook
            HSSFWorkbook wb = excelUtil.getHSSFWorkbook(sheetName, title, content, null);

            //响应到客户端
            try {
                this.setResponseHeader(response, fileName);
                OutputStream os = response.getOutputStream();
                wb.write(os);
                os.flush();
                os.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //GET T1List
        public List<CountT1Entity> outT1LIST(Map<String,Object> map) {
            HttpServletRequest request = CommonUtil.getHttpRequest();
            Integer pageNum = CommonUtil
                    .valueOf(request.getParameter("pageNum"), 1);
            Integer pageSize = CommonUtil.valueOf(request.getParameter("pageSize"),
                    100000);
//            String orderField = request.getParameter("sort");
//            String orderDirection = request.getParameter("order");
            map.put("pageNum", pageNum.toString());
            map.put("pageSize", pageSize.toString());
//            map.put("sort", orderField);
//            map.put("order",orderDirection);
            String url = "http://172.31.109.39/yj-account/count/t1ByDate";
            String result=new HttpClientUtils().doPost(url,map);
            JSONObject t = JSONObject.parseObject(result);
            List<CountT1Entity> list = JSON.parseArray(t.getString("list"),CountT1Entity.class);
        return list;
        }








    //发送响应流方法
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(),"ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
