package fr.byoim.encheres.bll;

public class BLLException extends Exception {
	private static final long serialVersionUID = 1L;

	// Constructeurs
	public BLLException() {
		super();
	}

	public BLLException(String message) {
		super(message);
	}

	public BLLException(String message, Throwable exception) {
		super(message, exception);
	}

	// Methodes
	public String getMessage() {
		StringBuffer sb = new StringBuffer();
		sb.append(super.getMessage());
		return sb.toString();
	}
}
