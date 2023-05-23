package com.scm.myblog.entity.BO;

import com.alibaba.fastjson.annotation.JSONField;
import com.scm.myblog.entity.DOMAIN.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class LoginUser implements UserDetails {

    private User u;
    //保证此对象不被fastjson序列化
    @JSONField(serialize = false)
    private List<SimpleGrantedAuthority> collect;
    private List<String> permissions;

    public LoginUser(User user) {
        this.u = user;
    }

    public LoginUser(User user, List<String> authList) {
        this.u = user;
        this.permissions = authList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //把permissions中的String权限信息封装成SimpleGrantedAuthority
        if (collect != null) {
            return collect;
        }
        collect = permissions.stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
        return collect;
    }

    @Override
    public String getPassword() {
        return u.getUserPassword();
    }

    @Override
    public String getUsername() {
        return u.getUserName();
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
