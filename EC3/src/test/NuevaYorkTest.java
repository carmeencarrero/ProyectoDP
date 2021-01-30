package test;

import static org.junit.Assert.*;

import juego.NuevaYork;
import juego.Sala;

import org.junit.BeforeClass;
import org.junit.Test;

public class NuevaYorkTest {

	private static NuevaYork NY;
	
	@BeforeClass
	public static void testGetInstancia() {
		NY = NuevaYork.getInstancia(35, 6, 6, 4);
		
		NY.crearJuego();
	}

	@Test
	public void testGetAncho() {
		//en este metodo se prueban el get y el set ancho
		
		assertEquals(6, NY.getAncho());
		NY.setAncho(10);
		assertEquals(10, NY.getAncho());
	}

	@Test
	public void testGetAlto() {
		//en este metodo se prueban el get y el set alto
		
		assertEquals(6, NY.getAlto());
		NY.setAlto(10);
		assertEquals(10, NY.getAlto());
	}

	@Test
	public void testGetDailyPlanet() {
		
		//en este metodo se pruban el get y el set daily planet
		assertEquals(35, NY.getDailyPlanet());
		NY.setDailyPlanet(59);
		assertEquals(59, NY.getDailyPlanet());
	}

	@Test
	public void testGetAltura() {
		//en este metodo se pruban el get y el set altura
		assertEquals(4, NY.getAltura());
		NY.setAltura(5);
		assertEquals(5, NY.getAltura());
	}

	@Test
	public void testObtenerSala() {
		Sala square = NY.obtenerSala(0);
		
		assertEquals(square.getIdentificador(), 0);
	}

}
