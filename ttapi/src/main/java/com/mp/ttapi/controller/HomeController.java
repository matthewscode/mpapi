package com.mp.ttapi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;



@Controller
@RequestMapping("/")
public class HomeController {

    
	@RequestMapping("/")
    public String list() {
        return "site/home";
    }
	
	@RequestMapping("/util")
	public String showUtility(){
		return "site/util";
	}
}
