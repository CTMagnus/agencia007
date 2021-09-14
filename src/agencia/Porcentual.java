package agencia;

import java.util.ArrayList;
import java.util.List;

public class Porcentual extends Promocion {
	
	List<Atraccion> atraccionesContenidas = new ArrayList<Atraccion>();
	protected boolean atraccionConCupo;
	private double interesDeLaoferta;
	private double tiempo;

	public Porcentual( TipoDeDescuento tipoDescuento, tipoDeProducto tipo, tipoDeAtraccion tipoAtraccion,String nombre,
			 double interesDeOferta, ArrayList<Atraccion> lista) {
		super(tipo, tipoAtraccion,nombre);
		this.atraccionesContenidas = lista;
		this.interesDeLaoferta = interesDeOferta/100;
		this.setTiempo();
		this.setAtraccionConCupo();
		
	}
	
	
public void setTiempo() {
		
		for (int i = 0; i < atraccionesContenidas.size(); i++) {
			this.tiempo += atraccionesContenidas.get(i).getTiempo();
		}
		
	}
	
	public double getTiempo() {
		return this.tiempo;
	}
	
	
	
	
	@Override
	public double getPrecio() {
		double costo = 0;
		for (int i = 0; i < atraccionesContenidas.size(); i++) {
			costo += atraccionesContenidas.get(i).getPrecio();
		}
		costo = costo * (1-this.getInteresDeLaoferta());
		return costo;
	}
	
	//Getters and setters
	
	

	@Override
	public String toString() {
		String retorno = this.getNombre() + " " + this.getPrecio()+ " " + atraccionesContenidas;
		return retorno;
	}

	public void setAtraccionesContenidas(ArrayList<Atraccion> atraccionesContenidas) {
		this.atraccionesContenidas = atraccionesContenidas;
	}

	public double getInteresDeLaoferta() {
		return interesDeLaoferta;
	}
	
	
	
	public void setAtraccionConCupo() {
		int contador = 0;
		for (int i=0; i < atraccionesContenidas.size();i++) {
			if(atraccionesContenidas.get(i).atraccionConCupo==true) contador ++;
		}
		atraccionConCupo = contador == atraccionesContenidas.size();
	}
	
	
	public void reducirCupoPromocion() {
		
		for (int i = 0; i < atraccionesContenidas.size(); i++) {
			atraccionesContenidas.get(i).reducirCupo();
		}
		
		this.setAtraccionConCupo();
		
		}
	
	@Override
	public boolean getAtraccionesConCupo() {
		return this.atraccionConCupo;
	}
	
	
	
}
