package ru.itis.judgeassistant.security.details;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.judgeassistant.models.users.User;

import java.util.Collection;
import java.util.Collections;

@RequiredArgsConstructor
public class UserDetailsImpl implements UserDetails {
    private final User user;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String roleAuthority = user.getRole().name();
        SimpleGrantedAuthority authority =  new SimpleGrantedAuthority(roleAuthority);

        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    @Override
    public String getUsername() {
        return user.getLogin();
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
        return !user.isBanned();
    }

    @Override
    public boolean isEnabled() {
        return user.isConfirmed();
    }
}
