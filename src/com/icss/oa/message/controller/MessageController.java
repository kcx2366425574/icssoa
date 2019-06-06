package com.icss.oa.message.controller;

import java.io.IOException;
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
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.system.pojo.Employee;
import com.icss.oa.system.service.EmployeeService;

import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;

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

	@Autowired
	private EmployeeService empService;

	/**
	 * 增加职务
	 * 
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/mes/addMes")
	public void addMes(HttpServletRequest request, HttpServletResponse response, Message Message) {
		HttpSession session = request.getSession();

		String empLoginName = (String) session.getAttribute("empLoginName");
		Employee emp = empService.queryEmpByLoginName(empLoginName);
		session.setAttribute("empId", emp.getEmpId()); // 记录用户id

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
	 * 条件查询
	 * 
	 * @param request
	 * @param response
	 * @param start
	 * @param pageSize
	 * @param mesSendDate
	 * @param empEmail
	 * @param mesTitle
	 * @return
	 */
	@RequestMapping("/mes/queryByCondition")
	@ResponseBody
	public HashMap<String, Object> queryByCondition(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize, String mesSendDate, String empEmail, String mesTitle, Integer empId,
			String mesInfo) {

		if (pageNum == null) {
			pageNum = 0;
		}

		if (pageSize == null) {
			pageSize = 5;
		}

		Pager pager = new Pager(service.getMesCountByCondition(mesSendDate, empEmail, mesTitle, empId, mesInfo),
				pageSize, pageNum);

		List<Message> list = service.queryMesByCondition(pager, mesSendDate, empEmail, mesTitle, empId, mesInfo);

		// 在Map集合中存储分页数据和信息数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	/**
	 * 条件查询 没有收件人
	 * 
	 * @param request
	 * @param response
	 * @param start
	 * @param pageSize
	 * @param mesSendDate
	 * @param empEmail
	 * @param mesTitle
	 * @return
	 */
	@RequestMapping("/mes/queryByCondition1")
	@ResponseBody
	public HashMap<String, Object> queryByCondition1(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize, String mesSendDate, String empEmail, String mesTitle) {

		if (pageNum == null) {
			pageNum = 0;
		}

		if (pageSize == null) {
			pageSize = 5;
		}

		Pager pager = new Pager(service.getMesCount(), pageSize, pageNum);

		List<Message> list = service.queryMesByCondition1(pager, mesSendDate, empEmail, mesTitle);

		// 在Map集合中存储分页数据和信息数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	@RequestMapping("/mes/queryByEmpLoginName")
	@ResponseBody
	public HashMap<String, Object> queryByEmpLoginName(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize, String empLoginName) {

		HttpSession session = request.getSession();

		empLoginName = (String) session.getAttribute("empLoginName");
		Employee emp = empService.queryEmpByLoginName(empLoginName);
		session.setAttribute("empId", emp.getEmpId()); // 记录用户id

		if (pageNum == null) {
			pageNum = 0;
		}

		if (pageSize == null) {
			pageSize = 5;
		}

		Pager pager = new Pager(service.getMesCount(), pageSize, pageNum);

		List<Message> list = service.queryMesByLoginName(empLoginName, pager);

		// 在Map集合中存储分页数据和信息数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	/**
	 * 根据id查询信息
	 * 
	 * @param request
	 * @param response
	 * @param mesId
	 * @return
	 */
	@RequestMapping("/mes/queryById")
	@ResponseBody
	public Message queryById(HttpServletRequest request, HttpServletResponse response, Integer mesId) {

		Message mes = service.queryMesById(mesId);

		return mes;
	}

	/**
	 * 全文检索信息
	 * 
	 * @throws IOException
	 * @throws ParseException
	 * @throws InvalidTokenOffsetsException
	 */
	@RequestMapping("mes/queryByIndex")
	@ResponseBody
	public List<Message> queryByIndex(HttpServletRequest request, HttpServletResponse response, String queryStr)
			throws ParseException, IOException, InvalidTokenOffsetsException {

		return service.queryMesByIndex(queryStr);
	}

}
