package com.spring.hospitalmanagement.Repository;

import com.spring.hospitalmanagement.Entity.User;
import com.spring.hospitalmanagement.Enum.AuthProviderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.Repository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    Optional<User> findByUsername(String username);


    Optional<User> findByProviderIdAndProviderType(String providerId, AuthProviderType providerType);
}