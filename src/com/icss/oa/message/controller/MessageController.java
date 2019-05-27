package com.icss.oa.message.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;

/**
 * 职务控制器
 * 
 * @author Administrator
 *
 */
@Controller
public class MessageController {

	@Autowired
	private MessageService service;

	/**
	 * 增加职务
	 * 
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/mes/addMes")
	public void add(HttpServletRequest request, HttpServletResponse response, Message Message) {
		service.addMes(Message);
	}

	/**
	 * 查询职务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mes/queryMes")
	@ResponseBody
	public List<Message> query(HttpServletRequest request, HttpServletResponse response) {
		return service.queryMes();
	}

	/**
	 * 删除职务
	 * 
	 * @param request
	 * @param response
	 * @param Message
	 */
	@RequestMapping("/mes/deleteMes")
	public void delete(HttpServletRequest request, HttpServletResponse response, Integer mesId) {
		service.deleteMes(mesId);
	}

	/**
	 * 修改职务
	 * 
	 * @param request
	 * @param response
	 * @param Message
	 */
	@RequestMapping("/mes/updateMes")
	public void update(HttpServletRequest request, HttpServletResponse response, Message Message) {
		service.updateMes(Message);
	}

	/**
	 * 通过id查询职务
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mes/queryMesById")
	@ResponseBody
	public Message queryMesById(HttpServletRequest request, HttpServletResponse response, Integer mesId) {
		return service.queryMesById(mesId);
	}

	/**
	 * 通过题目进行模糊查询
	 * 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/mes/queryByTitleCondition")
	@ResponseBody
	public List<Message> queryByTitleCondition(HttpServletRequest request, HttpServletResponse response, Integer s,
			Integer pageSize, String mesTitle) {
		return service.queryByTitleCondition(s, pageSize, mesTitle);
	}
	
	

}
