package LoginValidation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class LoginValidationUsingPreparedStatementApp {
	public static final String SQLSELECTQUERY="select count(*) from logininfo where username=? and password=?";
	static Logger log = Logger.getLogger(LoginValidationUsingPreparedStatementApp.class);
	public static void main(String[] args) {
		LogClass.loadLog4j();
		Connection connection=null;
		PreparedStatement pstmt =null;
		Scanner scanner=null;
		ResultSet resultSet =null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///abc","root","Mohan");
			if (connection!=null) {
				pstmt = connection.prepareStatement(SQLSELECTQUERY);
				if (pstmt!=null) {
					scanner = new Scanner(System.in);
					log.info("ENTER THE USERNAME :: ");

					String username=scanner.next();
					log.info("ENTER THE PASSWORD :: ");
					String password=scanner.next();
					log.info("USERNAME ENTERED BY USER IS  "+username);
					log.info("PASSWORD ENTERED BY USER IS  "+password);
					pstmt.setString(1, username);
					pstmt.setString(2, password);

					resultSet = pstmt.executeQuery();
					if (resultSet!=null) {
						while (resultSet.next()) {
							int count = resultSet.getInt(1);
							if (count==0) {
								log.info("LOGIN IS INVALID.......");
							}else {
								log.info("LOGIN IS VALID......");

							}
						} 
					}
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			if (resultSet!=null) {
				try {
					resultSet.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (pstmt!=null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
			if (connection!=null) {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}

			}
		}
	}

}
