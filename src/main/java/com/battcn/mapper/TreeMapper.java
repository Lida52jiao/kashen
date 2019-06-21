package com.battcn.mapper;

import java.util.List;

import com.battcn.entity.Tree;

public interface TreeMapper {

	List<Tree> queryList();

	Tree findByObject(Tree tree);

	List<Tree> queryObjectForList(Tree t);

}
