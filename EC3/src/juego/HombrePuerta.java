package juego;

import estructurasDatos.Arbol;

/**
 * Implementación de los métodos de la clase HombrePuerta
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public class HombrePuerta {

	/** Atributo que determina si el portal esta cerrado o no*/
	private boolean portal;
	
	/** Estructura de datos que almacena las armas del hombre puerta*/
	private Arbol <Arma> armasPuerta;
	
	/** Atributo que almacena la altura del mapa para la condición de apertura */
	private int altura;
	
	/**
	 * Constructor de la clase HombrePuerta
	 * 
	 * @param altura
	 * 
	 */
	public HombrePuerta(int _altura){
		portal = false;
		armasPuerta = new Arbol <Arma>();
		altura = _altura;
	}
	
	
	/**
	 * Metodo que devuelve el portal
	 * 
	 * @return portal
	 */
	public boolean getPortal(){
		return portal;
	}
	
	/**
	 * Metodo que cambia el atributo portal por el valor pasado por parametro
	 * 
	 * @param estado
	 */
	public void setPortal(boolean estado){
		portal = estado;
	}
	
	
	/**
	 * Metodo que devuelve la altura
	 * 
	 * @return altura
	 */
	public int getAltura(){
		return altura;
	}
	
	/**
	 * Metodo que cambia el atributo altura por el valor pasado por parametro
	 * 
	 * @param _altura
	 */
	public void setAltura(int _altura){
		altura = _altura;
	}
	
	/**
	 * Metodo que devuelve el arbol con las armas del hombre puerta
	 * 
	 * @return armasPuerta
	 */
	public Arbol<Arma> getArmasPuerta(){
		return armasPuerta;
	}
	
	/**
	 * Metodo que inserta en el arbol el vector de armas pasado por parametro
	 * 
	 * @param armasPuert
	 */
	public void configurar(Arma [] armasPuert){
		for (int i = 0; i < armasPuert.length; i++){
			armasPuerta.insertar(armasPuert[i]);
		}
	}
	
	/**
	 * Metodo que devuelve el arma pasado por parametro si pertenece al arbol de hombre puerta
	 * 
	 * @param armaSH
	 * @return arma
	 */
	public Arma armaSH (Arma armaSH){
		return armasPuerta.pertenece2(armaSH);
	}
	
	
	/**
	 * Metodo que borra del arbol el arma pasado por parametro
	 * 
	 * @param arma
	 */
	public void borrarArma(Arma arma){
		armasPuerta.borrar(arma);
	}
	
	/**
	 * Devuelve el arma con mayor poder del arbol de armas del hombre puerta
	 * 
	 * @return arma con mayor poder
	 */
	public Arma armaMayor (){
		return ArbolArma.datoMayor(armasPuerta);
	}
	
	/**
	 * Metodo que devuelve si el arma pasada por parametro pertenece o no al arbol
	 * 
	 * @param dato
	 * @return true si pertenece y false en caso contrario
	 */
	public boolean pertenece(Arma dato){
		return armasPuerta.pertenece(dato);
	}
	
	
	/**
	 * Metodo que devuelve la profundidad del arbol
	 * 
	 * @return profundidad
	 */
	public int profundidad(){
		return armasPuerta.profundidad();
	}
	
	
	/**
	 * Metodo que abre el portal si se cumple la condicion de apertura
	 * 
	 * @return el estado del portal, true si esta abierto false si esta cerrado
	 */
	public boolean abrirPortal(){
		if (armasPuerta.profundidad() < altura)
			portal = true;
		else
			portal = false;
		return portal;
	}
	
	/**
	 * Metodo que muestra el hombre puerta
	 * 
	 * @return string
	 */
	public String mostrarHombreP(){
		String s = "";
		String cerradura = "";
		
		if (getPortal()){
			cerradura = "open";
		}else{
			cerradura = "closed";
		}
		s = s + cerradura + ":" + getAltura() + ":";
		System.out.print(cerradura + ":" + getAltura() + ":");
		s =  s + ArbolArma.mostrarArbol(armasPuerta);
		return s;
		
	}
}
