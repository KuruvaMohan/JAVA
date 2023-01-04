package LoginValidation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class LoginValidation {
	static Logger log = Logger.getLogger(LoginValidation.class);
	public static void main(String[] args) {
		LogClass.loadLog4j();
		Connection connection=null;
		Statement statement =null;
		Scanner scanner=null;
		ResultSet resultSet =null;
		try {
			connection = DriverManager.getConnection("jdbc:mysql:///abc","root","Mohan");
			if (connection!=null) {
				statement = connection.createStatement();
				if (statement!=null) {
					 scanner = new Scanner(System.in);
					 log.info("ENTER THE USERNAME :: ");
					 
					String username=scanner.next();
					log.info("ENTER THE PASSWORD :: ");
					String password=scanner.next();
					log.info("USERNAME ENTERED BY USER IS  "+username);
					log.info("PASSWORD ENTERED BY USER IS  "+password);
					
					log.info("select count(*) from logininfo where username='"+username+"' and password='"+password+"'");
					resultSet = statement.executeQuery("select count(*) from logininfo where username='"+username+"' and password='"+password+"'");
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
			if (statement!=null) {
				try {
					statement.close();
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
