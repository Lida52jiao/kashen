package com.battcn.service.system;

import java.util.List;

import com.battcn.entity.Tree;

public interface TreeService {

	List<Tree> queryList();

	Tree findByObject(Tree tree);

	List<Tree> queryObjectForList(Tree t);

}
