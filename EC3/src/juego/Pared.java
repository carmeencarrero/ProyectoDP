package juego;

/**
 * Implementación de los métodos de la clase Pared
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 *
 */
public class Pared {

	/** Atributo que guarda el identificador de la sala origen*/
	private int origen;
	
	/** Atributo que guarda el identificador de la sala destino*/
	private int destino;
	
	/**
	 * Constructor parametrizado de la clase Pared.
	 * 
	 * @param or
	 * @param ds
	 */
	Pared(int or, int ds){
		origen = or;
		destino = ds;
	}

	/**
	 * Método que devuelve el origen
	 * 
	 * @return origen
	 */
	public int getOrigen() {
		return origen;
	}
	
	/**
	 * Metodo que cambia el atributo origen por el valor pasado por parametro
	 * 
	 * @param origen
	 */
	public void setOrigen(int origen) {
		this.origen = origen;
	}

	/**
	 * Método que devuelve el destino
	 * 
	 * @return destino
	 */
	public int getDestino() {
		return destino;
	}

	/**
	 * Metodo que cambia el atributo destino por el valor pasado por parametro
	 * 
	 * @param destino
	 */
	public void setDestino(int destino) {
		this.destino = destino;
	}
}
