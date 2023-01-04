package JDBCBasicPrograms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class Delete {
	static Logger log = Logger.getLogger(Delete.class);
	public static void main(String[] args)  {
		LogClass.loadLog4j();
		Connection connection=null;
		Statement statement =null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///abc","root","Mohan");
			if (connection!=null) {
				statement = connection.createStatement();
				if (statement!=null) {
					int rowCount = statement.executeUpdate("Delete from student where sid=3");
				
					if (rowCount>0) {
						log.info("Row deleted Sucessfully "+rowCount);
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
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
