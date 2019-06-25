package com.icss.oa.stock.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.stock.pojo.StockRecord;
import com.icss.oa.stock.service.StockRecordService;

@Controller
public class StockRecordController {
	
	@Autowired
	private StockRecordService service;
	
	//增加库存记录
	@RequestMapping("/srd/add")
	public void add(HttpServletRequest request, HttpServletResponse response, StockRecord stockRecord) {
		service.addSrd(stockRecord);
	}
	
	//删除库存记录
	@RequestMapping("/srd/delete")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer srId) {
		service.deleteSrd(srId);
	}
	
	//修改库存记录
	@RequestMapping("/srd/update")
	public void update(HttpServletRequest request, HttpServletResponse response, StockRecord stockRecord) {
		service.updateSrd(stockRecord);
	}
	
	//根据id查询
	@RequestMapping("/srd/queryById")
	@ResponseBody
	public StockRecord queryById(HttpServletRequest request, HttpServletResponse response, Integer srId) {
		return service.querySrdById(srId);
	}
	
	//条件查询
	@RequestMapping("/srd/query")
	@ResponseBody
	public HashMap<String, Object> query(HttpServletRequest request, HttpServletResponse response, Integer pageSize,
			Integer pageNum, Integer srDeptId, Integer srStockId, String srTime) {

		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null)
			pageSize = service.getSrdCount(srDeptId, srStockId, srTime);
		
		Pager pager = new Pager(service.getSrdCount(srDeptId, srStockId, srTime), pageSize, pageNum);
		List<StockRecord> list = service.querySrdByCond(pager, srDeptId, srStockId, srTime);
		
		// 在Map集合中存储分页数据和名片数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}
	
	//导出excel报表
	@RequestMapping("/srd/export")
	public void export(HttpServletRequest request,HttpServletResponse response, Integer pageSize, Integer pageNum,
			Integer srDeptId, Integer srStockId, String srTime) throws IOException {
		
		System.out.println(srDeptId);
		// 设置浏览器以附件形式接收数据（下载文件）
		response.setHeader("content-disposition", "attachment;filename=stock.xls");		
		//响应输出流
		OutputStream out = response.getOutputStream();
		//输出数据
		if (pageNum == null)
			pageNum = 1;
		
		if (pageSize == null){
			
			pageSize = service.getSrdCount(srDeptId, srStockId, srTime);
		}
		Pager pager = new Pager(service.getSrdCount(srDeptId, srStockId, srTime), pageSize, pageNum);
		service.exportExcel(out, pager, srDeptId, srStockId, srTime);		
	}
	
}
