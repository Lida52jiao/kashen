package com.battcn.mapper;

import java.util.List;

import tk.mybatis.mapper.common.Mapper;

import com.battcn.entity.Mer;
import com.battcn.entity.MerChants;

public interface MerChantsMapper extends Mapper<MerChants> {

	List<MerChants> gain(MerChants m);

	List<MerChants> query(Mer m);

	void alter(MerChants t);

	void amend(MerChants t);

	List<MerChants> gets(MerChants m);
}
