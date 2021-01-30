package estructurasDatos;

/**
 * Implementacion de arbol binario de busqueda.
 * 
 * @version 3.0
 * @author Asignatura Desarrollo de Programas<br/>
 *         <b> Profesores DP </b><br>
 *         Curso 14/15
 */
public class Arbol<T extends Comparable<T>> {

	/** Dato almacenado en cada nodo del árbol. */
	private T datoRaiz;

	/** Atributo que indica si el árbol está vacío. */
	boolean esVacio;

	/** Hijo izquierdo del nodo actual */
	private Arbol<T> hIzq;

	/** Hijo derecho del nodo actual */
	private Arbol<T> hDer;

	/**
	 * Constructor por defecto de la clase. Inicializa un árbol vacío.
	 */
	public Arbol() {
		this.esVacio = true;
		this.hIzq = null;
		this.hDer = null;
	}

	/**
	 * Crea un nuevo árbol a partir de los datos pasados por parámetro.
	 * 
	 * @param hIzq
	 *            El hijo izquierdo del árbol que se está creando
	 * @param datoRaiz
	 *            Raíz del árbol que se está creando
	 * @param hDer
	 *            El hijo derecho del árbol que se está creando
	 */
	public Arbol(Arbol<T> hIzq, T datoRaiz, Arbol<T> hDer) {
		this.esVacio = false;
		this.datoRaiz = datoRaiz;
		this.hIzq = hIzq;
		this.hDer = hDer;
	}

	/**
	 * Devuelve el hijo izquierdo del árbol
	 * 
	 * @return El hijo izquierdo
	 */
	public Arbol<T> getHijoIzq() {
		return hIzq;
	}

	/**
	 * Devuelve el hijo derecho del árbol
	 * 
	 * @return Hijo derecho del árbol
	 */
	public Arbol<T> getHijoDer() {
		return hDer;
	}

	/**
	 * Devuelve la raíz del árbol
	 * 
	 * @return La raíz del árbol
	 */
	public T getRaiz() {
		return datoRaiz;
	}

	/**
	 * Comprueba si el árbol está vacío.
	 * 
	 * @return verdadero si el árbol está vacío, falso en caso contrario
	 */
	public boolean vacio() {
		return esVacio;
	}

	/**
	 * Inserta un nuevo dato en el árbol.
	 * 
	 * @param dato
	 *            El dato a insertar
	 * @return verdadero si el dato se ha insertado correctamente, falso en caso
	 *         contrario
	 */
	public boolean insertar(T dato) {
		boolean resultado = true;
		if (vacio()) {
			datoRaiz = dato;
			esVacio = false;
		} else {
			if (!(this.datoRaiz.equals(dato))) {
				Arbol<T> aux;
				if (dato.compareTo(this.datoRaiz) < 0) { // dato < datoRaiz
					if ((aux = getHijoIzq()) == null)
						hIzq = aux = new Arbol<T>();
				} else { // dato > datoRaiz
					if ((aux = getHijoDer()) == null)
						hDer = aux = new Arbol<T>();
				}
				resultado = aux.insertar(dato);
			} else
				resultado = false;
		}
		return resultado;
	}

	/**
	 * Comprueba si un dato se encuentra almacenado en el árbol
	 * 
	 * @param dato
	 *            El dato a buscar
	 * @return verdadero si el dato se encuentra en el árbol, falso en caso
	 *         contrario
	 */
	public boolean pertenece(T dato) {
		Arbol<T> aux = null;
		boolean encontrado = false;
		if (!vacio()) {
			if (this.datoRaiz.equals(dato))
				encontrado = true;
			else {
				if (dato.compareTo(this.datoRaiz) < 0) // dato < datoRaiz
					aux = getHijoIzq();
				else
					// dato > datoRaiz
					aux = getHijoDer();
				if (aux != null)
					encontrado = aux.pertenece(dato);
			}
		}
		return encontrado;
	}

	/**
	 * 
	 * Metodo que devuelve el nodo del arbol que es mas grande
	 * 
	 * @return Devuelve el dato mayor del arbol
	 */
	public T datoMayor() {
		Arbol<T> aux = null;
		T datoMayor = null;
		if (!vacio()) {
			datoMayor = this.getRaiz();
			aux = this.getHijoDer();
			if (aux != null) {
				datoMayor = aux.datoMayor();
			}
		}

		return datoMayor;
	}

