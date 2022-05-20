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
        System.out.println("Serviceҵ��㣺��ѯ�����˻�...");
        return iaccountdao.findAll();
    }


    @Override
    public void saveAccount(Account account) {
        System.out.println("Serviceҵ��㣺�����ʻ�...");
        iaccountdao.saveAccount(account);  //����service�е�saveAccount(account)����
    }
}