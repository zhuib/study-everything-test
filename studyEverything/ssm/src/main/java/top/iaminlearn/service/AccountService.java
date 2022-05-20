package top.iaminlearn.service;

import top.iaminlearn.entity.Account;

import java.util.List;

/**
 * Date: 2021/7/22 1:53
 */
public interface AccountService {
    // 查询所有账户
    public List<Account> findAll();

    // 保存帐户信息
    public void saveAccount(Account account);
}
