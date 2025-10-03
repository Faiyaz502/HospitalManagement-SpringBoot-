package com.spring.hospitalmanagement.Entity;

import com.spring.hospitalmanagement.Config.BaseEntity;
import com.spring.hospitalmanagement.Enum.AuthProviderType;
import com.spring.hospitalmanagement.Enum.RoleType;
import com.spring.hospitalmanagement.Security.RolePermissionMapping;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Builder
@Table(name = "users",
        indexes = {
        @Index(name = "provider_id_provider_type",columnList = "providerId,providerType")
        }

)
public class User extends BaseEntity implements UserDetails {


        @JoinColumn(unique = true)
        private String username;

        private String password;

        private String providerId;

        @Enumerated(EnumType.STRING)
        private AuthProviderType providerType;

        @ElementCollection(fetch = FetchType.EAGER)
        @Enumerated(EnumType.STRING)
        Set<RoleType> roles = new HashSet<>();



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
   //       return roles.stream()
//                .map(role -> new SimpleGrantedAuthority("ROLE_"+role.name()))
//                .collect(Collectors.toSet());


        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(
                role -> {
                    Set<SimpleGrantedAuthority> permissions = RolePermissionMapping.getAuthoritiesForRole(role);
                    authorities.addAll(permissions);
                    authorities.add(new SimpleGrantedAuthority("ROLE_"+role.name()));
                }
        );
        return authorities;
    }


}
