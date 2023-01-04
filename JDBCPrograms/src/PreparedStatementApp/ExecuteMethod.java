package PreparedStatementApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class ExecuteMethod {
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
				scanner = new Scanner(System.in);
				System.out.print("ENTER THE SQL QUERY :  ");
				String sqlQuery=scanner.nextLine();
				pstmt = connection.prepareStatement(sqlQuery);
				if (pstmt != null) {
					boolean execute = pstmt.execute();
					if (execute) {
						resultSet = pstmt.getResultSet();
						if (resultSet != null) {
							System.out.println("SID\tSNAME\tSAGE");
							while (resultSet.next()) {
								System.out.println(resultSet.getInt(1)+"\t"+resultSet.getString(2)+"\t"+resultSet.getInt(3));
							}
						}
					} else {
						int rowCount = pstmt.getUpdateCount();
						if (rowCount>0) {
							System.out.println("Row Manupulated Sucessfully : "+rowCount);
						}else {

							System.out.println("Data is not Manupulated");
						}

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


