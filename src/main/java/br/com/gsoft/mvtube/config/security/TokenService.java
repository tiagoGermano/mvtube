package br.com.gsoft.mvtube.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.gsoft.mvtube.model.user.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class TokenService {

	@Value("${mvtube.jwt.expiration}")
	private String expirationDate;
	
	@Value("${mvtube.jwt.secret}")
	private String secret;
	
	public String generateToken(Authentication authenticate) {
		User logginUser = (User) authenticate.getPrincipal();
		Date now = new Date();
		Date expireDate = new Date(now.getTime() + Long.parseLong(expirationDate));
		
		return Jwts.builder()
				.setIssuer("MvTube_API")
				.setSubject(logginUser.getId().toString())
				.setIssuedAt(now)
				.setExpiration(expireDate)
				.signWith(SignatureAlgorithm.HS256, secret)
				.compact();
	}
	
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
			return true;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return false;
		}
	}	

	public Long getJWTSubject(String token) {
		Claims jwtBody = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
		return Long.parseLong(jwtBody.getSubject());
	}
		
}
