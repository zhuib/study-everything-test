package top.iaminlearn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import top.iaminlearn.entity.Account;
import top.iaminlearn.service.AccountService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Date: 2021/7/22 2:03
 */
@Controller
public class AccountController {


    @Autowired   //������ע��
    private AccountService accountService;

    @RequestMapping("/account/findAll")
    public String findAll(Model model){
        System.out.println("Controller���ֲ㣺��ѯ�����˻�...");

        List<Account> list = accountService.findAll();

        model.addAttribute("list",list);
        return "list";  //����ͼ��������������ǰ׺��׺
    }

    @RequestMapping("/account/save")
    public void save(Account account, HttpServletRequest request, HttpServletResponse response) throws IOException {
        accountService.saveAccount(account);
        response.sendRedirect(request.getContextPath()+"/account/findAll");
        return;
    }
}