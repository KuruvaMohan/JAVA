package JDBCBasicPrograms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Mohan {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		System.out.println("Environment Created");
		String url ="jdbc:mysql://localhost:3306/abc";
		String user="root";
		String password="Mohan";
		Connection connection = DriverManager.getConnection(url, user, password);
		System.out.println("Connection Created");
		//		System.out.println("Connection implemented class name\t"+connection.getClass().getName());
		Statement statement = connection.createStatement();
		String sqlSelectQuery="SELECT * FROM STUDENT";
		ResultSet resultSet = statement.executeQuery(sqlSelectQuery);
		System.out.println("sid\tsname\tsage");
		while (resultSet.next()) {
			System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3));
		}
	}
}
