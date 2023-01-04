package PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Select {
	public static void main(String[] args) {

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
				System.out.print("ENTER THE SID :  ");
				int sid = scanner.nextInt();
				if (pstmt != null) {
					pstmt.setInt(1,sid);
					resultSet = pstmt.executeQuery();
					System.out.println("SID\tSNAME");
					while (resultSet.next()) {
						System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2));
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


