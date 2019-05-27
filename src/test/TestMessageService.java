package test;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.message.service.MessageService;
import com.icss.oa.system.pojo.Employee;

/**
 * ������Ϣҵ�������
 * 
 * @author bhl
 *
 */
public class TestMessageService {

	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	MessageService service = context.getBean(MessageService.class);
	
	//����ת��
	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		return date;
	}
	
	@Test
	//�����Ϣ
	public void testAddMes() throws ParseException {
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(1);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);
		
		Message mes = new Message("nini", "�ѷ�", "�Ѷ�", inform("1998-2-19 12:12:59"), "jjjjj",mesSender,mesReciver);
		service.addMes(mes);
	}
	
	@Test
	//�޸���Ϣ
	public void testUpdateMes() throws ParseException {
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(1);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);
		
		Message mes = new Message(11, "n",  "�ѷ�", "�Ѷ�", inform("1998-2-19 12:12:59"), "jjjjj",mesSender,mesReciver);
		service.updateMes(mes);
	}

	@Test
	//ɾ����Ϣ
	public void testDeleteMes() {
		service.deleteMes(12);
	}

	@Test
	//����id��ѯ��Ϣ
	public void testQueryMesById() {
		Message mes = service.queryMesById(11);
		System.out.println(mes);
	}

	@Test
	//��ѯȫ����Ϣ��Ȼ����з�ҳ
	public void testQueryMesByPage() {
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", 0);
		map.put("pageSize", 2);

		List<Message> list = service.queryMesByPage(map);

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//���ݷ���ʱ���ѯ��Ϣ
	public void testQueryMesSendDate() throws ParseException {
		List<Message> list = service.queryMesSendDate(inform("1998-2-19 00:00:00"));
		
		for(Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//�����ռ��������ѯ��Ϣ
	public void queryByReciverEmail() {
		
		List<Message> list = service.queryByReciverEmail("44441");
		
		for(Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//���ݷ���ʱ�����ģ����ѯ
	public void testQueryByDateCondition() {
		
		List<Message> list = service.queryByDateCondition(0, 1, "1998-02-19%");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//�����ռ����������ģ����ѯ
	public void testQueryByReciverCondition() {
		
		List<Message> list = service.queryByReciverCondition(0, 1, "44");
		
		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//������Ŀ����ģ����ѯ
	public void testQueryByTitleCondition() {

		List<Message> list = service.queryByTitleCondition(0, 3, "n");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}

}
