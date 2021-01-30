package juego;

import java.util.Collections;
import java.util.LinkedList;
import personajes.Personaje;

/**
 * Implementación de los métodos de la clase Sala
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 *
 */
public class Sala implements Comparable <Sala>{

	/** Identificador de la sala*/
	private int identificador;
	
	/** Lista en la que se guardaran los personajes de la sala*/
	private LinkedList <Personaje> personajes;
	
	/** Lista e la que se guardaran las armas de la sala*/
	private LinkedList<Arma> armas;
	
	/** Atributo que almacena el hombrePuerta */
	private HombrePuerta hombrePuerta;
	
	/** Atributo con la marca de la sala*/
	private int marca;
	
	/** Atributo que guarda la frecuencia de cada sala (el numero de veces que aparece en las rutas) */
	private int frecuencia;

	/**
	 * Constructor parametrizado de la clase Sala
	 * 
	 * @param id 
	 * 			el identificador de la sala
	 */
	public Sala (int _id){
		identificador = _id;
		personajes = new LinkedList <Personaje>();
		armas = new LinkedList <Arma>();
		hombrePuerta = null;
		marca = _id;
		frecuencia = 0;
		
	}

	/**
	 * Método que devuelve el identificador
	 * 
	 * @return identificador
	 */
	public int getIdentificador() {
		return identificador;
	}


	/**
	 * Metodo que cambia el atributo identificador por el valor pasado por parametro
	 * 
	 * @param identificador
	 */
	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
	/**
	 * Metodo que devuelve la frecuencia
	 * 
	 * @return frecuencia
	 */
	public int getFrecuencia(){
		return frecuencia;
	}
	
	/**
	 * Metodo que cambia el atributo frecuencia por el valor pasado por parametro
	 * 
	 * @param frecuencia
	 */
	public void setFrecuencia(int frecuencia){
		this.frecuencia = frecuencia;
	}
	
	/**
	 * Metodo que devuelve la lista de personajes
	 * @return lista de personajes
	 */
	public LinkedList<Personaje> getPersonajes(){
		return personajes;
	}
	
	/**
	 * Metodo que devuelve la marca
	 * 
	 * @return marca
	 */
	public int getMarca(){
		return marca;
	}
	
	/**
	 * Metodo que cambia el atributo marca por el valor pasado por parametro
	 * 
	 * @param _marca
	 */
	public void setMarca(int _marca){
		marca = _marca;
	}
	
	
	/**
	 * Metodo que inserta un arma de manera ordenada (mayor a menor poder) en la lista de armas
	 * 
	 * @param a
	 * 		el arma a insertar
	 */
	public void insertarArma(Arma a){
		armas.add(a);
		Collections.sort(armas, new CompararArma());
	}
	
	
	/**
	 * Metodo que devuelve la lista de armas
	 * 
	 * @return armas
	 */
	public LinkedList<Arma> getArmas(){
		return armas;
	}
	
	/**
	 * Metodo que devuelve el hombre puerta
	 * 
	 * @return hombre puerta
	 */
	public HombrePuerta getHP(){
		return hombrePuerta;
	}
	
	
	/**
	 * Metodo que devuelve true si la sala tiene armas y false en caso contrario (vacia)
	 * 
	 * @return true o false
	 */
	public boolean tieneArmas(){
		return !armas.isEmpty();
	}
	
	/**
	 * Metodo que devuelve true si la sala tiene personajes y false en caso contrario (vacia)
	 * 
	 * @return true o false
	 */
	public boolean tienePersonajes(){
		return !personajes.isEmpty();
	}
	
	/**
	 * Metodo que devuelve el numero de personajes que hay en la sala
	 * 
	 * @return tamaño de la lista
	 * numero de personajes
	 */
	public int numPersonajes(){
		return personajes.size();
	}
	
	/**
	 * Metodo que borrar un personaje de la lista de personajes de la sala
	 * 
	 * @param p
	 */
	public void borrarPersonaje(Personaje p){
		personajes.remove(p);
	}
	
	/**
	 * Metodo que inserta un personaje al final de la lista de personajes de la sala
	 * 
	 * @param p
	 */
	public void insertarPersonaje(Personaje p){
		personajes.addLast(p);
	}
	
	
	/**
	 * Metodo que inserta el hombre puerta 
	 * 
	 * @param doorman
	 */
	public void insertarHombrePuerta (HombrePuerta doorman){
		hombrePuerta = doorman;
	}
	
	/**
	 * Metodo que devuelve true si en la sala esta el hombre puerta y false en caso contrario
	 * 
	 * @return boolean
	 */
	public boolean salaPuerta(){
		if (hombrePuerta != null)
			return true;
		else
			return false;
	}
	
	/**
	 * Metodo que distribuye las armas dentro la sala
	 * 
	 * @param armasSala
	 * 			vector de armas a distribuir
	 * @param posVector
	 * 			posicion del vector en la que empezar a distribuir
	 */
	public void distribuirArmasSala(Arma[] armasSala, int posVector){
			int posicion = posVector;
			
			for (int i = 0; i < 5; i++){
			 insertarArma(armasSala[posicion]);
			 posicion++;
		 }
	}
	
	/**
	 * Metodo que devuelve el primer arma de la sala y la elimina
	 * 
	 * @return arma
	 */
	public Arma recogerArma(){
		Arma aux = null;
		if(!armas.isEmpty()){
			aux = armas.getFirst();
			armas.removeFirst();
		}
		return aux;
	}
	
	/**
	 * Metodo que procesa todos los personajes de la sala
	 * 
	 */
	public void procesarSala(int turno){
		LinkedList <Personaje> aux = new LinkedList <Personaje>();
		for(int i = personajes.size() - 1; i >= 0; i--){
			aux.addFirst(personajes.get(i));
		}
		
		for (int i = 0; i < aux.size(); i++){
			Personaje p = aux.get(i);
			if (p.getTurno() == turno){
				p.procesarPersonaje(this);
			}
		}
	}
	
	//------------------------METODOS MOSTRAR---------
	
	/**
	 * Metodo que muestra las armas de la sala por pantalla
	 * 
	 */
	public String mostrarArmas(){
		String s = armas.toString().replace("[", "").replace("]", "").replace(", (", "(");

		System.out.print(armas);
		return s;
		}
	
	
	
	public int compareTo(Sala o) {
		if (this.frecuencia > o.getFrecuencia()){
			return -1;
		}
		else if (this.frecuencia < o.getFrecuencia()){
			return 1;
		}
		if (this.identificador < o.getIdentificador()){
		return -1; //frecuencia == o.frecuencia
		}
		else if (this.identificador > o.getIdentificador()){
			return 1;
		}
		else
			return 0;
	}
}
