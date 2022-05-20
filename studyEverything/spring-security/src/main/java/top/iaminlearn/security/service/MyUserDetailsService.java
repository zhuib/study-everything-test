package top.iaminlearn.security.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import top.iaminlearn.security.entity.Users;
import top.iaminlearn.security.mapper.UsersMapper;

import java.util.List;

/**
 * Date: 2022/4/6 21:36
 */

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UsersMapper usersMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        QueryWrapper qw = new QueryWrapper();
        qw.eq("username",username);
        Users users = usersMapper.selectOne(qw);

        if (users == null) {
            throw new UsernameNotFoundException("用户名不存在！");
        }

        List<GrantedAuthority> auths = AuthorityUtils.commaSeparatedStringToAuthorityList("admins,ROLE_sale");
        return new User(users.getUsername(),
                new BCryptPasswordEncoder().encode(users.getPassword()),auths);
    }

    // 登录验证



}
