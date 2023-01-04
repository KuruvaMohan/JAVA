import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class ExecuteMothod {
	public static void main(String[] args) {
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
					   System.out.print("Enter the Query :");
					String query=scanner.nextLine();
					boolean execute = statement.execute(query);
					if (execute==true) {
						System.out.println("EXECUTED SELECT QUERY");
						 resultSet = statement.getResultSet();
						if (resultSet != null) {
							System.out.println("sname");
							while (resultSet.next()) {
								String name = resultSet.getString(2);
								System.out.println(name);
							}
						}
					} else {
						System.out.println("EXECUTED NON-SELECT QUERY");
						int rowCount = statement.getUpdateCount();
						if (rowCount>0) {
							System.out.println("Data Manupulated Sucessfully");
						} else {

							System.out.println("Data is not Manupulated");
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
