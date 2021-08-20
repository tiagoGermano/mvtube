package br.com.gsoft.mvtube.config.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.gsoft.mvtube.model.user.User;
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
		
}
