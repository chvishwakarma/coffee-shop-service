package com.coffeeshop.authentication.jwt;

import com.coffeeshop.authentication.dto.AuthenticatedUser;
import com.coffeeshop.domain.user.User;
import com.coffeeshop.service.UserService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;


/**
 * Provide JWT token creation and validation implementation
 * @author Chandan Vishwakarma
 */
@Component
public class JwtUtil {

  private static final Logger LOGGER = LoggerFactory.getLogger(JwtUtil.class);

  public static final String AUTHORIZATION = "Authorization";
  public static final String HEADER_APP_SUBJECT = "CoffeeShop-Subject";
  public static final String USER_AGENT = "User-Agent";
  public static final String JWT_TOKEN = "jwtToken";
  public static final String AUTH_SERVICE_JWT = "coffeeshop-service-jwt";

  private static UserService userService;

  @Autowired
  private UserService service;

  @PostConstruct
  public void init() {
    JwtUtil.userService = service;
  }

  public static AuthenticatedUser getUser(String token, String jwtSecretKey) {
    // @formatter:off
    Claims claims = Jwts.parser().setSigningKey(jwtSecretKey).parseClaimsJws(token).getBody();
    // @formatter:on
   /* User user = new User();
    user.setId(Long.parseLong(claims.getSubject()));*/
    LOGGER.info("JWT CLAIM ID : {}",claims.getSubject());
    User authenticatedUser = userService.findByID(Long.parseLong(claims.getSubject()));
    LOGGER.info("JWT USER RETRIEVED : {}",authenticatedUser);
    LOGGER.info("JWT ROLE : {}",new AuthenticatedUser(authenticatedUser).getAuthorities());
    return new AuthenticatedUser(authenticatedUser);
  }

  public static String getToken(String jwtSecretKey, TokenTO tokenTO) {
    // @formatter:off
    return  Jwts.builder().setSubject(tokenTO.getSubject())
                   .setExpiration(tokenTO.getExpirationDate())
                   .setIssuer(AUTH_SERVICE_JWT)
                   .setIssuedAt(new Date())
                   .setNotBefore(new Date())
                   .claim("roles", tokenTO.getRoles())
                   .claim("clientIP", tokenTO.getClientIpAddress())
                   .claim("browserFingerprintDigest", tokenTO.getBrowserFingerprintDigest())
                   .setHeaderParams(tokenTO.getHeaderClaims())
                   .signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
    // @formatter:on
  }

  public static boolean validateToken(String jwtSecretKey, TokenTO tokenTO) {
    try {
      // @formatter:off
      Jwts.parser()
          .setSigningKey(jwtSecretKey)
          .requireIssuer(tokenTO.getIssuer())
          .requireSubject(tokenTO.getSubject())
          .require("clientIP", tokenTO.getClientIpAddress())
          .require("browserFingerprintDigest", tokenTO.getBrowserFingerprintDigest())
          .parseClaimsJws(tokenTO.getJwtToken());
      // @formatter:on
    } catch (JwtException | IllegalArgumentException e) {
      LOGGER.warn("Invalid JWT Token ->", e);
      return false;
    }
    return true;
  }
}
