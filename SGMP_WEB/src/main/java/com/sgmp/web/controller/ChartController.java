package com.sgmp.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ChartController {
	@RequestMapping(value="/company_chart")
	public String chart() throws Exception{
		return "company_chart";
	}
}
