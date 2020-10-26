package logica;

public class CasillaReloj extends Casilla{
	private int posicion;
	
	public CasillaReloj () {
		super();
	}
	
	
	public void actualizar() {
		if (this.valor != null && this.valor < getCantElementos()) {
			valor++;
		}else {
			valor = 1;
		}
		entidadGrafica.actualizar(valor);
	}
	
	public void setPosicion(int p) {
		posicion=p;
	}
}
