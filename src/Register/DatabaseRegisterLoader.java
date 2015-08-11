package Register;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseRegisterLoader implements RegisterLoader {
	private Register1 register = new ListRegister(20);
	private String QUERY = null;
	private Connection con = new DBConnection().connect();
	private Statement stmt = null;
	private ResultSet rs = null;
	@Override
	public void save(Register1 register) {
		  try {
	        	Connection con = new DBConnection().connect();
	        	QUERY = "INSERT INTO student (id, firstname, phoneNumber) VALUES (?, ?, ?)";
				PreparedStatement stmt = con.prepareStatement(QUERY);
				stmt.execute("delete from student");
			    for(int count = 0; count < register.getCount(); count++){
			    	stmt.setInt(1, count+1);
					stmt.setString(2, register.getPerson(count).getName());
					stmt.setString(3, register.getPerson(count).getPhoneNumber());
					stmt.executeUpdate();
			    }
			    
				
				stmt.close();
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
      
	}

	/*
	 * ALTER TABLE student
CHANGE COLUMN surname phoneNumber varchar(32);
	 * */
	@Override
	public Register1 load() {
		try {
			QUERY = "SELECT id, firstname, phoneNumber FROM student";
			stmt = con.createStatement();
			rs = stmt.executeQuery(QUERY);
			while (rs.next()) {
				//System.out.printf("%4d %-32s %-32s%n", rs.getInt(1), rs.getString(2), rs.getString(3));
                register.addPerson(new Person (rs.getString(2),rs.getString(3)));
			}
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return register;
	}

	@Override
	public void fill(Register1 register) {
        try {
        	Connection con = new DBConnection().connect();
        	QUERY = "INSERT INTO student (id, firstname, phoneNumber) VALUES (?, ?, ?)";
			PreparedStatement stmt = con.prepareStatement(QUERY);
			stmt.execute("delete from student");
			stmt.setInt(1, 1);
			stmt.setString(2, "Fero");
			stmt.setString(3, "090554581");
			stmt.executeUpdate();
			stmt.setInt(1, 2);
			stmt.setString(2, "Ferasdso");
			stmt.setString(3, "0925854411");
			stmt.executeUpdate();
			stmt.setInt(1, 3);
			stmt.setString(2, "peter");
			stmt.setString(3, "0925854411");
			stmt.executeUpdate();
			stmt.close();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	        
	}

}
