package agencia;

import java.io.*;

import java.util.*;
import java.util.Map.Entry;

import agencia.Producto.*;


public class agencia {
	protected List<Usuario> listaDeUsuarios = new ArrayList<Usuario>();
	public List<Atraccion> listaDeAtracciones = new ArrayList<Atraccion>();
	public List<Promocion> listaDePromociones = new ArrayList<Promocion>();
	public List<String> nombres = new ArrayList<String>();
	public ArrayList<Producto> listaPromocionesAventuras;
	public ArrayList<Promocion> listaPromocionesPaisaje;
	public ArrayList<Promocion> listaPromocionesDegustacion;
	private ArrayList<Atraccion> mapaAtraccionesAventuras;
	private ArrayList<Atraccion> mapaAtraccionesPaisaje;
	private ArrayList<Atraccion> mapaAtraccionesDegustacion;
	private HashMap<String, Atraccion> mapaAtraccionesPorNombre;
	private List<AxB> promocionesAxb = new ArrayList<AxB>();
	private List<absoluta> promocionesAbsoluta = new ArrayList<absoluta>();
	private List<Porcentual> promocionesPorcentual = new ArrayList<Porcentual>();

	// Diccionario de Atraciones--------------------------------------------------

	// Carga de archivos --------------------------------------------------------

	@SuppressWarnings("unused")
	private void cargarDatos() throws IOException {

		this.LeeYcargaUsuarios("usuarios.txt");
		this.LeeYcargaAtracciones("atracciones.txt");
		this.mapaAtraccionPorNombre();
		this.LeeYcargaPromociones("promociones.txt");
	}

