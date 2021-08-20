package br.com.gsoft.mvtube.controller.auth.dto;

public class TokenBearedDto {

	private final String token;
	
	private final String type;
	
	public TokenBearedDto(String token) {
		this.token = token;
		this.type = "Bearer";
	}

	public String getToken() {
		return token;
	}
	
	public String getType() {
		return type;
	}
}
