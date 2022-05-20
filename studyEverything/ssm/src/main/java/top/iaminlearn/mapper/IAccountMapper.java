package top.iaminlearn.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.iaminlearn.entity.Account;

import java.util.List;

/**
 * Date: 2021/7/22 1:53
 */

@Mapper
@Repository  //此注解代表这是一个持久层，用法类似@controller、@service
public interface IAccountMapper {

//    @Select("select * from account")
    public List<Account> findAll();
//    @Insert("insert into account (name,money) value(#{name},#{money})")
    public void saveAccount(Account account);
}