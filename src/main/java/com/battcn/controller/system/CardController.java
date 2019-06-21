package com.battcn.controller.system;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.battcn.controller.BaseController;
import com.battcn.service.system.CardService;

@Controller
@RequestMapping("Card")
public class CardController extends BaseController {
	
	@Autowired
	private CardService cardService;

}
