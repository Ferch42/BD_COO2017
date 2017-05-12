package negocios;

public class NegociosException extends Exception {

	private static final long serialVersionUID = 1L;

	public NegociosException() {
		super();
	}

	public NegociosException(String message, Throwable cause,
			boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public NegociosException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegociosException(String message) {
		super(message);
	}

	public NegociosException(Throwable cause) {
		super(cause);
	}
}
