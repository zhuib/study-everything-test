package top.iaminlearn.test;

/**
 * Date: 2021/7/21 20:29
 */

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * ����spring��junit���ϣ�junit����ʱ����springIOC���� spring-test,junit
 */
@RunWith(SpringJUnit4ClassRunner.class)
// ����junit spring�����ļ�
@ContextConfiguration({"classpath:spring/spring-dao.xml", "classpath:spring/spring-service.xml" })
public class BaseTest {

}