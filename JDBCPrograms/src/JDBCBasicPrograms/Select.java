package JDBCBasicPrograms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class Select {
	static Logger log = Logger.getLogger(Select.class);
	public static void main(String[] args)  {
		LogClass.loadLog4j();
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
							log.info(resultSet.getString(2));
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
