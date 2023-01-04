package JDBCBasicPrograms;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.apache.log4j.Logger;

import Utils.LogClass;

public class ExecuteMothod {
	static Logger log = Logger.getLogger(ExecuteMothod.class);
	public static void main(String[] args) {
		LogClass.loadLog4j();
		Connection connection =null;
		Statement statement =null;
		Scanner scanner =null;
		ResultSet resultSet=null;
		try {
			 connection = DriverManager.getConnection("jdbc:mysql:///abc", "root", "Mohan");
			 if (connection != null) {
				  statement = connection.createStatement();
				  if (statement != null) {
					   scanner = new Scanner(System.in);
					   log.info("Enter the Query :");
					String query=scanner.nextLine();
					log.info("QUERY ENTERED BY USER IS  "+query);
					boolean execute = statement.execute(query);
					if (execute==true) {
						log.info("EXECUTED SELECT QUERY");
						 resultSet = statement.getResultSet();
						if (resultSet != null) {
							log.info("sname");
							while (resultSet.next()) {
								String name = resultSet.getString(2);
								log.info(name);
							}
						}
					} else {
						log.info("EXECUTED NON-SELECT QUERY");
						int rowCount = statement.getUpdateCount();
						if (rowCount>0) {
							log.info("Data Manupulated Sucessfully");
						} else {

							log.info("Data is not Manupulated");
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
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (scanner != null) {
					scanner.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
