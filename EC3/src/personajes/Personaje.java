package personajes;

import java.util.ArrayList;
import java.util.LinkedList;

import juego.ExceptionJuego;
import juego.NuevaYork;
import juego.Sala;

/**
 * Implementación de los métodos de la clase Personaje
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public abstract class Personaje implements Comparable<Personaje> {

	/** Atributo que contiene el nombre del personaje */
	private String nombre;

	/** Atributo que contiene la marca identificativa del personaje */
	private char marca;

	/** Atributo que contiene el turno en el que se mueve el personaje */
	private int turno;

	/**
	 * Atributo que contiene el identificador de la sala en la que se encuentra
	 * el personaje, al principio será la sala en la que empieza
	 */
	private int sala;

	/** Atributo que contiene el tipo del personaje */
	private String tipo;
	
	/** Atributo que guarda si el personaje se ha movido desde su sala inicial (solo se usa para el mostrar)*/
	private boolean movido;
	
	/** Atributo que contiene la lista de direcciones del personaje */
	private ArrayList<Direcciones> direcciones;

	/**
	 * Constructor parametrizado de la clase Personaje
	 * 
	 * @param _nombre
	 * @param _marca
	 * @param _turno
	 * 
	 */
	public Personaje(String _nombre, char _marca, int _turno, int _sala) {
		nombre = _nombre;
		marca = _marca;
		sala = _sala;
		tipo = null;
		turno = _turno;
		movido = false;
		direcciones = new ArrayList<Direcciones>();
	}

	/**
	 * Metodo que devuelve el nombre del personaje
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
	 * Metodo que devuelve la marca del personaje
	 * 
	 * @return marca
	 */
	public char getMarca() {
		return marca;
	}

	/**
	 * Metodo que cambia el atributo marca por el valor pasado por parametro
	 * 
	 * @param marca
	 */
	public void setMarca(char marca) {
		this.marca = marca;
	}

	/**
	 * Metodo que devuelve el turno del personaje
	 * 
	 * @return turno
	 */
	public int getTurno() {
		return turno;
	}

	/**
	 * Metodo que devuelve el tipo del personaje
	 * 
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Metodo que cambia el atributo tipo por el valor pasado por parametro
	 * 
	 * @param _tipo
	 */
	public void setTipo(String _tipo) {
		tipo = _tipo;
	}

	/**
	 * Metodo que devuelve la sala del personaje
	 * 
	 * @return id sala
	 */
	public int getSala() {
		return sala;
	}

	/**
	 * Metodo que cambia el atributo turno por el valor pasado por parametro
	 * 
	 * @param _turno
	 */
	public void setTurno(int _turno) {
		turno = _turno;
	}
	
	/**
	 *Metodo que devuelve el atributo movido 
	 *
	 * @return movido
	 */
	public boolean getMovido(){
		return movido;
	}

	/**
	 * Metodo que realiza todas las acciones del personaje en orden
	 * 
	 * @param salita
	 */
	public void procesarPersonaje(Sala salita) {
		if (salita.salaPuerta()) {
			interactuarPuerta(salita); // si esta en la misma sala en la que
										// esta el hombre puerta, interactua con
										// el
		}
		// el personaje realiza su movimiento e interactua con las armas y
		// personajes de esa nueva sala
		movimiento();

	}

	/**
	 * Metodo para el movimiento del personaje
	 * 
	 * @param salita
	 */
	public void movimiento() {
		NuevaYork NY = NuevaYork.getInstancia();
		int salaFinal = sala; // el personaje no se mueve
		if (!getMovido())
			movido = true;
		
		if (!direcciones.isEmpty()) {
			Direcciones aux = direcciones.get(0);

			switch (aux) {
			case N:
				if (sala > NY.getAncho() - 1)
					salaFinal = sala - NY.getAncho();
				break;

			case S:
				if (sala < (NY.getAncho() * NY.getAlto()) - NY.getAncho())
					salaFinal = sala + NY.getAncho();
				break;

			case E:
				if ((sala + 1) % NY.getAncho() != 0)
					salaFinal = sala + 1;
				break;

			case W:
				if (sala % NY.getAncho() != 0)
					salaFinal = sala - 1;
				break;

			}
			direcciones.remove(0);

		}
		turno++;
		if (salaFinal != sala) {
			if (!NY.portalAbierto()) {
				NY.borrarPersonaje(this, sala);
				sala = salaFinal;
				NY.insertarPersonaje(this, salaFinal);
			}
		}
		// el personaje interactua con las armas y los personajes de la nueva
		// sala
		Sala sala = NY.obtenerSala(salaFinal);
		interactuarArma(sala);
		interactuarPersonaje(sala);
	}

	/**
	 * Metodo abstracto para la interaccion con el arma de la sala
	 * 
	 * @param salita
	 */
	public abstract void interactuarArma(Sala salita);

	/**
	 * Metodo abstracto para la interaccion del personaje con el hombre puerta
	 * 
	 * @param salita
	 */
	public abstract void interactuarPuerta(Sala salita);

	/**
	 * Metodo abstracto para la interaccion del personaje con otros personajes
	 * 
	 * @param salita
	 */
	public abstract void interactuarPersonaje(Sala salita);

	/**
	 * Metodo abstracto que crea la ruta de cada personaje
	 * 
	 */
	public abstract void crearRutaPersonaje();

	/**
	 * Metodo que inserta en la lista de direcciones la ruta que tiene que seguir cada personaje
	 * 
	 * @param ruta
	 */
	public void rutaADirecciones(LinkedList<Integer> ruta) {

		NuevaYork NY = NuevaYork.getInstancia();
		int dimx = NY.getAncho();

		for (int i = 1; i < ruta.size(); i++) {
			int sala = ruta.get(i);

			if (sala == ruta.get(i - 1) - dimx) {
				direcciones.add(Direcciones.N);
			}
			
			if (ruta.get(i - 1) + dimx == sala) {
				direcciones.add(Direcciones.S);
			}

			if (sala == ruta.get(i - 1) + 1) {
				direcciones.add(Direcciones.E);
			}

			if (sala == ruta.get(i - 1) - 1) {
				direcciones.add(Direcciones.W);
			}
		}
	}

	/**
	 * Método creado para el control de errores del juego
	 * 
	 * @param turno
	 * @throws ExceptionJuego
	 */
	public void errorPersonaje(int turno) throws ExceptionJuego {
		if (turno < 0)
			throw new ExceptionJuego("Turno del personaje negativo");
	}

	// -----------------------------METODOS MOSTRAR----------------

	/**
	 * Metodo abstracto para mostrar las armas del personaje
	 * 
	 * @return string
	 */
	public abstract String mostrarArmas();

	/**
	 * Metodo abstracto para mostrar los personajes capturados (solo es
	 * utilizado por SH)
	 * 
	 * @return string
	 */
	public abstract String mostrarCapturados();

	/**
	 * Metodo que muestra la ruta del personaje
	 * 
	 * @return ruta
	 */
	public String mostrarRuta() {
		String s = "(path:" + getMarca() + ": ";
		System.out.print("(path:" + getMarca() + ": ");
		for (int i = 0; i < direcciones.size(); i++) {
			if (i == direcciones.size() - 1) {
				s = s + direcciones.get(i);
				System.out.print(direcciones.get(i));
			} else {
				s = s + direcciones.get(i) + " ";
				System.out.print(direcciones.get(i) + " ");
			}
		}
		s = s + ")\n";
		System.out.println(")");
		return s;
	}

	/**
	 * Metodo que muestra los personajes
	 * 
	 * @return string personaje
	 */
	public String mostrarPersonaje() {
		int turnoM = getTurno();
		
		if (getMovido())
		   turnoM = getTurno() - 1;
		
		System.out.print("(" + getTipo() + ":" + getMarca() + ":" + getSala()
				+ ":" + turnoM + ":");
		String s = "(" + getTipo() + ":" + getMarca() + ":" + getSala() + ":"
				+ turnoM + ":" + mostrarArmas();
		if (getTipo() == "shextrasensorial" || getTipo() == "shphysical"
				|| getTipo() == "shflight")
			s = s + mostrarCapturados();
		System.out.println(")");
		s = s + ")\n";
		return s;
	}

	/**
	 * Metodo que muestra los personajes que están en la sala adicional
	 * 
	 * @return string personaje
	 */
	public String mostrarPersonajeAdd(int ganador) {
		String s = "";
		int turnoM = getTurno();
		
		if (getMovido())
		   turnoM = getTurno() - 1;
		
		if (ganador != 0){	
		System.out.print("(");
		s = "(";
		}
		System.out.println(getTipo() + ":" + getMarca() + ":1111:"
				+ turnoM + ":");
		s = s + getTipo() + ":" + getMarca() + ":1111:" + turnoM
				+ ":" + mostrarArmas();
		System.out.println(")");
		s = s + ")\n";
		return s;
	}

	
	
	public int compareTo(Personaje o) {
		if (getMarca() > o.getMarca()) {
			return 1;
		} else if (getMarca() < o.getMarca()) {
			return -1;
		}
		return 0;
	}

	public boolean equals(Object objeto) {
		if (this == objeto) {
			return true;
		} else if (!(objeto instanceof Personaje)) {
			return false;
		}
		Personaje x = (Personaje) objeto;
		return this.getMarca() == x.getMarca();
	}

}
