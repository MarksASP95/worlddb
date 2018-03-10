package worlddb;

import javax.swing.*;

import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.*;
import java.util.ArrayList;

public class Parametros extends JFrame{
	
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private int anchoPantalla = pantalla.getSize().width;
	private int altoPantalla = pantalla.getSize().height;
	private LaminaParametros lamina;
	private ImageIcon icon;
        public static String tabla;
	
	public Parametros(String tabla) {
            
                addWindowListener(new WindowAdapter(){
                    public void windowClosing(WindowEvent e){
                        WorldDB.inicio.setVisible(true);
                    }
                });
                
                this.tabla = tabla;
		
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
                
                WorldDB.inicio.setVisible(false);
                		
		setVisible(true);
		
	}
        
        class LaminaParametros extends JPanel{
            
            private ArrayList condiciones = new ArrayList();

            private String[] operadores = {">","<"};

            private JLabel nombre = new JLabel("Nombre");
            private JLabel poblacion = new JLabel("Población");

            private JComboBox operador = new JComboBox(operadores);
            private JComboBox continenteCombo = new JComboBox();

            private JTextField nombreText = new JTextField();
            private JTextField poblacionText = new JTextField("0");

            JButton aceptar = new JButton("Aceptar");

            private Border border = BorderFactory.createLineBorder(Color.BLACK, 1);

            private TeclaEsNumero esNumero = new TeclaEsNumero();

            private Font labelFont = new Font("Arial", Font.PLAIN, 13);
	
	
	public LaminaParametros(Boolean c) {
		
		setLayout(null);
		
		nombre.setFont(new Font("Arial", Font.PLAIN, 14));
		poblacion.setFont(new Font("Arial", Font.PLAIN, 14));
		
		nombre.setBounds(20,20,60,30);
		poblacion.setBounds(20,70,100,20);
		nombreText.setBounds(140,20,140,30);
		nombreText.setFont(labelFont);
		operador.setBounds(140,70,50,30);
		operador.setFont(labelFont);
		poblacionText.setBounds(210,70,70,30);
		poblacionText.setFont(labelFont);
		aceptar.setBounds(110,120,100,30);
                
                poblacionText.addKeyListener(esNumero);
                
                aceptar.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                       Ventana.query = crearQuery(aceptar);
                       JFrame frame = (JFrame) SwingUtilities.getRoot(aceptar);
                       frame.setVisible(false);
                       WorldDB.inicio.setVisible(true);
                   } 
                });
                
		
		add(nombre);
		add(poblacion);
		add(nombreText);
		add(operador);
		add(poblacionText);
		add(aceptar);
		
	}
	
	public LaminaParametros() {
		
		setLayout(null);
		
		String[] regiones =  {"Cualquiera", "Antartica", "Australia and New Zeland", "Baltic Countries", "British Islands", "Caribbean", "Central Africa", "Central America", "Easter Africa", "Eastern Asia", "Melanesia", "Micronesia","Micronesia/Caribbean", "Middle East", "Nordic Countries", "North America", "Northern Africa", "Polynesia", "South America", "Southeast Asia", "Southern and Central Asia", "Southern Europe", "Western Africa", "Western Europe"};
		String[] continentes = {"Cualquiera", "Asia", "Africa", "North America", "South America", "Europe", "Oceania", "Antartica"};

		JLabel region = new JLabel("Región");
		JLabel continente = new JLabel("Continente");
		
		JComboBox regionCombo = new JComboBox(regiones);
		JComboBox continenteCombo = new JComboBox(continentes);
		
		nombre.setBounds(20,20,60,30);
		poblacion.setBounds(20,70,80,20);
		nombreText.setBounds(140,20,140,30);
		nombreText.setFont(labelFont);
		operador.setBounds(140,70,50,30);
		operador.setFont(labelFont);
		poblacionText.setBounds(210,70,70,30);
		poblacionText.setFont(labelFont);
		
		continente.setBounds(20,120,100,20);
		continenteCombo.setBounds(140,120,140,30);
		region.setBounds(20,170,60,30);
		regionCombo.setBounds(140,170,140,30);
		
		aceptar.setBounds(110,220,100,30);

		add(nombre);
		add(poblacion);
		add(nombreText);
		add(operador);
		add(poblacionText);
		add(aceptar);
		add(continente);
		add(continenteCombo);
		add(region);
		add(regionCombo);
                
                poblacionText.addKeyListener(esNumero);
                
                aceptar.addActionListener(new ActionListener(){
                   public void actionPerformed(ActionEvent e){
                       Ventana.query = crearQuery(aceptar);
                       JFrame frame = (JFrame) SwingUtilities.getRoot(aceptar);
                       frame.setVisible(false);
                       WorldDB.inicio.setVisible(true);
                   } 
                });
		
	}
        
        public String crearQuery(Component c){
            
            String query;

            for(Component actualComponent : c.getParent().getComponents()){
                if (actualComponent instanceof JTextField) {
                    condiciones.add(((JTextField) actualComponent).getText());
                }
                else if(actualComponent instanceof JComboBox){
                    String strItemSeleccionado = ((JComboBox) actualComponent).getSelectedItem().toString();
                    if (((JComboBox) actualComponent).getSelectedItem().toString().equals("Cualquiera")) {
                        condiciones.add("");
                    }
                    else{
                        condiciones.add(strItemSeleccionado);
                    }
                }
            }
            
            if(condiciones.get(2).equals("")){
                condiciones.set(2, "0");
                
            }
            if (condiciones.get(1).equals("<") && condiciones.get(2).equals("0")) {
                condiciones.set(1, ">");
                JOptionPane.showMessageDialog(null, "Se ha cambiado la condicion a MAYOR QUE (>)", "Atencion", JOptionPane.INFORMATION_MESSAGE);
            }
            
            
            if (tabla.equals("city")) {
                query = "SELECT * FROM city WHERE Name LIKE '%" + condiciones.get(0) + "%' AND Population " + condiciones.get(1) + Integer.parseInt(condiciones.get(2).toString()) + ";";
            }
            else{
                query = "SELECT * FROM country WHERE Name LIKE '%" + condiciones.get(0) + "%' AND Population " + condiciones.get(1) + Integer.parseInt(condiciones.get(2).toString()) + " AND Continent LIKE '%" + condiciones.get(3) + "%' AND Region LIKE '%" + condiciones.get(4) + "%';";
            }
            
            System.out.println(query);
           
            return query;
            
            
        }
        
        private class TeclaEsNumero implements KeyListener{

            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar())) {
                    e.consume();
                }
            }

            public void keyPressed(KeyEvent e) {}

            public void keyReleased(KeyEvent e) {}
            
        }
        
}

}



