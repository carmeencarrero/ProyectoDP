package juego;

import java.util.Comparator;

/**
 * Implementación de los métodos de la clase CompararArma
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 *
 */
public class CompararArma implements Comparator<Arma>{

	//comparador de enteros por poder
	public int compare(Arma o1, Arma o2){
		Arma a1 =  o1;
		Arma a2 =  o2;
		if (a1.getPoder() < a2.getPoder()){
			return 0;
		}
		else if (a1.getPoder()>a2.getPoder()){
			return -1;
		}
			return 1;
	}
}
