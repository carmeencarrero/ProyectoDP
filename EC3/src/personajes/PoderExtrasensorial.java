package personajes;

import java.util.LinkedList;

import juego.NuevaYork;

/**
 * Implementación de los métodos de la clase PoderExtrasensorial
 * 
 * @version 3.0
 * @author Carmen Carrero Hurtado
 * 
 */
public class PoderExtrasensorial extends SuperHeroe{

	/**
	 * Constructor parametrizado de la clase PoderExtrasensorial
	 * 
	 * @param _nombre
	 * @param _marca
	 * @param _turno
	 * @param _sala
	 */
	public PoderExtrasensorial(String _nombre, char _marca, int _turno, int _sala) {
		super(_nombre, _marca, _turno, _sala);
		setTipo("shextrasensorial");
	}

	/**
	 * Metodo que crea la ruta del personaje extrasensorial mediante la mano derecha
	 * 
	 */
	@Override
	public void crearRutaPersonaje() {
		LinkedList <Integer> ruta = manoDerecha();
		
		rutaADirecciones(ruta);
	}
	
	/**
	 * 
	 * Metodo que calcula el algoritmo de la mano derecha
	 * 
	 * @return lista con las salas por las que va el personaje
	 */
	private LinkedList<Integer> manoDerecha() {

		LinkedList<Integer> caminoElegido = new LinkedList<Integer>();
		NuevaYork NY = NuevaYork.getInstancia();
		
		boolean giroS = false;
		boolean giroN = false;
		boolean giroE = false;
		boolean giroO = false;
		int sala = 0; //sala en la que empieza el personaje
		char mano = 'W'; //como mira al sur, tiene la mano en la pared oeste
		
		int dimx = NY.getAncho();
		
		caminoElegido.add(sala);

		while (sala != NY.getDailyPlanet()) {
			
			switch (mano) {
			case 'N':
				if (!NY.existePared(sala, sala - dimx) && !giroS){
					sala = sala - dimx;
					caminoElegido.add(sala);
					mano = 'S';
					giroN = true;
				}
				else if (!NY.existePared(sala, sala - 1)) { //va el oeste
					sala = sala - 1;
					caminoElegido.add(sala);
					giroS = false;
				} else
					mano = 'W';
				break;

			case 'S':
				if (!NY.existePared (sala, sala + dimx) && !giroN){
					sala = sala + dimx; //cuando el personaje gira
					caminoElegido.add(sala);
					mano = 'N';
					giroS = true;
				}
				else if (!NY.existePared(sala, sala + 1)) { //va al este
					sala = sala + 1;
					caminoElegido.add(sala);
					giroN = false;
				} else
					mano = 'E';
				break;

			case 'E':
				if (!NY.existePared(sala, sala + 1) && !giroO){
					sala = sala + 1;
					caminoElegido.add(sala);
					mano = 'W';
					giroE = true;
					
				}
				else if (!NY.existePared(sala, sala - dimx)) { //va al norte
					sala = sala - dimx;
					caminoElegido.add(sala);
					giroO = false;
				} else
					mano = 'N';
				break;

			case 'W':
			
				if (!NY.existePared(sala, sala - 1) && !giroE){
					sala = sala - 1;
					caminoElegido.add(sala);
					mano = 'E';
					giroO = true;
					
				}
			else if (!NY.existePared(sala, dimx + sala)) { //va al sur
					sala = dimx + sala;
					caminoElegido.add(sala);
					giroE = false;
				}
				
		
			 else
					mano = 'S';

				break;

			}

		}

		return caminoElegido;
	}

}
