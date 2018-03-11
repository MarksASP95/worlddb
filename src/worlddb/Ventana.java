package worlddb;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.*;
import javax.swing.table.JTableHeader;
import java.sql.*;

public class Ventana extends JFrame{
	
	private PanelVentana panel = new PanelVentana();
	private Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
	private int anchoPantalla = pantalla.getSize().width;
	private int altoPantalla = pantalla.getSize().height;
	private ImageIcon icon;
        public static String query;
	
	public Ventana() {
		
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
	
        private JButton consultar = new JButton("Consultar");
        private Parametros ventanaParametros;

        private class PanelTabla extends JPanel{
            
            private String[] columnasPais = {"Name","Continent","Region","Population"};
            private String[] columnasCiudad = {"Name","District","Population"};
            
            private String[] columnas;
            
            private ResultSet setResultados;

            private String[][] data;
                     
            public PanelTabla(){
                
                setLayout(new BorderLayout());
                
                Consultar listenConsulta = new Consultar();
                
                consultar.addActionListener(listenConsulta);
                
            }
            
            private class Consultar implements ActionListener{
                
                public void actionPerformed(ActionEvent evt){
                    
                    try{
                        
                        setResultados = WorldDB.stmt.executeQuery(Ventana.query);
                        data = prepararData(setResultados);
   
                    }
                    catch(Exception e){System.out.println(e.getStackTrace());}
                                            
                        removeAll();
                        
                        JTable nuevaTabla = new JTable(data, columnas);
                        
                        JScrollPane pane = new JScrollPane(nuevaTabla);
                        
                        JTableHeader header = nuevaTabla.getTableHeader();
                        header.setBorder(BorderFactory.createLineBorder(Color.black, 1));
                        header.setFont(new Font("Arial", Font.BOLD, 12));

                        nuevaTabla.setBorder(BorderFactory.createLineBorder(Color.black, 1));

                        add(pane, BorderLayout.CENTER);
                        
                        repaint();
                        revalidate();
                    
                }
                
            }
                        
            public String[][] prepararData(ResultSet rs) throws SQLException{
                
                rs.last();
                int numeroFilas = rs.getRow();
                rs.beforeFirst();
                
                int numeroColumnas = (tablasbd.getSelectedItem().toString().equals("Ciudad") ? 3 : 4);
                columnas = (tablasbd.getSelectedItem().toString().equals("Ciudad") ? columnasCiudad : columnasPais);

                String data[][] = new String[numeroFilas][numeroColumnas];

                int i = 0;
                
                while(rs.next()){

                    for (int j = 0; j < columnas.length; j++) {
                        data[i][j] = rs.getString(columnas[j]);
                    }
                    
                    i++;
                    
                }
                            
                return data;
                
            }
        }
        
        private PanelTabla panelTabla = new PanelTabla();
        
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
                
                panelTabla.setBounds(30,115,440,295);
                add(panelTabla);
                                
                consultar.setFont(new Font("Arial", Font.BOLD, 12));
                consultar.setBounds(30,430,150,30);
                add(consultar);
                
                parametros.addActionListener(new ActionListener(){
                    public void actionPerformed(ActionEvent e){
                        ventanaParametros = new Parametros(tablasbd.getSelectedItem().toString().equals("Ciudad") ? "city" : "country");
                    }
                });
	
	}	
	
}
