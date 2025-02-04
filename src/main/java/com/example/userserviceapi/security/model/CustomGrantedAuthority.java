package com.example.userserviceapi.security.model;

import com.example.userserviceapi.models.Role;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;

@JsonDeserialize
@Getter
@Setter
@NoArgsConstructor
public class CustomGrantedAuthority implements GrantedAuthority {

    private String authority;

    public CustomGrantedAuthority(Role role) {
        this.authority = role.getName();
    }
    @Override
    public String getAuthority() {
        return authority;
    }
}