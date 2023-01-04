package LoginValidation;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginValidation {
	public static void main(String[] args) {
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
					 System.out.print("ENTER THE USERNAME :: ");
					 
					String username=scanner.next();
					System.out.print("ENTER THE PASSWORD :: ");
					String password=scanner.next();
					System.out.println("select count(*) from logininfo where username='"+username+"' and password='"+password+"'");
					resultSet = statement.executeQuery("select count(*) from logininfo where username='"+username+"' and password='"+password+"'");
					if (resultSet!=null) {
						while (resultSet.next()) {
							int count = resultSet.getInt(1);
							if (count==0) {
								System.out.println("LOGIN IS INVALID.......");
							}else {
							System.out.println("LOGIN IS VALID......");
							
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
