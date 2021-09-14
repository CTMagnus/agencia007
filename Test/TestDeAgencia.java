import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import agencia.*;
import agencia.Producto.tipoDeAtraccion;
import agencia.agencia;

public class TestDeAgencia {
	agencia a1 = null;
	@Before
	public void beforeSetUp() {
		a1 = new agencia();
	}
	/*
	 * @Test public void agenciaTEST() { assertNotNull(a1); }
	 * 
	 * @Test public void cargaDeUsersTest() throws IOException {
	 * a1.LeeYcargaUsuarios("TestUsuarios.txt");
	 * assertEquals(a1.listaDeUsuarios.get(0).getNombre(), "Primer TEST");
	 * assertEquals(a1.listaDeUsuarios.get(1).getNombre(), "Segundo  TEST");
	 * assertEquals(a1.listaDeUsuarios.get(2).getNombre(), "Tercer  TEST");
	 * assertEquals(a1.listaDeUsuarios.get(3).getNombre(), "Cuarto  TEST");
	 * assertEquals(a1.listaDeUsuarios.get(4).getNombre(), "Quinto TEST");
	 * assertEquals(a1.listaDeUsuarios.get(5).getMonedasDeOro(), 80);
	 * a1.listaDeUsuarios.get(5).restarDinero(50);
	 * assertEquals(a1.listaDeUsuarios.get(5).getMonedasDeOro(), 30);
	 * assertEquals(a1.listaDeUsuarios.get(5).getTiempoDisponible(), 18 , 0);
	 * a1.listaDeUsuarios.get(5).setTiempoDisponible(12);
	 * assertEquals(a1.listaDeUsuarios.get(5).getTiempoDisponible(), 6 , 0);
	 * 
	 * 
	 * 
	 * } public void CargaDeAtraccionesTest() throws IOException {
	 * a1.LeeYcargaAtracciones("TestAtracciones.txt");
	 * assertEquals(a1.listaDeAtracciones.get(0).getNombre(), "AVENTURA TEST 1");
	 * assertTrue(a1.listaDeAtracciones.get(1).getTipoDeAtraccion().equals(
	 * tipoDeAtraccion.AVENTURA));
	 * assertTrue(a1.listaDeAtracciones.get(8).getTipoDeAtraccion().equals(
	 * tipoDeAtraccion.PAISAJE));
	 * assertEquals(a1.listaDeAtracciones.get(6).getPrecio(), 130, 0);
	 * assertEquals(a1.listaDeAtracciones.get(10).getTiempo(), 10, 0);
	 * assertEquals(a1.listaDeAtracciones.get(7).getCupoDisponible(), 15, 0);
	 * a1.listaDeAtracciones.get(7).reducirCupo();
	 * assertEquals(a1.listaDeAtracciones.get(7).getCupoDisponible(), 14, 0);
	 * a1.listaDeAtracciones.get(7).reducirCupo();
	 * a1.listaDeAtracciones.get(7).reducirCupo();
	 * assertEquals(a1.listaDeAtracciones.get(7).getCupoDisponible(), 12, 0); }
	 * 
	 * public void CargaDePromosTest() throws IOException {
	 * a1.LeeYcargaPromociones("TestPromociones.txt");
	 * assertEquals(a1.listaDePromociones.get(2).getNombre(), "ABSOLUTA TEST 1" );
	 * assertEquals(a1.listaDePromociones.get(6).getPrecio(), 143 , 0); //TEST AXB
	 * assertEquals(a1.listaDePromociones.get(2).getPrecio(), 30 ,0 ); //TEST
	 * ABSOLUTA assertEquals(a1.listaDePromociones.get(4).getPrecio(), 114.4 , 0 );
	 * //TEST PORCENTUAL //Comprobaci�n de una promoci�n al azar, que sus
	 * atracciones correspondan a la lista principal
	 * assertTrue(a1.listaDeAtracciones.contains(a1.listaDePromociones.get(5).
	 * getAtraccionesContenidas().get(0)));
	 * assertTrue(a1.listaDeAtracciones.contains(a1.listaDePromociones.get(3).
	 * getAtraccionesContenidas().get(1)));
	 * 
	 * }
	 * 
	 * 
	 * @Test public void filtradoTest() throws IOException {
	 * a1.LeeYcargaUsuarios("TestUsuarios.txt");
	 * a1.LeeYcargaAtracciones("TestAtracciones.txt"); a1.mapaAtraccionPorNombre();
	 * a1.LeeYcargaPromociones("TestPromociones.txt"); a1.filtroSugerencias();
	 * //Comprueba de otorgar la unica atracci�n que puede comprar al usuario con
	 * menor dinero y tiempo
	 * assertTrue(a1.listaDeUsuarios.get(7).getSugerenciasDiarias().contains(a1.
	 * listaDeAtracciones.get(12))); //Comprueba de que el usuario con m�s tiempo y
	 * dinero contiene todas las atracciones
	 * assertTrue(a1.listaDeUsuarios.get(6).getSugerenciasDiarias().containsAll(a1.
	 * listaDeAtracciones));
	 * 
	 * }
	 * 
	 */
}
