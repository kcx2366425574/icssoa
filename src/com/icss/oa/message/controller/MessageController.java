package com.icss.oa.message.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
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
 * 信息控制器
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
	 * 增加信息
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
	 * 增加信息,根据登录名增加,已发信息
	 * 
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/mes/addMesByLoginName")
	public void addMesByLoginName(HttpServletRequest request, HttpServletResponse response, Integer[] emp,
			Message mes) {

		for (int i = 0; i < emp.length; i++) {

			Date mesSendDate = new Date();

			HttpSession session = request.getSession();

			String empLoginName = (String) session.getAttribute("empLoginName");
			Employee employee = empService.queryEmpByLoginName(empLoginName);
			employee.setEmpId(empService.getId(empLoginName));

			Integer empId = employee.getEmpId();

			Employee mesSender = new Employee();
			mesSender.setEmpId(empId);
			mes.setMesSender(mesSender);

			Employee mesReciver = empService.getById(emp[i]);

			mes.setMesReciver(mesReciver);

			mes.setMesSendConfirm("已发");
			mes.setMesReadConfirm("未读");
			mes.setMesSendDate(mesSendDate);

			service.addMes(mes);
		}
	}

	/**
	 * 增加信息,根据登录名增加,草稿箱
	 * 
	 * @param request
	 * @param response
	 * @param dept
	 */
	@RequestMapping("/mes/addDraftByLoginName")
	public void addDraftByLoginName(HttpServletRequest request, HttpServletResponse response, Message mes) {

		Date mesSendDate = new Date();

		HttpSession session = request.getSession();

		String empLoginName = (String) session.getAttribute("empLoginName");
		Employee emp = empService.queryEmpByLoginName(empLoginName);
		emp.setEmpId(empService.getId(empLoginName));

		Integer empId = emp.getEmpId();

		Employee mesSender = new Employee();
		mesSender.setEmpId(empId);
		mes.setMesSender(mesSender);

		mes.setMesSendConfirm("未发");
		mes.setMesReadConfirm("未读");
		mes.setMesSendDate(mesSendDate);

		service.addMes(mes);
	}

	/**
	 * 删除信息
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
	 * 修改信息
	 * 
	 * @param request
	 * @param response
	 * @param Message
	 */
	@RequestMapping("/mes/updateMes")
	public void update(HttpServletRequest request, HttpServletResponse response, Message mes) {

		if (mes.getMesSendConfirm().equals("已发")) {
			mes.setMesReadConfirm("已读");
			service.updateMes(mes);
		} else {
			service.updateMes(mes);
		}

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
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/mes/queryByCondition")
	@ResponseBody
	public HashMap<String, Object> queryByCondition(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize, String mesSendDate, String empEmail, String mesTitle, Integer empId,
			String mesInfo) throws UnsupportedEncodingException {

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

	/**
	 * 根据登录名查询
	 * 
	 * @param request
	 * @param response
	 * @param pageNum
	 * @param pageSize
	 * @param empLoginName
	 * @return
	 */
	@RequestMapping("/mes/queryByEmpLoginName")
	@ResponseBody
	public HashMap<String, Object> queryByEmpLoginName(HttpServletRequest request, HttpServletResponse response,
			Integer pageNum, Integer pageSize, String empLoginName, String mesSendDate, String empEmail,
			String mesTitle, Integer empId, String mesInfo) {

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

		Pager pager = new Pager(
				service.getMesCountByEmpLoginName(empLoginName, mesSendDate, empEmail, mesTitle, empId, mesInfo),
				pageSize, pageNum);

		List<Message> list = service.queryMesByLoginName(empLoginName, pager, mesSendDate, empEmail, mesTitle, empId,
				mesInfo);

		// 在Map集合中存储分页数据和信息数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	/**
	 * 查询草稿箱
	 * 
	 * @param request
	 * @param response
	 * @param mesSendConfirm
	 * @param pageNum
	 * @param pageSize
	 * @param empLoginName
	 * @return
	 */
	@RequestMapping("/mes/queryDraft")
	@ResponseBody
	public HashMap<String, Object> queryDraft(HttpServletRequest request, HttpServletResponse response,
			String mesSendConfirm, Integer pageNum, Integer pageSize, String empLoginName) {

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

		Pager pager = new Pager(service.getMesDraftCount("未发", empLoginName), pageSize, pageNum);

		List<Message> list = service.queryMesDraft("未发", pager, empLoginName);

		// 在Map集合中存储分页数据和信息数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	/**
	 * 查询发件箱
	 * 
	 * @param request
	 * @param response
	 * @param mesSendConfirm
	 * @param pageNum
	 * @param pageSize
	 * @param empLoginName
	 * @return
	 */
	@RequestMapping("/mes/queryOutbox")
	@ResponseBody
	public HashMap<String, Object> queryOutbox(HttpServletRequest request, HttpServletResponse response,
			String mesSendConfirm, Integer pageNum, Integer pageSize, String empLoginName) {

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

		Pager pager = new Pager(service.getMesDraftCount("已发", empLoginName), pageSize, pageNum);

		List<Message> list = service.queryMesDraft("已发", pager, empLoginName);

		// 在Map集合中存储分页数据和信息数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	/**
	 * 查询收件箱
	 * 
	 * @param request
	 * @param response
	 * @param pageNum
	 * @param pageSize
	 * @param empLoginName
	 * @return
	 */
	@RequestMapping("/mes/queryInbox")
	@ResponseBody
	public HashMap<String, Object> queryInbox(HttpServletRequest request, HttpServletResponse response, Integer pageNum,
			Integer pageSize, String empLoginName) {

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

		Pager pager = new Pager(service.getMesInboxCount(empLoginName), pageSize, pageNum);

		List<Message> list = service.queryMesInbox(pager, empLoginName);

		// 在Map集合中存储分页数据和信息数据
		HashMap<String, Object> map = new HashMap<>();
		map.put("pager", pager);
		map.put("list", list);

		return map;
	}

	/**
	 * 未读消息
	 * 
	 * @param request
	 * @param response
	 * @param mesSendConfirm
	 * @param mesReadConfirm
	 * @param pageNum
	 * @param pageSize
	 * @param empLoginName
	 * @return
	 */
	@RequestMapping("/mes/queryUnread")
	@ResponseBody
	public HashMap<String, Object> queryUnread(HttpServletRequest request, HttpServletResponse response,
			String mesSendConfirm, String mesReadConfirm, Integer pageNum, Integer pageSize, String empLoginName) {

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

		Pager pager = new Pager(service.getMesUnreadCount("已发", "未读", empLoginName), pageSize, pageNum);

		List<Message> list = service.queryMesUnread("已发", "未读", pager, empLoginName);

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

	/**
	 * 批量删除
	 */
	@RequestMapping("mes/deleteMany")
	public void deleteMany(HttpServletRequest request, HttpServletResponse response, Integer[] ids)
			throws ParseException, IOException, InvalidTokenOffsetsException {

		for (int i = 0; i < ids.length; i++) {
			service.deleteMes(ids[i]);
		}

	}

	/**
	 * 群发邮件
	 */
	@RequestMapping("mes/addMany")
	public void insertMany(HttpServletRequest request, HttpServletResponse response, Integer[] emp, Message mes) {
		for (int i = 0; i < emp.length; i++) {

			Employee employee = empService.getById(emp[i]);

			mes.setMesReciver(employee);

			service.addMes(mes);
		}
	}

}
