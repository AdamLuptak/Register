package Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	    private Connection DBConnection;
	    public Connection connect(){
	    
	        try {
	           Class.forName("com.mysql.jdbc.Driver");

	            System.out.println("Connection Success");
	        } catch (Exception e) {
	            System.out.println("Connection  error");
	        }
	   String url = "jdbc:mysql://localhost:3305/school";
	  
	        try {
	            DBConnection = DriverManager.getConnection(url, "root", "deckaa");
	            System.out.println("Database connect");
	        } catch (SQLException se) {
	             System.out.println("Database conenect " + se);
	            
	        }
	    
	       return DBConnection;
	    }
	    
	}


