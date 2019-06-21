package com.battcn.service.system.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Tree;
import com.battcn.mapper.TreeMapper;
import com.battcn.service.system.TreeService;
@Service
public class TreeServiceImpl implements TreeService {
	
	@Autowired
	private TreeMapper treeMapper;

	@Override
	public List<Tree> queryList() {
		
		return treeMapper.queryList();
	}

	@Override
	public Tree findByObject(Tree tree) {
		
		return treeMapper.findByObject(tree);
	}

	@Override
	public List<Tree> queryObjectForList(Tree t) {
		
		return treeMapper.queryObjectForList(t);
	}

}
