package PreparedStatementApp;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Insert {
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

				String sqlInsertQuery="insert into student (sid,sname,sage) values (?,?,?)";
				pstmt = connection.prepareStatement(sqlInsertQuery);

				scanner = new Scanner(System.in);
				System.out.print("ENTER THE SID :  ");
				int sid = scanner.nextInt();
				System.out.print("ENTER THE SNAME :  ");
				String sname = scanner.next();
				System.out.print("ENTER THE SAGE :  ");
				int sage = scanner.nextInt();

				if (pstmt != null) {
					pstmt.setInt(1,sid);
					pstmt.setString(2,sname	);
					pstmt.setInt(3, sage);
					int rowCount = pstmt.executeUpdate();
					if (rowCount>0) {
						System.out.println("Row Inserted Sucessfully : "+rowCount);
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
