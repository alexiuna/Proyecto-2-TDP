package logica;

import java.time.Duration;
import java.time.LocalTime;

public class Reloj {
	private CasillaReloj reloj[];
	private int cantCasillas;
	private LocalTime tiempoDeInicio;
    private Duration tiempoTranscurrido;
	
	public Reloj () {
		cantCasillas=5;
		reloj= new CasillaReloj[cantCasillas];
		
		for(int i=0; i< cantCasillas; i++) {
			reloj[i]=new CasillaReloj();
			reloj[i].setValor(0);
			//Iniciar Reloj 
	        tiempoDeInicio = LocalTime.now();
		}
	}
	
	public int getCantCasillas() {
		return cantCasillas;
	}
	
	public CasillaReloj getCasilla(int i) {
		return reloj[i];
	}
	
	public void actualizar() 
    {
        ActualizarReloj();

        String cadena = null;

        for (int j =0; j<cantCasillas; j++) 
        {
        	String cadenaHora = String.format("%01d", tiempoTranscurrido.toHours());
            String cadenaMinutos = String.format("%02d", tiempoTranscurrido.toMinutes());
            String cadenaSegundos = String.format("%06d", tiempoTranscurrido.getSeconds());

            Integer minuto = Integer.parseInt(cadenaMinutos);
            Integer segundo = Integer.parseInt(cadenaSegundos);

            String cadenaSegundosAux = null;

            if(minuto > 0 && segundo>59)
            {
                segundo = segundo - (60*minuto);
            }

            if(segundo<10)
            {
                cadenaSegundosAux = '0'+segundo.toString();
            }
            else
            {
                cadenaSegundosAux = segundo.toString();
            }

            cadena = cadenaHora+cadenaMinutos+cadenaSegundosAux;

            reloj[j].setValor(cadena.charAt(j)-'0');
        }
    }
	
	protected void ActualizarReloj()
    {
        LocalTime tiempoActual = LocalTime.now( );
        tiempoTranscurrido = Duration.between(tiempoDeInicio , tiempoActual);
    }
}
