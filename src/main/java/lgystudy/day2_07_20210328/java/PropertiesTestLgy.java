package lgystudy.day2_07_20210328.java;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTestLgy {
    //properties ：常用来处理配置文件。key和value都是String类型
    public static void main(String[] args) {
        FileInputStream fis = null;
        Properties properties = new Properties();

        try {
            fis = new FileInputStream("jdbc.properties");
            properties.load(fis);

            String name = properties.getProperty("name");
            String password = properties.getProperty("password");

            System.out.println("name=" + name + ", password = " + password);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
