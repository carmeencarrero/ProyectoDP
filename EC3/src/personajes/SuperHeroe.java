package personajes;

import java.util.LinkedList;

import estructurasDatos.Arbol;
import juego.ArbolArma;
import juego.Arma;
import juego.HombrePuerta;
import juego.NuevaYork;
import juego.Sala;

/**
 * Implementación de los métodos de la clase SuperHeroe
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public abstract class SuperHeroe extends Personaje {

	/** Atributo que almacena las armas del personaje */
	private Arbol<Arma> armasS;

	/** Lista que contiene los personajes que van capturando los SH */
	private LinkedList<Villano> capturados;

	/**
	 * Constructor parametrizado de la clase SuperHeroe
	 * 
	 * @param _nombre
	 * @param _marca
	 * @param _turno
	 * @param _sala
	 */
	public SuperHeroe(String _nombre, char _marca, int _turno, int _sala) {
		super(_nombre, _marca, _turno, _sala);
		armasS = new Arbol<Arma>();
		capturados = new LinkedList<Villano>();
	}

	/**
	 * Metodo que inserta un arma en el arbol de armas del superheroe
	 * 
	 * @param arma
	 */
	public void insertarArma(Arma arma) {
		armasS.insertar(arma);
	}

	/**
	 * Metodo que busca el arma pasado por parametro en el arbol de armas del SH
	 * y ademas lo devuelve
	 * 
	 * @param arma
	 * @return arma
	 */
	public Arma getArma(Arma arma) {
		return armasS.pertenece2(arma);
	}

	/**
	 * Metodo que borra el arma pasado por parametro del arbol de armas del SH
	 * 
	 * @param arma
	 */
	public void perderArma(Arma arma) {
		armasS.borrar(arma);
	}

	/**
	 * Metodo para la interaccion del personaje con otros
	 * 
	 * @param salita
	 */
	public void interactuarPersonaje(Sala salita) {
		LinkedList<Personaje> psala = salita.getPersonajes();
		boolean stop = false;

		if (!psala.isEmpty()) {
			for (int i = 0; i < psala.size() && !stop; i++) {
				if (psala.get(i).getTipo() == "villain") { // primer villano
															// encontrado
					stop = true;
					Villano posCaptura = (Villano) psala.get(i);

					Arma armaSH = armasS.pertenece2(posCaptura.getArma());
					if (armaSH != null
							&& armaSH.getPoder() > posCaptura.getArma()
									.getPoder()) {
						capturados.add(posCaptura);
						psala.remove(i);
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
	public void interactuarPuerta(Sala salita) {
		Arma armaSuperH = ArbolArma.datoMayor(armasS);
		HombrePuerta HP = salita.getHP();
		NuevaYork NY = NuevaYork.getInstancia();
		boolean stop = false;

		if (HP.getPortal()) { // el portal ha sido abierto
			NY.insertarAdicional(this); // lo inserto en la sala adicional
			NY.borrarPersonaje(this, salita.getIdentificador()); // y lo borro
																	// de la
																	// sala del
																	// mapa
			stop = true;
		}
		if (armaSuperH != null) {
			Arma armaPuerta = HP.armaSH(armaSuperH); // devuelvo el arma del
														// hombrepuerta si es el
														// mismo que el
														// superheroe
			// y null si el arma no esta
			if (armaPuerta != null) { // ha encontrado el arma

				if (armaSuperH.getPoder() > armaPuerta.getPoder()) {
					HP.borrarArma(armaPuerta);
				}
			}
			armasS.borrar(armaSuperH);
		}
		if (HP.abrirPortal() && !stop) { // el personaje ha abierto el portal
			NY.insertarAdicional(this);
			NY.borrarPersonaje(this, salita.getIdentificador());
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
		Arma armapersonaje;
		if (armasala != null) { // la sala tiene armas
			if (armasS.vacio()) {
				armasS.insertar(armasala);
			} else {
				armapersonaje = armasS.pertenece2(armasala);

				if (armapersonaje != null) {
					armapersonaje.setPoder(armapersonaje.getPoder()
							+ armasala.getPoder());
				} else {
					armasS.insertar(armasala);
				}
			}
		}
	}

	// ------------------METODOS MOSTRAR-----------

	/**
	 * Metodo que muestra las armas que tiene el personaje
	 * 
	 */
	public String mostrarArmas() {
		String s = ArbolArma.mostrarArbol(armasS);
		return s;
	}

	/**
	 * Metodo que muestra los personajes capturados
	 * 
	 */
	public String mostrarCapturados() {
		String s = "";
		for (int i = 0; i < capturados.size(); i++) {
			s = capturados.get(i).getMarca() + " ";
		}
		return s;
	}

}
