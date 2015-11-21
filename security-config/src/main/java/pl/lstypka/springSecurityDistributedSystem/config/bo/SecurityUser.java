package pl.lstypka.springSecurityDistributedSystem.config.bo;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 * Created by Lukasz Stypka on 2015-11-20.
 */
public class SecurityUser implements UserDetails {

    private Long userNo;
    private String username;
    private String password;
    private List<? extends GrantedAuthority> authorities;

    public SecurityUser(Long userNo, String username, String password, List<? extends GrantedAuthority> authorities) {
        this.userNo = userNo;
        this.username = username;
        this.password = password;
        this.authorities = authorities;
    }

    public Long getUserNo() {
        return userNo;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
