package com.battcn.service.system;

import com.battcn.service.BaseService;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Dada on 2018/11/21.
 */
public interface AgentmessService {

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRED)
    String deleteInformation(Long id, String merId, String merChantId);
}
