package com.battcn.service.system.impl;

import com.battcn.entity.*;
import com.battcn.service.system.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Dada on 2018/11/21.
 */
@Service
public class AgentmessServiceImpl  implements AgentmessService {
    @Autowired
    private AgentService agentService;
    @Autowired
    private MerChantsService merChantsService;
    @Autowired
    private TransactionService transactionService;
    @Autowired
    private UserentityService userentityService;
    @Autowired
    private AgentratedelService agentRateService;
    @Autowired
    private RatesdelService ratesdelService;
    @Autowired
    private BindAreaService bindAreaService;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    @Override
    public String deleteInformation(Long id, String merId, String merChantId)
    {
        Transaction transaction = new Transaction();
        transaction.setMerChantId(merChantId);
        transaction = transactionService.findByObject(transaction);
        if(transaction == null){
            agentService.delete(id);
            return "success";
        }
        //商户表
        //1改上级代理和代理等级
        MerChants mer = new MerChants();
        mer.setMerChantId(merChantId);
        mer = merChantsService.findByObject(mer);
        Agent a = new Agent();
        a.setMerId(merId);
        a = agentService.findByObject(a);
        if(a!=null) {
            mer.setAgentId(a.getOneMerId());
            mer.setAgentStatus("N");
        }
        merChantsService.update(mer);
        //2循环改直推上级代理
        MerChants ma = new MerChants();
        ma.setAgentId(merId);
        List<MerChants> mlist = merChantsService.queryObjectForList(ma);
        for (MerChants m : mlist) {
            if(m!=null) {
                m.setAgentId(a.getOneMerId());
                merChantsService.update(m);
            }
        }
        //代理商表
        Agent at = new Agent();
        at.setOneMerId(merId);
        List<Agent> al= agentService.queryObjectForList(at);
        for(Agent ag : al ){
            ag.setOneMerId(a.getOneMerId());
            agentService.update(ag);
        }
        agentService.delete(id);
        //中间表Transaction
        Transaction t = new Transaction();
        t.setMerChantId(merChantId);
        t = transactionService.findByObject(t);
        transactionService.delete( t.getId());
        //Userinfo
        UserEntity u = new UserEntity();
        u.setMerId(merId);
        u = userentityService.findByObject(u);
        userentityService.delete(u.getId());
        //Agentrate
        Agentr r = new Agentr();
        r.setMerchantid(merChantId);
        List<Agentr> alist = agentRateService.queryObjectForList(r);
        for (Agentr ar : alist) {
            if(ar!=null) {
                agentRateService.delete(ar.getId());
            }
        }
        //Rate
        Rate rs = new Rate();
        rs.setMerchantid(merChantId);
        List<Rate> rlist = ratesdelService.queryObjectForList(rs);
        for (Rate re : rlist) {
            if(re!=null) {
                ratesdelService.delete(re.getId());
            }
        }
        //Agentarea
        AgentBindArea aa = new AgentBindArea();
        aa.setMerChantId(merChantId);
        List<AgentBindArea> aalist = bindAreaService.queryObjectForList(aa);
            for (AgentBindArea ab : aalist) {
                if(ab!=null) {
                bindAreaService.delete(ab.getId());
            }
        }

        return "success";
    }
}
