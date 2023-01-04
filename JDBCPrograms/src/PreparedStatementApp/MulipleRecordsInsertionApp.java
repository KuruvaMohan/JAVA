package PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class MulipleRecordsInsertionApp {
	static Logger log = Logger.getLogger(MulipleRecordsInsertionApp.class);
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

				String sqlInsertQuery="insert into student (sid,sname,sage) values (?,?,?)";
				pstmt = connection.prepareStatement(sqlInsertQuery);

				scanner = new Scanner(System.in);
				int c=0;
				while(true) {
				log.info("ENTER THE SID :  ");
				int sid = scanner.nextInt();
				log.info("ENTER THE SNAME :  ");
				String sname = scanner.next();
				log.info("ENTER THE SAGE :  ");
				int sage = scanner.nextInt();
				
				
				log.info("SID ENTERED BY USER IS  "+sid);
				log.info("SNAME ENTERED BY USER IS  "+sname);
				log.info("SID ENTERED BY USER IS  "+sage);
				
				
				if (pstmt != null) {
					pstmt.setInt(1,sid);
					pstmt.setString(2,sname	);
					pstmt.setInt(3, sage);
					pstmt.executeUpdate();
					
					log.info("IF U WANT TO ENTER ANOTHER RECORD TYPE :Y/N");
					String reply = scanner.next();
					c++;
					if (reply.equalsIgnoreCase("N")) {
						break;
					}

				}
				}
				log.info("NO.OF RECORDS INSERTED IS : "+c);
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

