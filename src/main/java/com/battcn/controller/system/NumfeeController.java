package com.battcn.controller.system;

import com.battcn.annotation.SystemLog;
import com.battcn.constant.InstitutionIdNumber;
import com.battcn.controller.BaseController;
import com.battcn.entity.BlackListEntity;
import com.battcn.entity.Numfee;
import com.battcn.service.system.MerChantsService;
import com.battcn.service.system.MerchantsRateService;
import com.battcn.service.system.NumfeeService;
import com.battcn.service.system.limitcashService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Dada on 2018/11/13.
 */
@Controller
@RequestMapping("Numfee")
public class NumfeeController extends BaseController {
    @Autowired
    private NumfeeService numfeeService;
    @Autowired
    private MerChantsService merchantService;
    @Autowired
    private limitcashService limitservice;
    @Autowired
    private MerchantsRateService merchantsRateService;

    @RequestMapping("list")
    @SystemLog(module = "风控管理", methods = "商户代理费率配置")
    public String list(Model model) {
        model.addAttribute("res", findResByUser());
        return "/system/NumUpdate/numfeelist";
    }

    @RequestMapping(value = "findfee")
    @ResponseBody
    public PageInfo<Numfee> get(String merType) {
        Numfee m = new Numfee();
        if (!"".equals(merType)) {
            m.setMerType(merType);
        }
        PageInfo<Numfee> n = numfeeService.queryPageForList(m);
        return n;
    }


    //-------------编辑
    @RequestMapping("editfee")
    @SystemLog(module = "商户代理费率配置", methods = "编辑")
    public String alertInformation(Model model, Long id) {
        Numfee n = numfeeService.findByPrimaryKey(id);
        model.addAttribute("goods", n);
        return "/system/NumUpdate/numfeeedit";
    }

    @RequestMapping("edit")
    @ResponseBody
    public String edit(Long id,
                       String merType,
                       BigDecimal rate
    ) throws UnsupportedEncodingException {

        Numfee n = new Numfee();
        n.setId(id);
        n.setRate(rate);
        numfeeService.update(n);
        return "success";


    }
    //----------限制提现----------

    @RequestMapping("limit")
    @SystemLog(module = "风控管理", methods = "限制提现")
    public String limitlist(Model model) {
        model.addAttribute("res", findResByUser());
        return "/system/LimitCash/limitcash";
    }

    @RequestMapping(value = "findblack")
    @ResponseBody
    public PageInfo<BlackListEntity> findblack(String merchantId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!"".equals(merchantId)) {
            map.put("merchantId", merchantId);
        }
        map.put("institutionId", InstitutionIdNumber.AGENT);
        return limitservice.find(map);
    }
    //------编辑------
    @RequestMapping("addlimit")
    @SystemLog(module = "查询商户的信息", methods = "添加")
    public String insertInformation(Model model) {

        return "/system/LimitCash/addlist";
    }
    //------添加------
    @RequestMapping("add")
    @ResponseBody
    public String add(String merchantId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!"".equals(merchantId)) {
            map.put("merchantId", merchantId);
            map.put("institutionId", InstitutionIdNumber.AGENT);
        }
        String result = limitservice.add(map);
        return result;
    }
    //------删除------
    @RequestMapping(value = "dellimit", produces = "text/html;charset=UTF-8;")
    @ResponseBody
    public String deletelimit(String merchantId) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (!"".equals(merchantId)) {
            map.put("merchantId", merchantId);
            map.put("institutionId", InstitutionIdNumber.AGENT);
        }
        String result = limitservice.delete(map);
        return result;
    }
}
