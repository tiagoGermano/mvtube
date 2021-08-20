package br.com.gsoft.mvtube.controller.auth;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.gsoft.mvtube.config.security.TokenService;
import br.com.gsoft.mvtube.controller.auth.dto.LoginDto;
import br.com.gsoft.mvtube.controller.auth.dto.TokenBearedDto;

@RestController
@RequestMapping("/auth")
public class AuthenticationRestController {

	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenBearedDto> authentication(@RequestBody @Valid LoginDto loginDto){
		
		UsernamePasswordAuthenticationToken authenticationToken = loginDto.converter();
		try {
			Authentication authentication = authManager.authenticate(authenticationToken);
			String token = tokenService.generateToken(authentication);
			return ResponseEntity.ok(new TokenBearedDto(token));
			
		} catch (AuthenticationException e) {
			return ResponseEntity.badRequest().build();
		}

	}
}
