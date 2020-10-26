package logica;

public class Reloj {
	private CasillaReloj reloj[];
	private int cantCasillas;
	
	public Reloj () {
		cantCasillas=5;
		reloj= new CasillaReloj[cantCasillas];
		
		for(int i=0; i< cantCasillas; i++) {
			reloj[i]=new CasillaReloj();
			reloj[i].setValor(0);
		}
	}
	
	public int getCantCasillas() {
		return cantCasillas;
	}
	
	public CasillaReloj getCasilla(int i) {
		return reloj[i];
	}
}
