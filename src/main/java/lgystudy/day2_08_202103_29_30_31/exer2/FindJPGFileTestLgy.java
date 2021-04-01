package lgystudy.day2_08_202103_29_30_31.exer2;

import org.junit.Test;

import java.io.File;
import java.io.FilenameFilter;

public class FindJPGFileTestLgy {
    @Test
    public void test1(){
        File srcFile = new File("d:\\io");
        String[] fileNames = srcFile.list();
        for(String fileName : fileNames){
            if(fileName.endsWith(".txt")){
                System.out.println(fileName);
            }
        }
    }
    @Test
    public void test2(){
        File srcFile = new File("d:\\io");
        File[] listFiles = srcFile.listFiles();
        for(File file : listFiles){
            if(file.getName().endsWith(".txt")){
                System.out.println(file.getAbsolutePath());
            }
        }
    }
    @Test
    public void test3(){
        File srcFile = new File("d:\\io");
        File[] subFiles = srcFile.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return name.endsWith(".txt");
            }
        });
        for(File file : subFiles){
            System.out.println(file.getAbsolutePath());
        }
    }
}
