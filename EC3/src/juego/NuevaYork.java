package juego;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

import estructurasDatos.GenAleatorios;
import estructurasDatos.Grafo;
import personajes.Personaje;

/**
 * Implementación de los métodos de la clase NuevaYork
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public class NuevaYork {

	/** Dato almacenado que guarda el ancho del tablero */
	private int ancho; // dimx

	/** Dato almacenado que guarda el alto del tablero */
	private int alto; // dimy

	/** Atributo que guarda el tablero para la simulacion */
	private Sala[][] tablero;

	/** Identificador de la sala en la que se encuentra el hombre puerta */
	private int dailyPlanet;

	/** Atributo que almacena la altura suficiente para que se abra la puerta */
	private int altura;

	/** Atributo que almacena la estructura de datos grafo */
	private Grafo graph;

	/** Sala en la que insertarán los personajes que pasen por la puerta */
	private Sala adicional;

	/**
	 * Lista de listas que almacena todos los caminos entre el nodoinicio y la
	 * sala de la puerta
	 */
	private LinkedList<LinkedList<Integer>> caminosSala;

	/** Singlenton de la clase */
	private static NuevaYork mapa = null;

	/**
	 * Constructor de la clase NuevaYork
	 * 
	 */
	private NuevaYork() {
		dailyPlanet = 35;
		ancho = 6;
		alto = 6;
		altura = 4;
		graph = new Grafo();
		tablero = new Sala[alto][ancho];
		adicional = new Sala(1111);
		caminosSala = new LinkedList<LinkedList<Integer>>();
		crearMapa();
	}

	/**
	 * Constructor parametrizado de la clase NuevaYork
	 * 
	 * @param dimx
	 *            el ancho del tablero
	 * 
	 * @param dimy
	 *            el alto del tablero
	 * 
	 * @param salaDailyPlanet
	 *            el identificador de la sala del hombre puerta
	 * 
	 * @param alturaApertura
	 *            la altura que tiene que tener el arbol
	 * 
	 * @param caminosSala
	 *            lista que almacena los caminos del mapa
	 */
	private NuevaYork(int salaDailyPlanet, int dimx, int dimy,
			int alturaApertura) {
		dailyPlanet = salaDailyPlanet;
		ancho = dimx;
		alto = dimy;
		altura = alturaApertura;
		graph = new Grafo();
		tablero = new Sala[alto][ancho];
		adicional = new Sala(1111);
		caminosSala = new LinkedList<LinkedList<Integer>>();
		crearMapa();
	}

	/**
	 * Metodo Singlenton. Te devuelve la instancia de la clase NuevaYork, si es
	 * igual a nulo, te crea la instancia con los valores por defecto
	 * 
	 * @return mapa
	 */
	public static NuevaYork getInstancia() {
		if (mapa == null)
			mapa = new NuevaYork(); // creando mapa por defecto
		return mapa;
	}

	/**
	 * 
	 * Metodo Singlenton. Devuelve la instancia de la clase creada con los
	 * valores pasados por parametros
	 * 
	 * @param salaDailyPlanet
	 * @param dimx
	 * @param dimy
	 * @param alturaApertura
	 * @return mapa
	 */
	public static NuevaYork getInstancia(int salaDailyPlanet, int dimx,
			int dimy, int alturaApertura) {
		if (mapa == null)
			mapa = new NuevaYork(salaDailyPlanet, dimx, dimy, alturaApertura);

		return mapa;
	}

	/**
	 * Metodo que modifica la instancia poniéndola a nulo
	 * 
	 */
	public void instanciaNull() {
		mapa = null;
	}

	/**
	 * Método que devuelve la dimx del tablero
	 * 
	 * @return ancho
	 */
	public int getAncho() {
		return ancho;
	}

	/**
	 * Metodo que cambia el atributo ancho por el valor pasado por parametro
	 * 
	 * @param ancho
	 */
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	/**
	 * Método que devuelve la dimy del tablero
	 * 
	 * @return alto
	 */
	public int getAlto() {
		return alto;
	}

	/**
	 * Metodo que cambia el atributo alto por el valor pasado por parametro
	 * 
	 * @param alto
	 */
	public void setAlto(int alto) {
		this.alto = alto;
	}

	/**
	 * Método que devuelve el identificador de la sala en la que esta el hombre
	 * puerta
	 * 
	 * @return dailyPlanet
	 */
	public int getDailyPlanet() {
		return dailyPlanet;
	}

	/**
	 * Metodo que cambia el atributo dailyPlanet por el valor pasado por
	 * parametro
	 * 
	 * @param dailyPlanet
	 */
	public void setDailyPlanet(int dailyPlanet) {
		this.dailyPlanet = dailyPlanet;
	}

	/**
	 * Método que devuelve la altura que tiene que tener para que se cumpla la
	 * condicion
	 * 
	 * @return altura arbol
	 */
	public int getAltura() {
		return altura;
	}

	/**
	 * Metodo que cambia el atributo altura por el valor pasado por parametro
	 * 
	 * @param altura
	 */
	public void setAltura(int altura) {
		this.altura = altura;
	}
	
	/**
	 * Metodo que devuelve una lista con todos los caminos del tablero
	 * 
	 * @return caminosSala
	 */
	public LinkedList<LinkedList<Integer>> getCaminos(){
		return caminosSala;
	}

	/**
	 * Método que crea el mapa con los id de las salas
	 */
	public void crearMapa() {
		int id = 0;

		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				tablero[i][j] = new Sala(id);
				id++;

			}
		}
	}

	/**
	 * Metodo que crea el grafo
	 */
	public void crearGrafo() {
		for (int i = 0; i < ancho * alto; i++)
			graph.nuevoNodo(i);
	}

	/**
	 * Metodo que devuelve la sala del tablero cuyo identificador se le pasa por
	 * parametro
	 * 
	 * @param idSala
	 *            identificador de la sala a devolver
	 * @return sala
	 */
	public Sala obtenerSala(int idSala) {

		return tablero[idSala / ancho][idSala % ancho];

	}

	/**
	 * Calcula el identificador de la esquina situada en el suroeste del tablero
	 * 
	 * @return esquina suroeste
	 */
	public int esquinaSuroeste() {
		return (ancho * alto) - ancho;
	}

	/**
	 * Calcula el identificador de la esquina situada en el noreste del tablero
	 * 
	 * @return esquina noreste
	 */
	public int esquinaNoreste() {
		return ancho - 1;
	}

	/**
	 * Metodo que distribuye el vector de armas en las diferentes salas
	 * correspondientes. Las armas se distribuyen de cinco en cinco
	 * 
	 * @param idSalasConArmas
	 *            vector que contiene los id de las salas a repartir las armas
	 * @param armasSala
	 *            vector que contiene las armas a repartir
	 */
	public void distribuirArmas(Sala[] idSalasConArmas, Arma[] armasSala) {
		int sala = 0;
		int posicionV = 0;

		while (sala < 12) {

			Sala aux = idSalasConArmas[sala];
			aux.distribuirArmasSala(armasSala, posicionV);
			sala++;
			posicionV = posicionV + 5;

		}
	}

	/**
	 * Metodo que inserta el hombre puerta en la sala dailyplanet
	 * 
	 * @param doorMan
	 */
	public void insertarHombrePuerta(HombrePuerta doorMan) {
		Sala aux = obtenerSala(dailyPlanet);
		aux.insertarHombrePuerta(doorMan);
	}

	/**
	 * Metodo que inserta un personaje en una sala
	 * 
	 * @param p
	 * @param sala
	 */
	public void insertarPersonaje(Personaje p, int sala) {
		Sala aux = obtenerSala(sala);
		aux.insertarPersonaje(p);
	}

	/**
	 * Metodo que borra el personaje de la sala
	 * 
	 * @param p
	 * @param sala
	 */
	public void borrarPersonaje(Personaje p, int sala) {
		Sala aux = obtenerSala(sala);
		aux.borrarPersonaje(p);
	}

	/**
	 * Metodo que inserta el personaje en la sala adicional
	 * 
	 * @param p
	 */
	public void insertarAdicional(Personaje p) {
		adicional.insertarPersonaje(p);
	}

	/**
	 * Metodo que devuelve el hombre puerta
	 * 
	 * @return hombrepuerta
	 */
	public HombrePuerta obtenerHombreP() {
		Sala aux = obtenerSala(dailyPlanet);
		return aux.getHP();
	}

	/**
	 * Metodo que comprueba si el portal está abierto o no
	 * 
	 * @return true si el portal está abierto y false en caso contrario
	 */
	public boolean portalAbierto() {
		HombrePuerta HP = obtenerHombreP();
		return HP.getPortal();
	}

	/**
	 * Metodo que almacena las paredes que existen en el mapa siguiente el
	 * orden: N, E, S, O
	 * 
	 * @return paredesMapa lista con el conjunto de paredes del mapa
	 */
	public ArrayList<Pared> almacenarParedes() {
		ArrayList<Pared> paredesMapa = new ArrayList<Pared>();

		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				if (i != 0) { // norte
					Pared norte = new Pared(tablero[i][j].getIdentificador(),
							tablero[i - 1][j].getIdentificador());
					paredesMapa.add(norte);
				}
				if (j != ancho - 1) { // este
					Pared este = new Pared(tablero[i][j].getIdentificador(),
							tablero[i][j + 1].getIdentificador());
					paredesMapa.add(este);
				}
				if (i != alto - 1) { // sur
					Pared sur = new Pared(tablero[i][j].getIdentificador(),
							tablero[i + 1][j].getIdentificador());
					paredesMapa.add(sur);
				}
				if (j != 0) { // oeste
					Pared oeste = new Pared(tablero[i][j].getIdentificador(),
							tablero[i][j - 1].getIdentificador());
					paredesMapa.add(oeste);
				}
			}
		}
		return paredesMapa;
	}

	/**
	 * Metodo que usa el algoritmo de kruskal. Va derribando paredes de forma
	 * aleatoria(NO forma atajos)
	 */
	public void kruskal() {
		ArrayList<Pared> paredes = almacenarParedes();

		while (!paredes.isEmpty()) {
			int paredDerribar = GenAleatorios.generarNumero(paredes.size());

			int idOr = paredes.get(paredDerribar).getOrigen();
			int idDs = paredes.get(paredDerribar).getDestino();

			int marcaOr = obtenerSala(idOr).getMarca();
			int marcaDs = obtenerSala(idDs).getMarca();

			if (marcaOr != marcaDs) {

				if (marcaOr > marcaDs) // se pone la misma marca en las salas
					marcasMapa(marcaDs, marcaOr);
				else
					marcasMapa(marcaOr, marcaDs);

				graph.nuevoArco(idDs, idOr, 1); // se tira la pared creando un
												// arco con el origen y el
												// destino
				graph.nuevoArco(idOr, idDs, 1);

			}
			paredes.remove(paredDerribar);
		}
	}

	/**
	 * Cambia las marcas de las salas para que tengan la misma
	 * 
	 * @param marca1
	 * @param marca2
	 */
	public void marcasMapa(int marca1, int marca2) {
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				if (tablero[i][j].getMarca() == marca1)
					tablero[i][j].setMarca(marca2);
			}
		}
	}

	/**
	 * Metodo que devuelve true si existe pared entre dos salas y false si no
	 * hay pared
	 * 
	 * @param sala1
	 * @param sala2
	 * @return true o false
	 */
	public boolean existePared(int sala1, int sala2) {
		if (graph.adyacente(sala1, sala2))
			return false;
		else
			return true;
	}

	/**
	 * Metodo que crea los atajos del mapa
	 * 
	 */
	public void atajos() {
		int paredesDerribar = (ancho * alto * 5) / 100;
		boolean procesada = false;

		while (paredesDerribar != 0) {
			int sala = GenAleatorios.generarNumero(ancho * alto);
			if (sala >= ancho) { // no esta en primera fila (norte)
				if (existePared(sala, sala - ancho) && !huecosVacios(sala, 1)) {
					graph.nuevoArco(sala - ancho, sala, 1);
					graph.nuevoArco(sala, sala - ancho, 1);
					procesada = true;
				}
			}
			if (sala < (ancho * alto) - ancho && !procesada) { // no esta en
																// ultima fila
																// (sur)
				if (existePared(sala, sala + ancho) && !huecosVacios(sala, 2)) {
					graph.nuevoArco(sala, sala + ancho, 1);
					graph.nuevoArco(sala + ancho, sala, 1);
					procesada = true;
				}
			}
			if (sala % ancho != 0 && !procesada) { // no esta en primera columna
													// (oeste)
				if (existePared(sala, sala - 1) && !huecosVacios(sala, 3)) {
					graph.nuevoArco(sala, sala - 1, 1);
					graph.nuevoArco(sala - 1, sala, 1);
					procesada = true;
				}
			}
			if ((sala + 1) % ancho != 0 && !procesada) { // no esta en ultima
															// columna (este)
				if (existePared(sala, sala + 1) && !huecosVacios(sala, 4)) {
					graph.nuevoArco(sala, sala + 1, 1);
					graph.nuevoArco(sala + 1, sala, 1);
					procesada = true;
				}
			}

			if (procesada) {
				paredesDerribar--;
				procesada = false;
			}
		}
	}

	/**
	 * Metodo que devuelve true si se forma huecos vacios y false en caso
	 * contrario
	 * 
	 * @param sala
	 * @param direccion
	 * @return true si forma hueco false si no
	 */
	public boolean huecosVacios(int sala, int direccion) {
		boolean hueco = false;
		int norte = sala - ancho;
		int sur = sala + ancho;
		int este = sala + 1;
		int oeste = sala - 1;

		switch (direccion) {
		case 1: // norte
			if (graph.adyacente(sala, oeste)
					&& graph.adyacente(oeste, oeste - ancho)
					&& graph.adyacente(oeste - ancho, norte))
				hueco = true;
			if (graph.adyacente(sala, este)
					&& graph.adyacente(este, este - ancho)
					&& graph.adyacente(norte, este - ancho))
				hueco = true;
			break;

		case 2: // sur
			if (graph.adyacente(sala, oeste)
					&& graph.adyacente(oeste, oeste + ancho)
					&& graph.adyacente(sur, oeste + ancho))
				hueco = true;
			if (graph.adyacente(sala, este)
					&& graph.adyacente(este, este + ancho)
					&& graph.adyacente(sur, este + ancho))
				hueco = true;
			break;

		case 3: // oeste
			if (graph.adyacente(sala, norte)
					&& graph.adyacente(norte, oeste - ancho)
					&& graph.adyacente(oeste, oeste - ancho))
				hueco = true;
			if (graph.adyacente(sala, sur)
					&& graph.adyacente(sur, oeste + ancho)
					&& graph.adyacente(oeste, oeste + ancho))
				hueco = true;
			break;

		case 4: // este
			if (graph.adyacente(sala, norte)
					&& graph.adyacente(norte, norte + 1)
					&& graph.adyacente(este, norte + 1))
				hueco = true;
			if (graph.adyacente(sala, sur) && graph.adyacente(sur, sur + 1)
					&& graph.adyacente(sur + 1, este))
				hueco = true;
			break;
		}

		return hueco;
	}

	
	/**
	 * Metodo que crea todas las rutas desde la sala 0 hasta la sala del hombre puerta
	 * 
	 */
	public void crearRutas() {
		graph.recorridoProfundidad(0, dailyPlanet, caminosSala);
	}

	/**
	 * 
	 * Metodo que cuenta la frecuencia de las salas de los caminos del tablero y devuelve aquellos que son más visitados en un vector
	 * 
	 * @param caminos
	 * @return arraya con las salas mas frecuentadas
	 */
	public Sala[] vectorSalasArma(LinkedList<LinkedList<Integer>> caminos) {
		LinkedList<Sala> salas = new LinkedList<Sala>();
		Sala[] salasV = new Sala[12];
		
		for (int i = 0; i < caminos.size(); i++) {
			LinkedList<Integer> ruta = caminos.get(i); //cogemos primer camino
			for (int j = 0; j < ruta.size(); j++) {
				Sala aux = obtenerSala(ruta.get(j)); 
				if (!salas.contains(aux)) {
					salas.add(aux); //añadimos las salas
				}
				aux.setFrecuencia(aux.getFrecuencia() + 1);
			}
		} 
		Collections.sort(salas); //ordenamos por frecuencia
		salasV = salas.toArray(salasV); //pasamos la lista a array
		
		return salasV;
	}

	/**
	 * Devuelve el camino mas corto entre una sala y otra (ruta volador)
	 * 
	 * @param origen
	 * @param destino
	 * @return el camino mas corto
	 */
	public LinkedList<Integer> caminoMasCorto(int origen, int destino){
		return graph.caminoMinimo(origen, destino);
	}
	
	/**
	 * Devuelve el camino con los adyacentes mas bajos entre el origen y el destino (ruta fisicos)
	 * 
	 * @param origen
	 * @param destino
	 * @return el camino con id mas bajos
	 */
	public LinkedList<Integer> caminoIdMinimo(int origen, int destino){
		LinkedList<Integer> ruta = new LinkedList<Integer>();
		graph.caminoSalaMinima(origen, destino, ruta);
		
		return ruta;
	}
	
	/**
	 * Metodo "principal". Prepara el juego con todos los datos para su correcta
	 * simulacion
	 * 
	 * 
	 * @return string
	 */
	public String crearJuego() {
		String s = " ";
		crearGrafo();
		kruskal(); // mapa generado sin atajos
		s = pintarMapa(); // guarda el mapa sin atajos

		atajos(); // crea los atajos del mapa
		graph.floyd();
		graph.warshall();
		crearRutas();

		// ARMAS EN SALA
		// Creacion de las armas para repartir en salas
		Arma[] armasSalas = { new Arma("Mjolnir", 29), new Arma("Anillo", 1),
				new Arma("Garra", 27), new Arma("Armadura", 3),
				new Arma("Red", 25), new Arma("Escudo", 5),
				new Arma("Lucille", 23), new Arma("Lawgiver", 7),
				new Arma("GuanteInfinito", 21), new Arma("LazoVerdad", 9),
				new Arma("CadenaFuego", 19), new Arma("Capa", 11),
				new Arma("Flecha", 17), new Arma("Tridente", 13),
				new Arma("Antorcha", 15), new Arma("Baston", 28),
				new Arma("Latigo", 2), new Arma("MazaOro", 26),
				new Arma("CampoMagnetico", 4), new Arma("Tentaculo", 24),
				new Arma("CampoEnergia", 6), new Arma("Cetro", 22),
				new Arma("RayoEnergia", 8), new Arma("Laser", 20),
				new Arma("Bola", 10), new Arma("Espada", 18),
				new Arma("Sable", 12), new Arma("Acido", 16),
				new Arma("Gema", 14), new Arma("Nullifier", 23),
				new Arma("Mjolnir", 1), new Arma("Anillo", 29),
				new Arma("Garra", 3), new Arma("Armadura", 27),
				new Arma("Red", 5), new Arma("Escudo", 25),
				new Arma("Lucille", 7), new Arma("Lawgiver", 23),
				new Arma("GuanteInfinito", 9), new Arma("LazoVerdad", 21),
				new Arma("CadenaFuego", 11), new Arma("Capa", 19),
				new Arma("Flecha", 13), new Arma("Tridente", 17),
				new Arma("Antorcha", 28), new Arma("Baston", 15),
				new Arma("Latigo", 26), new Arma("MazaOro", 2),
				new Arma("CampoMagnetico", 24), new Arma("Tentaculo", 4),
				new Arma("CampoEnergia", 22), new Arma("Cetro", 6),
				new Arma("RayoEnergia", 20), new Arma("Laser", 8),
				new Arma("Bola", 18), new Arma("Espada", 10),
				new Arma("Sable", 16), new Arma("Acido", 12),
				new Arma("Gema", 1), new Arma("Nullifier", 3) };

		Sala[] idSalasConArmas = vectorSalasArma(caminosSala);
	    distribuirArmas(idSalasConArmas, armasSalas);

		// HOMBRE PUERTA
		HombrePuerta doorMan = new HombrePuerta(altura);
		// Creacion de las armas para el hombre puerta
		Arma[] armasPuerta = { new Arma("CampoEnergia", 5),
				new Arma("Armadura", 13), new Arma("Anillo", 11),
				new Arma("Acido", 1), new Arma("Antorcha", 5),
				new Arma("Bola", 3), new Arma("Baston", 22),
				new Arma("CadenaFuego", 11), new Arma("Espada", 11),
				new Arma("Cetro", 20), new Arma("Capa", 10),
				new Arma("CampoMagnetico", 5), new Arma("Escudo", 3),
				new Arma("Garra", 22), new Arma("Flecha", 12),
				new Arma("Gema", 4) };

		// Creacion del hombre puerta y configuracion

		doorMan.configurar(armasPuerta);
		// cerrar portal por si estuviera abierto
		doorMan.setPortal(false);
		insertarHombrePuerta(doorMan);

		return s;
	}

	/**
	 * Metodo que realiza la simulacion del juego
	 */
	public boolean simulacion(int turnoSimulacion) {
		boolean end = false;

		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				tablero[i][j].procesarSala(turnoSimulacion);

			}
		}
		if (portalAbierto()) {
			end = true;
		}

		return end;

	}

	/**
	 * Método creado para el control de errores del juego
	 * 
	 * @param dimx
	 * @param dimy
	 * @throws ExceptionJuego
	 */
	public void errorMapa(int dimx, int dimy) throws ExceptionJuego {
		if (dimx <= 0 || dimy <= 0)
			throw new ExceptionJuego("Dimensiones no aceptadas");
	}

	// -------------------------------METODOS MOSTRAR---------------------------

	/**
	 * Metodo que llama a todos los metodos pintar
	 * 
	 * @throws IOException
	 * 
	 */
	public void pintarTodo(BufferedWriter escrito) throws IOException {
		String s = "(map:" + dailyPlanet + ")\n";
		System.out.println("(map:" + dailyPlanet + ")");
		s = s + mostrarPuerta();
		s = s + pintarMapa();
		s = s + mostrarArmas();
		s = s + mostrarPersonaje();

		escrito.write(s);
	}

	/**
	 * Metodo que muestra los personajes del tablero
	 * 
	 * @return string
	 */
	public String mostrarPersonaje() {
		String s = "";
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				if (tablero[i][j].tienePersonajes()) {
					LinkedList<Personaje> personajes = tablero[i][j]
							.getPersonajes();
					for (int k = 0; k < personajes.size(); k++) {
						s = s + personajes.get(k).mostrarPersonaje();
					}
				}
			}
		}
		return s;
	}

	/**
	 * 
	 * Metodo que muestra las rutas de los personajes
	 * 
	 * @param escrito
	 * @throws IOException
	 */
	public void mostrarRutasPersonajes(BufferedWriter escrito)
			throws IOException {
		String s = "";
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				if (tablero[i][j].tienePersonajes()) {
					LinkedList<Personaje> personajes = tablero[i][j]
							.getPersonajes();
					for (int k = 0; k < personajes.size(); k++) {
						s = s + personajes.get(k).mostrarRuta();
					}
				}
			}
		}
		escrito.write(s);
	}

	/**
	 * Metodo que muestra el portal del mapa
	 * 
	 */
	public String mostrarPuerta() {
		String s = "";

		HombrePuerta HP = obtenerHombreP();
		s = "(doorman:";
		System.out.print("(doorman:");
		s = s + HP.mostrarHombreP();
		s = s + ")\n";
		System.out.println(")");
		return s;

	}

	/**
	 * Metodo que muestra las armas del tablero por parametros
	 * 
	 */
	public String mostrarArmas() {
		String s = "";
		for (int i = 0; i < alto; i++) {
			for (int j = 0; j < ancho; j++) {
				if (tablero[i][j].tieneArmas()) {
					s = s + "(square:" + tablero[i][j].getIdentificador() + ":";
					System.out.print("(square:"
							+ tablero[i][j].getIdentificador() + ":");
					s = s + tablero[i][j].mostrarArmas();
					s = s + ")\n";
					System.out.println(")");
				}
			}
		}
		return s;
	}

	/**
	 * Metodo que muestra los personajes que estan en la sala adicional
	 * 
	 * @return s
	 */
	public String mostrarPersonajesAdd() {
		String s = "(teseractomembers)\n";
		System.out.println("(teseractomembers)");
		LinkedList<Personaje> aux = adicional.getPersonajes();
		for (int i = 0; i < aux.size(); i++) {
			if (i == 0) {
				s = s + ("(owneroftheworld:");
				System.out.print("(owneroftheworld:");
			}
			s = s + aux.get(i).mostrarPersonajeAdd(i);
		}
		return s;
	}

	/**
	 * Método que muestra el mapa por pantalla
	 */
	public String pintarMapa() {
		String s = "";
		for (int i = -1; i < alto; i++) {
			if (i != -1) {
				s = s + "|";
				System.out.print("|");
			}
			for (int j = 0; j < ancho; j++) {

				if (i == -1) {
					s = s + " _";
					System.out.print(" _");
				} else {
					if (tablero[i][j].tienePersonajes()) {
						if (tablero[i][j].numPersonajes() == 1) {
							s = s
									+ tablero[i][j].getPersonajes().getFirst()
											.getMarca();
							System.out.print(tablero[i][j].getPersonajes()
									.getFirst().getMarca());
						} else {
							s = s + tablero[i][j].numPersonajes();
							System.out.print(tablero[i][j].numPersonajes());
						}
					} else {
						if (i >= alto - 1
								|| !graph.adyacente(
										tablero[i][j].getIdentificador(),
										tablero[i + 1][j].getIdentificador())) {
							s = s + "_";
							System.out.print("_");
						} else {
							s = s + " ";
							System.out.print(" ");
						}
					}

					if (j >= ancho - 1
							|| !graph.adyacente(
									tablero[i][j].getIdentificador(),
									tablero[i][j + 1].getIdentificador())) {
						s = s + "|";
						System.out.print("|");
					} else {
						s = s + " ";
						System.out.print(" ");
					}

				}
			}
			s = s + "\n";
			System.out.println();
		}
		return s;
	}
}
