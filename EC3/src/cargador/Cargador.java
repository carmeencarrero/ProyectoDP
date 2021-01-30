package cargador;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.List;

import personajes.*;
import juego.ExceptionJuego;
import juego.NuevaYork;

/**
 * Clase creada para ser usada en la utilidad cargador
 * contiene el main del cargador. Se crea una instancia de la clase Estacion, una instancia de la clase Cargador
 * y se procesa el fichero de inicio, es decir, se leen todas las líneas y se van creando todas las instancias de la simulación
 * 
 * @version 5.0 -  27/10/2016 
 * @author Profesores DP
 */
public class Cargador {
	/**  
	número de elementos distintos que tendrá la simulación - Mapa, Stark, Lannister, Baratheon, Targaryen
	*/
	static final int NUMELTOSCONF = 5;
	/**  
	atributo para almacenar el mapeo de los distintos elementos
	*/
	static private DatoMapeo [] mapeo;
	
	/**
	 *  constructor parametrizado 
	 *  @param e referencia a la instancia del patrón Singleton
	 */
	Cargador()  {
		mapeo = new DatoMapeo[NUMELTOSCONF];
		mapeo[0]= new DatoMapeo("MAP", 5);
		mapeo[1]= new DatoMapeo("SHPHYSICAL", 4);
		mapeo[2]= new DatoMapeo("SHEXTRASENSORIAL", 4);
		mapeo[3]= new DatoMapeo("SHFLIGHT", 4);
		mapeo[4]= new DatoMapeo("VILLAIN", 4);
		
	}
	
	/**
	 *  busca en mapeo el elemento leído del fichero inicio.txt y devuelve la posición en la que está 
	 *  @param elto elemento a buscar en el array
	 *  @return res posición en mapeo de dicho elemento
	 */
	private int queElemento(String elto)  {
	    int res=-1;
	    boolean enc=false;

	    for (int i=0; (i < NUMELTOSCONF && !enc); i++)  {
	        if (mapeo[i].getNombre().equals(elto))      {
	            res=i;
	            enc=true;
	        }
	    }
	    return res;
	}
	
	/**
	 *  método que crea las distintas instancias de la simulación 
	 *  @param elto nombre de la instancia que se pretende crear
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo de la instancia
	 * @throws IOException 
	 */
	public void crear(String elto, int numCampos, List<String> vCampos, BufferedWriter escrito) throws IOException	{
	    //Si existe elemento y el número de campos es correcto, procesarlo... si no, error
	    int numElto = queElemento(elto);
	    
	    //Comprobación de datos básicos correctos
	    if ((numElto!=-1) && (mapeo[numElto].getCampos() == numCampos))   {
	        //procesar
	        switch(numElto){
	        case 0:	   
	            escrito.write(crearMap(numCampos,vCampos));
	            break;
	        case 1:
	            crearSHPhysical(numCampos,vCampos);
	            break;
	        case 2:
	            crearSHExtraSensorial(numCampos,vCampos);
	            break;
	        case 3:
	            crearSHFlight(numCampos,vCampos);
	            break;
	        case 4:
	            crearVillain(numCampos,vCampos);
	            break;
	     	}
	    }
	    else
	        System.out.println("ERROR Cargador::crear: Datos de configuración incorrectos... " + elto + "," + numCampos+"\n");
	}

	/**
	 *  método que crea una instancia de la clase Mapa
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 *  
	 *  @return string
	 */
	private String crearMap(int numCampos, List<String> vCampos){
	  
		NuevaYork NY = NuevaYork.getInstancia();
	    int salaDP = Integer.parseInt(vCampos.get(3));
	    int dimx = Integer.parseInt(vCampos.get(1));
	    int dimy = Integer.parseInt(vCampos.get(2));
	    int altura = Integer.parseInt(vCampos.get(4));
	    NY.setDailyPlanet(salaDP);
	    NY.setAncho(dimx);
	    NY.setAlto(dimy);
	    NY.setAltura(altura);
	    NY.instanciaNull();
	    NY = NuevaYork.getInstancia(salaDP, dimx, dimy, altura);
	    
	  try {
		  NY.errorMapa(dimx, dimy);
		  
	  } catch (ExceptionJuego e){
		  System.err.println("Excepción del mapa capturada: " + e.getMessage());
		  System.out.println("...CREANDO MAPA POR DEFECTO...");
		  NY.instanciaNull();
		  NY = NuevaYork.getInstancia();
		
	  }
	  
	  String s = NY.crearJuego();
	    return s;
	}

