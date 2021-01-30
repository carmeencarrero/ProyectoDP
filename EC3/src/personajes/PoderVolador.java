package personajes;

import java.util.LinkedList;

import juego.NuevaYork;

/**
 * Implementación de los métodos de la clase PoderVolador
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public class PoderVolador extends SuperHeroe{

	/**
	 * Constructor parametrizado de la clase PoderVolador
	 * 
	 * @param _nombre
	 * @param _marca
	 * @param _turno
	 * @param _sala
	 */
	public PoderVolador(String _nombre, char _marca, int _turno, int _sala) {
		super(_nombre, _marca, _turno, _sala);
		setTipo("shflight");
	}

	/**
	 * Metodo que crea la ruta del poder volador con el algoritmo del camino mas corto
	 * 
	 */
	@Override
	public void crearRutaPersonaje() {
		NuevaYork NY = NuevaYork.getInstancia();
		
		LinkedList<Integer> ruta = NY.caminoMasCorto(NY.esquinaSuroeste(), NY.getDailyPlanet());
		
		rutaADirecciones(ruta);
	}

}
