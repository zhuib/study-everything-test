package top.iaminlearn.springbean;


import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Date: 2021/7/2 10:18
 */
public class Client {
    public static void main(String[] args) throws InterruptedException {
        // 为面试而准备的Bean生命周期加载过程
        ApplicationContext context = new ClassPathXmlApplicationContext("Bean-Lifecycle.xml");
        Student student = (Student)context.getBean("student");
        System.out.println("Student name = " +student.getName());
        ((ClassPathXmlApplicationContext) context).destroy();

    }
}
