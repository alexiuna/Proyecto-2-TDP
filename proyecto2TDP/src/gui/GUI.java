package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;

import logica.Casilla;
import logica.CasillaJuego;
import logica.CasillaReloj;
import logica.Juego;
import logica.Reloj;
import net.miginfocom.swing.MigLayout;

public class GUI extends JFrame {
	private Juego juego;
	private JPanel contentPane;
	private Reloj reloj;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GUI() {
		setResizable(false);
		setBackground(new Color(255, 255, 255));
		setForeground(new Color(128, 0, 128));
		setTitle("Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 546, 644);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(105, 105, 105));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel.setForeground(Color.BLACK);
		panel.setBackground(new Color(128, 0, 128));
		panel.setBounds(0, 75, 540, 540);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(9, 9, 1, 1));
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_2.setBackground(new Color(128, 0, 128));
		panel_2.setBounds(109, 11, 300, 60);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 5, 1, 1));
		
		
		
		try 
        {
			juego = new Juego();
        } 
        catch (Exception e1) 
        {
            JFrame f = new JFrame();
            JOptionPane.showMessageDialog(f,e1.getMessage());
            System.exit(1);
        }
		
		
		
		
		for (int i = 0; i <juego.getCantFilas(); i++) {
			for(int j =0; j<juego.getCantFilas(); j++) {
				CasillaJuego c = juego.getCasilla(i,j);
				c.setIndiceFila(i);
				c.setIndiceColumna(j);
				ImageIcon grafico = c.getEntidadGrafica().getGrafico();
				JLabel label = new JLabel();
				label.setOpaque(true);
	            label.setBackground(new Color(138, 43, 226));
				panel.add(label);
				
				
				
				
				
					label.addComponentListener(new ComponentAdapter() {
						@Override
						public void componentResized(ComponentEvent e) {
							reDimensionar(label, grafico);
							label.setIcon(grafico);
						}
					});
					if (c.getValor()==null) {
					label.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent e) {
							juego.accionar(c);
							reDimensionar(label,grafico);
							if (!(juego.elNumeroEsCorrecto(c.getIndiceFila(), c.getIndiceColumna(), c.getValor()))) {
								 Border border = BorderFactory.createLineBorder(Color.RED, 3);
	                             label.setBorder(border);
							}
							else {
								label.setBorder(null);
							}
							if(juego.ganarJuego())
                            {
                                JFrame f = new JFrame();
                                JOptionPane.showMessageDialog(f,"¡FELICIDADES, GANO EL JUEGO!");
                                System.exit(1);
                            }
						}
					});
					
				}
				else {
					label.setBackground(new Color(128, 0, 128));
				}
			}
		}
		
	
		
	}
	private void reDimensionar(JLabel label, ImageIcon grafico) {
		Image image = grafico.getImage();
		if (image != null) {  
			Image newimg = image.getScaledInstance(label.getWidth(), label.getHeight(),  java.awt.Image.SCALE_SMOOTH);
			grafico.setImage(newimg);
			label.repaint();
		}
	}
		
	}
	
	
	

