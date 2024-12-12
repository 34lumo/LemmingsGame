package exceptions;

public class RoleParseException extends GameParseException {

	public RoleParseException(String mensaje) {
		super(mensaje);
	}
	
	public RoleParseException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

}
