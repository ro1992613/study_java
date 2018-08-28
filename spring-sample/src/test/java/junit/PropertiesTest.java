package junit;

import java.io.IOException;
import java.util.Properties;

import org.junit.Test;

public class PropertiesTest {

	@Test
	public void tes1(){
		Properties p=new Properties();  
		//p需要InputStream对象进行读取文件，而获取InputStream有多种方法：  
		//1、通过绝对路径:InputStream is=new FileInputStream(filePath);  
		//2、通过Class.getResourceAsStream(path);  
		//3、通过ClassLoader.getResourceAsStream(path);   
		try {
			p.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}   
		System.out.println(p.getProperty("mysql.url"));;
	}
}
