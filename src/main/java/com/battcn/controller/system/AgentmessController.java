package com.battcn.controller.system;

import com.battcn.controller.BaseController;
import com.battcn.service.system.AgentmessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Dada on 2018/11/20.
 */
@Controller
@RequestMapping("Agentdel")
public class AgentmessController extends BaseController{
    @Autowired
    private AgentmessService agentmessService;

    @RequestMapping(value = "del",produces="text/html;charset=UTF-8;")
    @ResponseBody
    public String deleteInformation(Long id,String merId,String merChantId) {
        return agentmessService.deleteInformation(id,merId,merChantId);
    }
}
