package juego;


/**
 * Implementación de los métodos de la clase ExceptionJuego
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public class ExceptionJuego extends Exception {

	private static final long serialVersionUID = 1L;

	/**
	 * Constructor parametrizado de la clase ExceptionJuego
	 * 
	 * @param mensaje
	 */
	public ExceptionJuego(String mensaje) {
		super(mensaje);

	}
}
