package com.bridgelabz.ipl.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class LocalUser extends User {

    private String userId;

    public LocalUser(final String userId, final String username, final String password, final boolean enabled, final boolean accountNonExpired, final boolean credentialsNonExpired, final boolean accountNonLocked, final Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        System.out.println("Local User Extends User");
        this.userId = userId;
    }

    public String getUserId() {
    	System.out.println("Getting User Id");
        return userId;
    }
}
