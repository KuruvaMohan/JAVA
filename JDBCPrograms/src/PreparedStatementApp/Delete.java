package PreparedStatementApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class Delete {
	static Logger log = Logger.getLogger(Delete.class);
	public static final String SQLDELETEQUERY="delete from student where sid=?";
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

				pstmt = connection.prepareStatement(SQLDELETEQUERY);

				scanner = new Scanner(System.in);
				log.info("ENTER THE STUDENT ID WHICH NEEDS TO BE DELETED :  ");
				int sid = scanner.nextInt();
				log.info("SID ENTERED BY USER IS  "+sid);
				

				if (pstmt != null) {
					pstmt.setInt(1,sid);
					
					int rowCount = pstmt.executeUpdate();
					if (rowCount>0) {
						log.info("Row Deleted Sucessfully : "+rowCount);
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