	public List<Atraccion> LeeYcargaAtracciones(String archivo) throws IOException {

		// List<Atraccion> listaDeAtr= new ArrayList<Atraccion>();
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// instancia un objeto Scanner
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea = br.readLine();

			while (linea != null) {
				// Lee cada línea y la asiga a un array de String usando el espacio como
				// separador

				String[] datos = linea.split(",");

				// Crea la Atraccion con los datos de la línea de texto
				tipoDeProducto tipoDeP = tipoDeProducto.ATRACCION;
				// String tipoDeAtr = datos[0];
				String nombre = datos[1];
				int precio = Integer.parseInt(datos[2]);
				int cupo = Integer.parseInt(datos[3]);
				double tiempoNecesario = Double.parseDouble(datos[4]);
				tipoDeAtraccion tipoA;
				String paisaje = "PAISAJE";
				String aventura = "AVENTURA";

				// Se instancia una nueva atraccion y
				// Si no está repetida, se agrega a la lista
				if (aventura.equals(datos[0])) {
					tipoA = tipoDeAtraccion.AVENTURA;
				} else if (paisaje.equals(datos[0])) {
					tipoA = tipoDeAtraccion.PAISAJE;
				} else {
					tipoA = tipoDeAtraccion.DEGUSTACION;
				}

				Atraccion a = new Atraccion(tipoDeP, tipoA, nombre, precio, cupo, tiempoNecesario);

				listaDeAtracciones.add(a);

				linea = br.readLine();
			}

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Cierra el archivo
		try {
			if (fr != null) {
				fr.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return listaDeAtracciones;
	}

	public void LeeYcargaPromociones(String archivo) throws IOException {

		// List<Promocion> listaDePromociones = new ArrayList<Promocion>();
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// instancia un objeto Scanner
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea = br.readLine();

			while (linea != null) {
				// Lee cada línea y la asiga a un array de String usando el espacio como
				// separador

				String[] datos = linea.split(",");

				// System.out.println(linea);
				String tipoDesc = datos[0];
				TipoDeDescuento tDes;
				tipoDeProducto tipoDeP = tipoDeProducto.PROMOCION;
				String tipoDeAtr = datos[1];
				String nombre = datos[2];
				tipoDeAtraccion tipoA;
				Double descuento = Double.parseDouble(datos[3]);
				ArrayList<String> nombresAtracciones = new ArrayList<String>();
				ArrayList<Atraccion> atraccionesContenidas = new ArrayList<Atraccion>();

				// System.out.println(tipoDeAtr);
				for (int i = 4; i < datos.length; i++) {
					nombresAtracciones.add(datos[i]);
				}

				if (tipoDeAtr.equals("AVENTURA")) {
					tipoA = tipoDeAtraccion.AVENTURA;
				} else if (tipoDeAtr.equals("PAISAJE")) {
					tipoA = tipoDeAtraccion.PAISAJE;
				} else
					tipoA = tipoDeAtraccion.DEGUSTACION;

				for (String atr : nombresAtracciones) {

					if (mapaAtraccionesPorNombre.containsKey(atr)) {
						Atraccion a = mapaAtraccionesPorNombre.get(atr);
						atraccionesContenidas.add(a);
					}
				}
				// Se instancia una nueva Promocion y
				if (tipoDesc.equals("axb")) {
					tDes = TipoDeDescuento.AxB;
					listaDePromociones.add(new AxB(tDes, tipoDeP, tipoA, nombre, descuento, atraccionesContenidas));

				} else if (tipoDesc.equals("absoluta")) {
					tDes = TipoDeDescuento.ABSOLUTO;
					listaDePromociones
							.add(new absoluta(tDes, tipoDeP, tipoA, nombre, descuento, atraccionesContenidas));

				} else {
					tDes = TipoDeDescuento.PORCENTUAL;

					listaDePromociones
							.add(new Porcentual(tDes, tipoDeP, tipoA, nombre, descuento, atraccionesContenidas));
				}
				// System.out.println(tDes);
				// Si no está repetida, se agrega a la lista

				linea = br.readLine();
			}

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Cierra el archivo
		try {
			if (fr != null) {
				fr.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

	}

	public List<Usuario> LeeYcargaUsuarios(String archivo) throws IOException {

		// List<Atraccion> listaDeAtr= new ArrayList<Atraccion>();
		FileReader fr = null;
		BufferedReader br = null;

		try {
			// instancia un objeto Scanner
			fr = new FileReader(archivo);
			br = new BufferedReader(fr);
			String linea = br.readLine();

			while (linea != null) {
				// Lee cada línea y la asiga a un array de String usando el espacio como
				// separador

				String[] datos = linea.split(",");

				// Crea la Atraccion con los datos de la línea de texto
				//
				// tipoDeAtraccion preferencia)
				String nombre = datos[0];
				int dinero = Integer.parseInt(datos[1]);
				double tiempoNecesario = Double.parseDouble(datos[2]);
				String tipoDeAtr = datos[3];

				tipoDeAtraccion tipoA;

				// Se instancia una nueva atraccion y
				// Si no está repetida, se agrega a la lista
				if (tipoDeAtr.equals("aventura")) {
					tipoA = tipoDeAtraccion.AVENTURA;
				} else if (tipoDeAtr.equals("paisaje")) {
					tipoA = tipoDeAtraccion.PAISAJE;
				} else
					tipoA = tipoDeAtraccion.DEGUSTACION;

				Usuario u = new Usuario(nombre, dinero, tiempoNecesario, tipoA);

				if (!listaDeUsuarios.contains(u)) {
					listaDeUsuarios.add(u);
				}
				linea = br.readLine();
			}

		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Cierra el archivo
		try {
			if (fr != null) {
				fr.close();
			}
		} catch (Exception e2) {
			e2.printStackTrace();
		}

		return listaDeUsuarios;
	}

		// --------------------------------------------------------------------------
	
	//METODOS DE AYUDA PARA CHEQUEAR LAS CARGAS
	public void mostrarUsers() {
		for (int i = 0; i < listaDeUsuarios.size(); i++) {
			System.out.println(listaDeUsuarios.get(i).toString());
		}
	}

	public void mostrarAtracciones() {
		for (int i = 0; i < listaDeAtracciones.size(); i++) {
			System.out.println(listaDeAtracciones.get(i).toString());
		}
	}

	public void mostrarPromos() {

		for(Promocion p: listaDePromociones) {
			System.out.println(p.toString());
		}
	}

	public void guardarNombres() {
		for (int i = 0; i < listaDeAtracciones.size(); i++) {
			String aca = listaDeAtracciones.get(i).getNombre();
			if (nombres.contains(aca))
				continue;
			nombres.add(aca);
		}
	}

	public void mostrarNames() {
		for (int i = 0; i < nombres.size(); i++) {
			System.out.println(nombres.get(i));
		}
	}

	// ---------------------------------------------------------------------------------------
	
	// Se cargan las listas
	public void mapaAtraccionPorNombre() {

		// Mapa de Promociones de Aventuras
		mapaAtraccionesPorNombre = new HashMap<String, Atraccion>();

		for (Atraccion a : listaDeAtracciones) {

			String key = a.getNombre();
			mapaAtraccionesPorNombre.put(key, a);
		}

	}

	// Mapas de Promociones por tipo de Atraccion Ordenadas por precio
	public void listasDePromocionesPorTipoAtraccion() {

		// Mapa de Promociones de Aventuras
		listaPromocionesAventuras = new ArrayList<Producto>();

		for (Producto p : listaDePromociones) {
			if ((p.tipoDeAtraccion.name().equals("AVENTURA"))&& (p.getCupo()>0))
			{
				listaPromocionesAventuras.add((Promocion) p);
				}
			}
		

		// Mapa de Promociones de Paisaje
		listaPromocionesPaisaje = new ArrayList<Promocion>();

		for (Promocion p : listaDePromociones) {
			if ((p.tipoDeAtraccion.equals(tipoDeAtraccion.PAISAJE))
					&& (p.getAtraccionesConCupo())	) {
				listaPromocionesPaisaje.add((Promocion) p);
			}
		}
		// Mapa de Degustacion
		listaPromocionesDegustacion = new ArrayList<Promocion>();

		for (Producto p : listaDePromociones) {
			if (p.tipoDeAtraccion.equals(tipoDeAtraccion.DEGUSTACION)
					&& (p.getCupo()>0)) {
				listaPromocionesDegustacion.add((Promocion) p);
			}
		}
	}
	
	// Mapas de Atraccion Ordenadas por precio
	public void mapasDeAtraccionPorPrecio() {

		// Mapa de Aventuras
		atraccionesAventuras();

		atraccionesDegustacion();

		atraccionesPaisaje();

	}

	private void atraccionesPaisaje() {
		mapaAtraccionesPaisaje = new ArrayList<Atraccion>();

		for (Producto p : listaDeAtracciones) {
			if (p.tipoDeAtraccion.equals(tipoDeAtraccion.PAISAJE)) {
				mapaAtraccionesPaisaje.add((Atraccion) p);
			}
		}
	}

	private void atraccionesDegustacion() {
		mapaAtraccionesDegustacion = new ArrayList<Atraccion>();

		for (Producto p : listaDeAtracciones) {
			if (p.tipoDeAtraccion.equals(tipoDeAtraccion.DEGUSTACION)) {
				mapaAtraccionesDegustacion.add((Atraccion) p);
			}
		}
	}

	private void atraccionesAventuras() {
		mapaAtraccionesAventuras = new ArrayList<Atraccion>();

		for (Producto p : listaDeAtracciones) {
			if (p.tipoDeAtraccion.equals(tipoDeAtraccion.AVENTURA)) {
				mapaAtraccionesAventuras.add((Atraccion) p);
			}

		}
	}

	//----------------------------------------------------------------------------------------
	
	// Filtros
	
	// Recorre lista usuarios
	private void filtroSugerencias() {

		this.mapaAtraccionPorNombre();
		
		for (Usuario u : listaDeUsuarios) {

			// Por cada usuario se genera el mapa para que los cupos estén actualizados

			this.listasDePromocionesPorTipoAtraccion();
			// Primero entra en el mapa del tipo de atraccion preferida

			if (u.getPreferencia().equals(tipoDeAtraccion.AVENTURA)) {

				// primero ofrece las de su gusto y luego las que no coinciden
				filtroPreferenciaAventura(u);
				filtroPreferenciaDegustacion(u);
				filtroPreferenciPaisaje(u);
				filtroAtraccionAventura(u);
				filtroAtraccionDegustacion(u);
				filtroAtraccionPaisaje(u);
			}

			if (u.getPreferencia().equals(tipoDeAtraccion.DEGUSTACION) ) {

				// primero ofrece las de su gusto y luego las que no coinciden
				filtroPreferenciaDegustacion(u);
				filtroPreferenciPaisaje(u);
				filtroPreferenciaAventura(u);
				filtroAtraccionDegustacion(u);
				filtroAtraccionAventura(u);
				filtroAtraccionPaisaje(u);
			}

			if (u.getPreferencia().equals(tipoDeAtraccion.PAISAJE)) {

				// primero ofrece las de su gusto y luego las que no coinciden
				filtroPreferenciPaisaje(u);
				filtroPreferenciaDegustacion(u);
				filtroPreferenciaAventura(u);
				filtroAtraccionPaisaje(u);
				filtroAtraccionAventura(u);
				filtroAtraccionDegustacion(u);

			}

			System.out.println("muchas gracias " + u.getNombre() + " por tratar con nuestra agencia");

		}

	}

	private void filtroPreferenciPaisaje(Usuario u) {
		
			for (Promocion cadaPromoPaisaje : listaPromocionesPaisaje) {

				// comprueba que le alcanza el dinero
				if (u.getMonedasDeOro() >= cadaPromoPaisaje.getPrecio()) {
						// Comprueba que le alcance el tiempo
						if (u.getTiempoDisponible() >= cadaPromoPaisaje.getTiempo()) {

							// Si cumple con todo, la ofrece
							if (this.ofertar(u, cadaPromoPaisaje)) {

								// Si acepta la compra actualiza dinero y tiempo del Usuario. Guarda la compra
								u.restarDinero((int) cadaPromoPaisaje.getPrecio());
								u.setTiempoDisponible(cadaPromoPaisaje.getTiempo());
								u.setSugerenciasDiarias(cadaPromoPaisaje);
								cadaPromoPaisaje.reducirCupo();

								// Actualiza el cupo de las y atracciones
								for (Atraccion a : cadaPromoPaisaje.getAtraccionesContenidas()) {
									a.reducirCupo();
									System.out.println("Es cupo restante de " +  a.getNombre() + " " + a.getCupoDisponible());
								}
							}
						}
					}

				}

			}
	
	private void filtroPreferenciaDegustacion(Usuario u) {
	
		for (Promocion cadaPromoDegustacion : listaPromocionesDegustacion) {

			// comprueba que le alcanza el dinero
			if (u.getMonedasDeOro() >= cadaPromoDegustacion.getPrecio()) {
					// Comprueba que le alcance el tiempo
					if (u.getTiempoDisponible() >= cadaPromoDegustacion.getTiempo()) {

						// Si cumple con todo, la ofrece
						if (this.ofertar(u, cadaPromoDegustacion)) {

							// Si acepta la compra actualiza dinero y tiempo del Usuario. Guarda la compra
							u.restarDinero((int) cadaPromoDegustacion.getPrecio());
							u.setTiempoDisponible(cadaPromoDegustacion.getTiempo());
							u.setSugerenciasDiarias(cadaPromoDegustacion);

							// Actualiza el cupo de las y atracciones
							cadaPromoDegustacion.reducirCupo();
						}
					}
			}
		}
	}
	private void filtroPreferenciaAventura(Usuario u) {
		
		for (Producto cadaPromoAventura : listaPromocionesAventuras) {

			// comprueba que le alcanza el dinero
			if (u.getMonedasDeOro() >= cadaPromoAventura.getPrecio()) {
					// Comprueba que le alcance el tiempo
					if (u.getTiempoDisponible() >= cadaPromoAventura.getTiempo()) {

						// Si cumple con todo, la ofrece
						if (this.ofertar(u, cadaPromoAventura)) {

							// Si acepta la compra actualiza dinero y tiempo del Usuario. Guarda la compra
							u.restarDinero((int) cadaPromoAventura.getPrecio());
							u.setTiempoDisponible(cadaPromoAventura.getTiempo());
							u.setSugerenciasDiarias(cadaPromoAventura);

							// Actualiza el cupo de las y atracciones
							for (Atraccion a : ((Promocion) cadaPromoAventura).getAtraccionesContenidas()) {
								System.out.println("El cupo es de " +  a.getNombre() + " " + a.getCupoDisponible());
								a.reducirCupo();
								System.out.println("Es cupo restante de " +  a.getNombre() + " " + a.getCupoDisponible());
							}
						}
					}
			}
		}
	}
	private void filtroAtraccionAventura(Usuario u) {

		this.atraccionesAventuras();
		for (Atraccion a : mapaAtraccionesAventuras) {

			// comprueba que le alcanza el dinero
			if (u.getMonedasDeOro() >= a.getPrecio() &&

					u.getTiempoDisponible() >= a.getTiempo()) {

				// Si cumple con todo, la ofrece
				if (this.ofertar(u, a)) {

					// Si acepta la compra actualiza dinero y tiempo del Usuario. Guarda la compra
					u.restarDinero((int) a.getPrecio());
					u.setTiempoDisponible(a.getTiempo());
					u.setSugerenciasDiarias(a);

					// Actualiza el cupo de las y atracciones
					a.reducirCupo();

				}
			}
		}
	}

	private void filtroAtraccionPaisaje(Usuario u) {

		this.atraccionesPaisaje();
		for (Atraccion a : mapaAtraccionesPaisaje) {

			// comprueba que le alcanza el dinero
			if (u.getMonedasDeOro() >= a.getPrecio() &&

					u.getTiempoDisponible() >= a.getTiempo()) {

				// Si cumple con todo, la ofrece
				if (this.ofertar(u, a)) {

					// Si acepta la compra actualiza dinero y tiempo del Usuario. Guarda la compra
					u.restarDinero((int) a.getPrecio());
					u.setTiempoDisponible(a.getTiempo());
					u.setSugerenciasDiarias(a);

					// Actualiza el cupo de las y atracciones
					a.reducirCupo();

				}
			}
		}
	}

	private void filtroAtraccionDegustacion(Usuario u) {

		this.atraccionesDegustacion();
		for (Atraccion a : mapaAtraccionesDegustacion) {

			// comprueba que le alcanza el dinero
			if (u.getMonedasDeOro() >= a.getPrecio() &&

					u.getTiempoDisponible() >= a.getTiempo()) {

// Si cumple con todo, la ofrece
				if (this.ofertar(u, a)) {

//Si acepta la compra actualiza dinero y tiempo del Usuario. Guarda la compra	
					u.restarDinero((int) a.getPrecio());
					u.setTiempoDisponible(a.getTiempo());
					u.setSugerenciasDiarias(a);

					// Actualiza el cupo de las y atracciones
					a.reducirCupo();;

				}
			}
		}
	}

	private boolean ofertar(Usuario u, Producto p) {
		@SuppressWarnings("resource")
		Scanner teclado = new Scanner(System.in);
		
		if(p.getTipoDeProducto().name().equals(tipoDeProducto.PROMOCION.name())) {
			System.out.println("---------------------------");
			System.out.println("Bienvenido/a "+ u.getNombre() + " usted tiene un credito disponible de: "+ u.getMonedasDeOro());
			System.out.println("Tiene un tiempo disponible de: "+ u.getTiempoDisponible() +" horas");
			System.out.println("Dada su preferencia por atracciones del tipo: " + u.getPreferencia() );
			System.out.println("Le sugerimos una PROMOCION:   " + p.getNombre() + ", que cuesta " + p.getPrecio() + " y tiempo " +
			p.getTiempo());
			
			System.out.print( "Acepta la oferta?. Por favor responda S por si o N por No: ");
		}
		if (p.getTipoDeProducto().name().equals(tipoDeProducto.ATRACCION.name())) {
			System.out.println("---------------------------");
			System.out.println("Bienvenido/a "+ u.getNombre() + " usted tiene un credito disponible de: "+ u.getMonedasDeOro());
			System.out.println("Tiene un tiempo disponible de: "+ u.getTiempoDisponible() +"horas");
			System.out.println("Dada su preferencia por atracciones del tipo: " + u.getPreferencia() );
			System.out.println("Le sugerimos:   " + p.getNombre() + ", que cuesta " + p.getPrecio());
			
			System.out.print( "Acepta la oferta?. Por favor responda S por si o N por No: ");
		}
		

		String respuesta = teclado.next();
		//Arcaico pero funciona
		boolean caso3 = respuesta.toLowerCase().equals("n") || respuesta.toLowerCase().equals("s");
		
		while(caso3!=true) {
			System.out.println("Su respuesta no era valida por favor reingrese la respuesta.");
			respuesta  = teclado.next();
			caso3 = respuesta.toLowerCase().equals("n") || respuesta.toLowerCase().equals("s");
			
		}
		
		
		while (!respuesta.equals("N") && !respuesta.equals("n") && !respuesta.equals("S") && !respuesta.equals("s")) {

			System.out.print("La respuesta no es válida.  Por favor responda S por si o N por No: ");

		} 

		// acepta oferta
		if (respuesta.equals("S") || respuesta.equals("s")) {

			// Prueba de salida
			System.out.println("Oferta aceptada. ");
			//System.out.println("Aún le quedan "+ u.getMonedasDeOro());
			System.out.println("Su itinerario actual contiene:  ");
			System.out.println(u.getSugerenciasDiariasToString());
			return true;
		}
		// rechaza oferta

		System.out.println("Oferta rechazada. ");
		return false;

	}

	//-------------------------------------------------------------------------------------------
	
	//Devolver archivos por usuarios con sus intinerarios
	
	public void emitirTicketDeUsuarios() {
		String file = null;
		PrintWriter salida = null;
		
		for(Usuario u : this.listaDeUsuarios) {
			try {
				file = u.getNombre() + ".txt";
				salida = new PrintWriter(new FileWriter(file));
				double precioTotal = 0;
				double tiempoTotal = 0;
				String escribir = u.getNombre() + " bienvenido estas son tus compras \n "+
				"Su saldo actual es de " + u.getMonedasDeOro() + " y su tiempo de " +  u.getTiempoDisponible() +
				". \n Este es su intinerario: \n";
				for(Producto p: u.getSugerenciasDiarias()) {
					escribir += p.getNombre().toUpperCase() + " una " + p.getTipoDeProducto().name() +
							" que cuesta " + p.getPrecio() + " y te tomara " + 
							p.getTiempo() + " recorrer. \n";
					precioTotal += p.getPrecio();
					tiempoTotal += p.getTiempo();
				}
				salida.print(escribir);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				salida.close();
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {

		agencia a1 = new agencia();
		a1.cargarDatos();
		a1.filtroSugerencias();
		//a1.emitirTicketDeUsuarios();
		/*
		for (int i = 0; i < a1.listaDePromociones.size(); i++) {
			System.out.println(a1.listaDePromociones.get(i).getNombre()+ "    " + 
					a1.listaDePromociones.get(i).getTipoDeAtraccion() + "    "
					+ a1.listaDePromociones.get(i).getPrecio() +
					"    "+ a1.listaDePromociones.get(i).getTiempo() + "   " + 
					a1.listaDePromociones.get(i).getAtraccionesConCupo());
		} */ /*
		for (int i = 0; i < a1.listaDeAtracciones.size(); i++) {
			System.out.println(a1.listaDeAtracciones.get(i).getNombre()+"   "+a1.listaDeAtracciones.get(i).getAtraccionConCupo());
		} */
		//
		//a1.listasDePromocionesPorTipoAtraccion();
		//System.out.println(a1.listaPromocionesAventuras.toString());			
	//	a1.mostrarPromos();
	//	a1.mapasPorAtraccionPorPrecio();
		
		
		
		
	
	}

}
