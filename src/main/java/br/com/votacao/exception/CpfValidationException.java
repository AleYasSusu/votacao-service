package br.com.votacao.exception;

 public class CpfValidationException extends RuntimeException {
    public CpfValidationException(String message) {
        super(message);
    }

    public CpfValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
