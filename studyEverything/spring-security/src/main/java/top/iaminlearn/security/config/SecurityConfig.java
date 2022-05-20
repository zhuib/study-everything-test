package top.iaminlearn.security.config;

import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import javax.sql.DataSource;

/**
 * Date: 2022/4/6 21:32
 */

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    DataSource dataSource;

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
//        jdbcTokenRepository.setCreateTableOnStartup(true); // 自动创建表，已经手动创建就不需要了
        return jdbcTokenRepository;
    }
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 退出登录
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/test/hello").permitAll();
        // 配置没有权限访问跳转自定义页面
        http.exceptionHandling().accessDeniedPage("/403.html");
        http.formLogin()
                .loginPage("/login.html")// 登录页面设置
                .loginProcessingUrl("/user/login")// 登录访问路径
                .defaultSuccessUrl("/success.html").permitAll() // 登录成功之后，跳转路径
                .and().authorizeRequests()
//                .antMatchers("/","/test/hello","/user/login").permitAll() // 设置哪些路径可以直接访问，不需要认证
//                .antMatchers("/","/test/hello").permitAll() // 设置哪些路径可以直接访问，不需要认证
//                .antMatchers("/test/index").hasAuthority("admins") // 访问的路径是否有该权限
//                .antMatchers("/test/index").hasAnyAuthority("admins,manage") // 访问的路径是否有该权限，有多个权限
                .antMatchers("/test/index").hasRole("sale") // 访问的路径是否有该角色
                .anyRequest().authenticated() // 所有请求都可以访问
                // 设置记住我
                .and().rememberMe().tokenRepository(persistentTokenRepository())
                .tokenValiditySeconds(60) // 设置token有效期
                .userDetailsService(userDetailsService) // 设置用户信息
                .and().csrf().disable(); // 关闭csrf 防护
    }
}
