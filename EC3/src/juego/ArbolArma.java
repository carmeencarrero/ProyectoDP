package juego;

import estructurasDatos.Arbol;


/**
 * Implementación de los métodos de la clase ArbolArma
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 *
 */
public class ArbolArma {

	/**
	 * 
	 * Metodo privado que devuelve el arma que tiene mayor poder del arbol
	 * pasado por parametro
	 * 
	 * @param ABB
	 *            arbol del que hay que sacar el nodo con mayor poder
	 * @param devolver
	 *            arma a devolver con el mayor poder
	 * 
	 * @return Devuelve el arma que tenga mayor poder
	 */
	private static Arma datoMayor(Arbol<Arma> ABB, Arma devolver) {

		Arbol<Arma> aux = new Arbol<Arma>();

		if (ABB != null) {
			if (ABB.getRaiz().getPoder() > devolver.getPoder()) {
				devolver = ABB.getRaiz();
			} else if (ABB.getRaiz().getPoder() == devolver.getPoder()) {
				if (ABB.getRaiz().getNombre().compareTo(devolver.getNombre()) < 0) {
					devolver = ABB.getRaiz();
				}
			}
			if (!ABB.vacio()) {

				aux = ABB.getHijoIzq();

				if (aux != null) {
					devolver = datoMayor(aux, devolver);
				}

				aux = ABB.getHijoDer();
				if (aux != null) {
					devolver = datoMayor(aux, devolver);
				}
			}
		}

		return devolver;
	}

	/**
	 * Metodo publico que llama al privado. Devuelve el arma que tiene mayor
	 * poder del arbol
	 * 
	 * @param ABB
	 * @return el arma con mayor poder
	 */
	public static Arma datoMayor(Arbol<Arma> ABB) {
		Arma mayor = new Arma("", -1); // -1 por si algun arma tiene el poder 0

		mayor = datoMayor(ABB, mayor);
		return mayor;
	}

	/**
	 * 
	 * Metodo mostrar arbol
	 * 
	 * @param armasS
	 * @return String
	 */
	public static String mostrarArbol(Arbol<Arma> armasS) {

		Arbol<Arma> aux = new Arbol<Arma>();
		String s = "";

		if (!armasS.vacio()) {

			if ((aux = armasS.getHijoIzq()) != null) {
				s = s + mostrarArbol(aux);
			}

			s = s + "(" + armasS.getRaiz().getNombre() + ","
					+ armasS.getRaiz().getPoder() + ")";
			System.out.print("(" + armasS.getRaiz().getNombre() + ","
					+ armasS.getRaiz().getPoder() + ")");

			if ((aux = armasS.getHijoDer()) != null) {
				s = s + mostrarArbol(aux);
			}
		}
		return s;
	}

}
