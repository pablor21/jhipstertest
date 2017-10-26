package com.github.client.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.client.security.AuthoritiesConstants;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.validator.constraints.Email;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.*;

/**
 * A user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GithubUser implements Serializable, UserDetails {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String login;

    @JsonProperty("avatar_url")
    private String avatarUrl;

    private String name;

    private String email;

    private String url;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "GithubUser{" +
            "id=" + id +
            ", login='" + login + '\'' +
            ", avatarUrl='" + avatarUrl + '\'' +
            ", name='" + name + '\'' +
            ", email='" + email + '\'' +
            ", url='" + url + '\'' +
            '}';
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        //make everyone ROLE_USER
        Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
        GrantedAuthority grantedAuthority = new GrantedAuthority() {
            //anonymous inner type
            public String getAuthority() {
                return AuthoritiesConstants.USER;
            }
        };
        grantedAuthorities.add(grantedAuthority);
        GrantedAuthority grantedAdminAuthority = new GrantedAuthority() {
            //anonymous inner type
            public String getAuthority() {
                return AuthoritiesConstants.ADMIN;
            }
        };
        grantedAuthorities.add(grantedAdminAuthority);
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return this.login;
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
