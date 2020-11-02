package logica;

public class CasillaReloj extends Casilla{
	private int posicion;
	
	public CasillaReloj () {
		super();
	}
	


    public void setValor(Integer numeroNuevo) 
    {
        if (numeroNuevo != null && numeroNuevo < this.getCantElementos()) 
        {
            this.valor = numeroNuevo;
        }

        entidadGrafica.actualizar(this.valor);
    }


   
}
