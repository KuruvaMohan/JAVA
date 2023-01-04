package Utils;

import org.apache.log4j.PropertyConfigurator;

public class LogClass {
	public static void loadLog4j() {
		String path="./src/Utils/log4j.properties";
		PropertyConfigurator.configure(path);
		
	}
}
