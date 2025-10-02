package com.spring.hospitalmanagement.Entity;

import com.spring.hospitalmanagement.Config.BaseEntity;
import com.spring.hospitalmanagement.Enum.AuthProviderType;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

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



    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }


}
