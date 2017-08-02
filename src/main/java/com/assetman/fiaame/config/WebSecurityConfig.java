package com.assetman.fiaame.config;

import com.assetman.fiaame.models.SecurityUser;
import com.assetman.fiaame.models.User;
import com.assetman.fiaame.security.UrlGrantedAuthority;
import com.assetman.fiaame.utils.MD5Util;
import io.ebean.Ebean;
import io.ebean.SqlRow;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hengfeihu on 2017/8/1.
 */
@Configuration
@EnableWebSecurity  //禁用Boot的默认Security配置，配合@Configuration启用自定义配置
@EnableGlobalMethodSecurity(prePostEnabled = true) //启用Security注解
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/js/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/fonts/**").permitAll()
                .antMatchers("/favicon.ico").permitAll()
                .anyRequest().authenticated()
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

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(getUrlUserService()).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return MD5Util.encode((String) rawPassword);
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return encodedPassword.equals(MD5Util.encode((String) rawPassword));
            }
        });
    }

    @Bean
    public UrlUserService getUrlUserService() {
        return new UrlUserService();
    }

    public class UrlUserService implements UserDetailsService {
        @Override
        public SecurityUser loadUserByUsername(String username) {
            User user = Ebean.find(User.class).where().eq("name", username).findUnique();
            if (user != null) {
                String sql = "select a.permission_url,a.method from s_permission a where a.roleid in (select b.id from s_role b where b.uid=:uid)";
                List<SqlRow> urls = Ebean.createSqlQuery(sql).setParameter("uid", user.id.intValue()).findList();
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                for (SqlRow row : urls) {
                    if (row != null && row.getString("permission_url") != null) {
                        GrantedAuthority grantedAuthority = new UrlGrantedAuthority(row.getString("permission_url"), row.getString("method"));
                        grantedAuthorities.add(grantedAuthority);
                    }
                }
                SecurityUser securityUser = new SecurityUser(user);
                securityUser.setGrantedAuthorities(grantedAuthorities);
                return securityUser;
            } else {
                throw new UsernameNotFoundException("登录用户：: " + username + " 不存在！！！");
            }
        }
    }
}
