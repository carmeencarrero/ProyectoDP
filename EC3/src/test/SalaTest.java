package test;

import static org.junit.Assert.*;

import java.util.LinkedList;

import juego.Arma;
import juego.HombrePuerta;
import juego.Sala;

import org.junit.BeforeClass;
import org.junit.Test;

import personajes.Personaje;
import personajes.PoderFisico;
import personajes.Villano;

public class SalaTest {

	private static Sala salita;
	
	@BeforeClass
	public static void inicioSala() {
		salita = new Sala(10);
		PoderFisico p1 = new PoderFisico("Spiderman", 'S', 0, 10);
		Villano p2 = new Villano("Malefica", 'M', 0, 10);
		salita.insertarPersonaje(p1);
		salita.insertarPersonaje(p2);
	}

	@Test
	public void testGetIdentificador() {
		//en este metodo se comprueba get y set identificador
		
		assertEquals(10, salita.getIdentificador());
		salita.setIdentificador(0);
		assertEquals(0, salita.getIdentificador());
		
	}

	@Test
	public void testGetFrecuencia() {
		//en este metodo se comprueba get y set frecuencia
		
		assertEquals(0, salita.getFrecuencia());
		salita.setFrecuencia(15);
		assertEquals(15, salita.getFrecuencia());
		
	}

	@Test
	public void testGetPersonajes() {
		PoderFisico p1 = new PoderFisico("Spiderman", 'S', 0, 10);
		Villano p2 = new Villano("Malefica", 'M', 0, 10);
		
		LinkedList <Personaje> personajes = salita.getPersonajes();
		assertEquals(personajes.getFirst(), p1);
		
		assertEquals(personajes.getLast(), p2);
	}

	@Test
	public void testGetMarca() {
		//en este metodo se comprueba get y set marca
		
		assertEquals(10, salita.getMarca());
		salita.setMarca(23);
		assertEquals(23, salita.getMarca());
	}

	@Test
	public void testInsertarArma() {
		//tambien se comprueba el metodo get armas
		
		Arma a1 = new Arma ("Pistola", 10);
		Arma a2 = new Arma ("Gema", 100);
		
		salita.insertarArma(a1);
		salita.insertarArma(a2);
		
	LinkedList<Arma> armas = salita.getArmas();
	//como a2 es mas grande deberia ir la primera
	
	assertEquals(a2, armas.getFirst());
	}

	@Test
	public void testGetHP() {
		//como hay hombre puerta el resultado no es null
		
		HombrePuerta HP = new HombrePuerta(5);
		salita.insertarHombrePuerta(HP);
		assertNotNull(salita.getHP());
	}

	@Test
	public void testTieneArmas() {
		
		Arma a1 = new Arma ("Botella", 10);
		Arma a2 = new Arma ("Zafiro", 28);
		
		salita.insertarArma(a1);
		salita.insertarArma(a2);
		//la sala tiene armas
		
		assertTrue(salita.tieneArmas());
		
	}

	@Test
	public void testTienePersonajes() {
		
		PoderFisico p1 = new PoderFisico("Spiderman", 'S', 0, 10);
		Villano p2 = new Villano("Malefica", 'M', 0, 10);
		salita.borrarPersonaje(p1);
		salita.borrarPersonaje(p2);
		
		assertFalse(salita.tienePersonajes());
		
		salita.insertarPersonaje(p1);
		salita.insertarPersonaje(p2);
		assertTrue(salita.tienePersonajes());

	}

	@Test
	public void testNumPersonajes() {
		
		//la sala tiene dos personajes (p1 y p2)
		assertEquals(2, salita.numPersonajes());
	}

	@Test
	public void testInsertarHombrePuerta() {
		HombrePuerta HP = new HombrePuerta(5);
		salita.insertarHombrePuerta(HP);
		
		assertNotNull(salita.getHP());
	}

	@Test
	public void testSalaPuerta() {
		
		HombrePuerta HP = new HombrePuerta(5);
		salita.insertarHombrePuerta(HP);
		assertTrue(salita.salaPuerta());
		
	}

	@Test
	public void testDistribuirArmasSala() {
		Arma[] armasSala = { new Arma("Mjolnir", 29), new Arma("Anillo", 1),
				new Arma("Garra", 27), new Arma("Armadura", 3),
				new Arma("Red", 25) };
		
		salita.distribuirArmasSala(armasSala, 0);
		
		assertTrue(salita.tieneArmas());
	}

	@Test
	public void testRecogerArma() {
	
		Arma a1 = new Arma("Gema", 100);
		salita.insertarArma(a1);
		
		Arma prueba = salita.recogerArma();
		
		assertEquals(prueba, a1);
	}


}
