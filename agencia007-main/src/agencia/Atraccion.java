package agencia;

public class Atraccion extends Producto {
	private String nombre;
	private int precio;
	private int cupo;
	private int cupoDisponible;
	private double tiempoNecesario;
	protected boolean atraccionConCupo = true;
	
	
	public Atraccion(tipoDeProducto tipo, 
			tipoDeAtraccion tipoDeAtraccion,
			String nombre,
			int precio,
			int cupo,
			double tiempoNecesario) {
		
		super(tipo, tipoDeAtraccion);
		this.nombre = nombre;
		this.precio = precio;
		this.cupo = cupo;
		this.tiempoNecesario = tiempoNecesario;
		this.cupoDisponible = cupo;
		this.comprobarCupo();
	}
	
	
	
	
	@Override
	public String toString() {
		String quienSoy = "";
		quienSoy += "Tipo de Atracción: " + this.tipoDeAtraccion + "; " + 
		"Nombre: " + getNombre() + "; " + "Precio: "+ getPrecio() + "; " + 
		"Cupo: "+ 	getCupo() + "; " + "Tiempo: " + getTiempo();
		return quienSoy;
		
	}
	//Getters and setters
	@Override
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}
	
	@Override
	public int getCupo() {
		return cupo;
	}

	public void setCupo(int cupo) {
		this.cupo = cupo;
	}

	public int getCupoDisponible() {
		return cupoDisponible;
	}

	public void setCupoDisponible(int cupoDisponible) {
		this.cupoDisponible = cupoDisponible;
	}

	@Override
	public double getTiempo() {
		
		return tiempoNecesario;
	}
	
	public void setTiempoNecesario(double tiempoNecesario) {
		this.tiempoNecesario = tiempoNecesario;
	}
	
	public boolean getAtraccionConCupo() {
		return atraccionConCupo;
	}

	public void setAtraccionConCupo(boolean atraccionConCupo) {
		this.atraccionConCupo = atraccionConCupo;
	}




	//metodos
	public void comprobarCupo() {
		atraccionConCupo = cupoDisponible > 0;
	}
	
	@Override
	public void reducirCupo() {
		setCupoDisponible(getCupoDisponible()-1);
		comprobarCupo();
	}


	public void ordenarPorPrecio() {
	
}

	
	
}
