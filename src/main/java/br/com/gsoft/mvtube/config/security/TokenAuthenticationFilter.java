package br.com.gsoft.mvtube.config.security;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.gsoft.mvtube.model.user.User;
import br.com.gsoft.mvtube.repository.user.UserRepository;

public class TokenAuthenticationFilter extends OncePerRequestFilter {

	private TokenService tokenService;
	
	private UserRepository userRepository;
	
	public TokenAuthenticationFilter(TokenService tokenService, UserRepository userRepository) {
		this.tokenService = tokenService;
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String token = getToken(request);
		if(tokenService.validateToken(token)) {
			authenticationClient(token);
		}
		
		filterChain.doFilter(request, response);
	}

	private void authenticationClient(String token) {
		Long userId = tokenService.getJWTSubject(token);
		Optional<User> user = userRepository.findById(userId);
		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user.get(), null, user.get().getAuthorities());
		
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private String getToken(HttpServletRequest request) {
		String token = request.getHeader("Authorization");
		if(token == null || token.isEmpty() || !token.startsWith("Bearer ")) {
			return null;
		}
		
		return token.substring(7);		
	}
	
}
