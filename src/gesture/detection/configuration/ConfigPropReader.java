package gesture.detection.configuration;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @author hirenmayani.com
 * 
 */

public class ConfigPropReader {
	String result = "";
	InputStream inputStream;
	static Properties prop;
	static String propFileName = null;

	   private static ConfigPropReader instance = null;
	   
	   private ConfigPropReader() {
	      // Exists only to defeat instantiation.
	   }
	   public static ConfigPropReader getInstance() {
	      if(instance == null) {
	         instance = new ConfigPropReader();
	         init(null);
	      }
	      return instance;
	   }

	public static void init(String propFilePath) {
		propFileName = propFilePath;
		prop = new Properties();
		FileInputStream inputStream;
		try {
			if (propFilePath == null)
				inputStream = new FileInputStream("other/config.properties");
			else
				inputStream = new FileInputStream(propFilePath);

			prop.load(inputStream);
		} catch (Exception e) {
			System.out.println("Exception while loading config file");
		}
	}

	public void killit() {
		try {
			inputStream.close();
		} catch (IOException e) {
			System.out.println("error while closing");
		}
	}

	public String getPropValues(String key) {
		try {
		return prop.getProperty(key);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
		}
		return null;
	}
}