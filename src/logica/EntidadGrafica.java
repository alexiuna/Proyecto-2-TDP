package logica;

import javax.swing.ImageIcon;

public class EntidadGrafica {
	
	private ImageIcon grafico;
	private String[] rutaImagenes;
	
	public EntidadGrafica() {
		grafico = new ImageIcon();
		rutaImagenes = new String[]{"/img/0.png","/img/1.png", "/img/2.png", "/img/3.png","/img/4.png", "/img/5.png", "/img/6.png", "/img/7.png","/img/8.png", "/img/9.png"};
		
	}
	
	public void actualizar(int indice) {
		if (indice < rutaImagenes.length) {
			ImageIcon imageIcon = new ImageIcon(this.getClass().getResource(rutaImagenes[indice]));
			this.grafico.setImage(imageIcon.getImage());
		}
	}
	
	public ImageIcon getGrafico() {
		return grafico;
	}
	
	public void setGrafico(ImageIcon g) {
		grafico = g;
	}
	
	public String[] getRutaImagenes() {
		return rutaImagenes;
	}
	
	public void setImagenes(String[] imagenes) {
		rutaImagenes = imagenes;
	}
	
}
