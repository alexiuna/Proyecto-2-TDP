package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Juego {
	private CasillaJuego [][] tablero;
	private int cantFilas;
	private File archivo;
   
	
	public Juego() throws Exception{
		cantFilas = 9;
		tablero = new CasillaJuego[cantFilas][cantFilas];
		archivo=null;
		if(!(this.insercionArchivoTablero())) {
			throw new Exception("ERROR: El archivo no tiene el formato correcto");
		}

		if(!(this.verificarSudoku())){
			throw new Exception("ERROR: El archivo no es sudoku valido");
		}
		despejarTablero();
	}
	
	public void accionar(CasillaJuego c) {
		c.actualizar();
	}
	public CasillaJuego getCasilla(int i, int j) {
		return this.tablero[i][j];
	}


	
	private boolean elNumeroEstaEnFila(int n, int fila, int columna) {
		boolean respuesta=false;
		int indiceC=0;
		while(!respuesta && indiceC<cantFilas) {
			if(columna!= indiceC) {
				if(tablero[fila][indiceC].getValor()!=null) {
					respuesta= (tablero[fila][indiceC].getValor().equals(n));
				}
			}
			indiceC++;
		}
		return respuesta;
	}
	
	private boolean elNumeroEstaEnColumna(int n, int columna, int fila) {
		boolean respuesta=false;
		int indiF=0;
		while(!respuesta && indiF<cantFilas) {
			if(indiF!=fila) {
				if(tablero[indiF][columna].getValor()!=null) {
					
					respuesta= (tablero[indiF][columna].getValor().equals(n));
				}
				
			}
			indiF++;
		}
		
		return respuesta;
	}

	private boolean elNumeroEstaEnElPanel(int n, int fila, int columna) {
		int priUbiF= primerUbiPanelBusqueda(fila);
		int priUbiC= primerUbiPanelBusqueda(columna);
		boolean respuesta= false;
		int indiF=priUbiF;
		int indiC=priUbiC;
		while (!respuesta && indiF< priUbiF+3) {
			while(!respuesta && indiC<priUbiC+3) {
				if (fila!=indiF && columna!=indiC) {
					if(tablero[indiF][indiC].getValor()!=null){
						respuesta= (tablero[indiF][indiC].getValor().equals(n));
					}
				}
				indiC++;
			}
			indiF++;
		}
		return respuesta;
	}
	
	private int primerUbiPanelBusqueda(int indice) {
		if(indice%3!=0) {
			indice=primerUbiPanelBusqueda(indice-1);
		}
		return indice;
	}
	
	
	public void insertarNuevoNumero (int indiceF, int indiceC, int n) {
		EntidadGrafica g= new EntidadGrafica();
		g.actualizar(n);
		tablero[indiceF][indiceC].setValor(n);
		tablero[indiceF][indiceC].setGrafica(g);
	}
	
	public boolean elNumeroEsCorrecto (int indiceF, int indiceC, int n) {
		boolean respuesta= (!elNumeroEstaEnFila(n,indiceF, indiceC)) && (!elNumeroEstaEnColumna (n, indiceC, indiceF)) && (!elNumeroEstaEnElPanel(n, indiceF, indiceC));
		return respuesta;
	}
	
	
	public int getCantFilas() {
		return cantFilas;
	}
	
	
	public boolean insercionArchivoTablero () {
		int indiceF;
		FileReader fr=null;
		BufferedReader br=null;

		boolean respuesta=true;
		JFrame f = new JFrame();
        String ruta = JOptionPane.showInputDialog(f, "Ingrese la ruta del archivo solucion:");
        archivo= new File(ruta);
		try {
			indiceF=0;
			
			fr = new FileReader (archivo);
			br = new BufferedReader(fr);


			for (int i=0; i<cantFilas && respuesta; i++) 
			{
				String linea = br.readLine();
				int k = 0;

				for (int j=0; j<cantFilas && respuesta; j++) 
				{
					if(linea.charAt(k)>='1' && linea.charAt(k)<='9')
					{
						if(k<16 && linea.charAt(k+1)==' ')
						{
							tablero[i][j] = new CasillaJuego();
							tablero[i][j].setIndiceFila(i);
							tablero[i][j].setIndiceColumna(j);
							tablero[i][j].setValor(linea.charAt(k)-'0');
							k = k + 2;
						}
						else if(k==16)
						{
							tablero[i][j] = new CasillaJuego();
							tablero[i][j].setIndiceFila(i);
							tablero[i][j].setIndiceColumna(j);
							tablero[i][j].setValor(linea.charAt(k)-'0');
						}
						else
						{
							respuesta = false;
						}
					}
					else
					{
						respuesta = false;
					}
				}
			}
		}

		catch(Exception e){
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if(null != fr)
				{
					fr.close();
				}
			}
			catch (Exception e2)
			{ 
				e2.printStackTrace();
			}
		}
		return respuesta;
	}

	private boolean verificarSudoku()
    {
		
        boolean respuesta = true;

        for (int i=0; i<cantFilas && respuesta; i++) 
        {
            for (int j=0; j<cantFilas && respuesta; j++) 
            {

                CasillaJuego c = tablero[i][j];
                
                if((c.getValor()==null) || !(elNumeroEsCorrecto(i,j,c.getValor())))
                {
                	
                    respuesta = false;
                }

            }
        }

        return respuesta;
    }
	
	private void despejarTablero() 
    {
        Random rand = new Random();

        for (int i=0; i<cantFilas; i++) 
        {
            for (int j=0; j<cantFilas; j++) 
            {
                int valor = rand.nextInt(10);
                if(valor==0)
                {
                    tablero[i][j] = new CasillaJuego();
                    tablero[i][j].setIndiceFila(i);
                    tablero[i][j].setIndiceColumna(j);
                }
            }
        }
    }
	
	public boolean ganarJuego()
    {
        boolean respuesta = false;

        if(verificarSudoku())
            respuesta = true;

        return respuesta;
    }
}
