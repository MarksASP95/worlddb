package worlddb;

import javax.swing.UIManager;


public class WorldDB {
	
	public static void main(String[] args) {
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
		//Ventana inicio = new Ventana();
		
		Parametros prueba = new Parametros("country");
		
	}

}
