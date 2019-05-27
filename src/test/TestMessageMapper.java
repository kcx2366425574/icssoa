package test;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.icss.oa.message.dao.MessageMapper;
import com.icss.oa.message.pojo.Message;
import com.icss.oa.system.pojo.Employee;

/**
 * ������Ϣ������
 * @author bhl
 *
 */
public class TestMessageMapper {

	// ���Spring��������
	ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

	// ���Mapper����(dao����
	MessageMapper mapper = context.getBean(MessageMapper.class);
	
	//����ת��
	public Date inform(String str) throws ParseException {

		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = format.parse(str);
		return date;
	}

	@Test
	//����
	public void testInsert() throws ParseException {
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(11);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(3);
		
		Message mes = new Message("ninijjj", "�ѷ�", "�Ѷ�", inform("1998-2-19 12:12:59"), "jjjjj",mesSender,mesReciver);
		
		mapper.insert(mes);
	}

	@Test
	//�޸�
	public void update() throws ParseException {
		
		Employee mesSender = new Employee();
		mesSender.setEmpId(1);
		
		Employee mesReciver = new Employee();
		mesReciver.setEmpId(2);
		
		Message mes = new Message(11, "n", "�ѷ�", "�Ѷ�", inform("1998-2-19 12:12:59"), "jjjjj",mesSender,mesReciver);
		mapper.update(mes);
	}

	@Test
	//ɾ��
	public void testDelete() {
		mapper.delete(13);
	}

	@Test
	//����id���в�ѯ
	public void testQueryById() {
		Message mes = mapper.queryById(11);
		System.out.println(mes);
	}

	@Test
	//ȫ����ѯȻ����з�ҳ
	public void testQueryByPage() {
		
		HashMap<String, Integer> map = new HashMap<>();
		map.put("start", 0);
		map.put("pageSize", 2);

		List<Message> list = mapper.queryByPage(map);

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//���ݷ���ʱ����в�ѯ
	public void testQueryBySendDate() throws ParseException {

		List<Message> list = mapper.queryBySendDate(inform("1998-2-19 00:00:00"));

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//�����ռ���������в�ѯ
	public void queryByReciverEmail() {

		List<Message> list = mapper.queryByReciverEmail("44441");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//���ݷ���ʱ�����ģ����ѯ
	public void testQueryByDateCondition() {

		List<Message> list = mapper.queryByDateCondition(0, 3, "1998-02-19%");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//�����ռ����������ģ����ѯ
	public void testQueryByReciverCondition() {

		List<Message> list = mapper.queryByReciverCondition(0,1,"44");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
	
	@Test
	//������Ŀ����ģ����ѯ
	public void testQueryByTitleCondition() {

		List<Message> list = mapper.queryByTitleCondition(0, 3, "ni");

		for (Message mes : list) {
			System.out.println(mes);
		}
	}
}
