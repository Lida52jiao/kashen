package com.battcn.service.system;

import com.battcn.entity.AgentBindArea;
import com.battcn.service.BaseService;

/**
 * Created by Dada on 2018/11/22.
 */
public interface AgentBindService extends BaseService<AgentBindArea> {
     String bindArea(String merId, String merChantId, String province,
                     String city, String region);
}
