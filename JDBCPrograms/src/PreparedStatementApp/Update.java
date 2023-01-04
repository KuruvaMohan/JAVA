package PreparedStatementApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Update {
	public static void main(String[] args) {
		
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
				System.out.print("ENTER THE SID :  ");
				int sid = scanner.nextInt();
				System.out.print("ENTER THE SNAME :  ");
				String sname = scanner.next();

				if (pstmt != null) {
					pstmt.setInt(2,sid);
					pstmt.setString(1,sname	);
					int rowCount = pstmt.executeUpdate();
					if (rowCount>0) {
						System.out.println("Row Updated Sucessfully : "+rowCount);
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
