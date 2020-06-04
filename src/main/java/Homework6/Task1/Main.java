package Homework6.Task1;


import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

/**
 * Homework 6 Task 1
 * Logic and main structure of program
 *
 * Program must read the file and put all words in collection without repeats
 *
 * @author Matveev
 *
 */

public class Main {
    static  Set<String> strings = new TreeSet<>();
    static String s = "";

    public static void main(String[] args) throws IOException {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream("Example")))){
            while ((s = reader.readLine()) != null ) {
                StringTokenizer st = new StringTokenizer(s, " ");
                while (st.hasMoreTokens()){
                    strings.add(st.nextToken());
                }
            }
        }

        //strings.addAll();
        List<String> listOfStrings = new ArrayList<>();
        listOfStrings.addAll(strings);
        listOfStrings.sort(String::compareToIgnoreCase);
        System.out.println(listOfStrings.toString());
    }


}