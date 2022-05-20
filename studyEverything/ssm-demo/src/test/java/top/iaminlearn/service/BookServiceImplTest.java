package top.iaminlearn.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import top.iaminlearn.dto.AppointExecution;
import top.iaminlearn.test.BaseTest;

/**
 * Date: 2021/7/21 20:35
 */
public class BookServiceImplTest extends BaseTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testAppoint() throws Exception {
        long bookId = 1001;
        long studentId = 12345678910L;
        AppointExecution execution = bookService.appoint(bookId, studentId);
        System.out.println(execution);
    }

}