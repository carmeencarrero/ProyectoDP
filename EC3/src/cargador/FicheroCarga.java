package cargador;

/**
 * Clase creada para ser usada en la utilidad cargador
 * estudiada previamente en sesi�n práctica "Excepciones"
 * 
 * @version 1.0 -  02/11/2011 
 * @author Profesores DP
 */
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import juego.NuevaYork;

public class FicheroCarga {
	/**
	 * atributo de la clase que indica el numero máximo de campos que se pueden
	 * leer
	 */
	public static final int MAXCAMPOS = 10;

	/**
	 * buffer para almacenar el flujo de entrada
	 */
	private static BufferedReader bufferIn;

	/**
	 * buffer para almacenar el flujo de salida
	 */
	private static BufferedWriter bufferOut;

	/**
	 * Metodo para procesar el fichero. Sin excepciones
	 * 
	 * @return codigoError con el error que se ha producido
	 * @throws Exception. Puede
	 *             lanzar una excepción en la apertura del buffer de lectura
	 */
	public static void procesarFichero(String nombreFichero, Cargador cargador)
			throws FileNotFoundException, IOException {
		List<String> vCampos = new ArrayList<String>();
		String S = new String();
		int numCampos = 0;
		String ficheroSalida = "registro.log";

		bufferOut = new BufferedWriter(new FileWriter(ficheroSalida));

		System.out.println("Procensando el fichero...");
		bufferIn = new BufferedReader(new FileReader(nombreFichero));// creación
																		// del
																		// filtro
																		// asociado
																		// al
																		// flujo
																		// de
																		// datos

		while ((S = bufferIn.readLine()) != null) {
			System.out.println("S: " + S);
			if (!S.startsWith("--")) {
				vCampos.clear();
				numCampos = trocearLinea(S, vCampos);
			    cargador.crear(vCampos.get(0), numCampos, vCampos, bufferOut);
			}
		}
		int maxTurno = 50;
		boolean end = false;
		NuevaYork NY = NuevaYork.getInstancia();
		NY.mostrarRutasPersonajes(bufferOut);

		for (int turno = 0; turno < maxTurno && !end; turno++) {
			System.out.println("(turn:" + turno + ")");
			bufferOut.write("(turn:" + turno + ")\n");

			end = NY.simulacion(turno);
			NY.pintarTodo(bufferOut);

		}
		
	String s = NY.mostrarPersonajesAdd();
		bufferOut.write(s);
		bufferOut.close();
		bufferIn.close(); // cerramos el filtro
	}

	/**
	 * Metodo para trocear cada línea y separarla por campos
	 * 
	 * @param S
	 *            cadena con la línea completa leída
	 * @param vCampos
	 *            . Array de String que contiene en cada posición un campo
	 *            distinto
	 * @return numCampos. Número campos encontrados
	 */
	private static int trocearLinea(String S, List<String> vCampos) {
		boolean eol = false;
		int pos = 0, posini = 0, numCampos = 0;

		while (!eol) {
			pos = S.indexOf("#");
			if (pos != -1) {
				vCampos.add(new String(S.substring(posini, pos)));
				S = S.substring(pos + 1, S.length());
				numCampos++;
			} else
				eol = true;
		}
		return numCampos;
	}

}
