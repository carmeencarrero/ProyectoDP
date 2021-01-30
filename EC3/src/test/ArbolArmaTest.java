package test;

import static org.junit.Assert.*;
import juego.ArbolArma;
import juego.Arma;

import org.junit.Test;

import estructurasDatos.Arbol;

public class ArbolArmaTest {

	@Test
	public void testDatoMayor() {
		
		Arbol <Arma> aux = new Arbol<Arma>();
		Arma a1 = new Arma("L", 20); //creamos distintas aux con distinto poder
		Arma a2 = new Arma("O", 40);
		Arma a3 = new Arma("R", 130); //la mayor es D, 150
		Arma a4 = new Arma("D", 150);
		Arma a5 = new Arma("H", 100);
		Arma a6 = new Arma("A", 20);
		aux.insertar(a1);
		aux.insertar(a2);
		aux.insertar(a4);
		aux.insertar(a3);
		aux.insertar(a5);
		aux.insertar(a6);
	
		
	Arma prueba = ArbolArma.datoMayor(aux);
		
		assertEquals(a4, prueba); //comprueba si los objetos son iguales
		
		Arma a7 = new Arma("B", 200); //insertamos dos armas con el mismo poder
		Arma a8 = new Arma("C", 200); //el poder mayor ahora es 200
		aux.insertar(a8);
		aux.insertar(a7);
		
		prueba = ArbolArma.datoMayor(aux);
		assertEquals(a7, prueba); //en caso de empate se elige por orden alfabetico
		
	}

}
