package Homework12.task2;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;

public class MemoryMetaError {

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        // hardcode for class
        for (int i = 1; i <10; i++){

            File sourceFile = new File(i +"class"+ ".java");
            String hardCode = "public class "+ i +"class "+ " { \n public void doWork() {\n String[] a" + i +" = new String[120]; +} }";

            JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
            File classFileDir = new File("E:"+File.separator+"Kraken"+File.separator+"Allhomeworks\\");
            compiler.run(null, null, null, sourceFile.getPath());

            FileWriter fr = new FileWriter(sourceFile);
            fr.append(hardCode);
            fr.write(hardCode);
            fr.close();
            System.out.println(classFileDir);
            URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{ classFileDir.toURI().toURL()});
            Class<?> Worker = classLoader.loadClass(i+"class");
        }

        // compile

        // load


    }

}

