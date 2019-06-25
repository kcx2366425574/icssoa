package com.icss.oa.folder.controller;

import java.util.HashMap;
import java.util.List;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.icss.oa.common.Pager;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.folder.service.FolderService;
import com.icss.oa.schedule.pojo.Schedule;


@Controller
public class FolderController {
	@Autowired
	private FolderService service;
	@RequestMapping("/fol/add")
	public void add(HttpServletRequest request,HttpServletResponse response,Folder fol)
	{
		service.addFolder(fol);
	}
	@RequestMapping("/fol/delete")
	public void delete(HttpServletRequest request,HttpServletResponse response,Integer folId)
	{
		service.deleteFolder(folId);
	}
	@RequestMapping("/fol/update")
	public void update(HttpServletRequest request,HttpServletResponse response,Folder folder) {
		service.updateFolder(folder);
	}
	@RequestMapping("/fol/query")
	@ResponseBody
	public List<Folder> query(HttpServletRequest request,HttpServletResponse response,Integer folEmp)
	{
		//get empId by session object
		HttpSession session = request.getSession();
		folEmp= (Integer) session.getAttribute("empId");
		System.out.println(folEmp);
		return service.query(folEmp);
	}
	@RequestMapping("/fol/queryAll")
	@ResponseBody
	public List<Folder> queryAll()
	{
		return service.queryAll();
	}
	@RequestMapping("/fol/queryById")
	@ResponseBody
	public Folder queryById(HttpServletRequest request,HttpServletResponse response,Integer folId)
	{
		return service.queryFolderById(folId);
	}
	@RequestMapping("/fol/queryByPage")
	@ResponseBody
	public HashMap<String, Object> queryByPage(HttpServletRequest request,HttpServletResponse response,int pageSize,int pageNum)
	{
		Pager pager=new Pager(service.getCount(), pageSize, pageNum);
		List<Folder> list=service.queryFolderByPage(pager);
		HashMap<String, Object> map = new HashMap<>();
		//storage page data and schedule data in map set
		map.put("pager",pager);
		map.put("list", list);
		return map;
	}
}
