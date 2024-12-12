package exceptions;

public class FileConfigException extends GameLoadException{

	public FileConfigException(String mensaje) {
		super(mensaje);
	}
	public FileConfigException(String mensaje, Throwable causa) {
		super(mensaje, causa);
	}

}
