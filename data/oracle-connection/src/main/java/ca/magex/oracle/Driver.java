package ca.magex.oracle;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Driver {

	public static void main(String[] args) throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection connection = null;
		connection = DriverManager.getConnection("jdbc:oracle:thin:@docker:49161:xe", "system", "oracle");
		PreparedStatement statement = connection.prepareStatement("select * from names");
		if (statement.execute()) {
			ResultSet rs = statement.getResultSet();
			while (rs.next()) {
				System.out.println(rs.getString("name"));
			}
		}
		statement.close();
		connection.close();
	}

}
