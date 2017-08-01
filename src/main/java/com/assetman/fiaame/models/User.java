package com.assetman.fiaame.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by hengfeihu on 2017/7/26.
 */
@Entity
@Table(name = "s_user")
public class User extends BaseModel implements UserDetails {
    @Column(name = "name", length = 120)
    public String name; //用户名
    @Column(name = "email", length = 50)
    public String email;//用户邮箱
    @Column(name = "password", length = 120)
    public String password;//用户密码

    @Transient
    public List<SysRole> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> auths = new ArrayList<>();
        List<SysRole> roles = this.roles;
        for (SysRole role : roles) {
            auths.add(new SimpleGrantedAuthority(role.name));
        }
        return auths;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.name;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
