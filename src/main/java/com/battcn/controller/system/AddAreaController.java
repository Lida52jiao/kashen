package com.battcn.controller.system;

import com.battcn.controller.BaseController;
import com.battcn.entity.Area;
import com.battcn.service.system.AddAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: Create By DaDa
 * @Date: 2019/4/28 10:20
 */
@Controller
@RequestMapping("addArea")
public class AddAreaController extends BaseController {
    @Autowired
    private AddAreaService areaService;


    @RequestMapping("getProvince")
    public String getProvince (Model model){
        Area a = new Area();
        a.setParent_id("0");
        List<Area> list = areaService.queryObjectForList(a);
        model.addAttribute("province",list);
        return "/system/addArea/addList";
    }
    @RequestMapping("addCity")
    @ResponseBody
    public String addCity(String province,
                          String city,
                          String area_name,
                          String code,
                          String is_last,
                          String area
                          ){
        if(!"".equals(area)&&"1".equals(area)) {
            if ("".equals(province) || "".equals(area_name) || "".equals(code) || "".equals(is_last)) {
                return "Can not be empty";
            }
            try {
                Area a = new Area();
                a.setParent_id(province);
                a.setArea_name(area_name);
                a.setCode(code);
                a.setIs_last("0");
                a.setLevel("2");
                areaService.save(a);
            } catch (Exception e) {
                return "fail";
            }
            return "success";
        }else if(!"".equals(area)&&"2".equals(area)){
            if("".equals(city)||"".equals(area_name)||"".equals(code)){
                return "Can not be empty";
            }
            try {
                Area a = new Area();
                a.setParent_id(city);
                a.setArea_name(area_name);
                a.setCode(code);
                a.setIs_last("1");
                a.setLevel("3");
                areaService.save(a);
            }catch (Exception e) {
                return "fail";
            }
            return "success";
        }
        return "fail";
    }

    @RequestMapping("getCity")
    public String getCity (Model model) {
        return "/system/addArea/addCity";
    }
//    @RequestMapping("addCounty")
//    @ResponseBody
//    public String addCounty(String city,
//                            String area_name,
//                            String code,
//                            String is_last){
//        if("".equals(city)||"".equals(area_name)||"".equals(code)){
//            return "Can not be empty";
//        }
//        try {
//        Area a = new Area();
//        a.setParent_id(city);
//        a.setArea_name(area_name);
//        a.setCode(code);
//        a.setIs_last("1");
//        a.setLevel("3");
//
//            areaService.save(a);
//        }catch (Exception e) {
//            return "添加县级区域失败";
//        }
//        return "添加县级区域成功";
//    }
}