	/**
	 * Borrar un dato del árbol.
	 * 
	 * @param dato
	 *            El dato que se quiere borrar
	 */
	public void borrar(T dato) {
		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) { // dato<datoRaiz
				if (hIzq != null)
					hIzq = hIzq.borrarOrden(dato);

			} else if (dato.compareTo(this.datoRaiz) > 0) { // dato>datoRaiz
				if (hDer != null)
					hDer = hDer.borrarOrden(dato);

			} else // En este caso el dato es datoRaiz
			{
				if (hIzq == null && hDer == null) {
					esVacio = true;
				} else
					borrarOrden(dato);
			}
		}
	}

	/**
	 * Borrar un dato. Este método es utilizado por el método borrar anterior.
	 * 
	 * @param dato
	 *            El dato a borrar
	 * @return Devuelve el árbol resultante después de haber realizado el
	 *         borrado
	 */
	private Arbol<T> borrarOrden(T dato) {
		T datoaux;
		Arbol<T> retorno = this;
		Arbol<T> aborrar, candidato, antecesor;

		if (!vacio()) {
			if (dato.compareTo(this.datoRaiz) < 0) { // dato<datoRaiz
				if (hIzq != null)
					hIzq = hIzq.borrarOrden(dato);
			} else if (dato.compareTo(this.datoRaiz) > 0) { // dato>datoRaiz
				if (hDer != null)
					hDer = hDer.borrarOrden(dato);
			} else {
				aborrar = this;
				if ((hDer == null) && (hIzq == null)) { /* si es hoja */
					retorno = null;
				} else {
					if (hDer == null) { /* Solo hijo izquierdo */
						aborrar = hIzq;
						datoaux = this.datoRaiz;
						datoRaiz = hIzq.getRaiz();
						hIzq.datoRaiz = datoaux;
						hIzq = hIzq.getHijoIzq();
						hDer = aborrar.getHijoDer();

						retorno = this;
					} else if (hIzq == null) { /* Solo hijo derecho */
						aborrar = hDer;
						datoaux = datoRaiz;
						datoRaiz = hDer.getRaiz();
						hDer.datoRaiz = datoaux;
						hDer = hDer.getHijoDer();
						hIzq = aborrar.getHijoIzq();

						retorno = this;
					} else { /* Tiene dos hijos */
						candidato = this.getHijoIzq();
						antecesor = this;
						while (candidato.getHijoDer() != null) {
							antecesor = candidato;
							candidato = candidato.getHijoDer();
						}

						/* Intercambio de datos de candidato */
						datoaux = datoRaiz;
						datoRaiz = candidato.getRaiz();
						candidato.datoRaiz = datoaux;
						aborrar = candidato;
						if (antecesor == this)
							hIzq = candidato.getHijoIzq();
						else
							antecesor.hDer = candidato.getHijoIzq();
					} // Eliminar solo ese nodo, no todo el subarbol
					aborrar.hIzq = null;
					aborrar.hDer = null;
				}
			}
		}
		return retorno;
	}

	/**
	 * Recorrido inOrden del árbol.
	 */
	public void inOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				aux.inOrden();
			}

			System.out.println(this.datoRaiz);

			if ((aux = getHijoDer()) != null) {
				aux.inOrden();
			}
		}
	}

	/**
	 * Recorrido preOrden del árbol.
	 */
	public void preOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {

			System.out.println(this.datoRaiz);

			if ((aux = getHijoIzq()) != null) {
				aux.inOrden();
			}

			if ((aux = getHijoDer()) != null) {
				aux.inOrden();
			}
		}
	}

	/**
	 * Recorrido postOrden del árbol.
	 */
	public void postOrden() {
		Arbol<T> aux = null;
		if (!vacio()) {

			if ((aux = getHijoIzq()) != null) {
				aux.inOrden();
			}

			if ((aux = getHijoDer()) != null) {
				aux.inOrden();
			}

			System.out.println(this.datoRaiz);
		}
	}

	/**
	 * Método que calcula la profundidad del árbol.
	 * 
	 * @return Devuelve la profundidad del árbol dado
	 */
	public int profundidad() {
		int profundidad = 0;
		int proD = 0, proI = 0;
		Arbol<T> aux = null;

		if (!vacio()) {

			proD = 1;
			proI = 1;

			// profundidad izquierda
			aux = getHijoIzq();
			if (aux != null) {
				proI = aux.profundidad() + 1;
			}

			// profundidad derecha
			aux = getHijoDer();
			if (aux != null) {
				proD = aux.profundidad() + 1;
			}
		}
		if (proD > proI)
			profundidad = proD;
		else
			profundidad = proI;

		return profundidad;
	}

	/**
	 * Método que dice si un elemento es nodo hoja o no
	 * 
	 * @param dato
	 *            El dato a comprobar
	 * @result Devuelve true si el dato es un nodo hoja y false en caso
	 *         contrario
	 */
	public boolean estaEnNodoHoja(T dato) {
		boolean nodoHoja = false;
		Arbol<T> aux = null;

		if (!vacio()) {

			if (this.datoRaiz.equals(dato)) {
				if (this.getHijoIzq() == null && this.getHijoDer() == null)
					nodoHoja = true;

			} else {

				if (dato.compareTo(this.datoRaiz) < 0) { // dato < datoRaiz
					aux = getHijoIzq();
					if (aux != null)
						nodoHoja = aux.estaEnNodoHoja(dato);

				}
				if (dato.compareTo(this.datoRaiz) > 0) { // dato > datoRaiz
					aux = getHijoDer();
					if (aux != null)
						nodoHoja = aux.estaEnNodoHoja(dato);

				}

			}
		}

		return nodoHoja;
	}

	/**
	 * Método que calcula cuántos nodos hoja tiene el árbol
	 * 
	 * @return Devuelve el número de nodos hoja
	 */
	public int nodosHoja() {
		int hojas = 0;
		Arbol<T> aux = null;

		if (!vacio()) {
			if ((aux = getHijoIzq()) != null) {
				hojas = hojas + aux.nodosHoja();
			}
			if (getHijoDer() == null && getHijoIzq() == null) {
				hojas++;
			}
			if ((aux = getHijoDer()) != null) {
				hojas = hojas + aux.nodosHoja();
			}
		}
		return hojas;

	}

	/**
	 * Comprueba si un dato se encuentra almacenado en el árbol y ademas lo
	 * devuelve
	 * 
	 * @param dato
	 *            el dato a buscar
	 * @return el dato que se ha buscado en el arbol y null si no lo encuentra
	 */
	public T pertenece2(T dato) {
		Arbol<T> aux = null;
		T devolver = null;

		if (!vacio()) {
			if (dato != null) {
				if (this.datoRaiz.equals(dato))
					return datoRaiz;
				else {
					if (dato.compareTo(this.datoRaiz) < 0) // dato < datoRaiz
						aux = getHijoIzq();
					else
						// dato > datoRaiz
						aux = getHijoDer();
					if (aux != null)
						devolver = aux.pertenece2(dato);
				}
			}
		}
		return devolver;
	}
	

	/**
	 * Método que main que realiza las pruebas con el árbol.
	 * 
	 * @param args
	 *            Argumentos del main
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Arbol<Integer> arbol = new Arbol<Integer>();
		System.out.println("Ejemplos sesion árbol binario de búsqueda");

		Integer[] datos = { new Integer(20), new Integer(7), new Integer(18),
				new Integer(6), new Integer(5), new Integer(1), new Integer(22) };

		for (int i = 0; i < datos.length; i++) {
			arbol.insertar(datos[i]);
		}

		System.out.println("El árbol tiene " + arbol.nodosHoja()
				+ " nodos hoja");

		if (arbol.estaEnNodoHoja(new Integer(1)))
			System.out.println("El dato 1 es hoja ");
		else
			System.out.println("El dato 1 NO es hoja ");

		System.out
				.println("La profundidad del árbol es " + arbol.profundidad());

		// Insertando datos repetidos
		if (arbol.insertar(new Integer(22)) == false)
			System.out.println("El ABB no admite elementos duplicados");

		// Pertenencia de un dato
		if (arbol.pertenece(new Integer(22)))
			System.out.println("Pertenece");
		else
			System.out.println("NO Pertenece");

		// Recorrido en inOrden
		System.out.println("InOrden");
		arbol.inOrden();

		// // Probando el borrado de diferentes datos -- Descomentar estas
		// líneas
		// // para ver qué ocurre
		// arbol.borrar(new T(20));
		// System.out.println("Borrado " + 20);
		// arbol.borrar(new T(15));
		// System.out.println("Borrado " + 15);

		// Borrando datos del árbol
		for (int i = 0; i < datos.length; i++) {
			arbol.borrar(datos[i]);
			System.out.println("Borrado " + datos[i]);
			arbol.inOrden();
		}

		System.out.println("El árbol tiene " + arbol.nodosHoja()
				+ " nodos hoja");

		if (arbol.estaEnNodoHoja(new Integer(1)))
			System.out.println("El dato 1 es hoja ");
		else
			System.out.println("El dato 1 NO es hoja ");
	}
}
