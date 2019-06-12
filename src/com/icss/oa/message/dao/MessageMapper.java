package com.icss.oa.message.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.icss.oa.message.pojo.Message;

/**
 * 在线信息dao层
 * 
 * @author bhl
 *
 */
public interface MessageMapper {

	void insert(Message mes); // 增加信息

	void update(Message mes); // 修改信息

	void delete(Integer mesId); // 删除信息

	Message queryById(Integer mesId); // 根据id查询信息

	int getCount(); // 获得总数

	// 条件查询个数
	int getCountByCondition(@Param("mesSendDate") String mesSendDate, // 日期
			@Param("empEmail") String empEmail, // 邮箱
			@Param("mesTitle") String mesTitle, // 题目
			@Param("mesReciver") Integer mesReciver, // 收件人
			@Param("mesInfo") String mesInfo // 邮件信息
	); // 根据条件获得总记录数

	// 条件查询
	List<Message> queryByCondition(@Param("start") Integer start, @Param("pageSize") Integer pageSize, // 分页
			@Param("mesSendDate") String mesSendDate, // 日期
			@Param("empEmail") String empEmail, // 邮箱
			@Param("mesTitle") String mesTitle, // 题目
			@Param("mesReciver") Integer mesReciver, // 收件人
			@Param("mesInfo") String mesInfo // 邮件信息
	);

	// 条件查询1
	List<Message> queryByCondition1(@Param("start") Integer start, @Param("pageSize") Integer pageSize, // 分页
			@Param("mesSendDate") String mesSendDate, // 日期
			@Param("empEmail") String empEmail, // 邮箱
			@Param("mesTitle") String mesTitle // 题目
	);

	// 获得最后一个添加的信息的id
	int getLastInsertId();

	// 根据登录名获得信息
	List<Message> queryByLoginName(@Param("empLoginName") String empLoginName, @Param("start") Integer start,
			@Param("pageSize") Integer pageSize,
			@Param("mesSendDate") String mesSendDate, // 日期
			@Param("empEmail") String empEmail, // 邮箱
			@Param("mesTitle") String mesTitle, // 题目
			@Param("mesReciver") Integer mesReciver, // 收件人
			@Param("mesInfo") String mesInfo // 邮件信息
			);

	// 根据登录名获得个数
	int getCountByEmpLoginName(@Param("empLoginName") String empLoginName,
			@Param("mesSendDate") String mesSendDate, // 日期
			@Param("empEmail") String empEmail, // 邮箱
			@Param("mesTitle") String mesTitle, // 题目
			@Param("mesReciver") Integer mesReciver, // 收件人
			@Param("mesInfo") String mesInfo // 邮件信息
			);

	// 草稿箱以及发件箱
	List<Message> queryDraft(@Param("mesSendConfirm") String mesSendConfirm, // 是否发送
			@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("empLoginName") String empLoginName);

	// 草稿箱以及发件箱的个数
	int getCountDraft(@Param("mesSendConfirm") String mesSendConfirm, @Param("empLoginName") String empLoginName);

	// 收件箱
	List<Message> queryInbox(@Param("start") Integer start, @Param("pageSize") Integer pageSize,
			@Param("empLoginName") String empLoginName);

	// 收件箱的个数
	int getCountInbox(@Param("empLoginName") String empLoginName);

	// 未读消息查询
	List<Message> queryUnread(@Param("mesSendConfirm") String mesSendConfirm, // 是否发送
			@Param("mesReadConfirm") String mesReadConfirm, @Param("start") Integer start,
			@Param("pageSize") Integer pageSize, @Param("empLoginName") String empLoginName);

	// 未读消息的个数
	int getCountUnread(@Param("mesSendConfirm") String mesSendConfirm, @Param("mesReadConfirm") String mesReadConfirm,
			@Param("empLoginName") String empLoginName);

}
