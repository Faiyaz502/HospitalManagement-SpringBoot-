package com.spring.hospitalmanagement.Security;

import com.spring.hospitalmanagement.Entity.User;
import com.spring.hospitalmanagement.Enum.AuthProviderType;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Objects;

@Slf4j
@Component
public class AuthUtil {

    @Value("${jwt.secretKey}")
    private String secretKey;

    public SecretKey getSecretKey(){

        return Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

    }

    public String genereateToken(User user){


        return Jwts.builder()
                .subject(user.getUsername())
                .claim("userId",user.getId().toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+1000*60*10))
                .signWith(getSecretKey())
                .compact();


    }


    public String getUsernameFromToken(String token) {


        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();


        return claims.getSubject();


    }

    //--------------OAUTH2----------------------

    public AuthProviderType getProviderTypeFromRegistraitonId(String registrationId){


        return switch (registrationId.toLowerCase()){

            case "google" -> AuthProviderType.GOOGLE ;
            case "github" -> AuthProviderType.GITHUB ;
            case "facebook" -> AuthProviderType.FACEBOOK ;
            default -> throw new IllegalArgumentException("Unsupported OAuth2 provider"+registrationId);


        };

    }


    public String determineProviderIdFromOAuth2User(OAuth2User oAuth2User,String registrationId ){

        String providerId = switch (registrationId.toLowerCase()){

            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("id").toString();
            default -> {
                log.error("Unsupported OAuth2 provider"+registrationId);
                throw new IllegalArgumentException("Unsupported OAuth2 provider"+registrationId);
            }


        };

        if (providerId == null || providerId.isBlank()){

            log.error("Unable to detemine provideId for the provider : {}"+registrationId);
            throw new IllegalArgumentException("Unable to detemine provideId for OAuth2 Login");

        }

    return providerId;

    }

    public String determineUsernameFromOAuth2User(OAuth2User oAuth2User,String registerId, String providerId){


        String email = oAuth2User.getAttribute("email");

        if(email != null && !email.isBlank()){

            return email;


        }


        return switch (registerId.toLowerCase()){

            case "google" -> oAuth2User.getAttribute("sub");
            case "github" -> oAuth2User.getAttribute("login");
                     default -> providerId;


        };


    }



}
