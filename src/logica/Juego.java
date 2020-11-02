package logica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Juego {
	private CasillaJuego [][] tablero;
	private int cantFilas;
	private File archivo;
   
	
	public Juego(String ruta) throws Exception{
		cantFilas = 9;
		tablero = new CasillaJuego[cantFilas][cantFilas];
		archivo=null;
		if(!(this.insercionArchivoTablero(ruta))) {
			throw new Exception("ERROR: El archivo no tiene el formato correcto");
		}

		if(!(this.verificarSudoku())){
			throw new Exception("ERROR: El archivo no es sudoku valido");
		}
		despejarTablero();
	}
	
	public void accionar(CasillaJuego c) {
		
		c.actualizar();
		System.out.println(c.getValor());
		if (!elNumeroEsCorrecto(c.getIndiceFila(), c.getIndiceColumna(), c.getValor())) {
			c.setCasillaErronea(true);
		}
		else {
			c.setCasillaErronea(false);
		}
	}
	
	public CasillaJuego getCasilla(int i, int j) {
		return this.tablero[i][j];
	}

	
    private boolean recorridoColumnaErronea(CasillaJuego c)
    {
        boolean respuesta = true;
        int columna = c.getIndiceColumna();

        for(int indiceFila=0; indiceFila<cantFilas; indiceFila++)
        {
            if(tablero[indiceFila][columna]!=c && tablero[indiceFila][columna].getValor()!=null)
            {
                if(tablero[indiceFila][columna].getValor().equals(c.getValor()))
                {
                    tablero[indiceFila][columna].setCasillaErronea(true);
                    respuesta = false;
                }
            }
        }

        return respuesta;
    }

   
    private boolean recorridoPanelErroneo(CasillaJuego c)
    {
        boolean toReturn = true;

        int primerFila = primerUbiPanelBusqueda(c.getIndiceFila());
        int primerColumna = primerUbiPanelBusqueda(c.getIndiceColumna());
        int indiceFila = primerFila;
        int indiceColumna = primerColumna;

        while(toReturn && indiceFila<primerFila+3) 
        {
            while(toReturn && indiceColumna<primerColumna+3)
            {
                if(tablero[indiceFila][indiceColumna]!=c && tablero[indiceFila][indiceColumna].getValor()!=null)
                {
                    if(tablero[indiceFila][indiceColumna].getValor().equals(c.getValor()))
                    {
                        tablero[indiceFila][indiceColumna].setCasillaErronea(true);
                        toReturn = false;
                    }
                }
                indiceColumna++;
            }
            indiceFila++;
        }

        return toReturn;
    }
    private boolean recorridoFilaErronea(CasillaJuego c)
    {
        boolean toReturn = true;
        int fila = c.getIndiceFila();

        for(int indiceColumna=0; indiceColumna<cantFilas; indiceColumna++)
        {
            if(tablero[fila][indiceColumna]!=c && tablero[fila][indiceColumna].getValor()!=null)
            {
                if(tablero[fila][indiceColumna].getValor().equals(c.getValor()))
                {
                    tablero[fila][indiceColumna].setCasillaErronea(true);
                    toReturn = false;
                }
            }
        }

        return toReturn;
    }
	
	private boolean elNumeroEstaEnFila(int n, int fila, int columna) {
		boolean respuesta=false;
		int indiceC=0;
		while(!respuesta && indiceC<cantFilas) {
			if(columna!= indiceC) {
				if(tablero[fila][indiceC].getValor()!=null) {
					respuesta= (tablero[fila][indiceC].getValor().equals(n));
					if(respuesta) {
						tablero[fila][indiceC].setCasillaErronea(true);
					}
					else {
						if(tablero[fila][indiceC].getCasillaErronea()==true)
							if(recorridoFilaErronea(tablero[fila][indiceC]) & recorridoColumnaErronea(tablero[fila][indiceC]) & recorridoPanelErroneo(tablero[fila][indiceC]))
								tablero[fila][indiceC].setCasillaErronea(false);
					}
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
				
					if(respuesta) {
						tablero[indiF][columna].setCasillaErronea(true);
						
					}
					else {
						if(tablero[indiF][columna].getCasillaErronea()==true)
							if(recorridoFilaErronea(tablero[indiF][columna]) & recorridoColumnaErronea(tablero[indiF][columna]) & recorridoPanelErroneo(tablero[indiF][columna]))
								tablero[indiF][columna].setCasillaErronea(false);
					}
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
						if(respuesta) {
							tablero[indiF][indiC].setCasillaErronea(true);
						}
						else {
							if(tablero[indiF][indiC].getCasillaErronea()==true)
								if(recorridoFilaErronea(tablero[indiF][indiC]) & recorridoColumnaErronea(tablero[indiF][indiC]) & recorridoPanelErroneo(tablero[indiF][indiC]))
									tablero[indiF][indiC].setCasillaErronea(false);
						}
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
		boolean respuesta= (!elNumeroEstaEnFila(n,indiceF, indiceC)) & (!elNumeroEstaEnColumna (n, indiceC, indiceF)) & (!elNumeroEstaEnElPanel(n, indiceF, indiceC));
		return respuesta;
	}
	
	
	public int getCantFilas() {
		return cantFilas;
	}
	
	
	public boolean insercionArchivoTablero (String ruta) throws Exception {
		int indiceF;
		FileReader fr=null;
		BufferedReader br=null;

		boolean respuesta=true;
        archivo= new File(ruta);
        

        if (ruta==null)
        {
            throw new Exception("ERROR: La ruta del archivo no puede ser vacia.");
        }

        try 
        {
            archivo = new File(ruta);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
        } 
        catch (FileNotFoundException e) 
        {
            throw new Exception("ERROR: La ruta del archivo es incorrecta.");
        }

        
        
        
		try {
			indiceF=0;
		


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
                
                if(c.getCasillaErronea()||c.getValor()==null )
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
                int valor = rand.nextInt(2);//TOQUE ESTA LINEA
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
