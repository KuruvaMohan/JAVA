package PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class Select {
	static Logger log = Logger.getLogger(Select.class);
	public static void main(String[] args) {
		LogClass.loadLog4j();
		String URL = "jdbc:mysql:///abc";
		String user="root";
		String password="Mohan";

		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner scanner = null;
		ResultSet resultSet=null;
		try {
			connection = DriverManager.getConnection(URL, user, password);
			if (connection != null) {

				String sqlSelecrQuery="select sid,sname from student where sid=?";
				pstmt = connection.prepareStatement(sqlSelecrQuery);

				scanner = new Scanner(System.in);
				log.info("ENTER THE SID :  ");
				int sid = scanner.nextInt();
				
				log.info("SID ENTERED BY USER IS  "+sid);
				
				if (pstmt != null) {
					pstmt.setInt(1,sid);
					resultSet = pstmt.executeQuery();
					log.info("SID\tSNAME");
					while (resultSet.next()) {
						log.info(resultSet.getInt(1)+"\t"+resultSet.getString(2));
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			if (resultSet != null) {
			try {
					resultSet.close();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			}
			if (scanner != null) {
			try {
					scanner.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			}
			if (pstmt!= null) {
			try {
					pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
			if (connection!= null) {
			try {
					connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			}
		}
	}
}


