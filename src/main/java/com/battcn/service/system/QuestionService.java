package com.battcn.service.system;

import java.util.List;
import java.util.Map;

import com.battcn.entity.Interlocution;
import com.battcn.entity.Title;
import com.github.pagehelper.PageInfo;

public interface QuestionService {

	List<Title> queryObjectForList();

	String save(String title, String appId, String question, String answer,
			String answers, String merId);

	PageInfo<Interlocution> query(Map<String, Object> map);

	Interlocution recieve(Map<String, Object> map);

	String alter(Map<String, Object> map);

	String deletes(Map<String, Object> map);

}
