package top.iaminlearn.service;

import top.iaminlearn.entity.Account;

import java.util.List;

/**
 * Date: 2021/7/22 1:53
 */
public interface AccountService {
    // ��ѯ�����˻�
    public List<Account> findAll();

    // �����ʻ���Ϣ
    public void saveAccount(Account account);
}
