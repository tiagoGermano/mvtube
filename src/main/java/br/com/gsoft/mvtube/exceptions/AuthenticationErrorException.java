package br.com.gsoft.mvtube.exceptions;

public class AuthenticationErrorException extends Exception {

	private static final long serialVersionUID = -6670815862955692942L;

	public AuthenticationErrorException(String msg) {
		super(msg);
	}
}
