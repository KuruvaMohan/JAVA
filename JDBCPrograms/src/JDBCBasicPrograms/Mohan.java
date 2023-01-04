package JDBCBasicPrograms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class Mohan {
	static Logger log = Logger.getLogger(Mohan.class);
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		LogClass.loadLog4j();
		Class.forName("com.mysql.cj.jdbc.Driver");
		log.info("Environment Created");
		String url ="jdbc:mysql://localhost:3306/abc";
		String user="root";
		String password="Mohan";
		Connection connection = DriverManager.getConnection(url, user, password);
		log.info("Connection Created");
		//		log.info("Connection implemented class name\t"+connection.getClass().getName());
		Statement statement = connection.createStatement();
		String sqlSelectQuery="SELECT * FROM STUDENT";
		ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
		log.info("sid\tsname\tsage");
		while (resultSet.next()) {
			log.info(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3));
		}
	}
}
