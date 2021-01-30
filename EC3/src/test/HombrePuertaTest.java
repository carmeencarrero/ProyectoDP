package test;

import static org.junit.Assert.*;
import juego.Arma;
import juego.HombrePuerta;

import org.junit.BeforeClass;
import org.junit.Test;

import estructurasDatos.Arbol;

public class HombrePuertaTest {

	private static HombrePuerta HP;

	@BeforeClass
	public static void inicioTest() {
		HP = new HombrePuerta(4);
		Arma[] armasPuerta = { new Arma("Mjolnir", 29), new Arma("Anillo", 1),
				new Arma("Garra", 27), new Arma("Armadura", 3),
				new Arma("Red", 25) };

		HP.configurar(armasPuerta);
	}

	@Test
	public void testGetPortal() {
		//en este metodo se comprueban el getPortal y el setPortal
		
		
		HP.setPortal(true);
		// como el portal esta abierto se comprueba que sea true
		assertTrue(HP.getPortal());

		HP.setPortal(false);
		assertFalse(HP.getPortal());
	}

	@Test
	public void testGetAltura() {
		//en este metodo se comprueban el get y el set altura
		
		HP.setAltura(4);
		// la altura inicial es 4
		assertEquals(HP.getAltura(), 4);
	}

	@Test
	public void testConfigurar() {

		//el metodo HP.configurar(armasPuerta) ha sido llamado en el inicio
		
		
		Arbol<Arma> aux = new Arbol<Arma>(); // Arbol que debera ser igual que
												// el arbol del hombre puerta
												// despues de la configuracion
		aux.insertar(new Arma("Mjolnir", 29));
		aux.insertar(new Arma("Anillo", 1));
		aux.insertar(new Arma("Garra", 27));
		aux.insertar(new Arma("Armadura", 3));
		aux.insertar(new Arma("Red", 25));
		
		assertEquals(aux.getRaiz(), HP.getArmasPuerta().getRaiz());
	}

	@Test
	public void testArmaSH() {
			
		Arma a1 = new Arma ("Garra", 27);
		Arma a2 = new Arma ("CampoEnergía", 5);
		
		//comprobamos que devuelve el arma pasado por parametro si pertenece al hombre puerta
	Arma prueba = HP.armaSH(a1);
	
	assertEquals(prueba, a1);
	
	prueba = HP.armaSH(a2); //debe dar nulo porque no la encuentra
	assertNull(prueba);
	
	}

	@Test
	public void testArmaMayor() {
		
		//el arma con mayor poder es Mjolnir, 29
		
		Arma prueba = HP.armaMayor();
		assertEquals(prueba, new Arma("Mjolnir", 29));
		
		//insertamos un arma mayor que Mjolnir
		HP.getArmasPuerta().insertar(new Arma("Gema", 38));
		prueba = HP.armaMayor();
		assertEquals(prueba, new Arma("Gema", 38));
	}

	@Test
	public void testPertenece() {
		Arma prueba = new Arma("Anillo", 1); //este arma SI pertenece al arbol
		assertTrue(HP.pertenece(prueba));
		
		prueba = new Arma ("Blue", 10); //no pertenece al hombre puerta
		assertFalse(HP.pertenece(prueba));
	}

	@Test
	public void testAbrirPortal() {
		//como profundidad > altura el portal esta cerrado
		assertFalse(HP.abrirPortal());
		
		HP.setAltura(10);
		//profundidad < altura el porta se abre
		assertTrue(HP.abrirPortal());
		
	}

}
