package top.iaminlearn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.iaminlearn.mapper.IAccountMapper;
import top.iaminlearn.entity.Account;
import top.iaminlearn.service.AccountService;

import java.util.List;

/**
 * Date: 2021/7/22 1:54
 */
@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private IAccountMapper iaccountdao;

    @Override
    public List<Account> findAll() {
        System.out.println("Service业务层：查询所有账户...");
        return iaccountdao.findAll();
    }


    @Override
    public void saveAccount(Account account) {
        System.out.println("Service业务层：保存帐户...");
        iaccountdao.saveAccount(account);  //调用service中的saveAccount(account)方法
    }
}