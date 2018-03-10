package worlddb;

import javax.swing.*;

import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.List;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Arrays;

public class Parametros extends JFrame{
	
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private int anchoPantalla = pantalla.getSize().width;
	private int altoPantalla = pantalla.getSize().height;
	private LaminaParametros lamina;
	private ImageIcon icon;
	
	public Parametros(String tabla) {
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		if(tabla.equals("city")) {
			setSize(320,200);
			lamina = new LaminaParametros(true);
			setTitle("Consultar por ciudad");
		}else {
			setSize(320,300);
			lamina = new LaminaParametros();
			setTitle("Consultar por pais");
		}
		
		icon = new ImageIcon(Parametros.class.getResource("earth.png"));
		setIconImage(icon.getImage());
		
		setLocation(anchoPantalla/2 - this.getSize().width/2, altoPantalla/2 - this.getSize().height/2);
		
		setResizable(false);

		add(lamina);
		
		setVisible(true);
		

		
	}

}

class LaminaParametros extends JPanel{
	
	private String[] operadores = {">","<"};
	
	private ArrayList<String> condiciones;
	
	private JLabel nombre = new JLabel("Nombre");
	private JLabel poblacion = new JLabel("Población");
	
	private JComboBox operador = new JComboBox(operadores);
	private JComboBox continenteCombo = new JComboBox();
	
	private JTextField nombreText = new JTextField();
	private JTextField poblacionText = new JTextField("0");
	
	JButton consultar = new JButton("Consultar");
	
	private Border border = BorderFactory.createLineBorder(Color.BLACK, 1);
	
	
	public LaminaParametros(Boolean c) {
		
		setLayout(null);
		
		nombre.setFont(new Font("Arial", Font.PLAIN, 14));
		poblacion.setFont(new Font("Arial", Font.PLAIN, 14));
		
		nombre.setBounds(20,20,60,30);
		poblacion.setBounds(20,70,100,20);
		nombreText.setBounds(140,20,140,30);
		nombreText.setFont(new Font("Arial", Font.PLAIN, 13));
		operador.setBounds(140,70,50,30);
		operador.setFont(new Font("Arial", Font.PLAIN, 13));
		poblacionText.setBounds(210,70,70,30);
		poblacionText.setFont(new Font("Arial", Font.PLAIN, 13));
		consultar.setBounds(110,120,100,30);
		
		add(nombre);
		add(poblacion);
		add(nombreText);
		add(operador);
		add(poblacionText);
		add(consultar);
		
	}
	
	public LaminaParametros() {
		
		setLayout(null);
		
		String[] regiones =  {"Cualquiera", "Antartica", "Australia y Nueva Zelanda", "Países Balticos", "Islas Británicas", "Caribe", "Africa Central", "América Central", "Este de África", "Este de Asia", "Melanesia", "Micronesia","Micronesia/Caribe", "Medio Este", "Países Nórdicos", "América del Norte", "África del Norte", "Polynesia", "América del Sur", "Sureste de Asia", "Sur y Centro de Asia", "Sur de Europa", "Oeste de África", "Oeste de Europa"};
		String[] continentes = {"Cualquiera", "Asia", "Africa", "América del Norte", "América del Sur", "Europa", "Oceanía", "Antártica"};

		JLabel region = new JLabel("Región");
		JLabel continente = new JLabel("Continente");
		
		JComboBox regionCombo = new JComboBox(regiones);
		JComboBox continenteCombo = new JComboBox(continentes);
		
		nombre.setBounds(20,20,60,30);
		poblacion.setBounds(20,70,80,20);
		nombreText.setBounds(140,20,140,30);
		nombreText.setFont(new Font("Arial", Font.PLAIN, 13));
		operador.setBounds(140,70,50,30);
		operador.setFont(new Font("Arial", Font.PLAIN, 13));
		poblacionText.setBounds(210,70,70,30);
		poblacionText.setFont(new Font("Arial", Font.PLAIN, 13));
		
		continente.setBounds(20,120,100,20);
		continenteCombo.setBounds(140,120,140,30);
		region.setBounds(20,170,60,30);
		regionCombo.setBounds(140,170,140,30);
		
		consultar.setBounds(110,220,100,30);

		add(nombre);
		add(poblacion);
		add(nombreText);
		add(operador);
		add(poblacionText);
		add(consultar);
		add(continente);
		add(continenteCombo);
		add(region);
		add(regionCombo);
		
	}
	
}
