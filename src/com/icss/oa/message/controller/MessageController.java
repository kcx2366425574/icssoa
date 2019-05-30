package com.icss.oa.message.controller;

import java.util.HashMap;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.icss.oa.common.Pager;
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
	public void addMes(HttpServletRequest request, HttpServletResponse response, Message Message) {
		service.addMes(Message);
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
	 * 分页查询信息,简单查询然后进行分页
	 * @param request
	 * @param response
	 * @param emp
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/mes/queryByPage")
	@ResponseBody
	public HashMap<String, Object> queryByPage(HttpServletRequest request, HttpServletResponse response, Message mes, Integer pageNum, Integer pageSize) {

		Pager pager = new Pager(service.getMesCount(), pageSize, pageNum);
	
		List<Message> list = service.queryByPage(pageNum, pageSize);
		
		//在Map集合中存储分页数据和员工数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);
		
		return map;
	}
	
	@RequestMapping("/mes/queryByCondition")
	@ResponseBody
	public HashMap<String, Object> queryByCondition(HttpServletRequest request, HttpServletResponse response, 
			Integer start, Integer pageSize,
			String mesSendDate, String empEmail, String mesTitle
			) {
		
		Pager pager = new Pager(service.getMesCount(), pageSize, start);
		
		List<Message> list = service.queryMesByCondition(start, pageSize, mesSendDate, empEmail, mesTitle);
		
		//在Map集合中存储分页数据和员工数据
				HashMap<String, Object> map = new HashMap<>();
				map.put("pager", pager);
				map.put("list", list);
		
		return map;
	}

}
