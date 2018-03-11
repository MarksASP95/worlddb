package worlddb;

import java.sql.*;
import javax.swing.UIManager;


public class WorldDB {

        public static Ventana inicio;
        public static Connection conn;
        public static Statement stmt;
    
	public static void main(String[] args) throws SQLException{
		
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
                catch(Exception e) {
			System.out.println(e.getMessage());
		}

                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/world","root","");
                stmt = conn.createStatement();

		inicio = new Ventana();

	}

}
