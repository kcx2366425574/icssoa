package com.icss.oa.folder.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.http.impl.conn.SystemDefaultDnsResolver;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.icss.oa.common.Pager;
import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.folder.service.FilesService;
import com.icss.oa.schedule.pojo.Schedule;
import com.icss.oa.system.pojo.Employee;


@Controller
public class FilesController {
	
	@Autowired
	private FilesService service;
	
	@RequestMapping("/file/add")
	public void add(@RequestParam("fileInfo") MultipartFile fileInfo,@RequestParam("folId2")Integer folId2,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Folder folder=new Folder();
		folder.setFolId(folId2);
		Date createdate=new Date(new java.util.Date().getTime());
		Date updateDate=new Date(new java.util.Date().getTime());
		Files file=new Files(fileInfo.getOriginalFilename(), Integer.valueOf((int)fileInfo.getSize()), folder, "共享",createdate ,updateDate ,fileInfo.getBytes());
		service.addFiles(file);
	}
	@RequestMapping("/file/delete")
	public void delete(Integer fileId)
	{
		service.deleteFiles(fileId);
	}
	 @RequestMapping("/file/update")
	 public void update(HttpServletRequest request,HttpServletResponse response,Files file)
	 {
		 service.updateFiles(file);
	 }
	 @RequestMapping("/file/query")
	 public List<Files> Query(HttpServletRequest request,HttpServletResponse response)
	 {
		 return  service.queryFile();
	 }
		@RequestMapping("/file/queryById")
		@ResponseBody
		public Files queryById(HttpServletRequest request,HttpServletResponse response,Integer fileId) {
			return service.queryFileById(fileId);
		}
	 @RequestMapping("/file/queryByFolder")
	 public List<Files> QueryByFolder(HttpServletRequest request,HttpServletResponse response,Integer folId)
	 {
		 return service.queryByFolder(folId);
	 }
	 @RequestMapping("/file/search") 
		@ResponseBody
		public HashMap<String, Object> search(HttpServletRequest request,HttpServletResponse responsep,Integer pageNum,
				Integer pageSize,Integer folId) {
			
			if (pageNum == null)
				pageNum = 1;
			
			if (pageSize == null)
				pageSize = 10;		
			
			Pager pager = new Pager(service.getCountByCondition(folId), pageSize, pageNum);
			
			List<Files> list = service.queryByConditon(pager, folId);		
			//在Map集合中存储分页数据和员工数据
			HashMap<String, Object> map = new HashMap<>();
			map.put("pager",pager);
			map.put("list", list);
			
			return map;		
		}
	 @RequestMapping("/file/get")
	 @ResponseBody
	 public byte[] get(HttpServletRequest request, HttpServletResponse response,Integer fileId)
	 {
		 Files files=service.queryFileById(fileId);
		 return files.getFileInfo();
	 }
	 /**
	  * download by FileID
	 * @throws IOException 
	  */
	 @RequestMapping("/file/download")
	 public void download(HttpServletRequest request, HttpServletResponse response,Integer fileId) throws IOException
	 {
		 Files files=service.queryFileById(fileId);
		 String fiName = new String(files.getFileName().getBytes(),"iso-8859-1");
		 response.setHeader("content-disposition", "attachment;filename=" + fiName);
		 OutputStream out = response.getOutputStream();
		 FileCopyUtils.copy(files.getFileInfo(), out);
	 }
		/**
		 * 批量删除
		 */
		@RequestMapping("/file/deleteMany")	
		public void deleteMany(HttpServletRequest request,HttpServletResponse response,Integer[] ids) throws ParseException, IOException, InvalidTokenOffsetsException {
			
			System.out.println(Arrays.toString(ids));
			service.deleteMany(ids);		
		}
	}
