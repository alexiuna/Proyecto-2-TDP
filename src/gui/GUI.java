package gui;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Color;
import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


import logica.CasillaJuego;
import logica.CasillaReloj;
import logica.Juego;
import logica.Reloj;

public class GUI extends JFrame {
	private Juego juego;
	private JPanel contentPane;
	private Reloj reloj;
	private Timer[] timers;
	
	private JLabel[][] matrizLabel;

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
		panel_2.setBounds(69, 11, 420, 61);
		contentPane.add(panel_2);
		panel_2.setLayout(new GridLayout(0, 7, 0, 0));
		
		
		
		try 
        {
			JFrame f = new JFrame();
	        String ruta = JOptionPane.showInputDialog(f, "Ingrese la ruta del archivo solucion:");
			juego = new Juego(ruta);
			matrizLabel=new JLabel[9][9];
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
				matrizLabel[i][j]=label; //Agregue esta linea
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
							for (int k = 0; k < matrizLabel.length; k++) 
                            {
                                for(int l = 0; l< matrizLabel.length; l++) 
                                {
                                    Boolean casillaErronea = juego.getCasilla(k, l).getCasillaErronea();

                               
                                        if(casillaErronea == true)
                                        {
                                            Border border = BorderFactory.createLineBorder(Color.RED, 3);
                                            matrizLabel[k][l].setBorder(border);
                                        }
                                        else if(casillaErronea == false)
                                        {
                                            matrizLabel[k][l].setBorder(null);
                                        }
                                    
                                }
                            }

	                      
							if(juego.ganarJuego())
                            {
								for (int i=0; i<timers.length; i++)
                                {
                                    timers[i].stop();
                                }
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
		
		reloj = new Reloj();

        timers = new Timer[reloj.getCantCasillas()];

        for(int j=0; j<reloj.getCantCasillas(); j++) 
        {
            CasillaReloj d = reloj.getCasilla(j);

            ImageIcon grafico = d.getEntidadGrafica().getGrafico();
            JLabel label = new JLabel();
            label.setSize(60, 60);

            panel_2.add(label);

            label.addComponentListener(new ComponentAdapter() 
            {
                @Override
                public void componentResized(ComponentEvent e) 
                {
                    reDimensionar(label, grafico);
                    label.setIcon(grafico);
                }
            });

            Timer timer = new Timer(1000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) 
                {
                    reloj.actualizar();
                    reDimensionar(label, grafico);
                }
            });

            timer.start();
            timers[j]=timer;

            if(j==0|| j==2)
            {
                JLabel lblSeparador = new JLabel(":");
                lblSeparador.setHorizontalAlignment(SwingConstants.CENTER);
                lblSeparador.setFont(new Font("Arial Rounded MT Bold", Font.BOLD, 48));
                lblSeparador.setForeground(Color.WHITE);
                lblSeparador.setSize(60, 60);
                panel_2.add(lblSeparador);
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
	
	
	

