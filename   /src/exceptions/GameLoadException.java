package exceptions;

public class GameLoadException extends GameModelException {

	public GameLoadException(String mensaje) {
		super(mensaje);
		
	}
	public GameLoadException(String mensaje, Throwable causa) {
		super(mensaje, causa);
		
	}

}
