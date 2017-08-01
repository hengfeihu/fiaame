package com.assetman.fiaame.config;

import com.assetman.fiaame.models.SysRole;
import com.assetman.fiaame.models.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Created by hengfeihu on 2017/8/1.
 */
@Configuration
@EnableWebSecurity  //禁用Boot的默认Security配置，配合@Configuration启用自定义配置
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用Security注解
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    /**
     * 身份验证配置，用于注入自定义身份验证Bean和密码校验规则
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getCurrentUserDetailsService());
    }

    /**
     * Web层面的配置，一般用来配置无需安全检查的路径
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers("/js/**", "/css/**", "/images/**", "/**/favicon.ico");
    }

    /**
     * Request层面的配置，对应XML Configuration中的<http>元素
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .anyRequest().authenticated()
                .antMatchers("/", "/index").permitAll()
                .and()
                .formLogin()
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/checklogin")
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
                .deleteCookies("s")
                .permitAll()
                .and()
                .rememberMe()
                .tokenValiditySeconds(60 * 60 * 24 * 7)
                .key("s");
    }


    @Bean
    public CurrentUserDetailsService getCurrentUserDetailsService() {
        return new CurrentUserDetailsService();
    }

    private class CurrentUserDetailsService implements UserDetailsService {
        @Override
        public User loadUserByUsername(String s) throws UsernameNotFoundException {
            User user = User.db().find(User.class).where().eq("name", s).findUnique();
            if (user == null) {
                throw new UsernameNotFoundException("用户名不存在");
            }
            user.roles = SysRole.db().find(SysRole.class).where().eq("uid", user.id.intValue()).findList();
            return user;
        }
    }
}
