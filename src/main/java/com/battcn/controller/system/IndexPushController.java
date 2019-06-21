package com.battcn.controller.system;

import com.battcn.controller.BaseController;
import com.battcn.entity.AppName;
import com.battcn.entity.IndexPush;
import com.battcn.entity.UserEntity;
import com.battcn.service.system.AppNameService;
import com.battcn.service.system.IndexPushService;
import com.battcn.util.*;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @Author: Create By DaDa
 * @Date: 2019/3/13 10:52
 */
@Controller
@RequestMapping("indexPush")
public class IndexPushController extends BaseController {

    @Autowired
    private IndexPushService indexPushService;

    @Autowired
    private AppNameService appNameService;

    @RequestMapping("list")
    public String list(Model model) {
        UserEntity k = UserEntityUtil.getUserFromSession();
        AppName appName = new AppName();
        appName.setAgentId(k.getMerId());
        AppName appNames = appNameService.findByObject(appName);
        model.addAttribute("appName", appNames);
        model.addAttribute("res", findResByUser());
        return "/system/indexPush/list";
    }

    //查询展示
    @RequestMapping("getPush")
    @ResponseBody
    public PageInfo<IndexPush> getPush() {
        UserEntity k = UserEntityUtil.getUserFromSession();
        IndexPush ip = new IndexPush();

        if (k.getMerId().startsWith("T")) {
            return indexPushService.queryPageForList();
        }

        AppName appName = new AppName();
        appName.setAgentId(k.getMerId());
        AppName appNames = appNameService.findByObject(appName);
        ip.setAppName(appNames.getAppName());
        return indexPushService.queryPageForList(ip);
    }

    //添加弹窗图片信息
    @RequestMapping("addPush")
    @ResponseBody
    public String addPush(String appName, @RequestParam(value = "imgUrl") MultipartFile file, String msg, String title, String actionPath, HttpServletRequest request) {
        if (null == appName || "".equals(appName) || null == actionPath || "".equals(actionPath) || null == title || "".equals(title)) {
            return "s";
        }
        String filePath = "";
        UserEntity k = UserEntityUtil.getUserFromSession();
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                // 文件保存路径
                filePath = request.getSession().getServletContext()
                        .getRealPath("/")
                        + file.getOriginalFilename();
                // 转存文件
                file.transferTo(new File(filePath));
            } catch (Exception e) {
                e.printStackTrace();
                return "f";
            }
        } else {
            return "fail";
        }
        String s = MD5Util.getMD5String(filePath);
        String ossKey = "indexPush/" + s;
        String result = "";
        OSSClientUtil clientUtil = new OSSClientUtil();
        Date expiration = new Date(new Date().getTime() + 3600L * 1000 * 24
                * 365 * 10);
        // 上传
        InputStream instream = null;
        try {
            instream = new FileInputStream(filePath);
            clientUtil.uploadFile2OSS(instream, ossKey);
            // 获取url
            URL url = clientUtil.createUrl(ossKey, expiration);
            clientUtil.destory();
            result = url.toString();
            IndexPush n = new IndexPush();
            AppName appNames = new AppName();
            appNames.setAgentId(k.getMerId());
            appNames = appNameService.findByObject(appNames);
            n.setAppName(appName);
            n.setAppId(appNames.getAppId());
            n.setActionPath(actionPath);
            n.setTitle(title);
            n.setCreatDate(DateUtil.longToString(new Date().getTime(), "yyyy-MM-dd HH:mm:ss"));
            n.setImgUrl(result);
            n.setMsg(msg);
            indexPushService.save(n);
            return "success";
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return "f";
        }
    }

    //删除
    @RequestMapping("delete")
    @ResponseBody
    public String deleteNews(String id) {
        String[] newIds = id.toString().split(",");
        String count = "";
        for (String newId : newIds) {
            count = indexPushService.delete(Long.parseLong(newId));
        }
        return count;
    }


}
