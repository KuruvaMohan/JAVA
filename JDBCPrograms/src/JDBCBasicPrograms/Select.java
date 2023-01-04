package JDBCBasicPrograms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Select {
	public static void main(String[] args)  {
		Connection connection=null;
		Statement statement =null;
		ResultSet resultSet =null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///abc","root","Mohan");
			if (connection!=null) {
				statement = connection.createStatement();
				if (statement!=null) {
					resultSet = statement.executeQuery("select * from student");

					if (resultSet!=null) {
						while (resultSet.next()) {
							System.out.println(resultSet.getString(2));
						} 
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			if (resultSet!=null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if (statement!=null) {
				try {
					statement.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				
			}
		}
	}

}
