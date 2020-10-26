package logica;

public class Casilla {
	
	/*
	 * Indica el indice en el arreglo de las imagenes
	 */
	protected Integer valor;
	/*
	 * Entidad Grafica de la casilla
	 */
	protected EntidadGrafica entidadGrafica;
	
	
	protected Integer indiceFila;
	protected Integer indiceColumna;
	
	
	
	public Casilla() {
		valor = null;
		entidadGrafica = new EntidadGrafica();
		indiceFila=null;
		indiceColumna=null;
		
	}
	
	public void setIndiceFila (int i) {
		indiceFila=i;
	}
	
	public void setIndiceColumna (int i) {
		indiceColumna=i;
	}
	
	public Integer getIndiceFila() {
		return indiceFila;
	}
	
	public Integer getIndiceColumna() {
		return indiceColumna;
	}
	
	

	
	public int getCantElementos() {
		return this.entidadGrafica.getRutaImagenes().length;
	}
	
	
	public Integer getValor() {
		return this.valor;
	}
	
	public void setValor(Integer valor) {
		if (valor!=null && valor < this.getCantElementos()) {
			this.valor = valor;
			this.entidadGrafica.actualizar(this.valor);
		}else {
			this.valor = null;
		}
	}
	
	public EntidadGrafica getEntidadGrafica() {
		return this.entidadGrafica;
	}
	
	public void setGrafica(EntidadGrafica g) {
		this.entidadGrafica = g;
	}
	
	
}
	
	
	
	
	

