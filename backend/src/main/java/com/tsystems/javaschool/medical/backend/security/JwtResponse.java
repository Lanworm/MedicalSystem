package com.tsystems.javaschool.medical.backend.security;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Date;

public class JwtResponse {

    private final String token;
    private final Date expiresAt;
    private final Collection<GrantedAuthority> authorities;

    public JwtResponse(String token, Date expiresAt, Collection<GrantedAuthority> authorities) {
        this.token = token;
        this.expiresAt = expiresAt;
        this.authorities = authorities;
    }

    public Collection<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getToken() {
        return token;
    }

    public Date getExpiresAt() {
        return expiresAt;
    }
}
