package cn.muses.trade.entity;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

import static org.springframework.util.Assert.notNull;

// 此处，将用户的信息和权限放在一起
public class UserInfo implements UserDetails {

    // 用户基本信息
    private String id;
    private String username;
    private String password;
    private Member member;
    private Collection<? extends GrantedAuthority> authorities;

    public UserInfo(Member member, String username) {
        this.id = member.getId().toString();
        notNull(username, "VERIFICATION_CODE_NOT_EXISTS");
        try {
//            当string值为空的时候进行String.equals(Object)属性值对比则会因为null的原因报错NullPointerException异常应检查是否为空或者是捕捉异常
            if (member.getMobilePhone().equals(username)) {
                this.username = member.getMobilePhone();
            }
        } catch (Exception e) {
            System.out.println("Phone is go");
        }
        if (member.getEmail().equals(username)) {
            System.out.println("Email is go");
            this.username = member.getEmail();
        }
        this.password = member.getPassword();
        this.member = member;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    // 注意：下面的返回值必须都是true
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

    @Override
    public String toString() {
        return "UserInfo{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}

