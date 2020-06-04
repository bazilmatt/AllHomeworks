package Homework9.Task1;

import java.io.File;
import java.io.FileWriter;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Scanner;
import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

public class RuntimeCompilation {

    public static void main(String[] args) throws Exception {

        // Create an empty source File
        File sourceFile = new File("Hello.java");

        // hardcode for class
        String hardCode = "public class Hello { \n public void hello() {\n ";

        //buffer code
        Scanner in = new Scanner(System.in);
        boolean flag = true;
        StringBuilder sourceCode = new StringBuilder();
        String buffer;
        sourceCode.append('\n');

        //
        while (flag){
            buffer = in.nextLine();
            if (buffer.equals("quit")){
                flag = false;
                continue;
            }
            sourceCode.append(buffer);
            sourceCode.append('\n');
        }

        // write the source code into the source file
        FileWriter writer = new FileWriter(sourceFile);
        writer.write(hardCode);
        writer.append(sourceCode);
        writer.append("} }");
        writer.close();

        // compile

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        File classFileDir = new File("E:\\Kraken\\Allhomeworks\\");
        compiler.run(null, null, null, sourceFile.getPath());
        // load
        System.out.println(classFileDir);
        URLClassLoader classLoader = URLClassLoader.newInstance(new URL[]{ classFileDir.toURI().toURL()});
        Class<?> Hello = classLoader.loadClass("Hello");

        Hello.getDeclaredConstructor().newInstance();
    }
}

