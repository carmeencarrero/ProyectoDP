package test;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;
import personajes.PoderFisico;

public class PersonajeTest {

	private static PoderFisico p1;
	
	@BeforeClass
	public static void inicioTest() {
		p1 = new PoderFisico("Kili", 'K', 1, 0);
	}

	@Test
	public void testGetNombre() {
		//se comprueba el get y el set nombre
		
		assertEquals("Kili", p1.getNombre());
		p1.setNombre("MM");
		assertEquals("MM", p1.getNombre());
	}

	@Test
	public void testGetMarca() {
		//se comprueba el get y el set marca
		
		assertEquals('K', p1.getMarca());
		p1.setMarca('C');
		assertEquals('C', p1.getMarca());
	}

	@Test
	public void testGetTurno() {
		//se comprueba el get y el set turno
		
		assertEquals(1, p1.getTurno());
		p1.setTurno(2);
		assertEquals(2, p1.getTurno());
	}

	@Test
	public void testGetSala() {
		
		assertEquals(0, p1.getSala());
	}


	@Test
	public void testGetMovido() {
		
		assertFalse(p1.getMovido());
	}

}
