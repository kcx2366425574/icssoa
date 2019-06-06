package com.icss.oa.folder.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.icss.oa.folder.pojo.Files;
import com.icss.oa.folder.pojo.Folder;
import com.icss.oa.folder.service.FilesService;
import com.icss.oa.system.pojo.Employee;


@Controller
public class FilesController {
	@Autowired
	private FilesService service;
	@RequestMapping("/file/add")
	public void add(@RequestParam("fileInfo") MultipartFile fileInfo,HttpServletRequest request,HttpServletResponse response) throws IOException
	{
		Folder folder=new Folder();
		folder.setFolId(4);
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
	 @RequestMapping("/file/get")
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
	}
