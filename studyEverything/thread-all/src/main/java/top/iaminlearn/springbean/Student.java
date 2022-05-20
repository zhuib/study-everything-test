package top.iaminlearn.springbean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * Date: 2021/7/2 10:12
 */
public class Student implements BeanNameAware, BeanFactoryAware,
        ApplicationContextAware, InitializingBean, DisposableBean {

    private String name;

    public Student() {
        System.out.println("构造函数调用...");

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println("属性注入...");
        this.name = name;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        System.out.println("Student.setBeanFactory");
    }

    @Override
    public void setBeanName(String name) {
        System.out.println("Student.setBeanName");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("Student.destroy");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Student.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        System.out.println("Student.setApplicationContext");
    }

    // 自定义 初始化和 销毁
    private void myPostConstruct() {
        System.out.println("Student.myPostConstruct");
    }

    private void myPreDestroy() {
        System.out.println("Student.myPreDestroy");
    }
}
