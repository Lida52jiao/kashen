package com.battcn.service.system.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.battcn.entity.Card;
import com.battcn.mapper.CardMapper;
import com.battcn.service.BaseServiceImpl;
import com.battcn.service.system.CardService;

@Service
public class CardServiceImpl extends BaseServiceImpl<Card> implements CardService {
	
	@Autowired
	private CardMapper cardMapper; 

}
