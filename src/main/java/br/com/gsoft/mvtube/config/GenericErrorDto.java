package br.com.gsoft.mvtube.config;

public class GenericErrorDto {

	private String message;

	public GenericErrorDto(String message) {
		super();
		this.message = message;
	}

	public String getMessage() {
		return message;
	}
}
