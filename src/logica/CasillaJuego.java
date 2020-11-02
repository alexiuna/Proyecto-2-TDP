package logica;

public class CasillaJuego extends Casilla {
	private boolean casillaErronea;
	
	public CasillaJuego () {
		super();
		casillaErronea=false;  
	}
	public void actualizar() {
		if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
			valor++;
		}else {
			this.valor = 1;
		}
		entidadGrafica.actualizar(this.valor);
	}
	
	public boolean getCasillaErronea() {
		return casillaErronea;
	}
	
	public void setCasillaErronea(boolean e) {
		casillaErronea=e;
	}
}
