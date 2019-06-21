package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Code;
import com.battcn.mapper.CodeMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.CodeService;

@Service
public class CodeServiceImpl extends BaseServiceImpl<Code> implements CodeService {
	
	@Autowired
	private CodeMapper codeMapper;

}
