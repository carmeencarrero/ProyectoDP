package personajes;

import java.util.LinkedList;

import juego.NuevaYork;

/**
 * Implementación de los métodos de la clase PoderFisico
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public class PoderFisico extends SuperHeroe{

	/**
	 * Constructor parametrizado de la clase PoderFisico
	 * 
	 * @param _nombre
	 * @param _marca
	 * @param _turno
	 * @param _sala
	 */
	public PoderFisico(String _nombre, char _marca, int _turno, int _sala) {
		super(_nombre, _marca, _turno, _sala);
		setTipo("shphysical");
	}

	/**
	 * Metodo que crea la ruta del poder fisico mediante el algoritmo del camino con los adyacentes mas pequeños
	 * 
	 */
	@Override
	public void crearRutaPersonaje() {
		NuevaYork NY = NuevaYork.getInstancia();
		LinkedList <Integer> ruta = NY.caminoIdMinimo(0, NY.getDailyPlanet());
		
		rutaADirecciones(ruta);
	}

}
