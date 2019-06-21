package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.Mer;
import com.battcn.entity.MerChants;
import com.battcn.service.BaseService;
import com.github.pagehelper.PageInfo;

public interface MerChantsService extends BaseService<MerChants> {

	PageInfo<MerChants> gain(MerChants m);

	PageInfo<MerChants> query(Mer m);

	void recieve(MerChants t);

	void alter(MerChants t);

	PageInfo<MerChants> gains(MerChants m);

	List<MerChants> getList(MerChants mer);






}
