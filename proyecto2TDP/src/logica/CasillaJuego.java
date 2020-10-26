package logica;

public class CasillaJuego extends Casilla {
	
	public CasillaJuego () {
		super();
	}
	public void actualizar() {
		if (this.valor != null && this.valor + 1 < this.getCantElementos()) {
			valor++;
		}else {
			this.valor = 1;
		}
		entidadGrafica.actualizar(this.valor);
	}
}
