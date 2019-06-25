package com.icss.oa.analyse.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.analyse.service.StockAnalyseService;

/**
 * 数据分析控制器
 * @author Administrator
 *
 */
@Controller
public class StockAnalyseController {
	
	@Autowired
	private StockAnalyseService service;
	
	@RequestMapping("/skAnalyse/query")
	@ResponseBody
	public List<Map<String, Object>> query(HttpServletRequest request,HttpServletResponse response) {
		return service.query();
	}

}
