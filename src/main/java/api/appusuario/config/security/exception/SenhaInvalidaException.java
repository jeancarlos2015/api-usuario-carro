package api.appusuario.config.security.exception;

public class SenhaInvalidaException extends RuntimeException {
	public SenhaInvalidaException() {
		super("Senha Inválida");
	}
}
