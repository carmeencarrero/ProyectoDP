package juego;


/**
 * Implementación de los métodos de la clase Arma
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 *
 */
public class Arma implements Comparable <Arma>{
	
	/** Atributo que guarda el nombre del arma */
	private String nombre;
	
	/** Dato que almacena el poder que tiene el arma */
	private int poder;

	/**
	 * Constructor parametrizado de la clase Arma
	 * 
	 * @param _nombre
	 * 			el nombre del arma
	 * @param _poder
	 * 			el poder que tiene el arma
	 */
	public Arma(String _nombre, int _poder){
		nombre = _nombre;
		poder = _poder;
	}
	
	
	/**
	 * Método que devuelve el nombre
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}


	/**
	 * Metodo que cambia el atributo nombre por el valor pasado por parametro
	 * 
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	/**
	 * Método que devuelve el poder
	 * 
	 * @return poder
	 */
	public int getPoder() {
		return poder;
	}


	/**
	 * Metodo que cambia el atributo poder por el valor pasado por parametro
	 * 
	 * @param poder
	 */
	public void setPoder(int poder) {
		this.poder = poder;
	}

	
	
	
	
	public String toString(){
		String s = "";
		s = "(" + this.getNombre() + "," + this.getPoder() + ")";
		return s;
	}

public int compareTo(Arma o) {
		if (this.nombre.compareTo(o.getNombre()) > 0){
			return 1;
		}
		else if (this.nombre.compareTo(o.getNombre()) < 0){
			return -1;
		}
		return 0; //nombre == o.nombre
	}
	
	@Override
	public boolean equals(Object objeto){
		if (this == objeto){
			return true;
		}else if(!(objeto instanceof Arma)){
			return false;
		}
			Arma x=(Arma)objeto; 
		return this.getNombre().equals(x.getNombre());
	}
}

