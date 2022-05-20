import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import top.iaminlearn.service.AccountService;

/**
 * Date: 2021/7/22 1:56
 */
public class TestSpring {
    @Test
    public void run1(){
        ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        AccountService as = (AccountService) ac.getBean("accountService");
        as.findAll();
    }
}