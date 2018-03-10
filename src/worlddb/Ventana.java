package worlddb;

import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;

public class Ventana extends JFrame{
	
	private PanelVentana panel = new PanelVentana();
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private int anchoPantalla = pantalla.getSize().width;
	private int altoPantalla = pantalla.getSize().height;
	private ImageIcon icon;
        public static String query;
	
	public Ventana() {
            
                query = "Hello";
		
		setSize(500,500);
		
		setTitle("Base de datos mundial");
		
		icon = new ImageIcon(Ventana.class.getResource("earth.png"));
		setIconImage(icon.getImage());
		
		setLocation(anchoPantalla/2 - this.getSize().width/2, altoPantalla/2 - this.getSize().height/2);
		
		add(panel);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setResizable(false);
                		
		setVisible(true);
	}

}

class PanelVentana extends JPanel{
	
	private String[] items = {"Pais", "Ciudad"};
	
	private JLabel consulta = new JLabel("Consulta");
	private JComboBox tablasbd = new JComboBox(items);
	private JButton parametros = new JButton("Parámetros");
	private JTable tabla = new JTable();
	private JButton limpiar = new JButton("Limpiar");
        private Parametros ventanaParametros;
	
	public PanelVentana() {
		
		setLayout(null);
		
		consulta.setFont(new Font("Arial", Font.BOLD, 14));
		consulta.setBounds(30,30,100,14);
		add(consulta);
		
		tablasbd.setFont(new Font("Arial", Font.PLAIN, 12));
		tablasbd.setBounds(30,65,200,30);
		add(tablasbd);
		
		parametros.setFont(new Font("Arial", Font.PLAIN, 12));
		parametros.setBounds(260,65,200,30);
		add(parametros);
		
		tabla.setFont(new Font("Arial", Font.PLAIN, 12));
		tabla.setBounds(30,115,440,295);
		add(tabla);
		
		limpiar.setFont(new Font("Arial", Font.PLAIN, 12));
		limpiar.setBounds(320,430,150,30);
		add(limpiar);
                
                parametros.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        ventanaParametros = new Parametros(tablasbd.getSelectedItem().toString().equals("Ciudad") ? "city" : "country");
                    }
                });
	
	}
	
	
}
