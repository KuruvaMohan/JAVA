package PreparedStatementApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class Update {
	static Logger log = Logger.getLogger(Update.class);
	public static void main(String[] args) {
		LogClass.loadLog4j();
		String URL = "jdbc:mysql:///abc";
		String user="root";
		String password="Mohan";
		
		Connection connection=null;
		PreparedStatement pstmt=null;
		Scanner scanner = null;
		try {
			connection = DriverManager.getConnection(URL, user, password);
			if (connection != null) {

				String sqlInsertQuery="update student set sname=? where sid=?";
				pstmt = connection.prepareStatement(sqlInsertQuery);

				scanner = new Scanner(System.in);
				log.info("ENTER THE SID :  ");
				int sid = scanner.nextInt();
				log.info("ENTER THE SNAME :  ");
				String sname = scanner.next();
				
				
				log.info("SID ENTERED BY USER IS  "+sid);
				log.info("SNAME ENTERED BY USER IS  "+sname);
				
				
				if (pstmt != null) {
					pstmt.setInt(2,sid);
					pstmt.setString(1,sname	);
					int rowCount = pstmt.executeUpdate();
					if (rowCount>0) {
						log.info("Row Updated Sucessfully : "+rowCount);
					}

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
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