	/**
	 *  método que crea una instancia de la clase SHPhysical
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearSHPhysical(int numCampos, List<String> vCampos){
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.parseInt(vCampos.get(3));
		int sala = 0;
		
		NuevaYork NY = NuevaYork.getInstancia();
		
		PoderFisico p = new PoderFisico(vCampos.get(1), marca, turno, sala);
	
		p.crearRutaPersonaje();
	    
	    try {
			  p.errorPersonaje(turno);
			  
		  } catch (ExceptionJuego e){
			  System.err.println("Excepción del personaje poder físico capturada: " + e.getMessage());
			  System.out.println("...INSERTANDO TURNO POR DEFECTO...");
			  p.setTurno(1);
			
		  }
		
	    
		NY.insertarPersonaje(p, sala);
	    System.out.println("Personaje creado correctamente");
	    
	}

	/**
	 *  método que crea una instancia de la clase SHExtraSensorial
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearSHExtraSensorial(int numCampos, List<String> vCampos){
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.parseInt(vCampos.get(3));
		int sala = 0;
		
		NuevaYork NY = NuevaYork.getInstancia();
		PoderExtrasensorial p = new PoderExtrasensorial(vCampos.get(1), marca, turno, sala);
		
		p.crearRutaPersonaje();
		
	       try {
				  p.errorPersonaje(turno);
				  
			  } catch (ExceptionJuego e){
				  System.err.println("Excepción del personaje poder extrasensorial capturada: " + e.getMessage());
				  System.out.println("...INSERTANDO TURNO POR DEFECTO...");
				  p.setTurno(1);
				
			  }
	       
		NY.insertarPersonaje(p, sala);
	    System.out.println("Personaje creado correctamente");
	    
	}	

	/**
	 *  método que crea una instancia de la clase SHFlight
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearSHFlight(int numCampos, List<String> vCampos){
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.parseInt(vCampos.get(3));
		NuevaYork NY = NuevaYork.getInstancia();
		int sala = NY.esquinaSuroeste();
		
		
		PoderVolador p = new PoderVolador(vCampos.get(1), marca, turno, sala);
		
		p.crearRutaPersonaje();
	       
	       try {
				  p.errorPersonaje(turno);
				  
			  } catch (ExceptionJuego e){
				  System.err.println("Excepción del personaje poder volador capturada: " + e.getMessage());
				  System.out.println("...INSERTANDO TURNO POR DEFECTO...");
				  p.setTurno(1);
				
			  }
	       
		NY.insertarPersonaje(p, sala);
	    System.out.println("Personaje creado correctamente");
	}	

	/**
	 *  método que crea una instancia de la clase Villain
	 *  @param numCampos número de atributos que tendrá la instancia
	 *  @param vCampos array que contiene los valores de cada atributo
	 */
	private void crearVillain(int numCampos, List<String> vCampos){
		char marca = vCampos.get(2).charAt(0);
		int turno = Integer.parseInt(vCampos.get(3));
		NuevaYork NY = NuevaYork.getInstancia();
		int sala = NY.esquinaNoreste();
		
	
		Villano p = new Villano(vCampos.get(1), marca, turno, sala);
	
	      p.crearRutaPersonaje();
	      
	      try {
			  p.errorPersonaje(turno);
			  
		  } catch (ExceptionJuego e){
			  System.err.println("Excepción del personaje villano capturada: " + e.getMessage());
			  System.out.println("...INSERTANDO TURNO POR DEFECTO...");
			  p.setTurno(1);
			
		  }
		NY.insertarPersonaje(p, sala);
	    System.out.println("Villano creado correctamente");
	}

}
