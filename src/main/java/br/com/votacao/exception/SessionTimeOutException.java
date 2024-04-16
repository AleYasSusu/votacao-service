package br.com.votacao.exception;

import org.springframework.http.HttpStatus;

public class SessionTimeOutException extends BusinessException {

	private static final long serialVersionUID = 5553707156721755355L;

	public SessionTimeOutException(String s) {
		super("sessao-7", HttpStatus.REQUEST_TIMEOUT);
	}
}
