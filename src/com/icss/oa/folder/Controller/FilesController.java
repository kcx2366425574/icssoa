package com.icss.oa.folder.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.icss.oa.folder.service.FilesService;

@Controller
public class FilesController {
	@Autowired
	private FilesService service;
	
	}
