package personajes;

import java.util.LinkedList;

import juego.Arma;
import juego.HombrePuerta;
import juego.NuevaYork;
import juego.Sala;

/**
 * Implementación de los métodos de la clase Villano
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public class Villano extends Personaje {

	/** Atributo que almacena el arma del personaje */
	private Arma armaV;


	/**
	 * Constructor parametrizado de la clase Villano
	 * 
	 * @param _nombre
	 * @param _marca
	 * @param _turno
	 * @param _armaV
	 * @param _sala
	 */
	public Villano(String _nombre, char _marca, int _turno, Arma _armaV,
			int _sala) {
		super(_nombre, _marca, _turno, _sala);
		armaV = _armaV;
		setTipo("villain");
	}

	/**
	 * Constructor parametrizado de la clase Villano
	 * 
	 * @param _nombre
	 * @param _marca
	 * @param _turno
	 * @param _sala
	 */
	public Villano(String _nombre, char _marca, int _turno, int _sala) {
		super(_nombre, _marca, _turno, _sala);
		armaV = null;
		setTipo("villain");
	}

	/**
	 * Metodo que devuelve el atributo arma
	 * 
	 * @return arma
	 */
	public Arma getArma(){
		return armaV;
	}
	
	/**
	 * Metodo que cambia el valor del atributo arma por el pasado por parametro
	 * 
	 * @param _armaV
	 */
	public void setArma(Arma _armaV) {
		armaV = _armaV;
	}

	@Override
	/**
	 * Metodo para la interaccion del personaje con otros
	 */
	public void interactuarPersonaje(Sala salita) {
		LinkedList <Personaje> psala = salita.getPersonajes();
		boolean stop = false;
		
		if (!psala.isEmpty()){
			for(int i = 0; i < psala.size() && !stop; i++){
				if (psala.get(i).getTipo() == "shextrasensorial" || psala.get(i).getTipo() == "shphysical" || psala.get(i).getTipo() == "shflight"){ //primer superheroe encontrado
					stop = true;
					SuperHeroe SH = (SuperHeroe) psala.get(i);
					Arma armaSH = SH.getArma(armaV);
					if (armaSH != null && armaSH.getPoder() < armaV.getPoder()){
						SH.perderArma(armaSH);
					}
				}
			}
		}

	}

	/**
	 * Metodo para la interaccion del personaje con el hombre puerta
	 * 
	 * @param salita
	 */
	@Override
	public void interactuarPuerta(Sala salita) {
		NuevaYork NY = NuevaYork.getInstancia();
		HombrePuerta HP = salita.getHP();
		boolean stop = false;
		Arma armaHP = HP.armaMayor();
		
		if(HP.getPortal()){ //el portal ha sido abierto
			NY.insertarAdicional(this); //lo inserto en la sala adicional
			NY.borrarPersonaje(this, salita.getIdentificador()); //y lo borro de la sala del mapa
			stop = true;
		}
		
			
				if (armaV.getPoder() >= armaHP.getPoder()) {
					HP.borrarArma(armaHP);
				
				
			if (HP.abrirPortal() && !stop){ //el personaje ha abierto el portal
					NY.insertarAdicional(this);
					NY.borrarPersonaje(this, salita.getIdentificador());
			}
		}
	}

	/**
	 * Metodo para la interaccion con el arma de la sala
	 * 
	 * @param salita
	 */
	@Override
	public void interactuarArma(Sala salita) {
		Arma armasala = salita.recogerArma();

		if (armasala != null) { // si hay arma (ha podido coger nulo por la
								// lista vacia)
			if (armaV == null) {
				armaV = armasala;
			} else {
				if (armasala.getPoder() > armaV.getPoder()) {
					salita.insertarArma(armaV);
					armaV = armasala;
				} else {
					salita.insertarArma(armasala);
				}
			}
		}
	}

	/**
	 * Metodo que crea la ruta del villano mediante el algoritmo de la mano izquierda
	 * 
	 */
	@Override
	public void crearRutaPersonaje() {
		
	LinkedList <Integer> ruta = manoIzquierda();
		
		rutaADirecciones(ruta);
		
	}
	
	/**
	 * Metodo que calcula el algoritmo de la mano izquierda
	 * 
	 * @return lista con las salas por las que pasa el personaje
	 */
	private LinkedList<Integer> manoIzquierda(){
		LinkedList<Integer> caminoElegido = new LinkedList<Integer>();
		NuevaYork NY = NuevaYork.getInstancia();
		
		boolean giroS = false;
		boolean giroN = false;
		boolean giroE = false;
		boolean giroO = false;
		int sala = NY.esquinaNoreste(); //sala en la que empieza el personaje
		char mano = 'E'; //como mira al sur, tiene la mano en la pared este
		
		int dimx = NY.getAncho();
		
		caminoElegido.add(sala);

		while (sala != NY.getDailyPlanet()) {
			
			switch (mano) {
			case 'N':
				if (!NY.existePared(sala, sala - dimx) && !giroS){
					sala = sala - dimx;
					caminoElegido.add(sala);
					mano = 'S';
					giroN = true;
				}
				else if (!NY.existePared(sala, sala + 1)) { //va el este
					sala = sala + 1;
					caminoElegido.add(sala);
					giroS = false;
				} else
					mano = 'E';
				break;

			case 'S':
				if (!NY.existePared (sala, sala + dimx) && !giroN){
					sala = sala + dimx; //cuando el personaje gira
					caminoElegido.add(sala);
					mano = 'N';
					giroS = true;
				}
				else if (!NY.existePared(sala, sala - 1)) { //va al oeste
					sala = sala - 1;
					caminoElegido.add(sala);
					giroN = false;
				} else
					mano = 'W';
				break;

			case 'E':
				if (!NY.existePared(sala, sala + 1) && !giroO){
					sala = sala + 1;
					caminoElegido.add(sala);
					mano = 'W';
					giroE = true;
					
				}
				else if (!NY.existePared(sala, sala + dimx)) { //va al sur
					sala = sala + dimx;
					caminoElegido.add(sala);
					giroO = false;
				} else
					mano = 'S';
				break;

			case 'W':
				if (!NY.existePared(sala, sala - 1) && !giroE){
					sala = sala - 1;
					caminoElegido.add(sala);
					mano = 'E';
					giroO = true;
					
				}
			else if (!NY.existePared(sala, sala - dimx)) { //va al norte
					sala = sala - dimx;
					caminoElegido.add(sala);
					giroE = false;
				} else
					mano = 'N';

				break;

			}

		}

		return caminoElegido;
	}
	

	// ---------------METODOS MOSTRAR------
	/**
	 * Metodo que muestra el arma del personaje
	 */
	public String mostrarArmas() {
		String s = "";
		if(armaV != null){
		s = armaV.toString();
		System.out.print(armaV);
		}
		
		return s;
	}

	@Override
	public String mostrarCapturados() {
		// TODO Auto-generated method stub
		return "";
	}


}
