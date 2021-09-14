package agencia;
import java.util.ArrayList;
import java.util.List;
public class absoluta extends Promocion {
	
	List<Atraccion> atraccionesContenidas = new ArrayList<Atraccion>();
	private double costo;
	protected boolean atraccionConCupo;
	private double tiempo;
	
	public absoluta( TipoDeDescuento tipoDescuento,  tipoDeProducto tipo, 
			tipoDeAtraccion tipoAtraccion,String nombre,double costo, List<Atraccion> lista) {
		super(tipo, tipoAtraccion,nombre);
		this.costo = costo;
		atraccionesContenidas.addAll( lista);
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
	public String getNombre() {
		return nombre;
	}
	
	@Override
	public double getPrecio() {
		double suma = 0;
		for (int i = 0; i < atraccionesContenidas.size(); i++) {
		 suma += atraccionesContenidas.get(i).getPrecio();
		}
		costo = suma - 5;
		return this.costo;
	}
	
	public void agregarAtracciones(List<Atraccion> lista) {
		this.atraccionesContenidas.addAll(lista);
	}
	
	@Override
	public String toString() {
		String retorno = this.getNombre() + " " + this.getPrecio() + " " + atraccionesContenidas;
		return retorno;
	}
	
	
	public void reducirCupoPromocion() {
		
		for (int i = 0; i < atraccionesContenidas.size(); i++) {
			atraccionesContenidas.get(i).reducirCupo();
		}
		
		this.setAtraccionConCupo();
		
		}
	public void setAtraccionConCupo() {
		int contador = 0;
		for (int i=0; i < atraccionesContenidas.size();i++) {
			if(atraccionesContenidas.get(i).atraccionConCupo==true) contador ++;
		}
		atraccionConCupo = contador == atraccionesContenidas.size();
	}
	
	@Override
	public boolean getAtraccionesConCupo() {
		return this.atraccionConCupo;
	}
	}
